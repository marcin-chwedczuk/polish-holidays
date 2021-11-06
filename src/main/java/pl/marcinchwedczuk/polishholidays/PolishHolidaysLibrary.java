package pl.marcinchwedczuk.polishholidays;

import static pl.marcinchwedczuk.polishholidays.EffectiveTimespan.fromYearOnward;
import static pl.marcinchwedczuk.polishholidays.HolidayType.*;
import static pl.marcinchwedczuk.polishholidays.HolidayDateAlgorithms.*;

import java.util.Arrays;
import java.util.List;

class PolishHolidaysLibrary {
  private final HolidayDefinition newYear =
      HolidayDefinition.newBuilder()
          .withDate(fixedAtMonthDay(1, 1))
          .withEnglishName("New Year's Day")
          .withPolishName("Nowy Rok")
          .withHolidayType(OTHER)
          .markAsPublicHoliday()
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
          .withDate(fixedAtMonthDay(1, 6))
          .withEnglishName("Epiphany")
          .withPolishName("Święto Trzech Króli")
          .withHolidayType(RELIGIOUS)
          .addOverride(HolidayDefinitionOverride.newBuilder()
              .withEffectiveTimespan(fromYearOnward(2011))
              .withOverride(definition -> definition.markAsPublicHoliday())
              .build())
          .build();

  private final HolidayDefinition valentinesDay =
      HolidayDefinition.newBuilder()
          .withDate(fixedAtMonthDay(2, 14))
          .withEnglishName("Valentine's Day")
          .withPolishName("Walentynki")
          .withHolidayType(OTHER)
          .build();

  private final HolidayDefinition goodFriday =
      HolidayDefinition.newBuilder()
          .withDate(relativeToEaster(-2))
          .withEnglishName("Good Friday")
          .withPolishName("Wielki Piątek")
          .withHolidayType(RELIGIOUS)
          .build();

  private final HolidayDefinition holySaturday =
      HolidayDefinition.newBuilder()
          .withDate(relativeToEaster(-1))
          .withEnglishName("Holy Saturday")
          .withPolishName("Wielka Sobota")
          .withHolidayType(RELIGIOUS)
          .build();

  // TODO: Rule valid from-to, overrides holiday attributes

  private final HolidayDefinition easter =
      HolidayDefinition.newBuilder()
          .withDate(new EasterDateHolidayDateAlgorithm())
          .withEnglishName("Easter")
          .withPolishName("Wielkanoc")
          .withHolidayType(RELIGIOUS)
          .markAsPublicHoliday()
          .build();

  private final HolidayDefinition easterMonday =
      HolidayDefinition.newBuilder()
          .withDate(relativeToEaster(1))
          .withEnglishName("Easter Monday")
          .withPolishName("Poniedziałek Wielkanocny")
          .withHolidayType(RELIGIOUS)
          .markAsPublicHoliday()
          .build();

  private final HolidayDefinition labourDay =
      HolidayDefinition.newBuilder()
          .withDate(fixedAtMonthDay(5, 1))
          .withEnglishName("Labour Day")
          .withPolishName("Święto Pracy")
          .withHolidayType(OTHER)
          .markAsPublicHoliday()
          .build();

  private final HolidayDefinition flagDay =
      HolidayDefinition.newBuilder()
          .withDate(fixedAtMonthDay(5, 2))
          .withEnglishName("Polish National Flag Day")
          .withPolishName("Dzień Flagi Rzeczypospolitej Polskiej")
          .withHolidayType(NATIONAL)
          .build();

  private final HolidayDefinition constitutionDay =
      HolidayDefinition.newBuilder()
          .withDate(fixedAtMonthDay(5, 3))
          .withEnglishName("Constitution Day")
          .withPolishName("Święto Konstytucji Trzeciego Maja")
          .withHolidayType(NATIONAL)
          .markAsPublicHoliday()
          .build();

  private final HolidayDefinition whiteSunday =
      HolidayDefinition.newBuilder()
          .withDate(relativeToEaster(49))
          .withEnglishName("White Sunday")
          .withPolishName("Zielone Świątki")
          .withHolidayType(RELIGIOUS)
          .markAsPublicHoliday()
          .build();

  private final HolidayDefinition mothersDay =
      HolidayDefinition.newBuilder()
          .withDate(fixedAtMonthDay(5, 26))
          .withEnglishName("Mother's Day")
          .withPolishName("Dzień Matki")
          .withHolidayType(UNOFFICIAL)
          .build();

  private final HolidayDefinition childrensDay =
      HolidayDefinition.newBuilder()
          .withDate(fixedAtMonthDay(6, 1))
          .withEnglishName("Children's Day")
          .withPolishName("Dzień Dziecka")
          .withHolidayType(UNOFFICIAL)
          .build();

  private final HolidayDefinition corpusChristi =
      HolidayDefinition.newBuilder()
          .withDate(relativeToEaster(60))
          .withEnglishName("Feast of Corpus Christi")
          .withPolishName("Boże Ciało")
          .withHolidayType(RELIGIOUS)
          .markAsPublicHoliday()
          .build();

  private final HolidayDefinition fathersDay =
      HolidayDefinition.newBuilder()
          .withDate(fixedAtMonthDay(6, 23))
          .withEnglishName("Father's Day")
          .withPolishName("Dzień Ojca")
          .withHolidayType(UNOFFICIAL)
          .build();

  private final HolidayDefinition assumptionOfMary =
      HolidayDefinition.newBuilder()
          .withDate(fixedAtMonthDay(8, 15))
          .withEnglishName("Assumption of Mary")
          .withPolishName("Wniebowzięcie Najświętszej Maryi Panny")
          .withHolidayType(RELIGIOUS)
          .markAsPublicHoliday()
          .build();

  private final HolidayDefinition allSaintsDay =
      HolidayDefinition.newBuilder()
          .withDate(fixedAtMonthDay(11, 1))
          .withEnglishName("All Saints' Day")
          .withPolishName("Wszystkich Świętych")
          .withHolidayType(RELIGIOUS)
          .markAsPublicHoliday()
          .build();

  private final HolidayDefinition independenceDay =
      HolidayDefinition.newBuilder()
          .withDate(fixedAtMonthDay(11, 11))
          .withEnglishName("National Independence Day")
          .withPolishName("Dzień Niepodległości")
          .withHolidayType(NATIONAL)
          .markAsPublicHoliday()
          .build();

  private final HolidayDefinition christmasEve =
      HolidayDefinition.newBuilder()
          .withDate(fixedAtMonthDay(12, 24))
          .withEnglishName("Christmas Eve")
          .withPolishName("Wigilia Bożego Narodzenia")
          .withHolidayType(RELIGIOUS)
          .build();

  private final HolidayDefinition christmas =
      HolidayDefinition.newBuilder()
          .withDate(fixedAtMonthDay(12, 25))
          .withEnglishName("Christmas")
          .withPolishName("Boże Narodzenie")
          .withHolidayType(RELIGIOUS)
          .markAsPublicHoliday()
          .build();

  private final HolidayDefinition christmasSecondDay =
      HolidayDefinition.newBuilder()
          .withDate(fixedAtMonthDay(12, 26))
          .withEnglishName("Christmas")
          .withPolishName("Boże Narodzenie")
          .withHolidayType(RELIGIOUS)
          .markAsPublicHoliday()
          .build();

  private final HolidayDefinition newYearsEve =
      HolidayDefinition.newBuilder()
          .withDate(fixedAtMonthDay(12, 31))
          .withEnglishName("New Year's Eve")
          .withPolishName("Sylwester")
          .withHolidayType(UNOFFICIAL)
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
}
