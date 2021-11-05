package pl.marcinchwedczuk.polishholidays;

import static pl.marcinchwedczuk.polishholidays.HolidayType.*;

import java.util.Arrays;
import java.util.List;

class PolishHolidaysLibrary {
  private final HolidayDefinition newYear =
      HolidayDefinition.newBuilder()
          .usesAlgorithm(fixedAtMonthDay(1, 1))
          .withEnglishName("New Year's Day")
          .withPolishName("Nowy Rok")
          .defineRule(
              HolidayDefinitionRule.newBuilder()
                  .withHolidayType(OTHER)
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
          .usesAlgorithm(fixedAtMonthDay(1, 6))
          .withEnglishName("Epiphany")
          .withPolishName("Święto Trzech Króli")
          .defineRule(HolidayDefinitionRule.newBuilder()
              .withHolidayType(RELIGIOUS)
              .validUntilYearExcluding(2011)
              .build())
          .defineRule(HolidayDefinitionRule.newBuilder()
              .withHolidayType(RELIGIOUS)
              .validFromYearIncluding(2011)
              .markAsPublicHoliday()
              .build())
          .build();

  private final HolidayDefinition valentinesDay =
      HolidayDefinition.newBuilder()
          .usesAlgorithm(fixedAtMonthDay(2, 14))
          .withEnglishName("Valentine's Day")
          .withPolishName("Walentynki")
          .defineRule(
              HolidayDefinitionRule.newBuilder()
                  // I don't think it's in religious category in Poland
                  .withHolidayType(OTHER)
                  .build())
          .build();

  private final HolidayDefinition goodFriday =
      HolidayDefinition.newBuilder()
          .usesAlgorithm(relativeToEaster(-2))
          .withEnglishName("Good Friday")
          .withPolishName("Wielki Piątek")
          .defineRule(
              HolidayDefinitionRule.newBuilder()
                  .withHolidayType(RELIGIOUS)
                  .build())
          .build();

  private final HolidayDefinition holySaturday =
      HolidayDefinition.newBuilder()
          .usesAlgorithm(relativeToEaster(-1))
          .withEnglishName("Holy Saturday")
          .withPolishName("Wielka Sobota")
          .defineRule(
              HolidayDefinitionRule.newBuilder()
                  .withHolidayType(RELIGIOUS)
                  .build())
          .build();

  private final HolidayDefinition easter =
      HolidayDefinition.newBuilder()
          .usesAlgorithm(new EasterDateHolidayDateAlgorithm())
          .withEnglishName("Easter")
          .withPolishName("Wielkanoc")
          .defineRule(
              HolidayDefinitionRule.newBuilder()
                  .withHolidayType(RELIGIOUS)
                  .markAsPublicHoliday()
                  .build())
          .build();

  private final HolidayDefinition easterMonday =
      HolidayDefinition.newBuilder()
          .usesAlgorithm(relativeToEaster(1))
          .withEnglishName("Easter Monday")
          .withPolishName("Poniedziałek Wielkanocny")
          .defineRule(
              HolidayDefinitionRule.newBuilder()
                  .withHolidayType(RELIGIOUS)
                  .markAsPublicHoliday()
                  .build())
          .build();

  private final HolidayDefinition labourDay =
      HolidayDefinition.newBuilder()
          .usesAlgorithm(fixedAtMonthDay(5, 1))
          .withEnglishName("Labour Day")
          .withPolishName("Święto Pracy")
          .defineRule(
              HolidayDefinitionRule.newBuilder()
                  .withHolidayType(OTHER)
                  .markAsPublicHoliday()
                  .build())
          .build();

  private final HolidayDefinition flagDay =
      HolidayDefinition.newBuilder()
          .usesAlgorithm(fixedAtMonthDay(5, 2))
          .withEnglishName("Polish National Flag Day")
          .withPolishName("Dzień Flagi Rzeczypospolitej Polskiej")
          .defineRule(
              HolidayDefinitionRule.newBuilder()
                  .withHolidayType(NATIONAL)
                  .build())
          .build();

  private final HolidayDefinition constitutionDay =
      HolidayDefinition.newBuilder()
          .usesAlgorithm(fixedAtMonthDay(5, 3))
          .withEnglishName("Constitution Day")
          .withPolishName("Święto Konstytucji Trzeciego Maja")
          .defineRule(
              HolidayDefinitionRule.newBuilder()
                  .withHolidayType(NATIONAL)
                  .markAsPublicHoliday()
                  .build())
          .build();

  private final HolidayDefinition whiteSunday =
      HolidayDefinition.newBuilder()
          .usesAlgorithm(relativeToEaster(49))
          .withEnglishName("White Sunday")
          .withPolishName("Zielone Świątki")
          .defineRule(
              HolidayDefinitionRule.newBuilder()
                  .withHolidayType(RELIGIOUS)
                  .markAsPublicHoliday()
                  .build())
          .build();

  private final HolidayDefinition mothersDay =
      HolidayDefinition.newBuilder()
          .usesAlgorithm(fixedAtMonthDay(5, 26))
          .withEnglishName("Mother's Day")
          .withPolishName("Dzień Matki")
          .defineRule(
              HolidayDefinitionRule.newBuilder()
                  .withHolidayType(UNOFFICIAL)
                  .build())
          .build();

  private final HolidayDefinition childrensDay =
      HolidayDefinition.newBuilder()
          .usesAlgorithm(fixedAtMonthDay(6, 1))
          .withEnglishName("Children's Day")
          .withPolishName("Dzień Dziecka")
          .defineRule(
              HolidayDefinitionRule.newBuilder()
                  .withHolidayType(UNOFFICIAL)
                  .build())
          .build();

  private final HolidayDefinition corpusChristi =
      HolidayDefinition.newBuilder()
          .usesAlgorithm(relativeToEaster(60))
          .withEnglishName("Feast of Corpus Christi")
          .withPolishName("Boże Ciało")
          .defineRule(
              HolidayDefinitionRule.newBuilder()
                  .withHolidayType(RELIGIOUS)
                  .markAsPublicHoliday()
                  .build())
          .build();

  private final HolidayDefinition fathersDay =
      HolidayDefinition.newBuilder()
          .usesAlgorithm(fixedAtMonthDay(6, 23))
          .withEnglishName("Father's Day")
          .withPolishName("Dzień Ojca")
          .defineRule(
              HolidayDefinitionRule.newBuilder()
                  .withHolidayType(UNOFFICIAL)
                  .build())
          .build();

  private final HolidayDefinition assumptionOfMary =
      HolidayDefinition.newBuilder()
          .usesAlgorithm(fixedAtMonthDay(8, 15))
          .withEnglishName("Assumption of Mary")
          .withPolishName("Wniebowzięcie Najświętszej Maryi Panny")
          .defineRule(
              HolidayDefinitionRule.newBuilder()

                  .withHolidayType(RELIGIOUS)
                  .markAsPublicHoliday()
                  .build())
          .build();

  private final HolidayDefinition allSaintsDay =
      HolidayDefinition.newBuilder()
          .usesAlgorithm(fixedAtMonthDay(11, 1))
          .withEnglishName("All Saints' Day")
          .withPolishName("Wszystkich Świętych")
          .defineRule(
              HolidayDefinitionRule.newBuilder()
                  .withHolidayType(RELIGIOUS)
                  .markAsPublicHoliday()
                  .build())
          .build();

  private final HolidayDefinition independenceDay =
      HolidayDefinition.newBuilder()
          .usesAlgorithm(fixedAtMonthDay(11, 11))
          .withEnglishName("National Independence Day")
          .withPolishName("Dzień Niepodległości")
          .defineRule(
              HolidayDefinitionRule.newBuilder()
                  .withHolidayType(NATIONAL)
                  .markAsPublicHoliday()
                  .build())
          .build();

  private final HolidayDefinition christmasEve =
      HolidayDefinition.newBuilder()
          .usesAlgorithm(fixedAtMonthDay(12, 24))
          .withEnglishName("Christmas Eve")
          .withPolishName("Wigilia Bożego Narodzenia")
          .defineRule(
              HolidayDefinitionRule.newBuilder()
                  .withHolidayType(RELIGIOUS)
                  .build())
          .build();

  private final HolidayDefinition christmas =
      HolidayDefinition.newBuilder()
          .usesAlgorithm(fixedAtMonthDay(12, 25))
          .withEnglishName("Christmas")
          .withPolishName("Boże Narodzenie")
          .defineRule(
              HolidayDefinitionRule.newBuilder()
                  .withHolidayType(RELIGIOUS)
                  .markAsPublicHoliday()
                  .build())
          .build();

  private final HolidayDefinition christmasSecondDay =
      HolidayDefinition.newBuilder()
          .usesAlgorithm(fixedAtMonthDay(12, 26))
          .withEnglishName("Christmas")
          .withPolishName("Boże Narodzenie")
          .defineRule(
              HolidayDefinitionRule.newBuilder()

                  .withHolidayType(RELIGIOUS)
                  .markAsPublicHoliday()
                  .build())
          .build();

  private final HolidayDefinition newYearsEve =
      HolidayDefinition.newBuilder()
          .usesAlgorithm(fixedAtMonthDay(12, 31))
          .withEnglishName("New Year's Eve")
          .withPolishName("Sylwester")
          .defineRule(
              HolidayDefinitionRule.newBuilder()
                  .withHolidayType(UNOFFICIAL)
                  .build())
          .build();

  public List<HolidayDefinition> holidaysDefinitions() {
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

  public int validSinceYearIncluding() {
    return 2000;
  }

  private static HolidayDateAlgorithm fixedAtMonthDay(int month, int day) {
    return new FixedDateHolidayDateAlgorithm(month, day);
  }

  private static HolidayDateAlgorithm relativeToEaster(int offsetDays) {
    return new RelativeDateHolidayDateAlgorithm(
        new EasterDateHolidayDateAlgorithm(), offsetDays);
  }
}
