package ch.hslu.oop.SW12.streams.temperature;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static ch.hslu.oop.SW12.streams.exceptionhandling.Warnings.fileIoException;

public class TemperatureCourseFileHandler {
  private static final Logger LOG = LogManager.getLogger(TemperatureCourseFileHandler.class);

  private static final String BINARY_FILE_NAME = "target" + File.separator + "temperatures.txt";
  private static final String CSV_FILE_NAME =
    "src" + File.separator + "test" + File.separator + "resources" + File.separator + "netatmo-export-201801-201804" + ".csv";

  // todo: für SRP sollte hier eigentlich nicht direkt der TemperatureCourse verwendet werden 0=)
  public static void writeDataBinary(final TemperatureCourse temperatureCourse) {
    if (temperatureCourse == null || temperatureCourse.getTemperatures().isEmpty()) {
      return;
    }
    try (final DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream(BINARY_FILE_NAME))) {
      dataOutputStream.write(temperatureCourse.getCount());
      for (final Temperature temperature : temperatureCourse.getTemperatures()) {
        dataOutputStream.writeDouble(temperature.getCurrentTemperatureInCelsius());
      }
    } catch (final IOException ioException) {
      LOG.error(fileIoException(BINARY_FILE_NAME), ioException);
    }
  }

  public static TemperatureCourse readDataBinary() {
    final TemperatureCourse result = new TemperatureCourse();
    try (final DataInputStream dataOutputStream = new DataInputStream(new FileInputStream(BINARY_FILE_NAME))) {
      final int countOfResults = dataOutputStream.read();
      for (int i = 0; i < countOfResults; i++) {
        result.add(Temperature.createFromCelsius(dataOutputStream.readDouble()));
      }
    } catch (final EOFException eofException) {
      LOG.error("Der Datentyp wurde in dem File nicht gefunden, daher gab es eine EOF exception: ", eofException);
    } catch (final IOException ioException) {
      LOG.error(fileIoException(BINARY_FILE_NAME), ioException);
    }

    return result;
  }

  public static List<MeasuringPoint> readDataCsv() {
    if (new File(CSV_FILE_NAME).exists()) {
      try (final BufferedReader bufferedReader = //
             new BufferedReader(new InputStreamReader(new FileInputStream(CSV_FILE_NAME), StandardCharsets.UTF_8))) {

        return parseInputFile(bufferedReader);
      } catch (final IOException ioException) {
        LOG.error(ioException.getMessage(), ioException);
      }
    }
    return null;
  }

  private static List<MeasuringPoint> parseInputFile(final BufferedReader bufferedReader) throws IOException {
    final List<MeasuringPoint> measuringPoints = new ArrayList<>();
    String line;
    while ((line = bufferedReader.readLine()) != null) {
      measuringPoints.add(parseMeasuringPoint(line));
    }
    return measuringPoints;
  }

  private static MeasuringPoint parseMeasuringPoint(final String line) {
    final String[] values = line.split(";");
    final Temperature temperature = Temperature.createFromCelsius(Double.parseDouble(values[2]));
    final LocalDateTime timestamp = LocalDateTime.parse(values[1], DateTimeFormatter.ofPattern("\"yyyy/MM/dd " +
                                                                                               "HH:mm:ss\""));
    return new MeasuringPoint(temperature, timestamp);
  }
}
