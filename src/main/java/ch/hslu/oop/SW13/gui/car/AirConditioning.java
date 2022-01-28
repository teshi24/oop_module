package ch.hslu.oop.SW13.gui.car;

public final class AirConditioning implements Switchable {

  private int airPower = 0;

  @Override
  public void switchOn() {
    if (isSwitchedOff()) {
      airPower = 100;
    }
  }

  @Override
  public void switchOff() {
    if (isSwitchedOn()) {
      airPower = 0;
    }
  }

  @Override
  public boolean isSwitchedOn() {
    return airPower != 0;
  }

  @Override
  public boolean isSwitchedOff() {
    return airPower == 0;
  }
}
