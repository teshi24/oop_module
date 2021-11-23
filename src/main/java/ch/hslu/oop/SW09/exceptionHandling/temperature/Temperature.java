package ch.hslu.oop.SW09.exceptionHandling.temperature;

import org.assertj.core.util.VisibleForTesting;

import java.util.Objects;

/**
 * Holds a temperature.
 */
public final class Temperature implements Comparable<Temperature> {
  public static final double KELVIN_OFFSET = -TemperatureUnit.CELSIUS.getMinValue();

  private final double currentTemperatureInCelsius;

  /**
   * Creates a new Temperature object.
   *
   * @param currentTemperatureInCelsius initializes temperature in the new object
   * @return new Temperature object
   * @throws IllegalArgumentException Exception thrown if the given temperature is lower than the minimal °C value
   *                                  {@link TemperatureUnit#CELSIUS}
   */
  public static Temperature createFromCelsius(final double currentTemperatureInCelsius) throws IllegalArgumentException {
    validateGivenTemperature(currentTemperatureInCelsius, TemperatureUnit.CELSIUS);
    return new Temperature(currentTemperatureInCelsius);
  }

  /**
   * Creates a new Temperature object.
   *
   * @param currentTemperatureInKelvin initializes temperature in the new object
   * @return new Temperature object
   * @throws IllegalArgumentException Exception thrown if the given temperature is lower than the minimal K value
   *                                  {@link TemperatureUnit#KELVIN}
   */
  public static Temperature createFromKelvin(final double currentTemperatureInKelvin) throws IllegalArgumentException {
    validateGivenTemperature(currentTemperatureInKelvin, TemperatureUnit.KELVIN);
    return new Temperature(convertKelvinToCelsius(currentTemperatureInKelvin));
  }

  /**
   * Creates a new Temperature object.
   *
   * @param currentTemperatureInFahrenheit initializes temperature in the new object
   * @return new Temperature object
   * @throws IllegalArgumentException Exception thrown if the given temperature is lower than the minimal °F value
   *                                  {@link TemperatureUnit#FAHRENHEIT}
   */
  public static Temperature createFromFahrenheit(final double currentTemperatureInFahrenheit) throws IllegalArgumentException {
    validateGivenTemperature(currentTemperatureInFahrenheit, TemperatureUnit.FAHRENHEIT);
    return new Temperature(convertFahrenheitToCelsius(currentTemperatureInFahrenheit));
  }

  private static void validateGivenTemperature(final double givenTemperature, final TemperatureUnit givenUnit) throws IllegalArgumentException {
    if (givenTemperature < givenUnit.getMinValue()) {
      throw new IllegalArgumentException(getMessageTemperatureToLow(givenTemperature, givenUnit));
    }
  }

  @VisibleForTesting
  static String getMessageTemperatureToLow(final double givenTemperature, final TemperatureUnit givenUnit) {
    final String MESSAGE_TEMPLATE_TEMPERATURE_TO_LOW = "%1$.2f %3$s is an invalid Temperature. " + //
                                                       "Smallest valid value: %2$.2f %3$s.";
    return String.format(MESSAGE_TEMPLATE_TEMPERATURE_TO_LOW, givenTemperature, givenUnit.getMinValue(), givenUnit);
  }

  private Temperature(final double currentTemperatureInCelsius) {
    this.currentTemperatureInCelsius = currentTemperatureInCelsius;
  }

  /**
   * Copies the given Temperature object
   *
   * @param currentTemperature base object to be copied
   */
  public Temperature(final Temperature currentTemperature) {
    this.currentTemperatureInCelsius = currentTemperature.getCurrentTemperatureInCelsius();
  }

  public static double convertCelsiusToKelvin(final double temperatureInCelsius) {
    return temperatureInCelsius + KELVIN_OFFSET;
  }

  public static double convertKelvinToCelsius(final double temperatureInKelvin) {
    return temperatureInKelvin - KELVIN_OFFSET;
  }

  public static double convertCelsiusToFahrenheit(final double temperatureInCelsius) {
    return temperatureInCelsius * 1.8 + 32;
  }

  public static double convertFahrenheitToCelsius(final double temperatureInFahrenheit) {
    return (temperatureInFahrenheit - 32) / 1.8;
  }

  public double getCurrentTemperatureInCelsius() {
    return currentTemperatureInCelsius;
  }

  public double getCurrentTemperatureInKelvin() {
    return convertCelsiusToKelvin(currentTemperatureInCelsius);
  }

  public double getCurrentTemperatureInFahrenheit() {
    return convertCelsiusToFahrenheit(currentTemperatureInCelsius);
  }

  /**
   * @param element Element of the period system
   * @return StateOfAggregation of the element, based on the current temperature
   */
  public StateOfAggregation getCurrentStateOfAggregationForElement(final ElementOfPeriodSystem element) {
    return element.getStateOfAggregationBasedOnTemperature(currentTemperatureInCelsius);
  }

  @Override
  public final boolean equals(final Object o) {
    if (o == this) {
      return true;
    }
    if (!(o instanceof final Temperature temperature)) {
      return false;
    }

    return Double.compare(temperature.currentTemperatureInCelsius, this.currentTemperatureInCelsius) == 0;
  }

  @Override
  public final int hashCode() {
    return Objects.hash(currentTemperatureInCelsius);
  }

  /**
   * @param temperature
   * @return if null - returns -1; else comparison of the currentTemperature {@see Double#compare}
   */
  @Override
  public int compareTo(final Temperature temperature) {
    if (temperature == null) {
      return 1;
    }
    return Double.compare(this.currentTemperatureInCelsius, temperature.currentTemperatureInCelsius);
  }

  @Override
  public String toString() {
    return "Temperature{" + "currentTemperatureInCelsius=" + currentTemperatureInCelsius + '}';
  }
}
