package ch.hslu.oop.SW03.temperature;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TemperatureTest {

  private Temperature temperature;
  final int defaultValue = 20;

  @BeforeEach
  void setUp() {
    temperature = new Temperature();
  }

  @Test
  void defaultValues() {
    assertCurrentTemperature(defaultValue);
  }

  @Test
  void constructor() {
    temperature = new Temperature(0);
    assertCurrentTemperature(0);
  }

  private void assertCurrentTemperature(final double expected) {
    Assertions.assertEquals(expected, temperature.getCurrentTemperatureInCelsius());
    Assertions.assertEquals(expected + 273.15, temperature.getCurrentTemperatureInKelvin());
    Assertions.assertEquals(expected * 1.8 + 32, temperature.getCurrentTemperatureInFahrenheit());
  }

  @Test
  void increaseCurrentTemperatureInCelsiusOrKelvin() {
    final double increment = 2;
    temperature.increaseCurrentTemperatureInCelsiusOrKelvin(increment);
    assertCurrentTemperature(defaultValue + increment);
  }

  @Test
  void increaseCurrentTemperatureInCelsiusOrKelvinNegativeValue() {
    final double increment = -2;
    temperature.increaseCurrentTemperatureInCelsiusOrKelvin(increment);
    assertCurrentTemperature(defaultValue + increment);
  }

  @Test
  void decreaseCurrentTemperatureInCelsiusOrKelvin() {
    final double decrement = 2;
    temperature.decreaseCurrentTemperatureInCelsiusOrKelvin(decrement);
    assertCurrentTemperature(defaultValue - decrement);
  }

  @Test
  void decreaseCurrentTemperatureInCelsiusOrKelvinNegativeValue() {
    final double decrement = -2;
    temperature.decreaseCurrentTemperatureInCelsiusOrKelvin(decrement);
    assertCurrentTemperature(defaultValue - decrement);
  }

  @Test
  void getCurrentStateOfAggregationForElementDependingOnCurrentTemperature() {
    Assertions.assertEquals(StateOfAggregation.LIQUID,
                            temperature.getCurrentStateOfAggregationForElement(ElementOfPeriodSystem.Hg));
    temperature.setCurrentTemperatureInCelsius(1000);
    Assertions.assertEquals(StateOfAggregation.GAS,
                            temperature.getCurrentStateOfAggregationForElement(ElementOfPeriodSystem.Hg));
    temperature.setCurrentTemperatureInCelsius(-1000);
    Assertions.assertEquals(StateOfAggregation.SOLID,
                            temperature.getCurrentStateOfAggregationForElement(ElementOfPeriodSystem.Hg));
  }
}