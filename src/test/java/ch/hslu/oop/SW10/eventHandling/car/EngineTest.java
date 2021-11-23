package ch.hslu.oop.SW10.eventHandling.car;

import org.junit.jupiter.api.BeforeEach;

class EngineTest extends SwitchableTest<Engine> {

  @Override
  @BeforeEach
  void setupTestee() {
    this.testee = new Engine();
  }
}