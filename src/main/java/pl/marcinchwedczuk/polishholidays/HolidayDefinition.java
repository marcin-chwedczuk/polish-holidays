package pl.marcinchwedczuk.polishholidays;

import java.util.*;

public class HolidayDefinition {
  public static HolidayDefinitionBuilder newBuilder() {
    return new HolidayDefinitionBuilder();
  }

  private final List<HolidayDefinitionRule> rules;

  HolidayDefinition(List<HolidayDefinitionRule> rules) {
    this.rules = rules;
  }

  public Collection<HolidayDefinitionRule> rules() {
    return Collections.unmodifiableList(rules);
  }

  public Optional<Holiday> maybeHolidayForYear(int year) {
    return rules.stream()
        .filter(entry -> entry.isDefinedForYear(year))
        .findFirst()
        .map(entry -> entry.holidayInstanceForYear(year));
  }
}
