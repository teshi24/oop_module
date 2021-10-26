package ch.hslu.oop.SW03.temperature;

public class Temperature {
  private double currentTemperatureInCelsius;

  /**
   * Creates a new Temparature with default value 20
   */
  public Temperature() {
    this.currentTemperatureInCelsius = 20;
  }

  /**
   * @param currentTemperatureInCelsius
   */
  public Temperature(final double currentTemperatureInCelsius) {
    this.currentTemperatureInCelsius = currentTemperatureInCelsius;
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
    return currentTemperatureInCelsius + 273.15;
  }

  public double getCurrentTemperatureInFahrenheit() {
    return currentTemperatureInCelsius * 1.8 + 32;
  }

  public void setCurrentTemperatureInCelsius(final double currentTemperatureInCelsius) {
    this.currentTemperatureInCelsius = currentTemperatureInCelsius;
  }

  public void setCurrentTemperatureInKelvin(final double currentTemperatureInKelvin) {
    this.currentTemperatureInCelsius = currentTemperatureInKelvin - 273.15;
  }

  public void setCurrentTemperatureInFahrenheit(final double currentTemperatureInFahrenheit) {
    this.currentTemperatureInCelsius = (currentTemperatureInFahrenheit - 32) / 1.8;
  }

  public StateOfAggregation getCurrentStateOfAggregationForElement(final ElementOfPeriodSystem element) {
    return element.getStateOfAggregationBasedOnTemperature(currentTemperatureInCelsius);
  }
}
