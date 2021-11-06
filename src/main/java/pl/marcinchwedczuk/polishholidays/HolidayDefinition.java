package pl.marcinchwedczuk.polishholidays;

import java.util.*;

import static java.util.Objects.requireNonNull;
import static pl.marcinchwedczuk.polishholidays.ArgumentChecks.checkNonBlankString;

public class HolidayDefinition {
  public static HolidayDefinitionBuilder newBuilder() {
    return new HolidayDefinitionBuilder();
  }

  private final EffectiveTimespan holidayEffectiveTimespan;
  private final HolidayDateAlgorithm holidayDateAlgorithm;

  private final String holidayEnglishName;
  private final String holidayPolishName;

  private final HolidayType holidayType;
  private final boolean publicHoliday;

  private final List<HolidayDefinitionOverride> rules;

  HolidayDefinition(
      EffectiveTimespan holidayEffectiveTimespan,
      HolidayDateAlgorithm holidayDateAlgorithm,
      String holidayEnglishName,
      String holidayPolishName,
      HolidayType holidayType,
      boolean isPublicHoliday,
      List<HolidayDefinitionOverride> rules) {

    requireNonNull(holidayEffectiveTimespan);
    requireNonNull(holidayDateAlgorithm);
    checkNonBlankString(holidayEnglishName, "English name cannot be empty.");
    checkNonBlankString(holidayPolishName, "Polish name cannot be empty.");
    requireNonNull(holidayType);
    requireNonNull(rules);

    // TODO: Check rules not overlapping

    // TODO: Other checks
    this.holidayEffectiveTimespan = holidayEffectiveTimespan;
    this.holidayDateAlgorithm = holidayDateAlgorithm;
    this.holidayEnglishName = holidayEnglishName;
    this.holidayPolishName = holidayPolishName;
    this.holidayType = holidayType;
    this.publicHoliday = isPublicHoliday;
    this.rules = rules;
  }

  public EffectiveTimespan holidayEffectiveTimespan() {
    return this.holidayEffectiveTimespan;
  }

  public HolidayDateAlgorithm holidayDateAlgorithm() {
    return this.holidayDateAlgorithm;
  }

  public String holidayEnglishName() {
    return this.holidayEnglishName;
  }

  public String holidayPolishName() {
    return this.holidayPolishName;
  }

  public HolidayType holidayType() {
    return this.holidayType;
  }

  public boolean isPublicHoliday() {
    return publicHoliday;
  }

  public Collection<HolidayDefinitionOverride> rules() {
    return Collections.unmodifiableList(rules);
  }

  public Optional<Holiday> maybeHolidayForYear(int year) {
    if (!holidayEffectiveTimespan.includesYear(year)) {
      return Optional.empty();
    }

    Optional<HolidayDefinitionOverride> maybeOverride = rules.stream()
        .filter(rule -> rule.isInEffectForYear(year))
        .findFirst();

    HolidayDefinition definitionWithOverrides = maybeOverride
        .map(override -> override.apply(this))
        .orElse(this);

    return Optional.of(new Holiday(
        definitionWithOverrides.holidayDateAlgorithm.holidayDateForYear(year),
        definitionWithOverrides.holidayEnglishName,
        definitionWithOverrides.holidayPolishName,
        definitionWithOverrides.holidayType,
        definitionWithOverrides.publicHoliday));
  }

  public HolidayDefinitionBuilder toBuilder() {
    return new HolidayDefinitionBuilder(
        this.holidayEffectiveTimespan,
        this.holidayDateAlgorithm,
        this.holidayEnglishName,
        this.holidayPolishName,
        this.holidayType,
        this.publicHoliday,
        this.rules);
  }
}
