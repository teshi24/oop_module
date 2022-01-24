package ch.hslu.oop.SW10.eventHandling.car;

import ch.hslu.oop.SW10.eventHandling.car.engine.Engine;
import ch.hslu.oop.SW10.eventHandling.car.engine.EngineState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.beans.PropertyChangeEvent;

import static ch.hslu.oop.SW10.eventHandling.car.engine.EngineTest.getEngineStateChangeEvent;
import static ch.hslu.oop.SW10.eventHandling.car.engine.EngineTest.matchesPropertyChangeEvent;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class CarTest extends SwitchableTest<Car> {

  @Override
  @BeforeEach
  public void setupTestee() {
    testee = new Car();
  }

  @Test
  void listensToEvents() {
    testee.propertyChange(new PropertyChangeEvent(this, "property", "old", "new"));
  }

  @Test
  void switchOn_switchesOnEngine() {
    final Car testeeSpy = spy(Car.class);
    testeeSpy.switchOn();

    final PropertyChangeEvent expectedEngineStateChangeEvent = getEngineStateChangeEvent(mock(Engine.class),
                                                                                         EngineState.OFF,
                                                                                         EngineState.ON);

    verify(testeeSpy, times(1)).propertyChange(argThat(matchesPropertyChangeEvent(expectedEngineStateChangeEvent)));
  }

  @Test
  void switchOff_switchesOffEngine() {
    final Car testeeSpy = spy(Car.class);
    testeeSpy.switchOn();
    reset(testeeSpy);

    testeeSpy.switchOff();

    final PropertyChangeEvent expectedEngineStateChangeEvent = getEngineStateChangeEvent(mock(Engine.class),
                                                                                         EngineState.ON,
                                                                                         EngineState.OFF);

    verify(testeeSpy, times(1)).propertyChange(argThat(matchesPropertyChangeEvent(expectedEngineStateChangeEvent)));
  }
}
