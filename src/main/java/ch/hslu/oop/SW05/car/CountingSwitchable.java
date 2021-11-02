package ch.hslu.oop.SW05.car;

/**
 * A switchable item which also counts the Switches.
 */
public interface CountingSwitchable extends Switchable {

  /**
   * provides the counts of the switches
   */
  long getSwitchCount();
}
