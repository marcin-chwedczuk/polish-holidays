package pl.marcinchwedczuk.polishholidays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;

class FixedDateHolidayDateAlgorithmTest {
  @Test
  public void returns_proper_date() {
    FixedDateHolidayDateAlgorithm algo =
        new FixedDateHolidayDateAlgorithm(11, 13);

    assertThat(algo.holidayDateForYear(2000))
        .isEqualTo(LocalDate.of(2000, 11, 13));
  }
}
