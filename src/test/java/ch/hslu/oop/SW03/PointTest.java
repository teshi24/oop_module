package ch.hslu.oop.SW03;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class PointTest {

  @Test
  void getQuadrant() {
    Assertions.assertEquals(1, new Point(1, 1).getQuadrant());
    Assertions.assertEquals(2, new Point(-1, 1).getQuadrant());
    Assertions.assertEquals(3, new Point(-1, -1).getQuadrant());
    Assertions.assertEquals(4, new Point(1, -1).getQuadrant());
    Assertions.assertEquals(0, new Point(0, 0).getQuadrant());
    Assertions.assertEquals(0, new Point(1, 0).getQuadrant());
    Assertions.assertEquals(0, new Point(-1, 0).getQuadrant());
    Assertions.assertEquals(0, new Point(0, 1).getQuadrant());
    Assertions.assertEquals(0, new Point(0, -1).getQuadrant());
  }
}