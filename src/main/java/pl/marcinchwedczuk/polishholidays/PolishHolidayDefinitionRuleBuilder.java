package pl.marcinchwedczuk.polishholidays;

import java.util.List;
import java.util.Optional;

class PolishHolidayDefinitionRuleBuilder {
    static PolishHolidayDefinitionRuleBuilder builder(List<PolishHolidayDefinitionRule> entries) {
        return new PolishHolidayDefinitionRuleBuilder(entries);
    }

    private final List<PolishHolidayDefinitionRule> entries;

    private final Integer validFromYearIncluding;
    private final Integer validToYearExcluding;

    private final HolidayDateAlgorithm holidayDateAlgorithm;

    private final String englishName;
    private final String polishName;
    private final PolishHolidayType type;
    private final boolean publicHoliday;

    private PolishHolidayDefinitionRuleBuilder(List<PolishHolidayDefinitionRule> entries,
                                       Integer validFromYearIncluding,
                                       Integer validToYearExcluding,
                                       HolidayDateAlgorithm holidayDateAlgorithm,
                                       String englishName,
                                       String polishName,
                                       PolishHolidayType type,
                                       boolean publicHoliday) {
        this.entries = entries;
        this.validFromYearIncluding = validFromYearIncluding;
        this.validToYearExcluding = validToYearExcluding;
        this.holidayDateAlgorithm = holidayDateAlgorithm;
        this.englishName = englishName;
        this.polishName = polishName;
        this.type = type;
        this.publicHoliday = publicHoliday;
    }

    private PolishHolidayDefinitionRuleBuilder(List<PolishHolidayDefinitionRule> entries) {
        this(entries, null, null, null, null, null, null, false);
    }

    public PolishHolidayDefinitionRuleBuilder validFromYearIncluding(int year) {
        return new PolishHolidayDefinitionRuleBuilder(
                entries,
                year,
                validToYearExcluding,
                holidayDateAlgorithm,
                englishName,
                polishName,
                type,
                publicHoliday);
    }

    public PolishHolidayDefinitionRuleBuilder validUntilYearExcluding(int year) {
        return new PolishHolidayDefinitionRuleBuilder(
                entries,
                validFromYearIncluding,
                year,
                holidayDateAlgorithm,
                englishName,
                polishName,
                type,
                publicHoliday);
    }

    public PolishHolidayDefinitionRuleBuilder usesAlgorithm(HolidayDateAlgorithm holidayDateAlgorithm) {
        return new PolishHolidayDefinitionRuleBuilder(
                entries,
                validFromYearIncluding,
                validToYearExcluding,
                holidayDateAlgorithm,
                englishName,
                polishName,
                type,
                publicHoliday);
    }

    public PolishHolidayDefinitionRuleBuilder withEnglishName(String name) {
        return new PolishHolidayDefinitionRuleBuilder(
                entries,
                validFromYearIncluding,
                validToYearExcluding,
                holidayDateAlgorithm,
                name,
                polishName,
                type,
                publicHoliday);
    }

    public PolishHolidayDefinitionRuleBuilder withPolishName(String name) {
        return new PolishHolidayDefinitionRuleBuilder(
                entries,
                validFromYearIncluding,
                validToYearExcluding,
                holidayDateAlgorithm,
                englishName,
                name,
                type,
                publicHoliday);
    }

    public PolishHolidayDefinitionRuleBuilder withType(PolishHolidayType type) {
        return new PolishHolidayDefinitionRuleBuilder(
                entries,
                validFromYearIncluding,
                validToYearExcluding,
                holidayDateAlgorithm,
                englishName,
                polishName,
                type,
                publicHoliday);
    }

    public PolishHolidayDefinitionRuleBuilder markAsPublicHoliday() {
        return new PolishHolidayDefinitionRuleBuilder(
                entries,
                validFromYearIncluding,
                validToYearExcluding,
                holidayDateAlgorithm,
                englishName,
                polishName,
                type,
                true);
    }

    public void add() {
        PolishHolidayDefinitionRule entry = new PolishHolidayDefinitionRule(
                Optional.ofNullable(validFromYearIncluding),
                Optional.ofNullable(validToYearExcluding),
                holidayDateAlgorithm,
                englishName,
                polishName,
                type,
                publicHoliday);

        entries.add(entry);
    }
}
