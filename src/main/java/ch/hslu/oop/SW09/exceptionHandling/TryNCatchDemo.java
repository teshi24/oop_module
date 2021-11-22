package ch.hslu.oop.SW09.exceptionHandling;

import ch.hslu.oop.SW09.exceptionHandling.temperature.Temperature;
import org.assertj.core.util.VisibleForTesting;

import java.util.Scanner;

public class TryNCatchDemo {
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
      final Temperature chosenTemperature = parseInputToTemperature(input);
    } while (!programShouldExit(input));
    System.out.println("Programm beendet.");
  }

  @VisibleForTesting
  static Temperature parseInputToTemperature(final String input) {
    try {
      return new Temperature(Double.parseDouble(input));
    } catch (final NullPointerException | NumberFormatException numberFormatException) {
      if (!programShouldExit(input)) {
        System.out.println("Es wurde kein gültiger Input eingegeben.");
      }
      return null;
    }
  }

  @VisibleForTesting
  static boolean programShouldExit(final String input) {
    return "exit".equals(input);
  }
}
