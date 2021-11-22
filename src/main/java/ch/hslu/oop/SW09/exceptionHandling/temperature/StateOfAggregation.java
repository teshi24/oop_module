package ch.hslu.oop.SW09.exceptionHandling.temperature;

import java.util.Locale;

public enum StateOfAggregation {
  SOLID, LIQUID, GASEOUS;

  @Override
  public String toString() {
    return this.name().toLowerCase(Locale.ROOT);
  }
}
