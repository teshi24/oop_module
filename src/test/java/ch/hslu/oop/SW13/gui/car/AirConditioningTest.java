package ch.hslu.oop.SW13.gui.car;

import org.junit.jupiter.api.BeforeEach;

class AirConditioningTest extends SwitchableTest<AirConditioning> {

  @Override
  @BeforeEach
  public void setupTestee() {
    testee = new AirConditioning();
  }
}