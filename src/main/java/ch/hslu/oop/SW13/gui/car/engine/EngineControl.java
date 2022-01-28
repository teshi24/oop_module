package ch.hslu.oop.SW13.gui.car.engine;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.beans.PropertyChangeEvent;

public class EngineControl {
  private static final Logger LOG = LogManager.getLogger(EngineControl.class);

  private final Engine engine;
  private final EngineView engineView;

  public EngineControl(final Engine engine, final EngineView engineView) {
    if (engine == null) {
      throw new NullPointerException("engine mustn't be null");
    }
    if (engineView == null) {
      throw new NullPointerException("engineView mustn't be null");
    }
    this.engine = engine;
    this.engineView = engineView;

    if (engine.isSwitchedOn()) {
      updateEngineViewSwitchedOn();
    } else {
      updateEngineViewRpmValue();
    }

    engine.addPropertyChangeListener(this::handleEngineEvent);
    engineView.addPropertyChangeListener(this::handleEngineViewEvent);
  }

  private void updateEngineViewSwitchedOn() {
    engineView.setStateOn();
    updateEngineViewRpmValue();
  }

  private void updateEngineViewSwitchedOff() {
    engineView.setStateOff();
    updateEngineViewRpmValue();
  }

  private void updateEngineViewRpmValue() {
    engineView.setRPM(engine.getRpm(), engine.isMinRpmReached());
  }

  private void handleEngineEvent(final PropertyChangeEvent event) {
    final String propertyName = event.getPropertyName();
    if ("engineState".equals(propertyName)) {
      if (EngineState.ON.equals(event.getNewValue())) {
        updateEngineViewSwitchedOn();
      } else if (EngineState.OFF.equals(event.getNewValue())) {
        updateEngineViewSwitchedOff();
      }
    } else if ("rpm".equals(propertyName)) {
      updateEngineViewRpmValue();
    } else {
      LOG.error("Unknown PropertyChangeEvent happened: {}", event);
    }
  }

  private void handleEngineViewEvent(final PropertyChangeEvent eventView) {
    final String propertyName = eventView.getPropertyName();
    if ("switchState".equals(propertyName)) {
      if (EngineState.ON.equals(eventView.getNewValue())) {
        engine.switchOn();
      } else if (EngineState.OFF.equals(eventView.getNewValue())) {
        engine.switchOff();
      }
    } else if ("increaseRpm".equals(propertyName)) {
      engine.increaseRpm();
    } else if ("decreaseRpm".equals(propertyName)) {
      engine.decreaseRpm();
    } else {
      LOG.error("Unknown PropertyChangeEvent happened: {}", eventView);
    }
  }
}
