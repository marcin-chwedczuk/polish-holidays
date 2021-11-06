package pl.marcinchwedczuk.polishholidays;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EffectiveTimespanTest {
    @Test
    public void equals_and_hashCode_work() {
        EqualsVerifier.simple()
                .forClass(EffectiveTimespan.class)
                .verify();
    }
}