package ch.hslu.oop.SW10.eventHandling.car;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.beans.PropertyChangeEvent;

class CarTest extends SwitchableTest<Car> {

  @Override
  @BeforeEach
  public void setupTestee() {
    this.testee = new Car();
  }

  @Test
  void listensToEvents() {
    testee.propertyChange(new PropertyChangeEvent(this, "property", "old", "new"));
  }
}