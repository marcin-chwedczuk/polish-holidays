package pl.marcinchwedczuk.polishholidays;

import static java.util.Objects.requireNonNull;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class HolidayCalendarBuilder {
  private final Integer validFromYearIncluding;
  private final Integer validToYearExcluding;

  private final List<HolidayDefinition> holidays;

  private HolidayCalendarBuilder(Integer validFromYearIncluding,
      Integer validToYearExcluding,
      List<HolidayDefinition> holidays) {
    this.validFromYearIncluding = validFromYearIncluding;
    this.validToYearExcluding = validToYearExcluding;
    this.holidays = holidays;
  }

  HolidayCalendarBuilder() {
    this(null, null, Collections.emptyList());
  }

  public HolidayCalendarBuilder preloadHolidays(
      List<HolidayDefinition> holidaysToPreload) {
    return new HolidayCalendarBuilder(
        this.validFromYearIncluding,
        this.validToYearExcluding,
        ImmutableListUtils.concat(this.holidays, holidaysToPreload));
  }

  public HolidayCalendarBuilder defineHoliday(
      HolidayDefinition holidayDefinition) {

    requireNonNull(holidayDefinition, "holidayDefinition cannot be null");

    return new HolidayCalendarBuilder(
        this.validFromYearIncluding,
        this.validToYearExcluding,
        ImmutableListUtils.add(this.holidays, holidayDefinition));
  }

  public HolidayCalendarBuilder removeAllHolidaysMatching(
      Predicate<HolidayDefinition> predicate) {
    return new HolidayCalendarBuilder(
        this.validFromYearIncluding,
        this.validToYearExcluding,
        ImmutableListUtils.removeIf(this.holidays, predicate));
  }

  public HolidayCalendarBuilder withValidFromYearIncluding(int year) {

    return new HolidayCalendarBuilder(
        year,
        this.validToYearExcluding,
        this.holidays);
  }

  public HolidayCalendarBuilder withValidToYearExcluding(int year) {
    return new HolidayCalendarBuilder(
        this.validFromYearIncluding,
        year,
        this.holidays);
  }

  public HolidayCalendar createCalendar() {
    return new HolidayCalendar(
        Optional.ofNullable(validFromYearIncluding),
        Optional.ofNullable(validToYearExcluding),
        holidays);
  }
}
