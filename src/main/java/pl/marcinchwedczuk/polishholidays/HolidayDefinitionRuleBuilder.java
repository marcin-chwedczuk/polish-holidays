package pl.marcinchwedczuk.polishholidays;

public class HolidayDefinitionRuleBuilder {
  private final EffectiveTimespan effectiveTimespan;
  private final HolidayDefinitionOverrideLogic override;

  private HolidayDefinitionRuleBuilder(
      EffectiveTimespan effectiveTimespan,
      HolidayDefinitionOverrideLogic override) {
    this.effectiveTimespan = effectiveTimespan;
    this.override = override;
  }

  HolidayDefinitionRuleBuilder() {
    this(null, null);
  }

  public HolidayDefinitionRuleBuilder withEffectiveTimespan(
      EffectiveTimespan effectiveTimespan) {
    return new HolidayDefinitionRuleBuilder(
        effectiveTimespan,
        this.override);
  }

  public HolidayDefinitionRuleBuilder withOverride(
      HolidayDefinitionOverrideLogic logic) {
    return new HolidayDefinitionRuleBuilder(
        this.effectiveTimespan,
        logic);
  }

  public HolidayDefinitionOverride build() {
    return new HolidayDefinitionOverride(effectiveTimespan, override);
  }
}
