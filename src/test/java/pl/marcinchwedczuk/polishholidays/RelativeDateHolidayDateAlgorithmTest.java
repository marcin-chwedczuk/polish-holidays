package pl.marcinchwedczuk.polishholidays;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

class RelativeDateHolidayDateAlgorithmTest {
  @Test
  public void works_with_positive_offset() {
    RelativeDateHolidayDateAlgorithm algorithm =
        new RelativeDateHolidayDateAlgorithm(
            new FixedDateHolidayDateAlgorithm(10, 20), 5);

    LocalDate returnedDate = algorithm.holidayDateForYear(2000);

    LocalDate expectedDate = LocalDate.of(2000, 10, 25);
    assertEquals(expectedDate, returnedDate);
  }

  @Test
  public void works_with_negative_offset() {
    RelativeDateHolidayDateAlgorithm algorithm =
        new RelativeDateHolidayDateAlgorithm(
            new FixedDateHolidayDateAlgorithm(10, 20), -5);

    LocalDate returnedDate = algorithm.holidayDateForYear(2000);

    LocalDate expectedDate = LocalDate.of(2000, 10, 15);
    assertEquals(expectedDate, returnedDate);
  }
}
