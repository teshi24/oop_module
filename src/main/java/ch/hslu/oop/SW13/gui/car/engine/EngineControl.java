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
      engineView.setStateOn();
    }

    engine.addPropertyChangeListener(this::handleEngineEvent);
    engineView.addPropertyChangeListener(this::handleEngineViewEvent);
  }

  private void handleEngineEvent(final PropertyChangeEvent event) {
    if ("engineState".equals(event.getPropertyName())) {
      if (EngineState.ON.equals(event.getNewValue())) {
        engineView.setStateOn();
        return;
      } else if (EngineState.OFF.equals(event.getNewValue())) {
        engineView.setStateOff();
        return;
      }
    }
    LOG.error("Unknown PropertyChangeEvent happened: {}", event);
  }
  private void handleEngineViewEvent(final PropertyChangeEvent eventView) {
    if ("switchState".equals(eventView.getPropertyName())) {
      if (EngineState.ON.equals(eventView.getNewValue())) {
        engine.switchOn();
        return;
      } else if (EngineState.OFF.equals(eventView.getNewValue())) {
        engine.switchOff();
        return;
      }
    }
    LOG.error("Unknown PropertyChangeEvent happened: {}", eventView);
  }
}
