package ch.hslu.oop.SW05.vererbung;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CircleTest {

  private Circle circle;

  private Circle getCircle(final int diameter) {
    return new Circle(0, 0, diameter);
  }

  @Test
  void getPerimeter_verySmallDiameter() {
    final int diameter = 1;
    circle = getCircle(diameter);
    Assertions.assertEquals(3, circle.getPerimeter());
  }

  @Test
  void getPerimeter_evenDiameter() {
    circle = getCircle(10);
    Assertions.assertEquals(31, circle.getPerimeter());
  }

  @Test
  void getPerimeter_unevenDiameter() {
    circle = getCircle(123);
    Assertions.assertEquals(386, circle.getPerimeter());
  }

  @Test
  void getPerimeter_0Diameter() {
    circle = getCircle(0);
    Assertions.assertEquals(0, circle.getPerimeter());
  }

  @Test
  void getArea_verySmallDiameter() {
    circle = getCircle(1);
    Assertions.assertEquals(0, circle.getArea());
  }

  @Test
  void getArea_evenDiameter() {
    circle = getCircle(10);
    Assertions.assertEquals(78, circle.getArea());
  }

  @Test
  void getArea_unevenDiameter() {
    circle = getCircle(123);
    Assertions.assertEquals(11882, circle.getArea());
  }

  @Test
  void getArea_0Diameter() {
    circle = getCircle(0);
    Assertions.assertEquals(0, circle.getArea());
  }
}