package ch.hslu.oop.SW09.exceptionHandling;

import ch.hslu.oop.SW09.exceptionHandling.temperature.Temperature;
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
  public static void main(String[] args) {
    String input;
    Scanner scanner = new Scanner(System.in);
    do {
      System.out.println("Bitte Temperatur eingeben ('exit' zum Beenden): ");
      input = scanner.next();
      if (!programShouldExit(input)) {
        final Temperature chosenTemperature = parseInputToTemperature(input);
      }
    } while (!programShouldExit(input));
    System.out.println("Programm beendet.");
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

  @VisibleForTesting
  static boolean programShouldExit(final String input) {
    return "exit".equals(input);
  }
}
