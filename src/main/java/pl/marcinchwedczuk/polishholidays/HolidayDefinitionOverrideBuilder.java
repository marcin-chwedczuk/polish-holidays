package pl.marcinchwedczuk.polishholidays;

public class HolidayDefinitionOverrideBuilder {
  private final EffectiveTimespan effectiveTimespan;
  private final HolidayDefinitionOverrideLogic override;

  private HolidayDefinitionOverrideBuilder(
      EffectiveTimespan effectiveTimespan,
      HolidayDefinitionOverrideLogic override) {
    this.effectiveTimespan = effectiveTimespan;
    this.override = override;
  }

  HolidayDefinitionOverrideBuilder() {
    this(null, null);
  }

  public HolidayDefinitionOverrideBuilder withEffectiveTimespan(
      EffectiveTimespan effectiveTimespan) {
    return new HolidayDefinitionOverrideBuilder(
        effectiveTimespan,
        this.override);
  }

  public HolidayDefinitionOverrideBuilder withOverride(
      HolidayDefinitionOverrideLogic logic) {
    return new HolidayDefinitionOverrideBuilder(
        this.effectiveTimespan,
        logic);
  }

  public HolidayDefinitionOverride build() {
    return new HolidayDefinitionOverride(effectiveTimespan, override);
  }
}
