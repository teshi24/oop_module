package ch.hslu.oop.SW10.eventHandling.car;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class CarTest extends SwitchableTest<Car> {

  @Override
  @BeforeEach
  void setupTestee() {
    this.testee = new Car();
  }

  @Test
  void switchOn_callsEngineSwitchOn() {
    final Car car = spy(Car.class);
    car.switchOn();
    verify(car, times(1)).isSwitchedOff();
  }
}