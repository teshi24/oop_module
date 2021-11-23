package ch.hslu.oop.SW10.eventHandling.car;

public class Engine implements Switchable {

  private int rpm = 0;

  @Override
  public void switchOn() {
    rpm = 500;
  }

  @Override
  public void switchOff() {
    rpm = 0;
  }

  @Override
  public boolean isSwitchedOn() {
    return rpm != 0;
  }

  @Override
  public boolean isSwitchedOff() {
    return rpm == 0;
  }
}
