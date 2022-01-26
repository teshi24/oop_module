package ch.hslu.oop.SW11.temperature;

import java.util.Locale;

public enum StateOfAggregation {
  SOLID, LIQUID, GASEOUS;

  @Override
  public String toString() {
    return name().toLowerCase(Locale.ROOT);
  }
}
