package ch.hslu.oop.SW10.eventHandling.temperature;

/**
 * Listener interface for new max temperatures.
 */
public interface TemperatureMaxChangeListener {

  /**
   * If the max temperature changes, the listener get notified.
   *
   * @param temperatureMaxChangeEvent TemperatureMaxEvent
   */
  void temperatureMaxChange(final TemperatureMaxChangeEvent temperatureMaxChangeEvent);
}
