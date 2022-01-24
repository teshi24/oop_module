package ch.hslu.oop.SW10.eventHandling.car.engine;

import ch.hslu.oop.SW10.eventHandling.car.SwitchableTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.beans.PropertyChangeListener;

import static ch.hslu.oop.SW10.exceptionhandling.Warnings.varShouldNotBeNull;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

class EngineTest extends SwitchableTest<Engine> {

  @Override
  @BeforeEach
  public void setupTestee() {
    this.testee = new Engine();
  }

  @Test
  void switchOn_propertyChangeEventTriggered() {
    // PropertyChangeEvent
  }

  @Test
  void switchOff_propertyChangeEventTriggered() {

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