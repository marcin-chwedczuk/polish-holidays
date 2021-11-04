package pl.marcinchwedczuk.polishholidays;

import java.util.ArrayList;
import java.util.List;

public class PolishHolidayCalendarBuilder {
  public static PolishHolidayCalendarBuilder newBuilderWithDefaultHolidays() {
    List<PolishHolidayDefinition> defaultHolidays =
        new DefaultHolidaysLibrary().defaultHolidaysDefinitions();

    return new PolishHolidayCalendarBuilder(defaultHolidays);
  }

  private final List<PolishHolidayDefinition> holidays;

  public PolishHolidayCalendarBuilder(List<PolishHolidayDefinition> holidays) {
    this.holidays = new ArrayList<>(holidays);
  }

  public PolishHolidayCalendar createCalendar() {
    return new PolishHolidayCalendar(holidays);
  }
}
