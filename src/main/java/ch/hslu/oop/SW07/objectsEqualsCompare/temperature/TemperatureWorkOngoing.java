package ch.hslu.oop.SW07.objectsEqualsCompare.temperature;

public class TemperatureWorkOngoing {
  private double currentTemperatureInCelsius;

  public TemperatureWorkOngoing() {
    this.currentTemperatureInCelsius = 20;
  }

  public TemperatureWorkOngoing(final double currentTemperatureInCelsius) {
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

  public StateOfAggregation getCurrentStateOfAggregationForElementSwitch(final ElementOfPeriodSystem element) {
    return switch (element) {
      case N -> getCurrentStateOfAggregationOfElementN();
      case Hg -> getCurrentStateOfAggregationOfElementHg();
      case Pb -> getCurrentStateOfAggregationOfElementPb();
    };
  }

  public StateOfAggregation getCurrentStateOfAggregationForElementElseIf(final ElementOfPeriodSystem element) {
    if (element == ElementOfPeriodSystem.N) {
      return getCurrentStateOfAggregationOfElementN();
    }
    if (element == ElementOfPeriodSystem.Hg) {
      return getCurrentStateOfAggregationOfElementHg();
    }
    if (element == ElementOfPeriodSystem.Pb) {
      return getCurrentStateOfAggregationOfElementPb();
    }
    throw new IllegalArgumentException();
  }

  public StateOfAggregation getCurrentStateOfAggregationForElement(final ElementOfPeriodSystem element) {
    return element.getStateOfAggregationBasedOnTemperature(currentTemperatureInCelsius);
  }

  /**
   * Ng = Stickstoff
   */
  public StateOfAggregation getCurrentStateOfAggregationOfElementN() {
    return getCurrentStateOfAggregation(-210.1, -196);
  }

  /**
   * Hg = Quecksilber
   */
  public StateOfAggregation getCurrentStateOfAggregationOfElementHg() {
    return getCurrentStateOfAggregation(-38.83, 357);
  }

  /**
   * PB = Blei
   */
  public StateOfAggregation getCurrentStateOfAggregationOfElementPb() {
    return getCurrentStateOfAggregation(327.43, 1744);
  }

  private StateOfAggregation getCurrentStateOfAggregation(final double meltingPoint, final double boilingPoint) {
    if (currentTemperatureInCelsius > meltingPoint && currentTemperatureInCelsius < boilingPoint) {
      return StateOfAggregation.LIQUID;
    }
    if (currentTemperatureInCelsius < meltingPoint) {
      return StateOfAggregation.SOLID;
    }
    return StateOfAggregation.GAS;
  }
}
