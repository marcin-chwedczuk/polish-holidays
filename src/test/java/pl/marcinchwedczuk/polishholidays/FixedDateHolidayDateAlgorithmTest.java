package pl.marcinchwedczuk.polishholidays;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class FixedDateHolidayDateAlgorithmTest {
    @Test
    public void works() {
        FixedDateHolidayDateAlgorithm algo = new FixedDateHolidayDateAlgorithm(
                11, 13);

        LocalDate returnedDate = algo.holidayDateForYear(2000);

        LocalDate expectedDate = LocalDate.of(2000, 11, 13);
        assertEquals(expectedDate, returnedDate);
    }
}