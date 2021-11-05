package pl.marcinchwedczuk.polishholidays;

import java.util.List;
import java.util.Optional;

public class HolidayDefinitionRuleBuilder {
  private final Integer validFromYearIncluding;
  private final Integer validToYearExcluding;

  private final HolidayType type;
  private final boolean publicHoliday;

  private HolidayDefinitionRuleBuilder(
      Integer validFromYearIncluding,
      Integer validToYearExcluding,
      HolidayType type,
      boolean publicHoliday) {
    this.validFromYearIncluding = validFromYearIncluding;
    this.validToYearExcluding = validToYearExcluding;
    this.type = type;
    this.publicHoliday = publicHoliday;
  }

  HolidayDefinitionRuleBuilder() {
    this(null, null, null, false);
  }

  public HolidayDefinitionRuleBuilder validFromYearIncluding(int year) {
    return new HolidayDefinitionRuleBuilder(
        year,
        validToYearExcluding,
        type,
        publicHoliday);
  }

  public HolidayDefinitionRuleBuilder validUntilYearExcluding(int year) {
    return new HolidayDefinitionRuleBuilder(
        validFromYearIncluding,
        year,
        type,
        publicHoliday);
  }


  public HolidayDefinitionRuleBuilder withHolidayType(HolidayType type) {
    return new HolidayDefinitionRuleBuilder(
        validFromYearIncluding,
        validToYearExcluding,
        type,
        publicHoliday);
  }

  public HolidayDefinitionRuleBuilder markAsPublicHoliday() {
    return new HolidayDefinitionRuleBuilder(
        validFromYearIncluding,
        validToYearExcluding,
        type,
        true);
  }

  public HolidayDefinitionRule build() {
    return new HolidayDefinitionRule(
        Optional.ofNullable(validFromYearIncluding),
        Optional.ofNullable(validToYearExcluding),
        type,
        publicHoliday);
  }
}
