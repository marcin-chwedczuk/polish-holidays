package pl.marcinchwedczuk.polishholidays;

import java.time.LocalDate;

interface HolidayDateAlgorithm {
    LocalDate holidayDateForYear(int year);
}
