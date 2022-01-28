package ch.hslu.oop.SW13.gui.car.engine;

import ch.hslu.oop.SW13.gui.car.Switchable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.core.util.VisibleForTesting;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import static ch.hslu.oop.SW13.gui.exceptionhandling.Warnings.varShouldNotBeNull;

public class Engine implements Switchable {
  private static final Logger LOG = LogManager.getLogger(Engine.class);

  private final List<PropertyChangeListener> listeners = new ArrayList<>();
  private EngineState engineState = EngineState.OFF;
  private int rpm = 0;

  @Override
  public void switchOn() {
    if (isSwitchedOff()) {
      final EngineState oldEngineState = engineState;
      engineState = EngineState.ON;
      rpm = 500;
      firePropertyChangeEvent(new PropertyChangeEvent(this, "engineState", oldEngineState, engineState));
    }
  }

  @Override
  public void switchOff() {
    if (isSwitchedOn()) {
      final EngineState oldEngineState = engineState;
      engineState = EngineState.OFF;
      rpm = 0;
      firePropertyChangeEvent(new PropertyChangeEvent(this, "engineState", oldEngineState, engineState));
    }
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
    LOG.info("PropertyChangeEvent fired: {}", event);
    for (final PropertyChangeListener listener : listeners) {
      listener.propertyChange(event);
    }
  }

  @Override
  public boolean isSwitchedOn() {
    return engineState == EngineState.ON;
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
