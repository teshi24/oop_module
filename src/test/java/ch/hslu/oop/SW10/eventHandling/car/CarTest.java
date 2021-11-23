package ch.hslu.oop.SW10.eventHandling.car;

import org.junit.jupiter.api.BeforeEach;

class CarTest extends SwitchableTest<Car> {

  @Override
  @BeforeEach
  void setupTestee() {
    this.testee = new Car();
  }
}