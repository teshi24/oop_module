package ch.hslu.oop.SW05.car;

/**
 * A switchable item which can be switched on and off (e.g. a light switch, a car, ...)
 */
public interface Switchable {

  /**
   * changes the state of the object to switched on
   */
  void switchOn();

  /**
   * changes the state of the object to switched off
   */
  void switchOff();

  /**
   * todo: hier immer einen Satz ergänzen
   *
   * @return true if the item is switched on
   */
  boolean isSwitchedOn();

  /**
   * todo: hier immer einen Satz ergänzen
   *
   * @return true if the item is switched off
   */
  boolean isSwitchedOff();
}
