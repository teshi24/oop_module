package ch.hslu.oop.SW10.eventHandling;

import ch.hslu.oop.SW10.eventHandling.temperature.Temperature;
import ch.hslu.oop.SW10.eventHandling.temperature.TemperatureCourse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class TemperatureApp {

  private static final Logger LOG = LogManager.getLogger(TemperatureApp.class);

  public static void main(final String[] args) {
    String input;
    final Scanner scanner = new Scanner(System.in);
    final TemperatureCourse course = new TemperatureCourse();

    course.addTemperatureMinChangeListener(e -> LOG.info("Sie haben gerade eine neue Minimum-Temperatur eingegeben!"));
    course.addTemperatureMaxChangeListener(e -> LOG.info("Sie haben gerade eine neue Maximum-Temperatur eingegeben!"));

    do {
      LOG.info("Bitte Temperatur eingeben ('exit' zum Beenden): ");
      input = scanner.next();
      if (!programShouldExit(input)) {
        course.add(parseInputToTemperature(input));
      }
    } while (!programShouldExit(input));
    printStats(course);
    LOG.info("Programm beendet.");
  }

  static Temperature parseInputToTemperature(final String input) {
    try {
      return Temperature.createFromCelsius(Double.parseDouble(input));
    } catch (final NullPointerException | NumberFormatException exception) {
      LOG.error("'{}' ist kein gültiger Input. Bitte versuchen Sie es erneut.\n" + //
                "Exception: {}", //
                input, exception);
      return null;
    }
  }

  static void printStats(final TemperatureCourse course) {
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

  static boolean programShouldExit(final String input) {
    return "exit".equals(input);
  }
}
