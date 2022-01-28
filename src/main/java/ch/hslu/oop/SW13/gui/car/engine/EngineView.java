package ch.hslu.oop.SW13.gui.car.engine;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import static ch.hslu.oop.SW13.gui.exceptionhandling.Warnings.varShouldNotBeNull;

public class EngineView extends VBox {
  private static final Logger LOG = LogManager.getLogger(EngineView.class);

  private final List<PropertyChangeListener> listeners = new ArrayList<>();

  private final Label labelState;
  private final Button buttonOn;
  private final Button buttonOff;
  private final Label labelRPM;
  private final Button buttonIncreaseRPM;
  private final Button buttonDecreaseRPM;

  public EngineView() {
    labelState = new Label();
    buttonOn = new Button(Config.Texts.ON);
    buttonOn.setOnAction(actionEvent -> {
      firePropertyChangeEvent(new PropertyChangeEvent(this, "switchState", EngineState.OFF, EngineState.ON));
    });
    buttonOff = new Button(Config.Texts.OFF);
    buttonOff.setOnAction(actionEvent -> {
      firePropertyChangeEvent(new PropertyChangeEvent(this, "switchState", EngineState.ON, EngineState.OFF));
    });

    labelRPM = new Label();
    buttonIncreaseRPM = new Button(Config.Texts.INCREASE_RPM);
    buttonIncreaseRPM.setOnAction(actionEvent -> {
      firePropertyChangeEvent(new PropertyChangeEvent(this, "increaseRpm", null, null));
    });
    buttonDecreaseRPM = new Button(Config.Texts.DECREASE_RPM);
    buttonDecreaseRPM.setOnAction(actionEvent -> {
      firePropertyChangeEvent(new PropertyChangeEvent(this, "decreaseRpm", null, null));
    });

    createLayout();
    setStateOff();
  }

  private void createLayout() {
    final BorderPane statePane = new BorderPane();
    statePane.setTop(buttonOn);
    statePane.setCenter(labelState);
    statePane.setBottom(buttonOff);

    final BorderPane rpmPane = new BorderPane();
    rpmPane.setTop(buttonIncreaseRPM);
    rpmPane.setCenter(labelRPM);
    rpmPane.setBottom(buttonDecreaseRPM);

    getChildren().addAll(statePane, rpmPane);
  }

  public String getTitle() {
    return Config.Texts.TITLE;
  }

  public void setStateOn() {
    labelState.setText(Config.Texts.IS_ON);
    labelState.setStyle(Config.Styles.LABEL_ON);
    buttonOn.setDisable(true);
    buttonOff.setDisable(false);
  }

  public void setStateOff() {
    labelState.setText(Config.Texts.IS_OFF);
    labelState.setStyle(Config.Styles.LABEL_OFF);
    buttonOn.setDisable(false);
    buttonOff.setDisable(true);
  }

  public void setRPM(final int rpm, final boolean minAmountReached) {
    labelRPM.setText("" + rpm);
    buttonIncreaseRPM.setDisable(false);
    buttonDecreaseRPM.setDisable(minAmountReached);
  }

  private static class Config {
    private static class Texts {
      private static final String IS_OFF = "The switch is OFF.";
      private static final String IS_ON = "The switch is ON.";
      private static final String ON = "On";
      private static final String OFF = "Off";
      private static final String TITLE = "Switch GUI with JavaFX";
      private static final String INCREASE_RPM = "Increase RPM";
      private static final String DECREASE_RPM = "Decrease RPM";
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
