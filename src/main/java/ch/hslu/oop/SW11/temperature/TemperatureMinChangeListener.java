package ch.hslu.oop.SW11.temperature;

/**
 * Listener interface for new min temperatures.
 */
public interface TemperatureMinChangeListener {

  /**
   * If the min temperature changes, the listener get notified.
   *
   * @param temperatureMinChangeEvent TemperatureMinEvent
   */
  void temperatureMinChange(final TemperatureMinChangeEvent temperatureMinChangeEvent);
}
