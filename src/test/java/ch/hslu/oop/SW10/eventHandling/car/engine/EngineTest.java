package ch.hslu.oop.SW10.eventHandling.car.engine;

import ch.hslu.oop.SW10.eventHandling.car.SwitchableTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatcher;
import org.mockito.Mockito;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import static ch.hslu.oop.SW10.exceptionhandling.Warnings.varShouldNotBeNull;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class EngineTest extends SwitchableTest<Engine> {

  public static ArgumentMatcher<PropertyChangeEvent> matchesPropertyChangeEvent(final PropertyChangeEvent expectedEvent) {
    return arg -> expectedEvent.getSource().getClass() == arg.getSource().getClass() && //
                  expectedEvent.getOldValue().equals(arg.getOldValue()) && //
                  expectedEvent.getNewValue().equals(arg.getNewValue()) && //
                  expectedEvent.getPropertyName().equals(arg.getPropertyName());
  }

  public static PropertyChangeEvent getEngineStateChangeEvent(final Engine engine,
                                                              final EngineState expectedOldEngineState,
                                                              final EngineState expectedNewEngineState) {
    return new PropertyChangeEvent(engine, "engineState", expectedOldEngineState, expectedNewEngineState);
  }

  @Override
  @BeforeEach
  public void setupTestee() {
    testee = new Engine();
  }

  @Test
  void switchOn_noListenerRegistered_canSwitchOn() {
    final Engine testeeSpy = Mockito.spy(Engine.class);
    testeeSpy.switchOn();
  }

  @Test
  void switchOn_singleListenerRegistered_informsAboutPropertyChangeEvent() {
    final Engine testeeSpy = Mockito.spy(Engine.class);
    final PropertyChangeListener listener = Mockito.spy(PropertyChangeListener.class);
    testeeSpy.addPropertyChangeListener(listener);

    testeeSpy.switchOn();

    final PropertyChangeEvent expectedEngineStateChangeEvent = getEngineStateChangeEvent(testeeSpy, EngineState.OFF,
                                                                                         EngineState.ON);
    verify(listener, times(1)).propertyChange(argThat(matchesPropertyChangeEvent(expectedEngineStateChangeEvent)));
  }

  @Test
  void switchOn_multipleListenerRegistered_informsAboutPropertyChangeEvent() {
    final Engine testeeSpy = Mockito.spy(Engine.class);
    final PropertyChangeListener listener1 = Mockito.spy(PropertyChangeListener.class);
    final PropertyChangeListener listener2 = Mockito.spy(PropertyChangeListener.class);
    testeeSpy.addPropertyChangeListener(listener1);
    testeeSpy.addPropertyChangeListener(listener2);

    testeeSpy.switchOn();

    final PropertyChangeEvent expectedEngineStateChangeEvent = getEngineStateChangeEvent(testeeSpy, EngineState.OFF,
                                                                                         EngineState.ON);
    verify(listener1, times(1)).propertyChange(argThat(matchesPropertyChangeEvent(expectedEngineStateChangeEvent)));
    verify(listener2, times(1)).propertyChange(argThat(matchesPropertyChangeEvent(expectedEngineStateChangeEvent)));
  }

  @Test
  void switchOff_noListenerRegistered_canSwitchOff() {
    final Engine testeeSpy = Mockito.spy(Engine.class);
    testeeSpy.switchOn();
    testeeSpy.switchOff();
  }

  @Test
  void switchOff_singleListenerRegistered_informsAboutPropertyChangeEvent() {
    final Engine testeeSpy = Mockito.spy(Engine.class);
    testeeSpy.switchOn();
    final PropertyChangeListener listener = Mockito.spy(PropertyChangeListener.class);
    testeeSpy.addPropertyChangeListener(listener);

    testeeSpy.switchOff();

    final PropertyChangeEvent expectedEngineStateChangeEvent = getEngineStateChangeEvent(testeeSpy, EngineState.ON,
                                                                                         EngineState.OFF);
    verify(listener, times(1)).propertyChange(argThat(matchesPropertyChangeEvent(expectedEngineStateChangeEvent)));
  }

  @Test
  void switchOff_multipleListenerRegistered_informsAboutPropertyChangeEvent() {
    final Engine testeeSpy = Mockito.spy(Engine.class);
    testeeSpy.switchOn();
    final PropertyChangeListener listener1 = Mockito.spy(PropertyChangeListener.class);
    final PropertyChangeListener listener2 = Mockito.spy(PropertyChangeListener.class);
    testeeSpy.addPropertyChangeListener(listener1);
    testeeSpy.addPropertyChangeListener(listener2);

    testeeSpy.switchOff();

    final PropertyChangeEvent expectedEngineStateChangeEvent = getEngineStateChangeEvent(testeeSpy, EngineState.ON,
                                                                                         EngineState.OFF);
    verify(listener1, times(1)).propertyChange(argThat(matchesPropertyChangeEvent(expectedEngineStateChangeEvent)));
    verify(listener2, times(1)).propertyChange(argThat(matchesPropertyChangeEvent(expectedEngineStateChangeEvent)));
  }

  @Test
  void addPropertyChangeListener_null_throwsNullPointerExceptionAndDoesNotAddListener() {
    final Exception thrownException = assertThrows(NullPointerException.class,
                                                   () -> testee.addPropertyChangeListener(null));
    assertThat(thrownException.getMessage()).isEqualTo(varShouldNotBeNull("listener"));

    assertThat(testee.getListeners()).isEmpty();
  }

  @Test
  void addPropertyChangeListener_listener_listenerRegistered() {
    final PropertyChangeListener listener = mock(PropertyChangeListener.class);
    testee.addPropertyChangeListener(listener);
    assertThat(testee.getListeners()).containsExactly(listener);
  }

  @Test
  void removePropertyChangeListener_null_doesNotRemoveRegisteredListeners() {
    final PropertyChangeListener listener = mock(PropertyChangeListener.class);
    testee.addPropertyChangeListener(listener);

    testee.removePropertyChangeListener(null);

    assertThat(testee.getListeners()).containsExactly(listener);
  }

  @Test
  void removePropertyChangeListener_onlyRegisteredListener_noListenerRegistered() {
    final PropertyChangeListener listener = mock(PropertyChangeListener.class);
    testee.addPropertyChangeListener(listener);

    testee.removePropertyChangeListener(listener);

    assertThat(testee.getListeners()).isEmpty();
  }

  @Test
  void removePropertyChangeListener_oneOfRegisteredListeners_listenerNotRegisteredOthersStillRegistered() {
    final PropertyChangeListener listener = mock(PropertyChangeListener.class);
    final PropertyChangeListener listener2 = mock(PropertyChangeListener.class);
    testee.addPropertyChangeListener(listener);
    testee.addPropertyChangeListener(listener2);

    testee.removePropertyChangeListener(listener2);

    assertThat(testee.getListeners()).containsExactly(listener);
  }

  @Test
  void removePropertyChangeListener_noneOfRegisteredListeners_othersStillRegistered() {
    final PropertyChangeListener listener = mock(PropertyChangeListener.class);
    testee.addPropertyChangeListener(listener);

    final PropertyChangeListener listener2 = mock(PropertyChangeListener.class);
    testee.removePropertyChangeListener(listener2);

    assertThat(testee.getListeners()).containsExactly(listener);
  }
}
