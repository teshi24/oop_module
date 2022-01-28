package ch.hslu.oop.SW13.gui.car;

public class Switch implements Switchable {

  private boolean isOn = false;

  @Override
  public void switchOn() {
    if (isSwitchedOff()) {
      isOn = true;
    }
  }

  @Override
  public void switchOff() {
    if (isSwitchedOn()) {
      isOn = false;
    }
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
