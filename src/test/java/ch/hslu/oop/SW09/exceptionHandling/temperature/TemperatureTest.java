package ch.hslu.oop.SW09.exceptionHandling.temperature;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static ch.hslu.oop.SW09.exceptionHandling.temperature.Temperature.FAHRENHEIT_OFFSET;
import static ch.hslu.oop.SW09.exceptionHandling.temperature.Temperature.KELVIN_OFFSET;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TemperatureTest {

  private static final int DEFAULT_VALUE = 20;

  private Temperature temperature;

  @BeforeEach
  void setUp() {
    temperature = Temperature.createFromCelsius(DEFAULT_VALUE);
  }

  @Test
  void createFromCelsius_createsNewTemperature() {
    temperature = Temperature.createFromCelsius(0);
    assertCurrentTemperature(0);
  }

  @Test
  void createFromKelvin_createsNewTemperature() {
    temperature = Temperature.createFromKelvin(KELVIN_OFFSET);
    assertCurrentTemperature(0);
  }

  @Test
  void createFromFahrenheit_createsNewTemperature() {
    temperature = Temperature.createFromFahrenheit(FAHRENHEIT_OFFSET);
    assertCurrentTemperature(0);
  }

  private void assertCurrentTemperature(final double expectedCelsiusValue) {
    assertEquals(expectedCelsiusValue, temperature.getCurrentTemperatureInCelsius());
    assertEquals(expectedCelsiusValue + KELVIN_OFFSET, temperature.getCurrentTemperatureInKelvin());
    assertEquals(expectedCelsiusValue * 1.8 + FAHRENHEIT_OFFSET, temperature.getCurrentTemperatureInFahrenheit());
  }

  @Test
  void increaseCurrentTemperatureInCelsiusOrKelvin() {
    final double increment = 2;
    temperature.increaseCurrentTemperatureInCelsiusOrKelvin(increment);
    assertCurrentTemperature(DEFAULT_VALUE + increment);
  }

  @Test
  void increaseCurrentTemperatureInCelsiusOrKelvinNegativeValue() {
    final double increment = -2;
    temperature.increaseCurrentTemperatureInCelsiusOrKelvin(increment);
    assertCurrentTemperature(DEFAULT_VALUE + increment);
  }

  @Test
  void decreaseCurrentTemperatureInCelsiusOrKelvin() {
    final double decrement = 2;
    temperature.decreaseCurrentTemperatureInCelsiusOrKelvin(decrement);
    assertCurrentTemperature(DEFAULT_VALUE - decrement);
  }

  @Test
  void decreaseCurrentTemperatureInCelsiusOrKelvinNegativeValue() {
    final double decrement = -2;
    temperature.decreaseCurrentTemperatureInCelsiusOrKelvin(decrement);
    assertCurrentTemperature(DEFAULT_VALUE - decrement);
  }

  @Test
  void getCurrentStateOfAggregationForElementDependingOnCurrentTemperature() {
    assertEquals(StateOfAggregation.LIQUID,
                 temperature.getCurrentStateOfAggregationForElement(ElementOfPeriodSystem.Hg));
    temperature.setCurrentTemperatureInCelsius(1000);
    assertEquals(StateOfAggregation.GASEOUS,
                 temperature.getCurrentStateOfAggregationForElement(ElementOfPeriodSystem.Hg));
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
  void compareTo_null_returns0() {
    assertEquals(1, temperature.compareTo(null));
  }

  @Test
  void compareTo_equalElement_returns0() {
    assertEquals(0, temperature.compareTo(temperature));
  }

  @Test
  void compareTo_smallerTemperatureGiven_returnsMinus1() {
    assertEquals(1,
                 temperature.compareTo(Temperature.createFromCelsius(temperature.getCurrentTemperatureInCelsius() - 1)));
  }

  @Test
  void compareTo_biggerTemperatureGiven_returns1() {
    assertEquals(-1,
                 temperature.compareTo(Temperature.createFromCelsius(temperature.getCurrentTemperatureInCelsius() + 1)));
  }

  @Test
  void convertCelsiusToKelvin_0_returnsKelvinOffset() {
    assertThat(Temperature.convertTemperatureInCelsiusToKelvin(0.0)).isEqualTo(KELVIN_OFFSET);
  }

  @Test
  void convertKelvinToCelsius_KelvinOffset_returns0() {
    assertThat(Temperature.convertTemperatureInKelvinToCelsius(KELVIN_OFFSET)).isEqualTo(0.0);
  }

  @Test
  void convertCelsiusToFahrenheit_0_returnsFahrenheitOffset() {
    assertThat(Temperature.convertTemperatureInCelsiusToFahrenheit(0.0)).isEqualTo(FAHRENHEIT_OFFSET);
  }

  @Test
  void convertCelsiusToFahrenheit_1_returnsCorrectFahrenheitValue() {
    assertThat(Temperature.convertTemperatureInCelsiusToFahrenheit(1)).isEqualTo(33.8);
  }

  @Test
  void convertFahrenheitToCelsius_FahrenheitOffset_returns0() {
    assertThat(Temperature.convertTemperatureInFahrenheitToCelsius(FAHRENHEIT_OFFSET)).isEqualTo(0);
  }

  @Test
  void convertFahrenheitToCelsius_1_returnsCorrectFahrenheitValue() {
    assertEquals(-17.22, Temperature.convertTemperatureInFahrenheitToCelsius(1), 0.01);
  }

  @Test
  void convertFahrenheitToCelsius_decimalValue_returnsCorrectFahrenheitValue() {
    assertEquals(-17.16, Temperature.convertTemperatureInFahrenheitToCelsius(1.11), 0.01);
  }
}