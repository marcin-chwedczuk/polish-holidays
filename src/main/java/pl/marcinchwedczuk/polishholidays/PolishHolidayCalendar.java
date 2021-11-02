package pl.marcinchwedczuk.polishholidays;

import javax.print.attribute.standard.MediaSize;
import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static pl.marcinchwedczuk.polishholidays.PolishHolidayType.*;

/**
 * The earliest supported year is 2000.
 */
class PolishHolidayCalendar {
    private final PolishHolidayDefinition newYear = PolishHolidayDefinition.create(rules -> {
        rules.newRule()
                .usesAlgorithm(fixedAtMonthDay(1, 1))
                .withEnglishName("New Year's Day")
                .withPolishName("Nowy Rok")
                .withType(OTHER)
                .markAsPublicHoliday()
                .add();
    });

    private final PolishHolidayDefinition epiphany = PolishHolidayDefinition.create(rules -> {
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
        ruleTemplate.validUntilYearExcluding(2011)
                .add();

        ruleTemplate.validFromYearIncluding(2011)
                .markAsPublicHoliday()
                .add();
    });

    private final PolishHolidayDefinition valentinesDay = PolishHolidayDefinition.create(rules -> {
        rules.newRule()
                .usesAlgorithm(fixedAtMonthDay(2, 14))
                .withEnglishName("Valentine's Day")
                .withPolishName("Walentynki")
                // I don't think it's in religious category in Poland
                .withType(OTHER)
                .add();
    });

    private final PolishHolidayDefinition goodFriday = PolishHolidayDefinition.create(rules -> {
       rules.newRule()
           .usesAlgorithm(relativeToEaster(-2))
           .withEnglishName("Good Friday")
           .withPolishName("Wielki Piątek")
           .withType(RELIGIOUS)
           .add();
    });

    private final PolishHolidayDefinition holySaturday = PolishHolidayDefinition.create(rules -> {
        rules.newRule()
                .usesAlgorithm(relativeToEaster(-1))
                .withEnglishName("Holy Saturday")
                .withPolishName("Wielka Sobota")
                .withType(RELIGIOUS)
                .add();
    });

    private final PolishHolidayDefinition easter = PolishHolidayDefinition.create(rules -> {
        rules.newRule()
                .usesAlgorithm(new EasterDateHolidayDateAlgorithm())
                .withEnglishName("Easter")
                .withPolishName("Wielkanoc")
                .withType(RELIGIOUS)
                .markAsPublicHoliday()
                .add();
    });

    private final PolishHolidayDefinition easterMonday = PolishHolidayDefinition.create(rules -> {
        rules.newRule()
                .usesAlgorithm(relativeToEaster(1))
                .withEnglishName("Easter Monday")
                .withPolishName("Poniedziałek Wielkanocny")
                .withType(RELIGIOUS)
                .markAsPublicHoliday()
                .add();
    });

    private final PolishHolidayDefinition labourDay = PolishHolidayDefinition.create(rules -> {
        rules.newRule()
                .usesAlgorithm(fixedAtMonthDay(5, 1))
                .withEnglishName("Labour Day")
                .withPolishName("Święto Pracy")
                .withType(OTHER)
                .markAsPublicHoliday()
                .add();
    });

    private final PolishHolidayDefinition flagDay = PolishHolidayDefinition.create(rules -> {
        rules.newRule()
                .usesAlgorithm(fixedAtMonthDay(5, 2))
                .withEnglishName("Polish National Flag Day")
                .withPolishName("Dzień Flagi Rzeczypospolitej Polskiej")
                .withType(NATIONAL)
                .add();
    });

    private final PolishHolidayDefinition constitutionDay = PolishHolidayDefinition.create(rules -> {
        // Wiki: ustanowione w 1919[1] i ponownie w 1990[2]
        rules.newRule()
                .usesAlgorithm(fixedAtMonthDay(5, 3))
                .withEnglishName("Constitution Day")
                .withPolishName("Święto Konstytucji Trzeciego Maja")
                .withType(NATIONAL)
                .markAsPublicHoliday()
                .add();
    });

    private final PolishHolidayDefinition whiteSunday = PolishHolidayDefinition.create(rules -> {
        rules.newRule()
                .usesAlgorithm(relativeToEaster(49))
                .withEnglishName("White Sunday")
                .withPolishName("Zielone Świątki")
                .withType(RELIGIOUS)
                .markAsPublicHoliday()
                .add();
    });

    private final PolishHolidayDefinition mothersDay = PolishHolidayDefinition.create(rules -> {
        rules.newRule()
                .usesAlgorithm(fixedAtMonthDay(5, 16))
                .withEnglishName("Mother's Day")
                .withPolishName("Dzień Matki")
                .withType(UNOFFICIAL)
                .add();
    });

    private final PolishHolidayDefinition corpusChristi = PolishHolidayDefinition.create(rules -> {
        rules.newRule()
                .usesAlgorithm(relativeToEaster(60))
                .withEnglishName("Boże Ciało")
                .withPolishName("Feast of Corpus Christi")
                .withType(RELIGIOUS)
                .markAsPublicHoliday()
                .add();
    });

    private final PolishHolidayDefinition fathersDay = PolishHolidayDefinition.create(rules -> {
        rules.newRule()
                .usesAlgorithm(fixedAtMonthDay(6, 23))
                .withEnglishName("Father's Day")
                .withPolishName("Dzień Ojca")
                .withType(UNOFFICIAL)
                .add();
    });

    private final PolishHolidayDefinition assumptionOfMary = PolishHolidayDefinition.create(rules -> {
        rules.newRule()
                .usesAlgorithm(fixedAtMonthDay(8, 15))
                .withEnglishName("Assumption of Mary")
                .withPolishName("Wniebowzięcie Najświętszej Maryi Panny")
                .withType(RELIGIOUS)
                .markAsPublicHoliday()
                .add();
    });

    private final PolishHolidayDefinition allSaintsDay = PolishHolidayDefinition.create(rules -> {
        rules.newRule()
                .usesAlgorithm(fixedAtMonthDay(11, 1))
                .withEnglishName("All Saints' Day")
                .withPolishName("Wszystkich Świętych")
                .withType(RELIGIOUS)
                .markAsPublicHoliday()
                .add();
    });

    private final PolishHolidayDefinition independenceDay = PolishHolidayDefinition.create(rules -> {
        rules.newRule()
                .usesAlgorithm(fixedAtMonthDay(11, 11))
                .withEnglishName("National Independence Day")
                .withPolishName("Dzień Niepodległości")
                .withType(NATIONAL)
                .markAsPublicHoliday()
                .add();
    });

    private final PolishHolidayDefinition christmasEve = PolishHolidayDefinition.create(rules -> {
        rules.newRule()
                .usesAlgorithm(fixedAtMonthDay(12, 25))
                .withEnglishName("Christmas Eve")
                .withPolishName("Wigilia Bożego Narodzenia")
                .withType(RELIGIOUS)
                .markAsPublicHoliday()
                .add();
    });

    private final PolishHolidayDefinition christmas = PolishHolidayDefinition.create(rules -> {
        rules.newRule()
                .usesAlgorithm(fixedAtMonthDay(12, 25))
                .withEnglishName("Christmas")
                .withPolishName("Boże Narodzenie")
                .withType(RELIGIOUS)
                .markAsPublicHoliday()
                .add();
    });

    private final PolishHolidayDefinition christmas2 = PolishHolidayDefinition.create(rules -> {
        rules.newRule()
                .usesAlgorithm(fixedAtMonthDay(12, 26))
                .withEnglishName("Christmas")
                .withPolishName("Boże Narodzenie")
                .withType(RELIGIOUS)
                .markAsPublicHoliday()
                .add();
    });

    public List<PolishHolidayDefinition> holidayDefinitions() {
        return Arrays.asList(
                newYear,
                epiphany,
                valentinesDay,
                goodFriday,
                holySaturday,
                easter,
                easterMonday,
                labourDay,
                flagDay,
                constitutionDay,
                whiteSunday,
                mothersDay,
                corpusChristi,
                fathersDay,
                assumptionOfMary,
                allSaintsDay,
                independenceDay,
                christmasEve,
                christmas,
                christmas2
        );
    }

    private static HolidayDateAlgorithm fixedAtMonthDay(int month, int day) {
        return new FixedDateHolidayDateAlgorithm(month, day);
    }

    private static HolidayDateAlgorithm relativeToEaster(int offsetDays) {
        return new RelativeHolidayDateAlgorithm(new EasterDateHolidayDateAlgorithm(), offsetDays);
    }

    public List<PolishHoliday> findHolidaysForYear(int year) {
        return holidayDefinitions().stream()
                .map(holidayDefinition ->
                        holidayDefinition.maybeHolidayForYear(year))
                .flatMap(Optionals::asStream)
                .collect(toList());
    }
}
