package ch.hslu.oop.SW10.eventHandling.car;

import org.junit.jupiter.api.BeforeEach;

class AirConditioningTest extends SwitchableTest<AirConditioning> {

  @Override
  @BeforeEach
  void setupTestee() {
    this.testee = new AirConditioning();
  }
}