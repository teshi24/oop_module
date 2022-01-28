package ch.hslu.oop.SW12.streams.temperature;

import java.util.Locale;

public enum StateOfAggregation {
  SOLID, LIQUID, GASEOUS;

  @Override
  public String toString() {
    return name().toLowerCase(Locale.ROOT);
  }
}
