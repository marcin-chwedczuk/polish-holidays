package pl.marcinchwedczuk.polishholidays;

import org.junit.jupiter.api.Test;
import pl.marcinchwedczuk.polishholidays.testutils.PolishHolidayAssert;

import javax.print.attribute.standard.MediaSize;
import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static pl.marcinchwedczuk.polishholidays.PolishHolidayType.*;
import static pl.marcinchwedczuk.polishholidays.testutils.PolishHolidayAssert.assertThat;

public class PolishHolidaysTest {
    // TODO: Support only 2000+ years

    @Test
    public void returns_list_of_holidays() {
        List<PolishHoliday> holidays = PolishHolidays.forYear(2021);

        // Source: https://www.nbp.pl/homen.aspx?f=/en/onbp/organizacja/schedule.html
        // Source: Google Calendar for year 2021

        assertThat(holidays.get(0))
                .hasDate(LocalDate.of(2021, 1, 1))
                .hasEnglishName("New Year's Day")
                .hasPolishName("Nowy Rok")
                .hasType(OTHER)
                .isPublicHoliday();

        assertHoliday(holidays.get(1),
                LocalDate.of(2021, 1, 6),
                RELIGIOUS,
                "Epiphany",
                "Święto Trzech Króli",
                true);

        assertHoliday(holidays.get(2),
                LocalDate.of(2021, 4, 4),
                RELIGIOUS,
                "Easter",
                "Wielkanoc",
                true);

        assertHoliday(holidays.get(3),
                LocalDate.of(2021, 4, 5),
                RELIGIOUS,
                "Easter Monday",
                "Poniedziałek Wielkanocny",
                true);

        assertHoliday(holidays.get(4),
                LocalDate.of(2021, 5, 1),
                OTHER,
                "Labour Day",
                "Święto Pracy",
                true);

        assertHoliday(holidays.get(5),
                LocalDate.of(2021, 5, 3),
                NATIONAL,
                "Constitution Day",
                "Święto Konstytucji Trzeciego Maja",
                true);

        assertHoliday(holidays.get(6),
                LocalDate.of(2021, 5, 23),
                RELIGIOUS,
                "Whit Sunday",
                "Zielone Świątki",
                true);

        assertHoliday(holidays.get(7),
                LocalDate.of(2021, 6, 3),
                RELIGIOUS,
                "Feast of Corpus Christi",
                "Boże Ciało",
                true);

        // Święto Wojska Polskiego
        assertHoliday(holidays.get(8),
                LocalDate.of(2021, 8, 15),
                RELIGIOUS,
                "Assumption of Mary",
                "Wniebowzięcie Najświętszej Maryi Panny",
                true);

        assertHoliday(holidays.get(9),
                LocalDate.of(2021, 10, 1),
                RELIGIOUS,
                "All Saint's Day",
                "Wszystkich Świętych",
                true);

        assertHoliday(holidays.get(10),
                LocalDate.of(2021, 11, 11),
                NATIONAL,
                "Independence Day",
                "Dzień Niepodległości",
                true);

        assertHoliday(holidays.get(11),
                LocalDate.of(2021, 12, 25),
                RELIGIOUS,
                "Christmas (1st day)",
                "Boże Narodzenie",
                true);

        assertHoliday(holidays.get(12),
                LocalDate.of(2021, 12, 26),
                RELIGIOUS,
                "Christmas (2st day)",
                "Boże Narodzenie",
                true);
    }

    private void assertHoliday(PolishHoliday holiday,
                               LocalDate expectedDate,
                               PolishHolidayType type,
                               String expectedEnglishName,
                               String expectedPolishName,
                               boolean expectedIsPublic) {
        assertThat(holiday.date())
                .as("holiday date")
                .isEqualTo(expectedDate);

        assertThat(holiday.englishName())
                .as("holiday english name")
                .isEqualTo(expectedEnglishName);

        assertThat(holiday.polishName())
                .as("holiday polish name")
                .isEqualTo(expectedPolishName);

        assertThat(holiday.isPublicHoliday())
                .as("is public holiday?")
                .isEqualTo(expectedIsPublic);
    }
}
