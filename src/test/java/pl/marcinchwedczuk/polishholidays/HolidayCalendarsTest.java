package pl.marcinchwedczuk.polishholidays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static pl.marcinchwedczuk.polishholidays.HolidayType.*;
import static pl.marcinchwedczuk.polishholidays.testutils.PolishHolidayAssert.assertThat;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class HolidayCalendarsTest {
  @Test
  void throws_exception_for_years_when_calendar_is_not_defined() {
    HolidayCalendar calendar = HolidayCalendars.createPolishHolidaysCalendar();

    assertThat(calendar.validFromYearInclusive())
        .hasValue(2000);

    assertThat(calendar.validToYearExcluding())
        .isEmpty();

    assertThrows(IllegalArgumentException.class, () -> {
      calendar.holidaysForYear(1999);
    });

    assertThrows(IllegalArgumentException.class, () -> {
      calendar.holidaysForYear(1985);
    });
  }

  @Test
  void new_holidays_can_added_to_calendar() {
    HolidayCalendar custom =
        HolidayCalendar.newBuilderWithPolishHolidaysDefined()
            .defineHoliday(
                HolidayDefinition.newBuilder()
                    .withEnglishName("Birthday")
                    .withPolishName("Urodziny")
                    .usesAlgorithm(new FixedDateHolidayDateAlgorithm(1, 2))
                    .defineRule(HolidayDefinitionRule.newBuilder()
                        // TODO: Rules should only have validity and algorithm, names should be on HolidayDefiniton
                        .validFromYearIncluding(1990)
                        .withHolidayType(OTHER)
                        // TODO: Make it part of the builder e.g. withFixedDate(), withRelativeToEasterDate()
                        .build())
                    .build())
            .createCalendar();

    List<Holiday> holidays = custom.holidaysForYear(2000);

    // Old holiday is preserved
    assertThat(holidays.get(0))
        .hasDate(LocalDate.of(2000, 1, 1))
        .hasEnglishName("New Year's Day")
        .hasPolishName("Nowy Rok")
        .hasType(OTHER)
        .isPublicHoliday();

    // New holiday
    assertThat(holidays.get(1))
        .hasDate(LocalDate.of(2000, 1, 2))
        .hasEnglishName("Birthday")
        .hasPolishName("Urodziny")
        .hasType(OTHER)
        .isNotPublicHoliday();
  }

  @Test
  @Disabled("TODO")
  void holiday_can_be_removed_from_the_calendar() {
    HolidayCalendar custom =
        HolidayCalendar.newBuilderWithPolishHolidaysDefined()
            .removeAllHolidaysMatching(holidayDefinition -> {
              return true;
            })
            .createCalendar();

    // TODO: Make removal possible
    assertFalse(true);
  }

  @Test
  public void two_holidays_can_be_defined_on_the_same_day() {
    HolidayCalendar custom =
        HolidayCalendar.newBuilderWithPolishHolidaysDefined()
            .defineHoliday(
                HolidayDefinition.newBuilder()
                    .withEnglishName("Test")
                    .withPolishName("Test")
                    .usesAlgorithm(new FixedDateHolidayDateAlgorithm(1, 1))
                    .defineRule(HolidayDefinitionRule.newBuilder()
                        .validFromYearIncluding(1990)
                        .withHolidayType(OTHER)
                        .build())
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
  public void returns_list_of_holidays() {
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
