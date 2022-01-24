package ch.hslu.oop.SW10.eventHandling.temperature;

public enum TemperatureUnit {
  CELSIUS("°C", -273.15), //
  KELVIN("K", 0), //
  FAHRENHEIT("°F", -459.67);

  private final String printName;
  private final double minValue;

  TemperatureUnit(final String printName, final double minValue) {
    this.printName = printName;
    this.minValue = minValue;
  }

  @Override
  public String toString() {
    return printName;
  }

  public double getMinValue() {
    return minValue;
  }
}
