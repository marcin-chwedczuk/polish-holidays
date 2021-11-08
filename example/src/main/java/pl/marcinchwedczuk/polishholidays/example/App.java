package pl.marcinchwedczuk.polishholidays.example;


import org.fusesource.jansi.Ansi;
import org.fusesource.jansi.AnsiConsole;
import pl.marcinchwedczuk.polishholidays.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static org.fusesource.jansi.Ansi.ansi;

/**
 * To run this example execute:
 * {@code ../mvnw -f pom.xml clean package}
 * {@code java -jar ./target/example-1.0-SNAPSHOT-jar-with-dependencies.jar}
 */
public class App {
    private static HolidayCalendar holidayCalendar;

    public static void main(String[] args) {
        AnsiConsole.systemInstall();

        holidayCalendar = configureCalendar();

        LocalDate now = LocalDate.now();
        printMonth(now.getMonthValue(), now.getYear());
    }

    private static HolidayCalendar configureCalendar() {
        return HolidayCalendar
                .newBuilderWithPolishHolidaysDefined()
                // Let's add Pi Day
                .defineHoliday(HolidayDefinition.newBuilder()
                        .withDate(HolidayDateAlgorithms.fixedAtMonthDay(3, 14))
                        .withPolishName("Dzień Liczby PI")
                        .withEnglishName("Pi Day")
                        .withHolidayType(HolidayType.UNOFFICIAL)
                        .build())
                .createCalendar();
    }

    private static void printMonth(int month, int year) {
        LocalDate curr = LocalDate.of(year, month, 1);
        printMonthName(curr);
        printDaysTable(month, year);
        System.out.println();
        printHolidaysSummary(year, month);
    }

    private static void printMonthName(LocalDate date) {
        String monthName = date.getMonth()
                .getDisplayName(TextStyle.FULL_STANDALONE, Locale.forLanguageTag("pl"));
        System.out.println(capitalize(monthName));
    }

    private static void printDaysTable(int month, int year) {
        LocalDate curr = LocalDate.of(year, month, 1);
        // Sun | Mon | Tue | Wed | Thu | Fri | Sat
        // DayOfWeek enum starts on Monday, we need it to start on Sunday

        // Print empty dates at the beginning
        int currentDayOfWeek = 0;
        openDayRow();
        while (toInt(curr.getDayOfWeek()) != currentDayOfWeek) {
            printEmptyDayBox();
            currentDayOfWeek++;
        }

        boolean openNewRow = false;
        while (curr.getMonthValue() == month) {
            if (openNewRow) {
                openNewRow = false;
                openDayRow();
            }

            printDayBox(curr);

            currentDayOfWeek = (currentDayOfWeek + 1) % 7;
            curr = curr.plusDays(1);

            if (currentDayOfWeek == 0) {
                closeDayRow();
                openNewRow = true;
            }
        }

        // Print empty dates at the end
        if (!openNewRow) {
            while (currentDayOfWeek != 0) {
                printEmptyDayBox();
                currentDayOfWeek = (currentDayOfWeek + 1) % 7;
            }
            closeDayRow();
        }
    }

    private static void printHolidaysSummary(int year, int month) {
        List<Holiday> holidaysInMonth = holidayCalendar.holidaysForYear(year).stream()
                .filter(h -> h.date().getMonthValue() == month)
                .collect(toList());

        List<Holiday> publicHolidays = holidaysInMonth.stream()
                .filter(Holiday::isPublicHoliday)
                .sorted(Comparator.comparing(Holiday::date))
                .collect(toList());

        List<Holiday> nonPublicHolidays = holidaysInMonth.stream()
                .filter(h -> !h.isPublicHoliday())
                .sorted(Comparator.comparing(Holiday::date))
                .collect(toList());

        List<Holiday> joined = Stream.concat(publicHolidays.stream(), nonPublicHolidays.stream())
                .collect(toList());

        for (Holiday holiday : joined) {
            System.out.format(" %s - %s%n", holiday.date(), holiday.polishName());
        }
    }

    private static void openDayRow() {
        System.out.print(" ");
    }

    private static void printEmptyDayBox() {
        System.out.print("   ");
    }

    private static void printDayBox(LocalDate day) {
        boolean isPublicHoliday = holidayCalendar
                .holidaysForYear(day.getYear()).stream()
                .anyMatch(holiday -> holiday.date().equals(day)
                        && holiday.isPublicHoliday());
        boolean isAnyHoliday = holidayCalendar
                .holidaysForYear(day.getYear()).stream()
                .anyMatch(holiday -> holiday.date().equals(day));
        boolean isToday = LocalDate.now().equals(day);

        boolean isRedBg = day.getDayOfWeek() == DayOfWeek.SUNDAY
                || isPublicHoliday;
        boolean isGrayBg = day.getDayOfWeek() == DayOfWeek.SATURDAY;
        boolean isBlueFg = isAnyHoliday && !isPublicHoliday;

        Ansi ansi = ansi();

        if (isRedBg) {
            ansi = ansi.bgRed();
        } else if (isGrayBg) {
            ansi = ansi.bgBright(Ansi.Color.BLACK);
        }

        if (isBlueFg) {
            ansi = ansi.fgCyan();
        }

        if (isToday) {
            ansi = ansi.bold();
        }

        System.out.print(ansi
                .a(String.format("%2d", day.getDayOfMonth()))
                .bgDefault().fgDefault().boldOff()
                .a(" "));
    }

    private static void closeDayRow() {
        System.out.println("");
    }

    private static int toInt(DayOfWeek dayOfWeek) {
        return (dayOfWeek.getValue() + 6) % 7;
    }

    private static String capitalize(String s) {
        if (s.length() < 1) return s;

        char first = Character.toUpperCase(s.charAt(0));
        return "" + first + s.substring(1);
    }
}
