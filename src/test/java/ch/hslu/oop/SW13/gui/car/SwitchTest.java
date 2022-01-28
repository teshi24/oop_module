package ch.hslu.oop.SW13.gui.car;

import org.junit.jupiter.api.BeforeEach;

class SwitchTest extends SwitchableTest<Switch> {

  @Override
  @BeforeEach
 public void setupTestee() {
    testee = new Switch();
  }
}