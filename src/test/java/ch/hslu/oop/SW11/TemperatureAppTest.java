package ch.hslu.oop.SW11;

import ch.hslu.oop.SW11.temperature.Temperature;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TemperatureAppTest {

  @Test
  void handleInput_integerAsString_returnsTemperature() {
    assertThat(TemperatureApp.parseInputToTemperature("1")).isEqualTo(Temperature.createFromCelsius(1));
  }

  @Test
  void handleInput_doubleAsString_returnsTemperature() {
    assertThat(TemperatureApp.parseInputToTemperature("1.1")).isEqualTo(Temperature.createFromCelsius(1.1));
  }

  @Test
  void handleInput_null_returnsNull() {
    assertThat(TemperatureApp.parseInputToTemperature(null)).isNull();
  }

  @Test
  void handleInput_randomString_returnsNull() {
    assertThat(TemperatureApp.parseInputToTemperature("random")).isNull();
  }

  @Test
  void programShouldExit_null_false() {
    assertFalse(TemperatureApp.programShouldExit(null));
  }

  @Test
  void programShouldExit_randomString_false() {
    assertFalse(TemperatureApp.programShouldExit("random"));
  }

  @Test
  void programShouldExit_exit_true() {
    assertTrue(TemperatureApp.programShouldExit("exit"));
  }
}