package ch.hslu.oop.SW10.eventHandling.car;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public abstract class SwitchableTest<T extends Switchable> {

  T testee;

  @BeforeEach
  abstract void setupTestee();

  @Test
  void isSwitchedOffPerDefault() {
    assertTrue(testee.isSwitchedOff());
  }

  @Test
  void canGetSwitchedOn() {
    testee.switchOn();
    assertTrue(testee.isSwitchedOn());
  }

  @Test
  void canGetSwitchedOff() {
    testee.switchOn();
    testee.switchOff();
    assertTrue(testee.isSwitchedOff());
  }
}
