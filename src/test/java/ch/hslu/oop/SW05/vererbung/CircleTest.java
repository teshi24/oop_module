package ch.hslu.oop.SW05.vererbung;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CircleTest {

  private Circle circle;

  @Test
  void getPerimeter_smallPerimeter() {
    circle = new Circle(0, 0, 1);
    Assertions.assertEquals(3, circle.getPerimeter());
  }

  @Test
  void getPerimeter_bigPerimeter() {
    circle = new Circle(0, 0, 123);
    Assertions.assertEquals(386, circle.getPerimeter());
  }

  @Test
  void getPerimeter_0Perimeter() {
    circle = new Circle(0, 0, 0);
    Assertions.assertEquals(0, circle.getPerimeter());
  }

  @Test
  void getArea_smallPerimeter() {
    circle = new Circle(0, 0, 1);
    Assertions.assertEquals(3, circle.getArea());
  }

  @Test
  void getArea_bigPerimeter() {
    circle = new Circle(0, 0, 123);
    Assertions.assertEquals(47529, circle.getArea());
  }

  @Test
  void getArea_0Perimeter() {
    circle = new Circle(0, 0, 0);
    Assertions.assertEquals(0, circle.getArea());
  }
}