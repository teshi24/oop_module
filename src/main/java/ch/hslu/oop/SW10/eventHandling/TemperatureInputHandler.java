package ch.hslu.oop.SW10.eventHandling;

import ch.hslu.oop.SW10.eventHandling.temperature.Temperature;
import ch.hslu.oop.SW10.eventHandling.temperature.TemperatureCourse;
import ch.hslu.oop.SW10.eventHandling.temperature.TemperatureMaxChangeEvent;
import ch.hslu.oop.SW10.eventHandling.temperature.TemperatureMaxChangeListener;
import ch.hslu.oop.SW10.eventHandling.temperature.TemperatureMinChangeEvent;
import ch.hslu.oop.SW10.eventHandling.temperature.TemperatureMinChangeListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TemperatureInputHandler implements TemperatureMaxChangeListener, TemperatureMinChangeListener {

  private static final Logger LOG = LogManager.getLogger(TemperatureInputHandler.class);

  Temperature parseInputToTemperature(final String input) {
    try {
      return Temperature.createFromCelsius(Double.parseDouble(input));
    } catch (final NullPointerException | NumberFormatException exception) {
      LOG.error("'{}' ist kein gültiger Input. Bitte versuchen Sie es erneut.\n" + //
                "Exception: {}", //
                input, exception);
      return null;
    }
  }

  @Override
  public void temperatureMaxChange(final TemperatureMaxChangeEvent temperatureMaxChangeEvent) {
    LOG.info("Sie haben gerade eine neue Maximum-Temperatur eingegeben!");
  }

  @Override
  public void temperatureMinChange(final TemperatureMinChangeEvent temperatureMinChangeEvent) {
    LOG.info("Sie haben gerade eine neue Minimum-Temperatur eingegeben!");
  }

  void printStats(final TemperatureCourse course) {
    if (course == null || course.getTemperatures().isEmpty()) {
      LOG.warn("Da keine Daten eingegeben wurden, kann keine Statistik generiert werden.");
      return;
    }

    LOG.info("Statistik");
    LOG.info("---------");
    LOG.info("Anzahl Temperaturwerte: {}", course.getCount());
    LOG.info("Durchschnittstemperatur: {}", course.getAverage());
    LOG.info("Min Temperatur: {}", course.getMinTemperature());
    LOG.info("Max Temperatur: {}", course.getMaxTemperature());
  }

  boolean programShouldExit(final String input) {
    return "exit".equals(input);
  }
}