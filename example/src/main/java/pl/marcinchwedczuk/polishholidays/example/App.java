package pl.marcinchwedczuk.polishholidays.example;


import org.fusesource.jansi.AnsiConsole;
import pl.marcinchwedczuk.polishholidays.HolidayCalendar;

import java.time.DayOfWeek;
import java.time.LocalDate;

import static org.fusesource.jansi.Ansi.*;
import static org.fusesource.jansi.Ansi.Color.*;

public class App {
    private static HolidayCalendar holidayCalendar;

    public static void main(String[] args) {
        AnsiConsole.systemInstall();

        holidayCalendar = HolidayCalendar
                .newBuilderWithPolishHolidaysDefined()
                .createCalendar();

        LocalDate now = LocalDate.now();

        for (int i = 1; i <= 12; i++) {
            System.out.println("--------------");
            printMonth(i, now.getYear());
        }
    }

    private static void printMonth(int month, int year) {
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

        // TODO:
    }

    private static void openDayRow() {
        System.out.print(" ");
    }

    private static void printEmptyDayBox() {
        System.out.print("   ");
    }

    private static void printDayBox(LocalDate day) {
        boolean isHoliday = holidayCalendar
                .holidaysForYear(day.getYear()).stream()
                .anyMatch(holiday -> holiday.date().equals(day)
                    && holiday.isPublicHoliday());
        if (isHoliday) {
            System.out.print(ansi()
                    .bgRed()
                    .a(String.format("%2d", day.getDayOfMonth()))
                    .bgDefault()
                    .a(" "));
        } else {
            System.out.format("%2d ", day.getDayOfMonth());
        }
    }

    private static void closeDayRow() {
        System.out.println("");
    }

    private static int toInt(DayOfWeek dayOfWeek) {
        return (dayOfWeek.getValue() + 6) % 7;
    }
}
