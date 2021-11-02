package ch.hslu.oop.SW05.vererbung;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RectangleTest {
  private Rectangle rectangle;

  private Rectangle getRectangle(final int width, final int height) {
    return new Rectangle(0, 0, width, height);
  }

  @Test
  void getPerimeter_0() {
    rectangle = getRectangle(0, 0);
    assertEquals(0, rectangle.getPerimeter());
  }

  @Test
  void getPerimeter_rectangle_heightBiggerThanWidth() {
    rectangle = getRectangle(5, 10);
    assertEquals(30, rectangle.getPerimeter());
  }

  @Test
  void getPerimeter_rectangle_widthBiggerThanHeight() {
    rectangle = getRectangle(100, 5);
    assertEquals(210, rectangle.getPerimeter());
  }

  @Test
  void getArea_0() {
    rectangle = getRectangle(0, 0);
    assertEquals(0, rectangle.getArea());
  }

  @Test
  void getArea_rectangle_heightBiggerThanWidth() {
    rectangle = getRectangle(5, 10);
    assertEquals(50, rectangle.getArea());
  }

  @Test
  void getArea_rectangle_widthBiggerThanHeight() {
    rectangle = getRectangle(100, 5);
    assertEquals(500, rectangle.getArea());
  }
}