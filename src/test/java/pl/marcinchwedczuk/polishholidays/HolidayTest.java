package pl.marcinchwedczuk.polishholidays;

import org.junit.jupiter.api.Test;

import nl.jqno.equalsverifier.EqualsVerifier;

class HolidayTest {
  @Test
  public void equals_and_hashCode_work() {
    EqualsVerifier.simple()
        .forClass(Holiday.class)
        .verify();
  }
}
