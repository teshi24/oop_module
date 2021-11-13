package ch.hslu.oop.SW07.objectsEqualsCompare.temperature;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
    assertEquals(expected, temperature.getCurrentTemperatureInCelsius());
    assertEquals(expected + 273.15, temperature.getCurrentTemperatureInKelvin());
    assertEquals(expected * 1.8 + 32, temperature.getCurrentTemperatureInFahrenheit());
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
    assertEquals(StateOfAggregation.LIQUID,
                 temperature.getCurrentStateOfAggregationForElement(ElementOfPeriodSystem.Hg));
    temperature.setCurrentTemperatureInCelsius(1000);
    assertEquals(StateOfAggregation.GAS, temperature.getCurrentStateOfAggregationForElement(ElementOfPeriodSystem.Hg));
    temperature.setCurrentTemperatureInCelsius(-1000);
    assertEquals(StateOfAggregation.SOLID,
                 temperature.getCurrentStateOfAggregationForElement(ElementOfPeriodSystem.Hg));
  }

  @Test
  void testEqualsContract() {
    EqualsVerifier.forClass(Temperature.class)//
                  .suppress(Warning.NONFINAL_FIELDS)//
                  .verify();
  }

  @Test
  void compareTo_equalElement_returns0() {
    assertEquals(0, temperature.compareTo(temperature));
  }

  @Test
  void compareTo_smallerTemperatureGiven_returnsMinus1() {
    assertEquals(1, temperature.compareTo(new Temperature(temperature.getCurrentTemperatureInCelsius() - 1)));
  }

  @Test
  void compareTo_biggerTemperatureGiven_returns1() {
    assertEquals(-1, temperature.compareTo(new Temperature(temperature.getCurrentTemperatureInCelsius() + 1)));
  }
}