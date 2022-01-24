package ch.hslu.oop.SW10.eventHandling;

import ch.hslu.oop.SW10.eventHandling.temperature.Temperature;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TemperatureInputHandlerTest {
  private TemperatureInputHandler inputHandler;

  @BeforeEach
  void setUp() {
    inputHandler = new TemperatureInputHandler();
  }

  @Test
  void handleInput_integerAsString_returnsTemperature() {
    assertThat(inputHandler.parseInputToTemperature("1")).isEqualTo(Temperature.createFromCelsius(1));
  }

  @Test
  void handleInput_doubleAsString_returnsTemperature() {
    assertThat(inputHandler.parseInputToTemperature("1.1")).isEqualTo(Temperature.createFromCelsius(1.1));
  }

  @Test
  void handleInput_null_returnsNull() {
    assertThat(inputHandler.parseInputToTemperature(null)).isNull();
  }

  @Test
  void handleInput_randomString_returnsNull() {
    assertThat(inputHandler.parseInputToTemperature("random")).isNull();
  }

  @Test
  void programShouldExit_null_false() {
    assertFalse(inputHandler.programShouldExit(null));
  }

  @Test
  void programShouldExit_randomString_false() {
    assertFalse(inputHandler.programShouldExit("random"));
  }

  @Test
  void programShouldExit_exit_true() {
    assertTrue(inputHandler.programShouldExit("exit"));
  }
}