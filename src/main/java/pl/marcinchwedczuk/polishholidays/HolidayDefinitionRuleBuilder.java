package pl.marcinchwedczuk.polishholidays;

import java.util.List;
import java.util.Optional;

public class HolidayDefinitionRuleBuilder {
  private final Integer validFromYearIncluding;
  private final Integer validToYearExcluding;

  private final HolidayDateAlgorithm holidayDateAlgorithm;

  private final String englishName;
  private final String polishName;

  private final HolidayType type;
  private final boolean publicHoliday;

  private HolidayDefinitionRuleBuilder(
      Integer validFromYearIncluding,
      Integer validToYearExcluding,
      HolidayDateAlgorithm holidayDateAlgorithm,
      String englishName,
      String polishName,
      HolidayType type,
      boolean publicHoliday) {
    this.validFromYearIncluding = validFromYearIncluding;
    this.validToYearExcluding = validToYearExcluding;
    this.holidayDateAlgorithm = holidayDateAlgorithm;
    this.englishName = englishName;
    this.polishName = polishName;
    this.type = type;
    this.publicHoliday = publicHoliday;
  }

  HolidayDefinitionRuleBuilder() {
    this(null, null, null, null, null, null, false);
  }

  public HolidayDefinitionRuleBuilder validFromYearIncluding(int year) {
    return new HolidayDefinitionRuleBuilder(
        year,
        validToYearExcluding,
        holidayDateAlgorithm,
        englishName,
        polishName,
        type,
        publicHoliday);
  }

  public HolidayDefinitionRuleBuilder validUntilYearExcluding(int year) {
    return new HolidayDefinitionRuleBuilder(
        validFromYearIncluding,
        year,
        holidayDateAlgorithm,
        englishName,
        polishName,
        type,
        publicHoliday);
  }

  public HolidayDefinitionRuleBuilder usesAlgorithm(
      HolidayDateAlgorithm holidayDateAlgorithm) {
    return new HolidayDefinitionRuleBuilder(
        validFromYearIncluding,
        validToYearExcluding,
        holidayDateAlgorithm,
        englishName,
        polishName,
        type,
        publicHoliday);
  }

  public HolidayDefinitionRuleBuilder withEnglishName(String name) {
    return new HolidayDefinitionRuleBuilder(
        validFromYearIncluding,
        validToYearExcluding,
        holidayDateAlgorithm,
        name,
        polishName,
        type,
        publicHoliday);
  }

  public HolidayDefinitionRuleBuilder withPolishName(String name) {
    return new HolidayDefinitionRuleBuilder(
        validFromYearIncluding,
        validToYearExcluding,
        holidayDateAlgorithm,
        englishName,
        name,
        type,
        publicHoliday);
  }

  public HolidayDefinitionRuleBuilder withType(HolidayType type) {
    return new HolidayDefinitionRuleBuilder(
        validFromYearIncluding,
        validToYearExcluding,
        holidayDateAlgorithm,
        englishName,
        polishName,
        type,
        publicHoliday);
  }

  public HolidayDefinitionRuleBuilder markAsPublicHoliday() {
    return new HolidayDefinitionRuleBuilder(
        validFromYearIncluding,
        validToYearExcluding,
        holidayDateAlgorithm,
        englishName,
        polishName,
        type,
        true);
  }

  public HolidayDefinitionRule build() {
    HolidayDefinitionRule rule = new HolidayDefinitionRule(
        Optional.ofNullable(validFromYearIncluding),
        Optional.ofNullable(validToYearExcluding),
        holidayDateAlgorithm,
        englishName,
        polishName,
        type,
        publicHoliday);

    return rule;
  }
}
