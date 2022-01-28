package ch.hslu.oop.SW13.gui.car.engine;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import static ch.hslu.oop.SW13.gui.exceptionhandling.Warnings.varShouldNotBeNull;

public class EngineView extends BorderPane {
  private static final Logger LOG = LogManager.getLogger(EngineView.class);

  private final List<PropertyChangeListener> listeners = new ArrayList<>();

  private final Label label;
  private final Button buttonOn;
  private final Button buttonOff;

  public EngineView() {
    label = new Label();
    buttonOn = new Button(Config.Texts.ON);
    buttonOff = new Button(Config.Texts.OFF);

    buttonOn.setOnAction(actionEvent -> {
      firePropertyChangeEvent(new PropertyChangeEvent(this, "switchState", EngineState.OFF, EngineState.ON));
    });
    buttonOff.setOnAction(actionEvent -> {
      firePropertyChangeEvent(new PropertyChangeEvent(this, "switchState", EngineState.ON, EngineState.OFF));
    });

    createLayout();
    setStateOff();
  }

  private void createLayout() {
    setTop(buttonOn);
    setCenter(label);
    setBottom(buttonOff);
  }

  public String getTitle() {
    return Config.Texts.TITLE;
  }

  public void setStateOn() {
    label.setText(Config.Texts.IS_ON);
    label.setStyle(Config.Styles.LABEL_ON);
    buttonOn.setDisable(true);
    buttonOff.setDisable(false);
  }

  public void setStateOff() {
    label.setText(Config.Texts.IS_OFF);
    label.setStyle(Config.Styles.LABEL_OFF);
    buttonOn.setDisable(false);
    buttonOff.setDisable(true);
  }

  private static class Config {
    private static class Texts {
      private static final String IS_OFF = "The switch is OFF.";
      private static final String IS_ON = "The switch is ON.";
      private static final String ON = "On";
      private static final String OFF = "Off";
      private static final String TITLE = "Switch GUI with JavaFX";
    }

    private static class Styles {
      private static final String LABEL_ON = "-fx-background-color: lightgreen;";
      private static final String LABEL_OFF = "-fx-background-color: red;";
    }
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

  /**
   * Registers a listener wo will be informed when {@link #firePropertyChangeEvent(PropertyChangeEvent)} is
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
   * {@link #firePropertyChangeEvent(PropertyChangeEvent)} is called.
   *
   * @param listener listener to be unregistered
   */
  public void removePropertyChangeListener(final PropertyChangeListener listener) {
    listeners.remove(listener);
  }
}
