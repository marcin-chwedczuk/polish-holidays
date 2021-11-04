package pl.marcinchwedczuk.polishholidays;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

import static java.util.Objects.requireNonNull;

public class HolidayDefinitionBuilder {
  private final List<HolidayDefinitionRule> rules = new ArrayList<>();

  public HolidayDefinitionBuilder defineRule(HolidayDefinitionRule rule) {
    rules.add(requireNonNull(rule));
    return this;
  }

  public HolidayDefinition build() {
    if (rules.isEmpty()) {
      throw new IllegalArgumentException(
          "Cannot create definition without entries.");
    }

    return new HolidayDefinition(rules);
  }
}
