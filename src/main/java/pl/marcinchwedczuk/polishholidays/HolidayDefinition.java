package pl.marcinchwedczuk.polishholidays;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class HolidayDefinition {
  public static HolidayDefinitionBuilder newBuilder() {
    return new HolidayDefinitionBuilder();
  }

  private final List<HolidayDefinitionRule> rules;

  HolidayDefinition(List<HolidayDefinitionRule> rules) {
    this.rules = Collections.unmodifiableList(new ArrayList<>(rules));
  }

  public Optional<Holiday> maybeHolidayForYear(int year) {
    return rules.stream()
        .filter(entry -> entry.isDefinedForYear(year))
        .findFirst()
        .map(entry -> entry.holidayInstanceForYear(year));
  }
}
