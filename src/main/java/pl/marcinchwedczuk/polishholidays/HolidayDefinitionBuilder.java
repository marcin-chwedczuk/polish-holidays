package pl.marcinchwedczuk.polishholidays;

import java.util.Collections;
import java.util.List;

public class HolidayDefinitionBuilder {
  private final EffectiveTimespan effectiveTimespan;
  private final HolidayDateAlgorithm holidayDateAlgorithm;

  private final String englishName;
  private final String polishName;

  private final HolidayType holidayType;
  private final boolean publicHoliday;

  private final List<HolidayDefinitionOverride> rules;

  HolidayDefinitionBuilder(
      EffectiveTimespan effectiveTimespan,
      HolidayDateAlgorithm holidayDateAlgorithm,
      String englishName,
      String polishName,
      HolidayType holidayType,
      boolean isPublicHoliday,
      List<HolidayDefinitionOverride> rules) {
    this.effectiveTimespan = effectiveTimespan;
    this.holidayDateAlgorithm = holidayDateAlgorithm;
    this.englishName = englishName;
    this.polishName = polishName;
    this.holidayType = holidayType;
    this.publicHoliday = isPublicHoliday;
    this.rules = rules;
  }

  HolidayDefinitionBuilder() {
    this(EffectiveTimespan.atAllTimes(),
        null,
        null,
        null,
        null,
        false,
        Collections.emptyList());
  }

  public HolidayDefinitionBuilder addOverride(HolidayDefinitionOverride rule) {
    return new HolidayDefinitionBuilder(
        this.effectiveTimespan,
        this.holidayDateAlgorithm,
        this.englishName,
        this.polishName,
        this.holidayType,
        this.publicHoliday,
        ImmutableListUtils.add(rules, rule));
  }

  public HolidayDefinitionBuilder withEffectiveTimespan(
      EffectiveTimespan effectiveTimespan) {
    return new HolidayDefinitionBuilder(
        effectiveTimespan,
        this.holidayDateAlgorithm,
        this.englishName,
        this.polishName,
        this.holidayType,
        this.publicHoliday,
        this.rules);
  }

  public HolidayDefinitionBuilder withDate(
      HolidayDateAlgorithm holidayDateAlgorithm) {
    return new HolidayDefinitionBuilder(
        this.effectiveTimespan,
        holidayDateAlgorithm,
        this.englishName,
        this.polishName,
        this.holidayType,
        this.publicHoliday,
        this.rules);
  }

  public HolidayDefinitionBuilder withEnglishName(String name) {
    return new HolidayDefinitionBuilder(
        this.effectiveTimespan,
        this.holidayDateAlgorithm,
        name,
        this.polishName,
        this.holidayType,
        this.publicHoliday,
        this.rules);
  }

  public HolidayDefinitionBuilder withPolishName(String name) {
    return new HolidayDefinitionBuilder(
        this.effectiveTimespan,
        this.holidayDateAlgorithm,
        this.englishName,
        name,
        this.holidayType,
        this.publicHoliday,
        this.rules);
  }

  public HolidayDefinitionBuilder withHolidayType(HolidayType type) {
    return new HolidayDefinitionBuilder(
        this.effectiveTimespan,
        this.holidayDateAlgorithm,
        this.englishName,
        this.polishName,
        type,
        this.publicHoliday,
        this.rules);
  }

  public HolidayDefinitionBuilder markAsPublicHoliday() {
    return new HolidayDefinitionBuilder(
        this.effectiveTimespan,
        this.holidayDateAlgorithm,
        this.englishName,
        this.polishName,
        this.holidayType,
        true,
        this.rules);
  }

  public HolidayDefinitionBuilder removePublicHolidayFlag() {
    return new HolidayDefinitionBuilder(
        this.effectiveTimespan,
        this.holidayDateAlgorithm,
        this.englishName,
        this.polishName,
        this.holidayType,
        false,
        this.rules);
  }

  public HolidayDefinition build() {
    return new HolidayDefinition(
        this.effectiveTimespan,
        this.holidayDateAlgorithm,
        this.englishName,
        this.polishName,
        this.holidayType,
        this.publicHoliday,
        this.rules);
  }
}
