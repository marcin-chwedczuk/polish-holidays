package pl.marcinchwedczuk.polishholidays;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class EasterDateHolidayDateAlgorithmTest {
    private final EasterDateHolidayDateAlgorithm algorithm = new EasterDateHolidayDateAlgorithm();

    @Test
    public void works() {
        // Easter dates taken from: https://pl.wikipedia.org/wiki/Wielkanoc
        assertEquals(
                LocalDate.of(2000, 4, 23),
                algorithm.holidayDateForYear(2000));

        assertEquals(
                LocalDate.of(2001, 4, 15),
                algorithm.holidayDateForYear(2001));

        assertEquals(
                LocalDate.of(2002, 3, 31),
                algorithm.holidayDateForYear(2002));

        assertEquals(
                LocalDate.of(2005, 3, 27),
                algorithm.holidayDateForYear(2005));

        assertEquals(
                LocalDate.of(2010, 4, 4),
                algorithm.holidayDateForYear(2010));

        assertEquals(
                LocalDate.of(2023, 4, 9),
                algorithm.holidayDateForYear(2023));
    }
}