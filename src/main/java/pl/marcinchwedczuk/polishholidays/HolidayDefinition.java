package pl.marcinchwedczuk.polishholidays;

import java.util.*;

import static java.util.Objects.requireNonNull;
import static pl.marcinchwedczuk.polishholidays.ArgumentChecks.checkNonBlankString;

public class HolidayDefinition {
  public static HolidayDefinitionBuilder newBuilder() {
    return new HolidayDefinitionBuilder();
  }

  private final HolidayDateAlgorithm holidayDateAlgorithm;

  private final String holidayEnglishName;
  private final String hoidayPolishName;

  private final List<HolidayDefinitionRule> rules;

  HolidayDefinition(
      HolidayDateAlgorithm holidayDateAlgorithm,
      String holidayEnglishName,
      String hoidayPolishName,
      List<HolidayDefinitionRule> rules) {
    requireNonNull(holidayDateAlgorithm);
    checkNonBlankString(holidayEnglishName, "English name cannot be empty.");
    checkNonBlankString(hoidayPolishName, "Polish name cannot be empty");

    // TODO: Add arg checker for this
    if (rules.isEmpty()) {
      throw new IllegalArgumentException(
          "Cannot create definition without entries.");
    }

    // TODO: Check rules not overlapping

    // TODO: Other checks
    this.holidayDateAlgorithm = holidayDateAlgorithm;
    this.holidayEnglishName = holidayEnglishName;
    this.hoidayPolishName = hoidayPolishName;
    this.rules = rules;
  }

  public HolidayDateAlgorithm holidayDateAlgorithm() {
    return this.holidayDateAlgorithm;
  }

  public String holidayEnglishName() {
    return this.holidayEnglishName;
  }

  public String holidayPolishName() {
    return this.hoidayPolishName;
  }

  public Collection<HolidayDefinitionRule> rules() {
    return Collections.unmodifiableList(rules);
  }

  public Optional<Holiday> maybeHolidayForYear(int year) {
    return rules.stream()
        .filter(rule -> rule.isInEffectForYear(year))
        .findFirst()
        .map(rule -> new Holiday(
            holidayDateAlgorithm.holidayDateForYear(year),
            holidayEnglishName,
            hoidayPolishName,
            rule.holidayType(),
            rule.isPublicHoliday()));
  }
}
