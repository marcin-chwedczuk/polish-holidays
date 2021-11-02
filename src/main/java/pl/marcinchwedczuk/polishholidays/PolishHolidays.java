package pl.marcinchwedczuk.polishholidays;

import java.util.List;

public class PolishHolidays {
  public static List<PolishHoliday> forYear(int year) {
    PolishHolidayCalendar calendar = new PolishHolidayCalendar();
    return calendar.findHolidaysForYear(year);
  }
}
