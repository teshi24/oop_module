package ch.hslu.oop.SW10.eventHandling.car;

public class Light implements Switchable {
  private boolean isOn = false;

  @Override
  public void switchOn() {
    isOn = true;
  }

  @Override
  public void switchOff() {
    isOn = false;
  }

  @Override
  public boolean isSwitchedOn() {
    return isOn;
  }

  @Override
  public boolean isSwitchedOff() {
    return !isOn;
  }
}
