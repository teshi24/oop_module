package ch.hslu.oop.SW10.eventHandling.car;

public class AirConditioning implements Switchable {

  private int airPower = 0;

  @Override
  public void switchOn() {
    airPower = 100;
  }

  @Override
  public void switchOff() {
    airPower = 0;
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
