package pl.marcinchwedczuk.polishholidays;

import java.time.LocalDate;
import java.util.Objects;

class RelativeDateHolidayDateAlgorithm implements HolidayDateAlgorithm {
  private final HolidayDateAlgorithm base;
  private final int offsetDays;

  public RelativeDateHolidayDateAlgorithm(
      HolidayDateAlgorithm base,
      int offsetDays) {
    this.base = Objects.requireNonNull(base);
    this.offsetDays = offsetDays;
  }

  @Override
  public LocalDate holidayDateForYear(int year) {
    return base.holidayDateForYear(year).plusDays(offsetDays);
  }
}
