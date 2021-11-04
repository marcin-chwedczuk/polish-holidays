package pl.marcinchwedczuk.polishholidays;

public class HolidayCalendars {
  public static HolidayCalendar createPolishHolidaysCalendar() {
    return HolidayCalendar
        .newBuilderWithPolishHolidaysDefined()
        .createCalendar();
  }
}
