package ch.hslu.oop.SW04.car;

public class Car implements Switchable {
  private SteeringWheel steeringWheel;
  private Wheel frontLeftWheel;
  private Wheel frontRightWheel;
  private Wheel backLeftWheel;
  private Wheel backRightWheel;
  private Engine engine;
  private Light frontLight;
  private Light backLight;
  private AirConditioning airConditioning;
  private Pedal accelerator;
  private Pedal brakePedal;
  private Switch starterSwitch;

  public Car() {
    steeringWheel = new SteeringWheel();
    frontLeftWheel = new Wheel();
    frontRightWheel = new Wheel();
    backLeftWheel = new Wheel();
    backRightWheel = new Wheel();
    engine = new Engine();
    frontLight = new Light();
    backLight = new Light();
    airConditioning = new AirConditioning();
    accelerator = new Pedal();
    brakePedal = new Pedal();
    starterSwitch = new Switch();
  }

  @Override
  public void switchOn() {
    engine.switchOn();
  }

  @Override
  public void switchOff() {
    engine.switchOff();
  }

  @Override
  public boolean isSwitchedOn() {
    return engine.isSwitchedOn();
  }

  @Override
  public boolean isSwitchedOff() {
    return engine.isSwitchedOff();
  }
}
