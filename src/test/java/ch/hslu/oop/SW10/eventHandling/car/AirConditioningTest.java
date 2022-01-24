package ch.hslu.oop.SW10.eventHandling.car;

import org.junit.jupiter.api.BeforeEach;

class AirConditioningTest extends SwitchableTest<AirConditioning> {

  @Override
  @BeforeEach
  public void setupTestee() {
    this.testee = new AirConditioning();
  }
}