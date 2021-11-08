package pl.marcinchwedczuk.polishholidays;

import java.time.LocalDate;

/**
 * To understand this algorithm:
 * 
 * @see <a href="https://stackoverflow.com/a/26022891/1779504">StackOverflow question about
 *      computing Easter date</a>
 * @see <a href="https://en.wikipedia.org/wiki/Date_of_Easter">Wikipedia articles of the algorithm
 *      used</a>
 */
class EasterDateHolidayDateAlgorithm implements HolidayDateAlgorithm {
  @Override
  public LocalDate holidayDateForYear(int year) {
    int a = year % 19,
        b = year / 100,
        c = year % 100,
        d = b / 4,
        e = b % 4,
        g = (8 * b + 13) / 25,
        h = (19 * a + b - d - g + 15) % 30,
        j = c / 4,
        k = c % 4,
        m = (a + 11 * h) / 319,
        r = (2 * e + 2 * j - k - h + m + 32) % 7,
        n = (h - m + r + 90) / 25,
        p = (h - m + r + n + 19) % 32;

    // n - is month, p is day of the month
    return LocalDate.of(year, n, p);
  }
}
