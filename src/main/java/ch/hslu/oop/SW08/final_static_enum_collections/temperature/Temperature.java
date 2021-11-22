package ch.hslu.oop.SW08.final_static_enum_collections.temperature;

import java.util.Objects;

public final class Temperature implements Comparable<Temperature> {
  public static final double KELVIN_OFFSET = 273.15;
  public static final int FAHRENHEIT_OFFSET = 32;

  private double currentTemperatureInCelsius;

  /**
   * Creates a new Temperature with default value 20
   */
  public Temperature() {
    this.currentTemperatureInCelsius = 20;
  }

  /**
   * Creates a new Temperature with the given Temperature
   *
   * @param currentTemperatureInCelsius
   */
  public Temperature(final double currentTemperatureInCelsius) {
    this.currentTemperatureInCelsius = currentTemperatureInCelsius;
  }

  /**
   * Creates a new Temperature with the given Temperature
   *
   * @param currentTemperature
   */
  public Temperature(final Temperature currentTemperature) {
    this.currentTemperatureInCelsius = currentTemperature.getCurrentTemperatureInCelsius();
  }

  public static double convertTemperatureInCelsiusToKelvin(final double temperatureInCelsius) {
    return temperatureInCelsius + KELVIN_OFFSET;
  }

  public static double convertTemperatureInKelvinToCelsius(final double temperatureInKelvin) {
    return temperatureInKelvin - KELVIN_OFFSET;
  }

  public static double convertTemperatureInCelsiusToFahrenheit(final double temperatureInCelsius) {
    return temperatureInCelsius * 1.8 + FAHRENHEIT_OFFSET;
  }

  public static double convertTemperatureInFahrenheitToCelsius(final double temperatureInFahrenheit) {
    return (temperatureInFahrenheit - FAHRENHEIT_OFFSET) / 1.8;
  }

  /**
   * @param increaseValue
   */
  public void increaseCurrentTemperatureInCelsiusOrKelvin(final double increaseValue) {
    this.currentTemperatureInCelsius += increaseValue;
  }

  /**
   * @param decreaseValue
   */
  public void decreaseCurrentTemperatureInCelsiusOrKelvin(final double decreaseValue) {
    this.currentTemperatureInCelsius -= decreaseValue;
  }

  public double getCurrentTemperatureInCelsius() {
    return currentTemperatureInCelsius;
  }

  public double getCurrentTemperatureInKelvin() {
    return convertTemperatureInCelsiusToKelvin(currentTemperatureInCelsius);
  }

  public double getCurrentTemperatureInFahrenheit() {
    return convertTemperatureInCelsiusToFahrenheit(currentTemperatureInCelsius);
  }

  public void setCurrentTemperatureInCelsius(final double currentTemperatureInCelsius) {
    this.currentTemperatureInCelsius = currentTemperatureInCelsius;
  }

  public void setCurrentTemperatureInKelvin(final double currentTemperatureInKelvin) {
    this.currentTemperatureInCelsius = convertTemperatureInKelvinToCelsius(currentTemperatureInKelvin);
  }

  public void setCurrentTemperatureInFahrenheit(final double currentTemperatureInFahrenheit) {
    this.currentTemperatureInCelsius = convertTemperatureInFahrenheitToCelsius(currentTemperatureInFahrenheit);
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
