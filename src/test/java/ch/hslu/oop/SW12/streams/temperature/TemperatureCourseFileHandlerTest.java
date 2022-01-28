package ch.hslu.oop.SW12.streams.temperature;

import ch.hslu.oop.SW12.streams.ListAppender;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TemperatureCourseFileHandlerTest {

  private static ListAppender listAppender;

  @BeforeAll
  static void setup() {
    final Logger logger = (Logger) LogManager.getLogger(TemperatureCourseFileHandler.class);
    listAppender = new ListAppender("appenderTemperatureFileHandlerTest");
    logger.addAppender(listAppender);
    listAppender.start();
  }

  @AfterEach
  void clearAppenderList() {
    listAppender.clear();
  }

  @AfterAll
  static void tearDown() {
    listAppender.stop();
  }

  @Test
  void writeData_courseNull_calledWithoutLoggingAnyErrors() {
    TemperatureCourseFileHandler.writeDataBinary(null);
    assertThat(listAppender.getLog()).isEmpty();
  }

  @Test
  void writeData_courseWithEmptyList_calledWithoutLoggingAnyErrors() {
    TemperatureCourseFileHandler.writeDataBinary(new TemperatureCourse());
    assertThat(listAppender.getLog()).isEmpty();
  }

  @Test
  void writeData_courseWithSingletonList_calledWithoutLoggingAnyErrors() {
    final TemperatureCourse temperatureCourse = new TemperatureCourse();
    temperatureCourse.add(Temperature.createFromCelsius(10));
    TemperatureCourseFileHandler.writeDataBinary(temperatureCourse);
    assertThat(listAppender.getLog()).isEmpty();
  }

  @Test
  void writeData_courseWithList_calledWithoutLoggingAnyErrors() {
    final TemperatureCourse temperatureCourse = new TemperatureCourse();
    temperatureCourse.add(Temperature.createFromCelsius(10));
    temperatureCourse.add(Temperature.createFromCelsius(20));
    temperatureCourse.add(Temperature.createFromCelsius(30));
    TemperatureCourseFileHandler.writeDataBinary(temperatureCourse);
    assertThat(listAppender.getLog()).isEmpty();
  }

  @Test
  void readData_readsEnteredData() {
    final TemperatureCourse temperatureCourse = new TemperatureCourse();
    temperatureCourse.add(Temperature.createFromCelsius(5));
    temperatureCourse.add(Temperature.createFromCelsius(15));
    temperatureCourse.add(Temperature.createFromCelsius(25));
    TemperatureCourseFileHandler.writeDataBinary(temperatureCourse);

    final TemperatureCourse result = TemperatureCourseFileHandler.readDataBinary();

    assertThat(result).isEqualTo(temperatureCourse);
    assertThat(listAppender.getLog()).isEmpty();
  }

  @Test
  void readData_readsCsvData() {
    TemperatureCourseFileHandler.readDataCsv();
  }
}
