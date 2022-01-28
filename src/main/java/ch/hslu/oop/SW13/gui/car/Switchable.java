package ch.hslu.oop.SW13.gui.car;

/**
 * A switchable item which can be switched on and off (e.g. a light switch, a car, ...)
 */
public interface Switchable {

  /**
   * Changes the state of the object to switched on.
   */
  void switchOn();

  /**
   * Changes the state of the object to switched off.
   */
  void switchOff();

  /**
   * Checks if switchable is switched on.
   *
   * @return true if the item is switched on
   */
  boolean isSwitchedOn();

  /**
   * Checks if switchable is switched off.
   *
   * @return true if the item is switched off
   */
  boolean isSwitchedOff();
}
