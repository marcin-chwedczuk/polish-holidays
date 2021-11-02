package pl.marcinchwedczuk.polishholidays;

import static org.junit.jupiter.api.Assertions.*;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

class PolishHolidayTest {
  @Test
  public void equals_and_hashCode_work() {
    EqualsVerifier.simple().forClass(PolishHoliday.class).verify();
  }
}
