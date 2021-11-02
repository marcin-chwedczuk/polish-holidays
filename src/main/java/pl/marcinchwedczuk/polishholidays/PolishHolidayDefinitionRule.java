package pl.marcinchwedczuk.polishholidays;

import static java.util.Objects.requireNonNull;
import static pl.marcinchwedczuk.polishholidays.ArgumentChecks.checkNonBlankString;
import static pl.marcinchwedczuk.polishholidays.ArgumentChecks.checkNonEmptyRange;

import java.util.Optional;

class PolishHolidayDefinitionRule {
  private final Optional<Integer> validFromYearIncluding;
  private final Optional<Integer> validToYearExcluding;

  private final HolidayDateAlgorithm holidayDateAlgorithm;

  private final String englishName;
  private final String polishName;
  private final PolishHolidayType type;
  private final boolean publicHoliday;

  PolishHolidayDefinitionRule(
      Optional<Integer> validFromYearIncluding,
      Optional<Integer> validToYearExcluding,
      HolidayDateAlgorithm holidayDateAlgorithm,
      String englishName,
      String polishName,
      PolishHolidayType type,
      boolean publicHoliday) {
    requireNonNull(validFromYearIncluding);
    requireNonNull(validToYearExcluding);
    checkNonEmptyRange(validFromYearIncluding, validToYearExcluding, "Empty years range");
    requireNonNull(holidayDateAlgorithm);
    checkNonBlankString(englishName, "English name cannot be empty.");
    checkNonBlankString(polishName, "Polish name cannot be empty");
    requireNonNull(type);

    this.validFromYearIncluding = validFromYearIncluding;
    this.validToYearExcluding = validToYearExcluding;

    this.holidayDateAlgorithm = holidayDateAlgorithm;

    this.englishName = englishName;
    this.polishName = polishName;
    this.type = type;
    this.publicHoliday = publicHoliday;
  }

  boolean isDefinedForYear(int year) {
    int fromYear = validFromYearIncluding.orElse(Integer.MIN_VALUE);
    int toYear = validToYearExcluding.orElse(Integer.MAX_VALUE);
    return fromYear <= year && year < toYear;
  }

  PolishHoliday holidayInstanceForYear(int year) {
    return new PolishHoliday(
        holidayDateAlgorithm.holidayDateForYear(year),
        englishName,
        polishName,
        type,
        publicHoliday);
  }
}
