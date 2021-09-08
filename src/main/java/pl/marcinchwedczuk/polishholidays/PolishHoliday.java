package pl.marcinchwedczuk.polishholidays;

import java.time.LocalDate;
import java.util.Objects;

public final class PolishHoliday {
    private final LocalDate date;
    private final String englishName;
    private final String polishName;
    private final PolishHolidayType type;
    private final boolean publicHoliday;

    public PolishHoliday(LocalDate date,
                         String englishName,
                         String polishName,
                         PolishHolidayType type,
                         boolean publicHoliday) {
        this.date = date;
        this.englishName = englishName;
        this.polishName = polishName;
        this.type = type;
        this.publicHoliday = publicHoliday;
    }

    public LocalDate date() {
        return date;
    }

    public PolishHolidayType type() {
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
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PolishHoliday that = (PolishHoliday) o;
        return publicHoliday == that.publicHoliday &&
                date.equals(that.date) &&
                englishName.equals(that.englishName) &&
                polishName.equals(that.polishName) &&
                type == that.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, englishName, polishName, type, publicHoliday);
    }
}
