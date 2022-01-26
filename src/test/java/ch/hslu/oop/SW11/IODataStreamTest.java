package ch.hslu.oop.SW11;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import static ch.hslu.oop.SW11.exceptionhandling.Warnings.fileIoException;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class IODataStreamTest {

  private static ListAppender listAppender;
  private static final String TARGET_PATH = "target" + File.separator;
  private static final String validFileName = getFileName("validFileName");
  private static final String fileToBeFilled = getFileName("fileToBeFilled");
  private final String invalidFileName = getFileName("?!/^invalidFileName");

  @BeforeAll
  static void setup() throws IOException {
    final Logger logger = (Logger) LogManager.getLogger(IODataStream.class);
    listAppender = new ListAppender("appenderIODataStreamTest");
    logger.addAppender(listAppender);
    listAppender.start();

    final List<File> files = new ArrayList<>();
    files.add(new File(validFileName));
    files.add(new File(fileToBeFilled));
    for (final File file : files) {
      Files.deleteIfExists(file.toPath());
    }
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
  void writeData_validFileName_calledWithoutLoggingAnyErrors() {
    IODataStream.writeData(validFileName);
    assertThat(listAppender.getLog()).isEmpty();
  }

  @Test
  void writeData_invalidFileName_logsError() {
    final String fileName = invalidFileName;
    IODataStream.writeData(fileName);

    final List<LogEvent> logs = listAppender.getLog();
    assertEquals(1, logs.size());
    assertEquals(fileIoException(fileName), logs.get(0).getMessage().getFormattedMessage());
    assertEquals(Level.OFF, logs.get(0).getLevel());
  }

  @Test
  void readData_invalidFileName_logsErrorReturnsNull() {
    final String fileName = invalidFileName;
    final Object result = IODataStream.readData(fileName);

    assertThat(result).isNull();

    final List<LogEvent> logs = listAppender.getLog();
    assertEquals(1, logs.size());
    assertEquals(fileIoException(fileName), logs.get(0).getMessage().getFormattedMessage());
    assertEquals(Level.OFF, logs.get(0).getLevel());
  }

  @Test
  void readData_validFileName_logsNoError() {
    final String fileName = validFileName;
    IODataStream.readData(fileName);
    assertThat(listAppender.getLog()).isEmpty();
  }

  @Test
  void readData_validFileName_returnsEnteredValue() {
    final String fileName = validFileName;
    final Integer result = (Integer) IODataStream.readData(fileName);
    assertThat(result).isEqualTo(1);
    assertThat(listAppender.getLog()).isEmpty();
  }

  @Test
  void readData_entersValueBeforeRead_returnsEnteredValue() {
    final String fileName = fileToBeFilled;
    final Integer entry = 100;
    IODataStream.writeData(fileName, entry);
    System.out.println(IODataStream.readData(fileName));
    final Integer result = (Integer) IODataStream.readData(fileName);
    assertThat(result).isEqualTo(entry);
    assertThat(listAppender.getLog()).isEmpty();
  }

  private static String getFileName(final String fileName) {
    return TARGET_PATH + fileName + ".txt";
  }
}
