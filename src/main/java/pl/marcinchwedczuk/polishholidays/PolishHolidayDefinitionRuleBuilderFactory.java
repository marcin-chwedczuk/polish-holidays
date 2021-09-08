package pl.marcinchwedczuk.polishholidays;

@FunctionalInterface
interface PolishHolidayDefinitionRuleBuilderFactory {
    PolishHolidayDefinitionRuleBuilder newRule();
}
