package ch.hslu.oop.SW12.streams.temperature;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ElementOfPeriodSystemTest {

  @Test
  void getStateOfAggregationForTemperatureLiquid() {
    Assertions.assertEquals(StateOfAggregation.LIQUID,
                            ElementOfPeriodSystem.Hg.getStateOfAggregationBasedOnTemperature(0));
  }

  @Test
  void getStateOfAggregationForTemperatureSolid() {
    Assertions.assertEquals(StateOfAggregation.SOLID,
                            ElementOfPeriodSystem.Hg.getStateOfAggregationBasedOnTemperature(-100));
  }

  @Test
  void getStateOfAggregationForTemperatureGas() {
    Assertions.assertEquals(StateOfAggregation.GASEOUS,
                            ElementOfPeriodSystem.Hg.getStateOfAggregationBasedOnTemperature(1000));
  }
}