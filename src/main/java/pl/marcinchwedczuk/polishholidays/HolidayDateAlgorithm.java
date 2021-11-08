package pl.marcinchwedczuk.polishholidays;

import java.time.LocalDate;

public interface HolidayDateAlgorithm {
  LocalDate holidayDateForYear(int year);
}
