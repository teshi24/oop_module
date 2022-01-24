package ch.hslu.oop.SW10.eventHandling.car;

import ch.hslu.oop.SW10.eventHandling.car.engine.Engine;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class Car implements Switchable, PropertyChangeListener {
  private static final Logger LOG = LogManager.getLogger(Car.class);
  private final SteeringWheel steeringWheel;
  private final Wheel frontLeftWheel;
  private final Wheel frontRightWheel;
  private final Wheel backLeftWheel;
  private final Wheel backRightWheel;
  private final Engine engine;
  private final Light frontLight;
  private final Light backLight;
  private final AirConditioning airConditioning;
  private final Pedal accelerator;
  private final Pedal brakePedal;
  private final Switch starterSwitch;

  public Car() {
    steeringWheel = new SteeringWheel();
    frontLeftWheel = new Wheel();
    frontRightWheel = new Wheel();
    backLeftWheel = new Wheel();
    backRightWheel = new Wheel();

    engine = new Engine();
    engine.addPropertyChangeListener(this);

    frontLight = new Light();
    backLight = new Light();
    airConditioning = new AirConditioning();
    accelerator = new Pedal();
    brakePedal = new Pedal();
    starterSwitch = new Switch();
  }

  @Override
  public void switchOn() {
    if (isSwitchedOff()) {
      starterSwitch.switchOn();
      engine.switchOn();
    }
  }

  @Override
  public void switchOff() {
    if (isSwitchedOn()) {
      engine.switchOff();
      starterSwitch.switchOff();
    }
  }

  @Override
  public boolean isSwitchedOn() {
    return starterSwitch.isSwitchedOn();
  }

  @Override
  public boolean isSwitchedOff() {
    return !isSwitchedOn();
  }

  @Override
  public void propertyChange(final PropertyChangeEvent event) {
    LOG.info(event);
  }
}
