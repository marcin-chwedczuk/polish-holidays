package pl.marcinchwedczuk.polishholidays;

import static java.util.Collections.unmodifiableList;
import static java.util.Objects.requireNonNull;
import static java.util.stream.Collectors.toList;
import static pl.marcinchwedczuk.polishholidays.ArgumentChecks.checkNonEmptyRange;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class HolidayCalendar {
  public static HolidayCalendarBuilder newBuilder() {
    return new HolidayCalendarBuilder();
  }

  public static HolidayCalendarBuilder newBuilderWithPolishHolidaysDefined() {
    PolishHolidaysLibrary library = new PolishHolidaysLibrary();

    return new HolidayCalendarBuilder()
        .preloadHolidays(library.holidaysDefinitions())
        .withValidFromYearIncluding(library.validSinceYearIncluding());
  }

  private final Optional<Integer> validFromYearInclusive;
  private final Optional<Integer> validToYearExcluding;
  private final List<HolidayDefinition> holidayDefinitions;

  HolidayCalendar(Optional<Integer> validFromYearInclusive,
      Optional<Integer> validToYearExcluding,
      List<HolidayDefinition> holidayDefinitions) {

    requireNonNull(validFromYearInclusive);
    requireNonNull(validToYearExcluding);
    requireNonNull(holidayDefinitions);

    checkNonEmptyRange(
        validFromYearInclusive, validToYearExcluding,
        "Calendar validity range is empty.");

    this.validFromYearInclusive = validFromYearInclusive;
    this.validToYearExcluding = validToYearExcluding;
    this.holidayDefinitions =
        unmodifiableList(new ArrayList<>(holidayDefinitions));
  }

  public Optional<Integer> validFromYearInclusive() {
    return this.validFromYearInclusive;
  }

  public Optional<Integer> validToYearExcluding() {
    return this.validToYearExcluding;
  }

  public List<Holiday> holidaysForYear(int year) {
    checkCalendarValidForYear(year);

    return holidayDefinitions.stream()
        .map(holidayDefinition -> holidayDefinition.maybeHolidayForYear(year))
        .flatMap(Optionals::asStream)
        .sorted(Comparator.comparing(Holiday::date))
        .collect(toList());
  }

  private void checkCalendarValidForYear(int year) {
    if (year < validFromYearInclusive.orElse(Integer.MIN_VALUE)
        || year >= validToYearExcluding.orElse(Integer.MAX_VALUE)) {
      throw new IllegalArgumentException(
          "Calendar is not defined for year " + year + ".");
    }
  }
}
