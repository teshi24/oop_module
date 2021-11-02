package ch.hslu.oop.SW05.vererbung;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SquareTest {

  private Square square;

  private Square getSquare(final int sideLength) {
    return new Square(0, 0, sideLength);
  }

  @Test
  void getPerimeter_0() {
    square = getSquare(0);
    assertEquals(0, square.getPerimeter());
  }

  @Test
  void getPerimeter_square() {
    square = getSquare(5);
    assertEquals(20, square.getPerimeter());
  }

  @Test
  void getArea_0() {
    square = getSquare(0);
    assertEquals(0, square.getArea());
  }

  @Test
  void getArea_square_heightBiggerThanWidth() {
    square = getSquare(5);
    assertEquals(25, square.getArea());
  }
}