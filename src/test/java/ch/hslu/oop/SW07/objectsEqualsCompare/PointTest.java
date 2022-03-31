package ch.hslu.oop.SW07.objectsEqualsCompare;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PointTest {

  @Test
  void getQuadrant() {
    assertEquals(1, new Point(1, 1).getQuadrant());
    assertEquals(2, new Point(-1, 1).getQuadrant());
    assertEquals(3, new Point(-1, -1).getQuadrant());
    assertEquals(4, new Point(1, -1).getQuadrant());
    assertEquals(0, new Point(0, 0).getQuadrant());
    assertEquals(0, new Point(1, 0).getQuadrant());
    assertEquals(0, new Point(-1, 0).getQuadrant());
    assertEquals(0, new Point(0, 1).getQuadrant());
    assertEquals(0, new Point(0, -1).getQuadrant());
  }

  @Test
  void testEqualsContract() {
    EqualsVerifier.forClass(Point.class)//
                  .suppress(Warning.NONFINAL_FIELDS)//
                  .verify();
  }

  @Test
  void compareTo_samePoint_returns0() {
    final Point point = new Point(1, 2);
    assertEquals(0, point.compareTo(point));
  }

  @Test
  void compareTo_duplicationOfPoint_returns0() {
    final Point point = new Point(1, 2);
    assertEquals(0, point.compareTo(new Point(point.getX(), point.getY())));
  }

  @Test
  void compareTo_pointWithSmallerYValue_returns1() {
    final Point point = new Point(1, 2);
    assertEquals(1, point.compareTo(new Point(point.getX(), point.getY() - 1)));
  }

  @Test
  void compareTo_pointWithBiggerYValue_returnMinus1() {
    final Point point = new Point(1, 2);
    assertEquals(-1, point.compareTo(new Point(point.getX(), point.getY() + 1)));
  }

  @Test
  void compareTo_pointWithSmallerXValue_returns1() {
    final Point point = new Point(1, 2);
    assertEquals(1, point.compareTo(new Point(point.getX() - 1, point.getY())));
  }

  @Test
  void compareTo_pointWithBiggerXValue_returnMinus1() {
    final Point point = new Point(1, 2);
    assertEquals(-1, point.compareTo(new Point(point.getX() + 1, point.getY())));
  }

  @Test
  void compareTo_pointWithSmallerXValueAndSmallerYValue_returns1() {
    final Point point = new Point(1, 2);
    assertEquals(1, point.compareTo(new Point(point.getX() - 1, point.getY() - 1)));
  }

  @Test
  void compareTo_pointWithBiggerXValueAndBiggerYValue_returnMinus1() {
    final Point point = new Point(1, 2);
    assertEquals(-1, point.compareTo(new Point(point.getX() + 1, point.getY() + 1)));
  }

  @Test
  void compareTo_pointWithSmallerXValueAndBiggerYValue_returns1() {
    final Point point = new Point(1, 2);
    assertEquals(1, point.compareTo(new Point(point.getX() - 1, point.getY() + 1)));
  }

  @Test
  void compareTo_pointWithBiggerXValueAndSmallerYValue_returnMinus1() {
    final Point point = new Point(1, 2);
    assertEquals(-1, point.compareTo(new Point(point.getX() + 1, point.getY() - 1)));
  }
}
