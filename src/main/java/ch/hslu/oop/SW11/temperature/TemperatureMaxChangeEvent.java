package ch.hslu.oop.SW11.temperature;

import java.util.EventObject;

public class TemperatureMaxChangeEvent extends EventObject {

  private final Temperature maxTemperature;

  public TemperatureMaxChangeEvent(final Object source, final Temperature maxTemperature) {
    super(source);
    this.maxTemperature = maxTemperature;
  }

  public Temperature getMaxTemperature() {
    return maxTemperature;
  }
}
