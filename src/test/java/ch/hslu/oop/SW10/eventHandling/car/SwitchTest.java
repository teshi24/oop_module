package ch.hslu.oop.SW10.eventHandling.car;

import org.junit.jupiter.api.BeforeEach;

class SwitchTest extends SwitchableTest<Switch> {

  @Override
  @BeforeEach
  void setupTestee() {
    this.testee = new Switch();
  }
}