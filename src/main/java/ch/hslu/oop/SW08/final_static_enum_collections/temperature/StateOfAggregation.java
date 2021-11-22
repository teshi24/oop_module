package ch.hslu.oop.SW08.final_static_enum_collections.temperature;

import java.util.Locale;

public enum StateOfAggregation {
  SOLID, LIQUID, GASEOUS;

  @Override
  public String toString() {
    return this.name().toLowerCase(Locale.ROOT);
  }
}
