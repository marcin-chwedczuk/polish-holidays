package pl.marcinchwedczuk.polishholidays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static pl.marcinchwedczuk.polishholidays.HolidayDateAlgorithms.fixedAtMonthDay;
import static pl.marcinchwedczuk.polishholidays.HolidayType.*;
import static pl.marcinchwedczuk.polishholidays.testutils.PolishHolidayAssert.assertThat;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;

import org.junit.jupiter.api.Test;

public class HolidayCalendarsTest {
  @Test
  void throws_exception_for_years_for_which_calendar_is_not_defined() {
    HolidayCalendar calendar = HolidayCalendar.newBuilder()
        .withValidFromYearIncluding(2000)
        .withValidToYearExcluding(2005)
        .createCalendar();

    assertThat(calendar.validFromYearInclusive())
        .hasValue(2000);

    assertThat(calendar.validToYearExcluding())
        .hasValue(2005);

    assertThrows(IllegalArgumentException.class, () -> {
      calendar.holidaysForYear(1999);
    });

    assertThrows(IllegalArgumentException.class, () -> {
      calendar.holidaysForYear(2006);
    });
  }

  @Test
  void new_holidays_can_be_added_to_calendar() {
    HolidayCalendar custom =
        HolidayCalendar.newBuilderWithPolishHolidaysDefined()
            .defineHoliday(
                HolidayDefinition.newBuilder()
                    .withEnglishName("Birthday")
                    .withPolishName("Urodziny")
                    .withDate(fixedAtMonthDay(1, 2))
                    .withHolidayType(OTHER)
                    .build())
            .createCalendar();

    List<Holiday> holidays = custom.holidaysForYear(2000);

    // Old holiday is preserved
    assertThat(holidays.get(0))
        .hasEnglishName("New Year's Day");

    // New holiday
    assertThat(holidays.get(1))
        .hasDate(LocalDate.of(2000, 1, 2))
        .hasEnglishName("Birthday")
        .hasPolishName("Urodziny")
        .hasType(OTHER)
        .isNotPublicHoliday();
  }

  @Test
  void holiday_can_be_removed_from_calendar() {
    HolidayCalendar custom =
        HolidayCalendar.newBuilderWithPolishHolidaysDefined()
            .removeAllHolidaysMatching(definition -> definition
                .holidayEnglishName().equals("New Year's Day"))
            .createCalendar();

    Holiday firstHoliday = custom.holidaysForYear(2000).get(0);
    assertThat(firstHoliday)
        .hasEnglishName("Epiphany");
  }

  @Test
  void two_holidays_can_be_defined_on_the_same_day() {
    HolidayCalendar custom =
        HolidayCalendar.newBuilderWithPolishHolidaysDefined()
            .defineHoliday(
                HolidayDefinition.newBuilder()
                    .withEnglishName("Test")
                    .withPolishName("Test")
                    .withDate(fixedAtMonthDay(1, 1))
                    .withHolidayType(OTHER)
                    .build())
            .createCalendar();

    List<Holiday> holidays = custom.holidaysForYear(2000);

    assertThat(holidays.get(0))
        .hasDate(LocalDate.of(2000, 1, 1))
        .hasEnglishName("New Year's Day");

    assertThat(holidays.get(1))
        .hasDate(LocalDate.of(2000, 1, 1))
        .hasEnglishName("Test");
  }

  @Test
  void holiday_is_only_returned_for_its_effective_timespan() {
    HolidayCalendar calendar =
        HolidayCalendar.newBuilder()
            .defineHoliday(
                HolidayDefinition.newBuilder()
                    .withEffectiveTimespan(
                        EffectiveTimespan.fromYearsRange(2000, 2005))
                    .withEnglishName("Test")
                    .withPolishName("Test")
                    .withDate(fixedAtMonthDay(1, 1))
                    .withHolidayType(OTHER)
                    .build())
            .createCalendar();

    assertThat(calendar.holidaysForYear(1999))
        .isEmpty();

    assertThat(calendar.holidaysForYear(2000))
        .hasSize(1);

    assertThat(calendar.holidaysForYear(2004))
        .hasSize(1);

    // range end is excluding
    assertThat(calendar.holidaysForYear(2005))
        .isEmpty();
  }

  @Test
  void overrides_work() {
    HolidayCalendar calendar =
        HolidayCalendar.newBuilder()
            .defineHoliday(
                HolidayDefinition.newBuilder()
                    .withEffectiveTimespan(
                        EffectiveTimespan.fromYearsRange(2000, 2005))
                    .withEnglishName("Test")
                    .withPolishName("Test")
                    .withDate(fixedAtMonthDay(1, 1))
                    .withHolidayType(OTHER)
                    .addOverride(HolidayDefinitionOverride.newBuilder()
                        .withEffectiveTimespan(
                            EffectiveTimespan.forSingleYear(2000))
                        .withOverride(definition -> definition
                            .withEnglishName("Test2000"))
                        .build())
                    .addOverride(HolidayDefinitionOverride.newBuilder()
                        .withEffectiveTimespan(
                            EffectiveTimespan.fromYearsRange(2001, 2004))
                        .withOverride(
                            definition -> definition
                                .withEnglishName("TestXXX")
                                .withPolishName("TestYYY"))
                        .build())
                    .build())
            .createCalendar();

    assertThat(calendar.holidaysForYear(2000).get(0))
        .hasDate(LocalDate.of(2000, 1, 1))
        .hasEnglishName("Test2000")
        .hasPolishName("Test")
        .hasType(OTHER)
        .isNotPublicHoliday();

    assertThat(calendar.holidaysForYear(2001).get(0))
        .hasDate(LocalDate.of(2001, 1, 1))
        .hasEnglishName("TestXXX")
        .hasPolishName("TestYYY")
        .hasType(OTHER)
        .isNotPublicHoliday();

    assertThat(calendar.holidaysForYear(2003).get(0))
        .hasDate(LocalDate.of(2003, 1, 1))
        .hasEnglishName("TestXXX")
        .hasPolishName("TestYYY")
        .hasType(OTHER)
        .isNotPublicHoliday();

    assertThat(calendar.holidaysForYear(2004).get(0))
        .hasDate(LocalDate.of(2004, 1, 1))
        .hasEnglishName("Test")
        .hasPolishName("Test")
        .hasType(OTHER)
        .isNotPublicHoliday();
  }

  @Test
  void returns_list_of_holidays() {
    HolidayCalendar calendar = HolidayCalendars.createPolishHolidaysCalendar();
    List<Holiday> holidays = calendar.holidaysForYear(2021);
    Iterator<Holiday> iter = holidays.iterator();

    // Source: https://www.nbp.pl/homen.aspx?f=/en/onbp/organizacja/schedule.html
    // Source: Google Calendar for year 2021

    assertThat(iter.next())
        .hasDate(LocalDate.of(2021, 1, 1))
        .hasEnglishName("New Year's Day")
        .hasPolishName("Nowy Rok")
        .hasType(OTHER)
        .isPublicHoliday();

    assertThat(iter.next())
        .hasDate(LocalDate.of(2021, 1, 6))
        .hasEnglishName("Epiphany")
        .hasPolishName("Święto Trzech Króli")
        .hasType(RELIGIOUS)
        .isPublicHoliday();

    assertThat(iter.next())
        .hasDate(LocalDate.of(2021, 2, 14))
        .hasEnglishName("Valentine's Day")
        .hasPolishName("Walentynki")
        .hasType(OTHER)
        .isNotPublicHoliday();

    assertThat(iter.next())
        .hasDate(LocalDate.of(2021, 4, 2))
        .hasEnglishName("Good Friday")
        .hasPolishName("Wielki Piątek")
        .hasType(RELIGIOUS)
        .isNotPublicHoliday();

    assertThat(iter.next())
        .hasDate(LocalDate.of(2021, 4, 3))
        .hasEnglishName("Holy Saturday")
        .hasPolishName("Wielka Sobota")
        .hasType(RELIGIOUS)
        .isNotPublicHoliday();

    assertThat(iter.next())
        .hasDate(LocalDate.of(2021, 4, 4))
        .hasEnglishName("Easter")
        .hasPolishName("Wielkanoc")
        .hasType(RELIGIOUS)
        .isPublicHoliday();

    assertThat(iter.next())
        .hasDate(LocalDate.of(2021, 4, 5))
        .hasEnglishName("Easter Monday")
        .hasPolishName("Poniedziałek Wielkanocny")
        .hasType(RELIGIOUS)
        .isPublicHoliday();

    assertThat(iter.next())
        .hasDate(LocalDate.of(2021, 5, 1))
        .hasEnglishName("Labour Day")
        .hasPolishName("Święto Pracy")
        .hasType(OTHER)
        .isPublicHoliday();

    assertThat(iter.next())
        .hasDate(LocalDate.of(2021, 5, 2))
        .hasEnglishName("Polish National Flag Day")
        .hasPolishName("Dzień Flagi Rzeczypospolitej Polskiej")
        .hasType(NATIONAL)
        .isNotPublicHoliday();

    assertThat(iter.next())
        .hasDate(LocalDate.of(2021, 5, 3))
        .hasEnglishName("Constitution Day")
        .hasPolishName("Święto Konstytucji Trzeciego Maja")
        .hasType(NATIONAL)
        .isPublicHoliday();

    assertThat(iter.next())
        .hasDate(LocalDate.of(2021, 5, 23))
        .hasEnglishName("White Sunday")
        .hasPolishName("Zielone Świątki")
        .hasType(RELIGIOUS)
        .isPublicHoliday();

    assertThat(iter.next())
        .hasDate(LocalDate.of(2021, 5, 26))
        .hasEnglishName("Mother's Day")
        .hasPolishName("Dzień Matki")
        .hasType(UNOFFICIAL)
        .isNotPublicHoliday();

    assertThat(iter.next())
        .hasDate(LocalDate.of(2021, 6, 1))
        .hasEnglishName("Children's Day")
        .hasPolishName("Dzień Dziecka")
        .hasType(UNOFFICIAL)
        .isNotPublicHoliday();

    assertThat(iter.next())
        .hasDate(LocalDate.of(2021, 6, 3))
        .hasEnglishName("Feast of Corpus Christi")
        .hasPolishName("Boże Ciało")
        .hasType(RELIGIOUS)
        .isPublicHoliday();

    assertThat(iter.next())
        .hasDate(LocalDate.of(2021, 6, 23))
        .hasEnglishName("Father's Day")
        .hasPolishName("Dzień Ojca")
        .hasType(UNOFFICIAL)
        .isNotPublicHoliday();

    assertThat(iter.next())
        .hasDate(LocalDate.of(2021, 8, 15))
        .hasEnglishName("Assumption of Mary")
        .hasPolishName("Wniebowzięcie Najświętszej Maryi Panny")
        .hasType(RELIGIOUS)
        .isPublicHoliday();

    assertThat(iter.next())
        .hasDate(LocalDate.of(2021, 11, 1))
        .hasEnglishName("All Saints' Day")
        .hasPolishName("Wszystkich Świętych")
        .hasType(RELIGIOUS)
        .isPublicHoliday();

    assertThat(iter.next())
        .hasDate(LocalDate.of(2021, 11, 11))
        .hasEnglishName("National Independence Day")
        .hasPolishName("Dzień Niepodległości")
        .hasType(NATIONAL)
        .isPublicHoliday();

    assertThat(iter.next())
        .hasDate(LocalDate.of(2021, 12, 24))
        .hasEnglishName("Christmas Eve")
        .hasPolishName("Wigilia Bożego Narodzenia")
        .hasType(RELIGIOUS)
        .isNotPublicHoliday();

    assertThat(iter.next())
        .hasDate(LocalDate.of(2021, 12, 25))
        .hasEnglishName("Christmas")
        .hasPolishName("Boże Narodzenie")
        .hasType(RELIGIOUS)
        .isPublicHoliday();

    assertThat(iter.next())
        .hasDate(LocalDate.of(2021, 12, 26))
        .hasEnglishName("Christmas")
        .hasPolishName("Boże Narodzenie")
        .hasType(RELIGIOUS)
        .isPublicHoliday();

    assertThat(iter.next())
        .hasDate(LocalDate.of(2021, 12, 31))
        .hasEnglishName("New Year's Eve")
        .hasPolishName("Sylwester")
        .hasType(UNOFFICIAL)
        .isNotPublicHoliday();

    assertFalse(iter.hasNext(), "No more holidays in 2021");
  }
}
