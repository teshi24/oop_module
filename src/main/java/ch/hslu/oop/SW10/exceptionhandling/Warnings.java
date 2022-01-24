package ch.hslu.oop.SW10.exceptionhandling;

public final class Warnings {
  public static final String IS_NULL_TEMPLATE = "%s should not be null";

  private Warnings() {
  }

  public static String varShouldNotBeNull(final String varNameWhichIsNull) {
    return String.format(IS_NULL_TEMPLATE, varNameWhichIsNull);
  }
}
