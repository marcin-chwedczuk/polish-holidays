package pl.marcinchwedczuk.polishholidays;

import static java.util.Objects.requireNonNull;
import static pl.marcinchwedczuk.polishholidays.ArgumentChecks.checkNonBlankString;
import static pl.marcinchwedczuk.polishholidays.ArgumentChecks.checkNonEmptyRange;

import java.util.Optional;

class HolidayDefinitionRule {
  public static HolidayDefinitionRuleBuilder newBuilder() {
    return new HolidayDefinitionRuleBuilder();
  }

  private final Optional<Integer> validFromYearIncluding;
  private final Optional<Integer> validToYearExcluding;

  private final HolidayType type;
  private final boolean publicHoliday;

  HolidayDefinitionRule(
      Optional<Integer> validFromYearIncluding,
      Optional<Integer> validToYearExcluding,
      HolidayType type,
      boolean publicHoliday) {
    requireNonNull(validFromYearIncluding);
    requireNonNull(validToYearExcluding);

    checkNonEmptyRange(validFromYearIncluding, validToYearExcluding,
        "Empty years range");
    requireNonNull(type);

    this.validFromYearIncluding = validFromYearIncluding;
    this.validToYearExcluding = validToYearExcluding;

    this.type = type;
    this.publicHoliday = publicHoliday;
  }

  public HolidayType holidayType() {
    return this.type;
  }

  public boolean isPublicHoliday() {
    return publicHoliday;
  }

  public boolean isInEffectForYear(int year) {
    int fromYear = validFromYearIncluding.orElse(Integer.MIN_VALUE);
    int toYear = validToYearExcluding.orElse(Integer.MAX_VALUE);
    return fromYear <= year && year < toYear;
  }
}
