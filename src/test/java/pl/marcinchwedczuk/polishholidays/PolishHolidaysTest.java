package pl.marcinchwedczuk.polishholidays;

import org.junit.jupiter.api.Test;
import pl.marcinchwedczuk.polishholidays.testutils.PolishHolidayAssert;

import javax.print.attribute.standard.MediaSize;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static pl.marcinchwedczuk.polishholidays.PolishHolidayType.*;
import static pl.marcinchwedczuk.polishholidays.testutils.PolishHolidayAssert.assertThat;

public class PolishHolidaysTest {
    // TODO: Support only 2000+ years

    @Test
    public void returns_list_of_holidays() {
        List<PolishHoliday> holidays = PolishHolidays.forYear(2021);
        Iterator<PolishHoliday> iter = holidays.iterator();

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
