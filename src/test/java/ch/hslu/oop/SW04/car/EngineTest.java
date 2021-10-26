package ch.hslu.oop.SW04.car;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class EngineTest {

  private Engine engine;

  @BeforeEach
  void setUp() {
    engine = new Engine();
  }

  @Test
  void isSwitchedOffPerDefault() {
    assertTrue(engine.isSwitchedOff());
  }

  @Test
  void canGetSwitchedOn() {
    engine.switchOn();
    assertTrue(engine.isSwitchedOn());
  }

  @Test
  void canGetSwitchedOff() {
    engine.switchOn();
    engine.switchOff();
    assertTrue(engine.isSwitchedOff());
  }
}