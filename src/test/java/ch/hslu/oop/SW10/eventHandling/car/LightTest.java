package ch.hslu.oop.SW10.eventHandling.car;

import org.junit.jupiter.api.BeforeEach;

class LightTest extends SwitchableTest<Light> {

  @Override
  @BeforeEach
  public void setupTestee() {
    this.testee = new Light();
  }
}