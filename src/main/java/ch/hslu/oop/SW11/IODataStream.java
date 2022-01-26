package ch.hslu.oop.SW11;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import static ch.hslu.oop.SW11.exceptionhandling.Warnings.fileIoException;

public class IODataStream {
  private static final Logger LOG = LogManager.getLogger(IODataStream.class);

  public static void writeData(final String fileName) {
    try (final DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream(fileName))) {
      dataOutputStream.write(1);
      // dataOutputStream.wr+iteChars("text");
      // dataOutputStream.writeChars("/&%*'*\"text");
    } catch (final IOException ioException) {
      LOG.error(fileIoException(fileName), ioException);
    }
  }

  public static void writeData(final String fileName, final Integer entry) {
    try (final DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream(fileName))) {
      dataOutputStream.write(entry);
    } catch (final IOException ioException) {
      LOG.error(fileIoException(fileName), ioException);
    }
  }

  public static Object readData(final String fileName) {
    Object result = null;
    try (final DataInputStream dataOutputStream = new DataInputStream(new FileInputStream(fileName))) {
      result = dataOutputStream.read();
      // result = dataOutputStream.readLong();
    } catch (final EOFException eofException) {
      LOG.error("Der Datentyp wurde in dem File nicht gefunden, daher gab es eine EOF exception: ", eofException);
    } catch (final IOException ioException) {
      LOG.error(fileIoException(fileName), ioException);
    }
    return result;
  }
}
