package ch.hslu.oop.SW12.streams.temperature;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static ch.hslu.oop.SW12.streams.temperature.Temperature.KELVIN_OFFSET;
import static ch.hslu.oop.SW12.streams.temperature.Temperature.createFromCelsius;
import static ch.hslu.oop.SW12.streams.temperature.Temperature.getMessageTemperatureToLow;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TemperatureTest {

  private static final int DEFAULT_VALUE = 20;

  private Temperature temperature;

  @BeforeEach
  void setUp() {
    temperature = createFromCelsius(DEFAULT_VALUE);
  }

  @Test
  void createFromCelsius_validTemperature_createsNewTemperature() {
    temperature = createFromCelsius(0);
    assertCurrentTemperature(0);
  }

  @Test
  void createFromCelsius_temperatureToLow_throwsIllegalArgumentException() {
    final double invalidInput = TemperatureUnit.CELSIUS.getMinValue() - 1;
    final Executable executable = () -> createFromCelsius(invalidInput);
    final String message = getMessageTemperatureToLow(invalidInput, TemperatureUnit.CELSIUS);

    assertExceptionThrown(executable, message);
  }

  @Test
  void createFromKelvin_validTemperature_createsNewTemperature() {
    temperature = Temperature.createFromKelvin(KELVIN_OFFSET);
    assertCurrentTemperature(0);
  }

  @Test
  void createFromKelvin_temperatureToLow_throwsIllegalArgumentException() {
    final double invalidInput = TemperatureUnit.KELVIN.getMinValue() - 1;
    final Executable executable = () -> Temperature.createFromKelvin(invalidInput);
    final String messageTemperatureToLow = getMessageTemperatureToLow(invalidInput, TemperatureUnit.KELVIN);

    assertExceptionThrown(executable, messageTemperatureToLow);
  }

  @Test
  void createFromFahrenheit_validTemperature_createsNewTemperature() {
    temperature = Temperature.createFromFahrenheit(32);
    assertCurrentTemperature(0);
  }

  @Test
  void createFromFahrenheit_temperatureToLow_throwsIllegalArgumentException() {
    final double invalidInput = TemperatureUnit.FAHRENHEIT.getMinValue() - 1;
    final Executable executable = () -> Temperature.createFromFahrenheit(invalidInput);
    final String messageTemperatureToLow = getMessageTemperatureToLow(invalidInput, TemperatureUnit.FAHRENHEIT);

    assertExceptionThrown(executable, messageTemperatureToLow);
  }

  @Test
  void getCurrentStateOfAggregationForElementDependingOnCurrentTemperature() {
    assertEquals(StateOfAggregation.LIQUID,
                 temperature.getCurrentStateOfAggregationForElement(ElementOfPeriodSystem.Hg));

    temperature = createFromCelsius(1000);
    assertEquals(StateOfAggregation.GASEOUS,
                 temperature.getCurrentStateOfAggregationForElement(ElementOfPeriodSystem.Hg));

    temperature = createFromCelsius(-200);
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
    assertEquals(1, temperature.compareTo(createFromCelsius(temperature.getCurrentTemperatureInCelsius() - 1)));
  }

  @Test
  void compareTo_biggerTemperatureGiven_returns1() {
    assertEquals(-1, temperature.compareTo(createFromCelsius(temperature.getCurrentTemperatureInCelsius() + 1)));
  }

  @Test
  void convertCelsiusToKelvin_0_returnsKelvinOffset() {
    assertThat(Temperature.convertCelsiusToKelvin(0.0)).isEqualTo(KELVIN_OFFSET);
  }

  @Test
  void convertKelvinToCelsius_KelvinOffset_returns0() {
    assertThat(Temperature.convertKelvinToCelsius(KELVIN_OFFSET)).isEqualTo(0.0);
  }

  @Test
  void convertCelsiusToFahrenheit_0_returnsFahrenheitOffset() {
    assertThat(Temperature.convertCelsiusToFahrenheit(0.0)).isEqualTo(32);
  }

  @Test
  void convertCelsiusToFahrenheit_1_returnsCorrectFahrenheitValue() {
    assertThat(Temperature.convertCelsiusToFahrenheit(1)).isEqualTo(33.8);
  }

  @Test
  void convertFahrenheitToCelsius_FahrenheitOffset_returns0() {
    assertThat(Temperature.convertFahrenheitToCelsius(32)).isEqualTo(0);
  }

  @Test
  void convertFahrenheitToCelsius_1_returnsCorrectFahrenheitValue() {
    assertEquals(-17.22, Temperature.convertFahrenheitToCelsius(1), 0.01);
  }

  @Test
  void convertFahrenheitToCelsius_decimalValue_returnsCorrectFahrenheitValue() {
    assertEquals(-17.16, Temperature.convertFahrenheitToCelsius(1.11), 0.01);
  }

  private void assertCurrentTemperature(final double expectedCelsiusValue) {
    assertEquals(expectedCelsiusValue, temperature.getCurrentTemperatureInCelsius());
    assertEquals(expectedCelsiusValue + KELVIN_OFFSET, temperature.getCurrentTemperatureInKelvin());
    assertEquals(expectedCelsiusValue * 1.8 + 32, temperature.getCurrentTemperatureInFahrenheit());
  }

  private void assertExceptionThrown(final Executable executable, final String message) {
    final Exception thrownException = assertThrows(IllegalArgumentException.class, executable);
    assertThat(thrownException.getMessage()).isEqualTo(message);
  }
}