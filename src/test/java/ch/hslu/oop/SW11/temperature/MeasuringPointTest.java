package ch.hslu.oop.SW11.temperature;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

class MeasuringPointTest {

  @Test
  void testEqualsContract() {
    EqualsVerifier.forClass(MeasuringPoint.class)
                  .verify();
  }
}