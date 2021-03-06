package pl.marcinchwedczuk.polishholidays;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.util.Objects;

public final class Holiday {
  private final LocalDate date;
  private final String englishName;
  private final String polishName;
  private final HolidayType type;
  private final boolean publicHoliday;

  public Holiday(
      LocalDate date,
      String englishName,
      String polishName,
      HolidayType type,
      boolean publicHoliday) {
    this.date = requireNonNull(date);
    this.englishName = requireNonNull(englishName);
    this.polishName = requireNonNull(polishName);
    this.type = requireNonNull(type);
    this.publicHoliday = publicHoliday;
  }

  public LocalDate date() {
    return date;
  }

  public HolidayType type() {
    return type;
  }

  public String englishName() {
    return englishName;
  }

  public String polishName() {
    return polishName;
  }

  public boolean isPublicHoliday() {
    return publicHoliday;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    Holiday that = (Holiday) o;
    return publicHoliday == that.publicHoliday
        && Objects.equals(this.date, that.date)
        && Objects.equals(this.englishName, that.englishName)
        && Objects.equals(this.polishName, that.polishName)
        && type == that.type;
  }

  @Override
  public int hashCode() {
    return Objects.hash(date, englishName, polishName, type, publicHoliday);
  }
}
