package ch.hslu.oop.SW13.gui.car;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public abstract class SwitchableTest<T extends Switchable> {

  public T testee;

  @BeforeEach
  abstract public void setupTestee();

  @Test
  public void isSwitchedOffPerDefault() {
    assertTrue(testee.isSwitchedOff());
  }

  @Test
  public void isSwitchedOn_afterSwitchOn_True() {
    testee.switchOn();
    assertTrue(testee.isSwitchedOn());
  }

  @Test
  public void isSwitchedOn_afterSwitchOff_False() {
    testee.switchOn();
    testee.switchOff();
    assertFalse(testee.isSwitchedOn());
  }

  @Test
  void switchOn_callsIsSwitchedOff() {
    final T testeeSpy = spy(testee);
    testeeSpy.switchOn();
    verify(testeeSpy, times(1)).isSwitchedOff();
  }

  @Test
  public void isSwitchedOff_afterSwitchOff_True() {
    testee.switchOn();
    testee.switchOff();
    assertTrue(testee.isSwitchedOff());
  }

  @Test
  public void isSwitchedOff_afterSwitchOn_False() {
    testee.switchOn();
    assertFalse(testee.isSwitchedOff());
  }

  @Test
  void switchOff_callsIsSwitchedOn() {
    final T testeeSpy = spy(testee);
    testeeSpy.switchOff();
    verify(testeeSpy, times(1)).isSwitchedOn();
  }
}
