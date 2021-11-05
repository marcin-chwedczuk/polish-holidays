package pl.marcinchwedczuk.polishholidays;

import static java.util.Objects.requireNonNull;

import java.util.Collections;
import java.util.List;

public class HolidayDefinitionBuilder {
  private final HolidayDateAlgorithm holidayDateAlgorithm;

  private final String englishName;
  private final String polishName;

  private final List<HolidayDefinitionRule> rules;

  private HolidayDefinitionBuilder(
      HolidayDateAlgorithm holidayDateAlgorithm,
      String englishName,
      String polishName,
      List<HolidayDefinitionRule> rules) {
    this.holidayDateAlgorithm = holidayDateAlgorithm;
    this.englishName = englishName;
    this.polishName = polishName;
    this.rules = rules;
  }

  HolidayDefinitionBuilder() {
    this(null, null, null, Collections.emptyList());
  }

  public HolidayDefinitionBuilder defineRule(HolidayDefinitionRule rule) {
    return new HolidayDefinitionBuilder(
        this.holidayDateAlgorithm,
        this.englishName,
        this.polishName,
        ImmutableListUtils.add(rules, rule));
  }

  public HolidayDefinitionBuilder usesAlgorithm(
      HolidayDateAlgorithm holidayDateAlgorithm) {
    return new HolidayDefinitionBuilder(
        holidayDateAlgorithm,
        this.englishName,
        this.polishName,
        this.rules);
  }

  public HolidayDefinitionBuilder withEnglishName(String name) {
    return new HolidayDefinitionBuilder(
        this.holidayDateAlgorithm,
        name,
        this.polishName,
        this.rules);
  }

  public HolidayDefinitionBuilder withPolishName(String name) {
    return new HolidayDefinitionBuilder(
        this.holidayDateAlgorithm,
        this.englishName,
        name,
        this.rules);
  }

  public HolidayDefinition build() {
    return new HolidayDefinition(
        holidayDateAlgorithm,
        englishName,
        polishName,
        rules);
  }
}
