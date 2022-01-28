package ch.hslu.oop.SW13.gui.car;

import org.junit.jupiter.api.BeforeEach;

class LightTest extends SwitchableTest<Light> {

  @Override
  @BeforeEach
  public void setupTestee() {
    testee = new Light();
  }
}