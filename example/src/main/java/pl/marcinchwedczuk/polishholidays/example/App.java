package pl.marcinchwedczuk.polishholidays.example;


import java.time.DayOfWeek;
import java.time.LocalDate;

public class App {
    public static void main(String[] args) {
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
            printDayBox(null);
            currentDayOfWeek++;
        }

        boolean openNewRow = false;
        while (curr.getMonthValue() == month) {
            if (openNewRow) {
                openNewRow = false;
                openDayRow();
            }
            printDayBox(curr.getDayOfMonth());

            currentDayOfWeek = (currentDayOfWeek + 1) % 7;
            curr = curr.plusDays(1);

            if (currentDayOfWeek == 0) {
                closeDayRow();
                openNewRow = true;
            }
        }

        if (!openNewRow) {
            while (currentDayOfWeek != 0) {
                printDayBox(null);
                currentDayOfWeek = (currentDayOfWeek + 1) % 7;
            }
            closeDayRow();
        }

        // TODO:
    }

    private static void openDayRow() {
        System.out.print("*");
    }

    private static void printDayBox(Integer day) {
        if (day == null) {
            System.out.print("  |");
        } else {
            System.out.format("%2d|", day);
        }
    }

    private static void closeDayRow() {
        System.out.println("*");
    }

    private static int toInt(DayOfWeek dayOfWeek) {
        return (dayOfWeek.getValue() + 6) % 7;
    }
}
