package pl.marcinchwedczuk.polishholidays;

import java.util.List;
import java.util.Optional;

class PolishHolidayDefinitionRuleBuilder {
    static PolishHolidayDefinitionRuleBuilder builder(List<PolishHolidayDefinitionRule> entries) {
        return new PolishHolidayDefinitionRuleBuilder(entries);
    }

    private final List<PolishHolidayDefinitionRule> entries;

    private Optional<Integer> validFromYearIncluding = Optional.empty();
    private Optional<Integer> validToYearExcluding = Optional.empty();

    private HolidayDateAlgorithm holidayDateAlgorithm;

    private String englishName;
    private String polishName;
    private PolishHolidayType type;
    private boolean publicHoliday;

    private PolishHolidayDefinitionRuleBuilder(List<PolishHolidayDefinitionRule> entries) {
        this.entries = entries;
    }

    public PolishHolidayDefinitionRuleBuilder validStartingWithYear(int year) {
        this.validFromYearIncluding = Optional.of(year);
        return this;
    }

    public PolishHolidayDefinitionRuleBuilder validUntilYear(int year) {
        this.validToYearExcluding = Optional.of(year);
        return this;
    }

    public PolishHolidayDefinitionRuleBuilder usesAlgorithm(HolidayDateAlgorithm holidayDateAlgorithm) {
        this.holidayDateAlgorithm = holidayDateAlgorithm;
        return this;
    }

    public PolishHolidayDefinitionRuleBuilder withEnglishName(String name) {
        this.englishName = name;
        return this;
    }

    public PolishHolidayDefinitionRuleBuilder withPolishName(String name) {
        this.polishName = name;
        return this;
    }

    public PolishHolidayDefinitionRuleBuilder withType(PolishHolidayType type) {
        this.type = type;
        return this;
    }

    public PolishHolidayDefinitionRuleBuilder markAsPublicHoliday() {
        this.publicHoliday = true;
        return this;
    }

    public void add() {
        PolishHolidayDefinitionRule entry = new PolishHolidayDefinitionRule(
                validFromYearIncluding,
                validToYearExcluding,
                holidayDateAlgorithm,
                englishName,
                polishName,
                type,
                publicHoliday);

        entries.add(entry);
    }
}
