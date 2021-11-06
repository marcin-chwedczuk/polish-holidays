package pl.marcinchwedczuk.polishholidays;

public class HolidayDateAlgorithms {
  public static HolidayDateAlgorithm fixedAtMonthDay(int month, int day) {
    return new FixedDateHolidayDateAlgorithm(month, day);
  }

  public static HolidayDateAlgorithm relativeToEaster(int offsetDays) {
    return new RelativeDateHolidayDateAlgorithm(
        new EasterDateHolidayDateAlgorithm(), offsetDays);
  }
}
