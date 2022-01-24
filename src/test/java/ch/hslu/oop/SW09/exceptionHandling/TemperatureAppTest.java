package ch.hslu.oop.SW09.exceptionHandling;

import ch.hslu.oop.SW09.exceptionHandling.temperature.Temperature;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TemperatureAppTest {

  @Test
  void handleInput_integerAsString_returnsTemperature() {
    assertThat(TryNCatchDemo.parseInputToTemperature("1")).isEqualTo(Temperature.createFromCelsius(1));
  }

  @Test
  void handleInput_doubleAsString_returnsTemperature() {
    assertThat(TryNCatchDemo.parseInputToTemperature("1.1")).isEqualTo(Temperature.createFromCelsius(1.1));
  }

  @Test
  void handleInput_null_returnsNull() {
    assertThat(TryNCatchDemo.parseInputToTemperature(null)).isNull();
  }

  @Test
  void handleInput_randomString_returnsNull() {
    assertThat(TryNCatchDemo.parseInputToTemperature("random")).isNull();
  }

  @Test
  void programShouldExit_null_false() {
    assertFalse(TryNCatchDemo.programShouldExit(null));
  }

  @Test
  void programShouldExit_randomString_false() {
    assertFalse(TryNCatchDemo.programShouldExit("random"));
  }

  @Test
  void programShouldExit_exit_true() {
    assertTrue(TryNCatchDemo.programShouldExit("exit"));
  }
}