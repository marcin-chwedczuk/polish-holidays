package pl.marcinchwedczuk.polishholidays;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class HolidayCalendarBuilder {
  private final List<HolidayDefinition> holidays;

  HolidayCalendarBuilder(List<HolidayDefinition> holidays) {
    this.holidays = new ArrayList<>(holidays);
  }

  public HolidayCalendarBuilder defineHoliday(
      HolidayDefinition holidayDefinition) {
    holidays.add(requireNonNull(holidayDefinition));
    return this;
  }

  public HolidayCalendarBuilder removeAllHolidaysMatching(
      Predicate<HolidayDefinition> predicate) {
    holidays.removeIf(predicate);
    return this;
  }

  public HolidayCalendar createCalendar() {
    return new HolidayCalendar(holidays);
  }
}
