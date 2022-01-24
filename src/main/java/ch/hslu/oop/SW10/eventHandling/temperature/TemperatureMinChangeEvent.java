package ch.hslu.oop.SW10.eventHandling.temperature;

import java.util.EventObject;

public class TemperatureMinChangeEvent extends EventObject {

  private final Temperature minTemperature;

  public TemperatureMinChangeEvent(final Object source, final Temperature minTemperature) {
    super(source);
    this.minTemperature = minTemperature;
  }

  public Temperature getMinTemperature() {
    return new Temperature(minTemperature);
  }
}
