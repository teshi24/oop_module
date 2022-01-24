package ch.hslu.oop.SW10.eventHandling.car.engine;

import ch.hslu.oop.SW10.eventHandling.car.Switchable;
import org.assertj.core.util.VisibleForTesting;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import static ch.hslu.oop.SW10.exceptionhandling.Warnings.varShouldNotBeNull;

public class Engine implements Switchable {

  private EngineState engineState;
  private int rpm = 0;

  private final List<PropertyChangeListener> listeners = new ArrayList<>();

  @Override
  public void switchOn() {
    if (isSwitchedOff()) {
      engineState = EngineState.ON;
      rpm = 500;
    }
  }

  @Override
  public void switchOff() {
    if (isSwitchedOn()) {
      engineState = EngineState.OFF;
      rpm = 0;
    }
  }

  @Override
  public boolean isSwitchedOn() {
    return engineState == EngineState.ON;
  }

  @Override
  public boolean isSwitchedOff() {
    return !isSwitchedOn();
  }

  /**
   * Informs all PropertyChangeListeners about the PropertyChangeEvent taken place
   *
   * @param event PropertyChangeEvent.
   */
  private void firePropertyChangeEvent(final PropertyChangeEvent event) {
    for (final PropertyChangeListener listener : this.listeners) {
      listener.propertyChange(event);
    }
  }

  /**
   * Registers a listener wo will be informed when {@link Engine#firePropertyChangeEvent(PropertyChangeEvent)} is
   * called.
   *
   * @param listener new listener to be registered
   * @throws NullPointerException if listener is null
   */
  public void addPropertyChangeListener(final PropertyChangeListener listener) {
    if (listener == null) {
      throw new NullPointerException(varShouldNotBeNull("listener"));
    }
    listeners.add(listener);
  }

  /**
   * Unregister listener so that it will no longer be informed when
   * {@link Engine#firePropertyChangeEvent(PropertyChangeEvent)} is called.
   *
   * @param listener listener to be unregistered
   */
  public void removePropertyChangeListener(final PropertyChangeListener listener) {
    listeners.remove(listener);
  }

  @VisibleForTesting
  List<PropertyChangeListener> getListeners() {
    return listeners;
  }
}
