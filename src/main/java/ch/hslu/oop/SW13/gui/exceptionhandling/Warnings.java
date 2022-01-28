package ch.hslu.oop.SW13.gui.exceptionhandling;

public final class Warnings {
  public static final String IS_NULL_TEMPLATE = "%s should not be null";
  public static final String FILE_IO_EXCEPTION_TEMPLATE = "Datei %s nicht lesbar.";

  private Warnings() {
  }

  public static String varShouldNotBeNull(final String varNameWhichIsNull) {
    return String.format(IS_NULL_TEMPLATE, varNameWhichIsNull);
  }

  public static String fileIoException(final String fileName) {
    return String.format(FILE_IO_EXCEPTION_TEMPLATE, fileName);
  }
}
