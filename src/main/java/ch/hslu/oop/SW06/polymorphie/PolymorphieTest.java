package ch.hslu.oop.SW06.polymorphie;

import ch.hslu.oop.SW05.car.Engine;
import ch.hslu.oop.SW05.car.Switchable;
import ch.hslu.oop.SW05.vererbung.Circle;
import ch.hslu.oop.SW05.vererbung.Rectangle;
import ch.hslu.oop.SW05.vererbung.Shape;

public class PolymorphieTest {

  // casts ändern nur betrachtungsweise (den statischen Typ)
  // nicht was das objekt zur laufzeit eigentlich ist (den dynamischen Typ)

  public static void main(String[] args) {
    // implizites Upcasting
    final Shape rectangleAsShape = new Rectangle(1, 1, 5, 10);
    final Shape circleAsShape = new Circle(1, 1, 10);

    rectangleAsShape.move(4, 4);
    rectangleAsShape.move(4, 4);

    // explizites Downcasting
    int diameter = ((Circle) circleAsShape).getDiameter();

    final Switchable motor = new Engine();
    motor.switchOn();
    motor.isSwitchedOn();
    motor.switchOff();
    motor.isSwitchedOff();
  }
}
