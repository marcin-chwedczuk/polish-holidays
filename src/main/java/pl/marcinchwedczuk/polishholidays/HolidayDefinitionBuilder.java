package pl.marcinchwedczuk.polishholidays;

import static java.util.Objects.requireNonNull;

import java.util.Collections;
import java.util.List;

public class HolidayDefinitionBuilder {
  private final List<HolidayDefinitionRule> rules;

  private HolidayDefinitionBuilder(List<HolidayDefinitionRule> rules) {
    this.rules = rules;
  }

  HolidayDefinitionBuilder() {
    this(Collections.emptyList());
  }

  public HolidayDefinitionBuilder defineRule(HolidayDefinitionRule rule) {
    requireNonNull(rule);
    return new HolidayDefinitionBuilder(ImmutableListUtils.add(rules, rule));
  }

  public HolidayDefinition build() {
    if (rules.isEmpty()) {
      throw new IllegalArgumentException(
          "Cannot create definition without entries.");
    }

    return new HolidayDefinition(rules);
  }
}
