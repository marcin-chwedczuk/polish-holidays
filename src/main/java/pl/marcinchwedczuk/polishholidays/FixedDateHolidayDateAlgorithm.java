package pl.marcinchwedczuk.polishholidays;

import static pl.marcinchwedczuk.polishholidays.ArgumentChecks.checkInRange;

import java.time.LocalDate;

class FixedDateHolidayDateAlgorithm implements HolidayDateAlgorithm {
  private final int month;
  private final int day;

  public FixedDateHolidayDateAlgorithm(int month, int day) {
    checkInRange(day, 1, 31, "Day out of range");
    checkInRange(month, 1, 12, "Month out of range");

    this.month = month;
    this.day = day;
  }

  @Override
  public LocalDate holidayDateForYear(int year) {
    return LocalDate.of(year, month, day);
  }
}
