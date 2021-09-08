package pl.marcinchwedczuk.polishholidays;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static pl.marcinchwedczuk.polishholidays.PolishHolidayType.*;

/**
 * The earliest supported year is 2000.
 */
class PolishHolidayCalendar {
    public final PolishHolidayDefinition newYear = PolishHolidayDefinition.create(rules -> {
        rules.newRule()
                .usesAlgorithm(fixedAtMonthDay(1, 1))
                .withEnglishName("New Year's Day")
                .withPolishName("Nowy Rok")
                .withType(OTHER)
                .markAsPublicHoliday()
                .add();
    });

    public final PolishHolidayDefinition epiphany = PolishHolidayDefinition.create(rules -> {
        PolishHolidayDefinitionRuleBuilder ruleTemplate = rules.newRule()
                .usesAlgorithm(fixedAtMonthDay(1, 6))
                .withEnglishName("Epiphany")
                .withPolishName("Święto Trzech Króli")
                .withType(RELIGIOUS);

        /* Wiki: https://pl.wikipedia.org/wiki/Objawienie_Pa%C5%84skie
           W powojennej Polsce było dniem wolnym od pracy do 16 listopada 1960.
           Zostało zniesione ustawą Sejmu PRL[4].
           We wrześniu 2010 rozpoczęto prace legislacyjne nad
           ponownym przywróceniem dnia wolnego.
           Odpowiednią ustawę uchwalił Sejm RP przy okazji zmiany Kodeksu pracy[5].
           Ustawa została podpisana[6] przez prezydenta Bronisława Komorowskiego
           19 listopada 2010 i ogłoszona w Dzienniku Ustaw 26 listopada 2010[7].
           6 stycznia 2011 był pierwszym od 1960 dniem Objawienia Pańskiego wolnym
           od pracy.
         */
        ruleTemplate.validUntilYear(2011)
                .add();

        ruleTemplate.validStartingWithYear(2011)
                .markAsPublicHoliday()
                .add();
    });

    public final PolishHolidayDefinition valentinesDay = PolishHolidayDefinition.create(rules -> {
        rules.newRule()
                .usesAlgorithm(fixedAtMonthDay(2, 14))
                .withEnglishName("Valentine's Day")
                .withPolishName("Walentynki")
                // I don't think it's in religious category in Poland
                .withType(OTHER)
                .add();
    });

    public List<PolishHolidayDefinition> holidayDefinitions() {
        return Arrays.asList(
                newYear,
                epiphany
        );
    }

    private static HolidayDateAlgorithm fixedAtMonthDay(int month, int day) {
        return new FixedDateHolidayDateAlgorithm(month, day);
    }

    public List<PolishHoliday> findHolidaysForYear(int year) {
        return holidayDefinitions().stream()
                .map(holidayDefinition ->
                        holidayDefinition.maybeHolidayForYear(year))
                .flatMap(Optionals::asStream)
                .collect(toList());
    }
}
