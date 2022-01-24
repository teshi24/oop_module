package ch.hslu.oop.SW10.eventHandling;

import ch.hslu.oop.SW10.eventHandling.temperature.Temperature;
import ch.hslu.oop.SW10.eventHandling.temperature.TemperatureCourse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.core.util.VisibleForTesting;

import java.util.Scanner;

public class TryNCatchDemo {

  private static final Logger LOG = LogManager.getLogger(TryNCatchDemo.class);

  /**
   * my expectations how the code works:
   * - console ask for a temperature as an input
   * - if the input is a float, the program continues and another temperature can be entered
   * - if one enters 'exit', it is intended, that the program ends
   * - if one enters no number, the app will crash since there is no exception handling
   * -- when I enter exit (or another NaN String), the app will crash
   * <p>
   * real outcome:
   * - as expected, yeyy :) (or maybe not, cause the app is not working as it should ;))
   * <p>
   * - Float.valueOf throws a NumberFormatException
   * - it is a checked exception - the program crashes
   *
   * @param args parameters for the app start - are ignored ;)
   */
  public static void main(final String[] args) {
    String input;
    final Scanner scanner = new Scanner(System.in);
    final TemperatureCourse course = new TemperatureCourse();
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

  @VisibleForTesting
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

  private static void printStats(final TemperatureCourse course) {
    if (course == null || course.getTemperatures().isEmpty()) {
      LOG.warn("Da keine Daten eingegeben wurden, kann keine Statistik generiert werden.");
      return;
    }

    LOG.info("Statistik");
    LOG.info("---------");
    LOG.info("Anzahl Temperaturwerte: {}", course.getCount());
    LOG.info("Durchschnittstemperatur: {}", course.getAverage());
    LOG.info("Min Temperatur: {}", course.getMin());
    LOG.info("Max Temperatur: {}", course.getMax());
  }

  @VisibleForTesting
  static boolean programShouldExit(final String input) {
    return "exit".equals(input);
  }
}
