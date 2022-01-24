package ch.hslu.oop.SW10.eventHandling.temperature;

import org.junit.jupiter.api.Test;

class TemperatureMinChangeEventTest {

  @Test
  void canCreateTemperatureMaxEvent() {
    final Temperature minTemperature = Temperature.createFromCelsius(1);
    new TemperatureMinChangeEvent(this, minTemperature);
  }
}