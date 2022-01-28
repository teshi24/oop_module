package ch.hslu.oop.SW12.streams.temperature;

import org.junit.jupiter.api.Test;

class TemperatureMaxChangeEventTest {

  @Test
  void canCreateTemperatureMaxEvent() {
    final Temperature maxTemperature = Temperature.createFromCelsius(1);
    new TemperatureMaxChangeEvent(this, maxTemperature);
  }
}