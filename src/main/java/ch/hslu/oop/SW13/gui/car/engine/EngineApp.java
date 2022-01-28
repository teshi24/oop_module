package ch.hslu.oop.SW13.gui.car.engine;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EngineApp extends Application {
  private static final Logger LOG = LogManager.getLogger(EngineApp.class);

  public static void main(final String[] args) {
    LOG.info("App startet.");
    launch(EngineApp.class, args);
  }

  @Override
  public void start(final Stage primaryStage) {
    final EngineView engineView = new EngineView();
    final Engine engine = new Engine();
    // engine.switchOn();
    final EngineControl engineControl = new EngineControl(engine, engineView);

    primaryStage.setTitle(engineView.getTitle());
    primaryStage.setScene(new Scene(engineView, 400, 400));
    primaryStage.show();

    LOG.info("GUI-Stage aktiviert und sichtbar.");
  }
}
