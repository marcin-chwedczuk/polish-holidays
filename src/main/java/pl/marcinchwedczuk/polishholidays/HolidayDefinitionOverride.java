package pl.marcinchwedczuk.polishholidays;

import static java.util.Objects.requireNonNull;

class HolidayDefinitionOverride {
  public static HolidayDefinitionRuleBuilder newBuilder() {
    return new HolidayDefinitionRuleBuilder();
  }

  private final EffectiveTimespan effectiveTimespan;
  private final HolidayDefinitionOverrideLogic override;

  HolidayDefinitionOverride(
      EffectiveTimespan effectiveTimespan,
      HolidayDefinitionOverrideLogic override) {

    requireNonNull(effectiveTimespan);
    requireNonNull(override);

    this.effectiveTimespan = effectiveTimespan;
    this.override = override;
  }

  public boolean isInEffectForYear(int year) {
    return effectiveTimespan.includesYear(year);
  }

  public HolidayDefinition apply(HolidayDefinition holidayDefinition) {
    HolidayDefinitionBuilder builder = holidayDefinition.toBuilder();
    return override
        .apply(builder)
        .build();
  }
}
