package pl.marcinchwedczuk.polishholidays;

import java.time.LocalDate;
import java.util.*;
import java.util.function.Consumer;

class PolishHolidayDefinition {
    public static PolishHolidayDefinition create(
            Consumer<PolishHolidayDefinitionRuleBuilderFactory> configureRules) {
        List<PolishHolidayDefinitionRule> entries = new ArrayList<>();

        configureRules.accept(() -> PolishHolidayDefinitionRuleBuilder.builder(entries));
        if (entries.isEmpty()) {
            throw new IllegalArgumentException("Cannot create definition without entries.");
        }

        return new PolishHolidayDefinition(Collections.unmodifiableList(entries));
    }

    private final List<PolishHolidayDefinitionRule> entries;

    private PolishHolidayDefinition(List<PolishHolidayDefinitionRule> entries) {
        this.entries = entries;
    }

    public Optional<PolishHoliday> holidayForDate(LocalDate date) {
        throw new RuntimeException("not impl");
    }

    public Optional<PolishHoliday> maybeHolidayForYear(int year) {
        return entries.stream()
            .filter(entry -> entry.isDefinedForYear(year))
            .findFirst()
            .map(entry -> entry.holidayInstanceForYear(year));
    }
}
