package pl.marcinchwedczuk.polishholidays;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.stream.Collectors;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.JavaTimeConversionPattern;
import org.junit.jupiter.params.provider.CsvSource;

class EasterDateHolidayDateAlgorithmTest {
  private final EasterDateHolidayDateAlgorithm easterDateAlgo =
      new EasterDateHolidayDateAlgorithm();

  // Easter dates taken from: https://pl.wikipedia.org/wiki/Wielkanoc
  @ParameterizedTest
  @CsvSource({
      "23/04/2000",
      "15/04/2001",
      "31/03/2002",
      "27/03/2005",
      "04/04/2010",
      "09/04/2023"
  })
  public void returns_correct_easter_date(
      @JavaTimeConversionPattern("dd/MM/yyyy") LocalDate expectedEasterDate) {
    int year = expectedEasterDate.getYear();

    assertThat(easterDateAlgo.holidayDateForYear(year))
        .isEqualTo(expectedEasterDate);
  }
}
