package ch.hslu.oop.SW08.final_static_enum_collections.temperature;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class TemperatureCourse {

  private final List<Temperature> temperatures;

  public TemperatureCourse() {
    temperatures = new ArrayList<>();
  }

  public List<Temperature> getTemperatures() {
    return new ArrayList<>(temperatures);
  }

  public boolean add(final Temperature temperature) {
    if (temperature == null) {
      return false;
    }
    return temperatures.add(new Temperature(temperature));
  }

  public void clear() {
    temperatures.clear();
  }

  public int getCount() {
    return temperatures.size();
  }

  public Temperature getMax() {
    if (temperatures.isEmpty()) {
      return null;
    }

    Temperature maxTemperature = null;
    for (final Temperature temperature : temperatures) {
      if (temperature.compareTo(maxTemperature) > 0) {
        maxTemperature = temperature;
      }
    }

    return new Temperature(maxTemperature);
  }

  /*
  Note: getMax könnte natürlich auch mit Collections.max implementiert werden
  bzw., getMin auch mit der Iteration.
   */

  public Temperature getMin() {
    if (temperatures.isEmpty()) {
      return null;
    }
    return Collections.min(temperatures);
  }

  public Temperature getAverage() {
    if (temperatures.isEmpty()) {
      return null;
    }
    double temperatureSum = 0;
    for (final Temperature temperature : temperatures) {
      temperatureSum += temperature.getCurrentTemperatureInCelsius();
    }
    final double averageTemperatureInCelsius = temperatureSum / getCount();

    return new Temperature(averageTemperatureInCelsius);
  }
}
