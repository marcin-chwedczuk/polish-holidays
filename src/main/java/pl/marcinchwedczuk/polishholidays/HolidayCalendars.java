package pl.marcinchwedczuk.polishholidays;

import java.util.List;

public class HolidayCalendars {
  public static HolidayCalendar createPolishHolidaysCalendar() {
    return HolidayCalendarBuilder
        .newBuilderWithDefaultHolidays()
        .createCalendar();
  }

  public static List<Holiday> forYear(int year) {
    return createPolishHolidaysCalendar().findHolidaysForYear(year);
  }
}
