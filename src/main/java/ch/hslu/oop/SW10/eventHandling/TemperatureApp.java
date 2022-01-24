package ch.hslu.oop.SW10.eventHandling;

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
    final TemperatureInputHandler inputHandler = new TemperatureInputHandler();
    course.addTemperatureMinChangeListener(inputHandler);
    course.addTemperatureMaxChangeListener(inputHandler);

    do {
      LOG.info("Bitte Temperatur eingeben ('exit' zum Beenden): ");
      input = scanner.next();
      if (!inputHandler.programShouldExit(input)) {
        course.add(inputHandler.parseInputToTemperature(input));
      }
    } while (!inputHandler.programShouldExit(input));
    inputHandler.printStats(course);
    LOG.info("Programm beendet.");
  }
}
