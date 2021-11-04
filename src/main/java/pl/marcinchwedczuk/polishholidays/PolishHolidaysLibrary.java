package pl.marcinchwedczuk.polishholidays;

import static pl.marcinchwedczuk.polishholidays.HolidayType.*;

import java.util.Arrays;
import java.util.List;

class PolishHolidaysLibrary {
  private final HolidayDefinition newYear =
      HolidayDefinition.newBuilder()
          .defineRule(
              HolidayDefinitionRule.newBuilder()
                  .usesAlgorithm(fixedAtMonthDay(1, 1))
                  .withEnglishName("New Year's Day")
                  .withPolishName("Nowy Rok")
                  .withType(OTHER)
                  .markAsPublicHoliday()
                  .build())
          .build();

  /*
   * Wiki: https://pl.wikipedia.org/wiki/Objawienie_Pa%C5%84skie W powojennej Polsce było dniem wolnym
   * od pracy do 16 listopada 1960. Zostało zniesione ustawą Sejmu PRL[4]. We wrześniu 2010 rozpoczęto
   * prace legislacyjne nad ponownym przywróceniem dnia wolnego. Odpowiednią ustawę uchwalił Sejm RP
   * przy okazji zmiany Kodeksu pracy[5]. Ustawa została podpisana[6] przez prezydenta Bronisława
   * Komorowskiego 19 listopada 2010 i ogłoszona w Dzienniku Ustaw 26 listopada 2010[7]. 6 stycznia
   * 2011 był pierwszym od 1960 dniem Objawienia Pańskiego wolnym od pracy.
   */
  private final HolidayDefinition epiphany =
      HolidayDefinition.newBuilder()
          .defineRule(HolidayDefinitionRule.newBuilder()
              .usesAlgorithm(fixedAtMonthDay(1, 6))
              .withEnglishName("Epiphany")
              .withPolishName("Święto Trzech Króli")
              .withType(RELIGIOUS)
              .validUntilYearExcluding(2011)
              .build())
          .defineRule(HolidayDefinitionRule.newBuilder()
              .usesAlgorithm(fixedAtMonthDay(1, 6))
              .withEnglishName("Epiphany")
              .withPolishName("Święto Trzech Króli")
              .withType(RELIGIOUS)
              .validFromYearIncluding(2011)
              .markAsPublicHoliday()
              .build())
          .build();

  private final HolidayDefinition valentinesDay =
      HolidayDefinition.newBuilder()
          .defineRule(
              HolidayDefinitionRule.newBuilder()
                  .usesAlgorithm(fixedAtMonthDay(2, 14))
                  .withEnglishName("Valentine's Day")
                  .withPolishName("Walentynki")
                  // I don't think it's in religious category in Poland
                  .withType(OTHER)
                  .build())
          .build();

  private final HolidayDefinition goodFriday =
      HolidayDefinition.newBuilder()
          .defineRule(
              HolidayDefinitionRule.newBuilder()
                  .usesAlgorithm(relativeToEaster(-2))
                  .withEnglishName("Good Friday")
                  .withPolishName("Wielki Piątek")
                  .withType(RELIGIOUS)
                  .build())
          .build();

  private final HolidayDefinition holySaturday =
      HolidayDefinition.newBuilder()
          .defineRule(
              HolidayDefinitionRule.newBuilder()
                  .usesAlgorithm(relativeToEaster(-1))
                  .withEnglishName("Holy Saturday")
                  .withPolishName("Wielka Sobota")
                  .withType(RELIGIOUS)
                  .build())
          .build();

  private final HolidayDefinition easter =
      HolidayDefinition.newBuilder()
          .defineRule(
              HolidayDefinitionRule.newBuilder()
                  .usesAlgorithm(new EasterDateHolidayDateAlgorithm())
                  .withEnglishName("Easter")
                  .withPolishName("Wielkanoc")
                  .withType(RELIGIOUS)
                  .markAsPublicHoliday()
                  .build())
          .build();

  private final HolidayDefinition easterMonday =
      HolidayDefinition.newBuilder()
          .defineRule(
              HolidayDefinitionRule.newBuilder()
                  .usesAlgorithm(relativeToEaster(1))
                  .withEnglishName("Easter Monday")
                  .withPolishName("Poniedziałek Wielkanocny")
                  .withType(RELIGIOUS)
                  .markAsPublicHoliday()
                  .build())
          .build();

  private final HolidayDefinition labourDay =
      HolidayDefinition.newBuilder()
          .defineRule(
              HolidayDefinitionRule.newBuilder()
                  .usesAlgorithm(fixedAtMonthDay(5, 1))
                  .withEnglishName("Labour Day")
                  .withPolishName("Święto Pracy")
                  .withType(OTHER)
                  .markAsPublicHoliday()
                  .build())
          .build();

  private final HolidayDefinition flagDay =
      HolidayDefinition.newBuilder()
          .defineRule(
              HolidayDefinitionRule.newBuilder()
                  .usesAlgorithm(fixedAtMonthDay(5, 2))
                  .withEnglishName("Polish National Flag Day")
                  .withPolishName("Dzień Flagi Rzeczypospolitej Polskiej")
                  .withType(NATIONAL)
                  .build())
          .build();

  private final HolidayDefinition constitutionDay =
      HolidayDefinition.newBuilder()
          .defineRule(
              HolidayDefinitionRule.newBuilder()
                  .usesAlgorithm(fixedAtMonthDay(5, 3))
                  .withEnglishName("Constitution Day")
                  .withPolishName("Święto Konstytucji Trzeciego Maja")
                  .withType(NATIONAL)
                  .markAsPublicHoliday()
                  .build())
          .build();

  private final HolidayDefinition whiteSunday =
      HolidayDefinition.newBuilder()
          .defineRule(
              HolidayDefinitionRule.newBuilder()
                  .usesAlgorithm(relativeToEaster(49))
                  .withEnglishName("White Sunday")
                  .withPolishName("Zielone Świątki")
                  .withType(RELIGIOUS)
                  .markAsPublicHoliday()
                  .build())
          .build();

  private final HolidayDefinition mothersDay =
      HolidayDefinition.newBuilder()
          .defineRule(
              HolidayDefinitionRule.newBuilder()
                  .usesAlgorithm(fixedAtMonthDay(5, 26))
                  .withEnglishName("Mother's Day")
                  .withPolishName("Dzień Matki")
                  .withType(UNOFFICIAL)
                  .build())
          .build();

  private final HolidayDefinition childrensDay =
      HolidayDefinition.newBuilder()
          .defineRule(
              HolidayDefinitionRule.newBuilder()
                  .usesAlgorithm(fixedAtMonthDay(6, 1))
                  .withEnglishName("Children's Day")
                  .withPolishName("Dzień Dziecka")
                  .withType(UNOFFICIAL)
                  .build())
          .build();

  private final HolidayDefinition corpusChristi =
      HolidayDefinition.newBuilder()
          .defineRule(
              HolidayDefinitionRule.newBuilder()
                  .usesAlgorithm(relativeToEaster(60))
                  .withEnglishName("Feast of Corpus Christi")
                  .withPolishName("Boże Ciało")
                  .withType(RELIGIOUS)
                  .markAsPublicHoliday()
                  .build())
          .build();

  private final HolidayDefinition fathersDay =
      HolidayDefinition.newBuilder()
          .defineRule(
              HolidayDefinitionRule.newBuilder()
                  .usesAlgorithm(fixedAtMonthDay(6, 23))
                  .withEnglishName("Father's Day")
                  .withPolishName("Dzień Ojca")
                  .withType(UNOFFICIAL)
                  .build())
          .build();

  private final HolidayDefinition assumptionOfMary =
      HolidayDefinition.newBuilder()
          .defineRule(
              HolidayDefinitionRule.newBuilder()
                  .usesAlgorithm(fixedAtMonthDay(8, 15))
                  .withEnglishName("Assumption of Mary")
                  .withPolishName("Wniebowzięcie Najświętszej Maryi Panny")
                  .withType(RELIGIOUS)
                  .markAsPublicHoliday()
                  .build())
          .build();

  private final HolidayDefinition allSaintsDay =
      HolidayDefinition.newBuilder()
          .defineRule(
              HolidayDefinitionRule.newBuilder()
                  .usesAlgorithm(fixedAtMonthDay(11, 1))
                  .withEnglishName("All Saints' Day")
                  .withPolishName("Wszystkich Świętych").withType(RELIGIOUS)
                  .markAsPublicHoliday()
                  .build())
          .build();

  private final HolidayDefinition independenceDay =
      HolidayDefinition.newBuilder()
          .defineRule(
              HolidayDefinitionRule.newBuilder()
                  .usesAlgorithm(fixedAtMonthDay(11, 11))
                  .withEnglishName("National Independence Day")
                  .withPolishName("Dzień Niepodległości")
                  .withType(NATIONAL)
                  .markAsPublicHoliday()
                  .build())
          .build();

  private final HolidayDefinition christmasEve =
      HolidayDefinition.newBuilder()
          .defineRule(
              HolidayDefinitionRule.newBuilder()
                  .usesAlgorithm(fixedAtMonthDay(12, 24))
                  .withEnglishName("Christmas Eve")
                  .withPolishName("Wigilia Bożego Narodzenia")
                  .withType(RELIGIOUS)
                  .build())
          .build();

  private final HolidayDefinition christmas =
      HolidayDefinition.newBuilder()
          .defineRule(
              HolidayDefinitionRule.newBuilder()
                  .usesAlgorithm(fixedAtMonthDay(12, 25))
                  .withEnglishName("Christmas")
                  .withPolishName("Boże Narodzenie")
                  .withType(RELIGIOUS)
                  .markAsPublicHoliday()
                  .build())
          .build();

  private final HolidayDefinition christmasSecondDay =
      HolidayDefinition.newBuilder()
          .defineRule(
              HolidayDefinitionRule.newBuilder()
                  .usesAlgorithm(fixedAtMonthDay(12, 26))
                  .withEnglishName("Christmas")
                  .withPolishName("Boże Narodzenie")
                  .withType(RELIGIOUS)
                  .markAsPublicHoliday()
                  .build())
          .build();

  private final HolidayDefinition newYearsEve =
      HolidayDefinition.newBuilder()
          .defineRule(
              HolidayDefinitionRule.newBuilder()
                  .usesAlgorithm(fixedAtMonthDay(12, 31))
                  .withEnglishName("New Year's Eve")
                  .withPolishName("Sylwester")
                  .withType(UNOFFICIAL)
                  .build())
          .build();

  public List<HolidayDefinition> defaultHolidaysDefinitions() {
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
        childrensDay,
        corpusChristi,
        fathersDay,
        assumptionOfMary,
        allSaintsDay,
        independenceDay,
        christmasEve,
        christmas,
        christmasSecondDay,
        newYearsEve);
  }

  private static HolidayDateAlgorithm fixedAtMonthDay(int month, int day) {
    return new FixedDateHolidayDateAlgorithm(month, day);
  }

  private static HolidayDateAlgorithm relativeToEaster(int offsetDays) {
    return new RelativeDateHolidayDateAlgorithm(
        new EasterDateHolidayDateAlgorithm(), offsetDays);
  }
}
