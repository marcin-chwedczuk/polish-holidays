package pl.marcinchwedczuk.polishholidays;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

import static java.util.Objects.requireNonNull;

public class HolidayCalendarBuilder {
  public static HolidayCalendarBuilder newBuilder() {
    return new HolidayCalendarBuilder(Collections.emptyList());
  }

  public static HolidayCalendarBuilder newBuilderWithDefaultHolidays() {
    List<HolidayDefinition> defaultHolidays =
        new PolishHolidaysLibrary().defaultHolidaysDefinitions();

    return new HolidayCalendarBuilder(defaultHolidays);
  }

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
