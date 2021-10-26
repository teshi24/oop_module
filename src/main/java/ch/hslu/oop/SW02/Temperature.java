package ch.hslu.oop.SW02;

public class Temperature {
  private double currentTemperatureInCelsius;

  public Temperature() {
    this.currentTemperatureInCelsius = 20;
  }

  public Temperature(final double currentTemperatureInCelsius) {
    this.currentTemperatureInCelsius = currentTemperatureInCelsius;
  }

  public void increaseCurrentTemperatureInCelsiusOrKelvin(final double increaseValue) {
    this.currentTemperatureInCelsius += increaseValue;
  }

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
}
