package ch.hslu.oop.SW04.datahiding;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LineTest {

  private Line line;

  @BeforeEach
  void setUp() {
    line = new Line();
  }

  @Test
  void adjustLine() {
    int xStartPoint = 1;
    int yStartPoint = 2;
    int xEndPoint = 3;
    int yEndPoint = 4;

    line.getStartPoint().setX(xStartPoint);
    line.getStartPoint().setY(yStartPoint);
    line.getEndPoint().setX(xEndPoint);
    line.getEndPoint().setY(yEndPoint);

    assertEquals(0, line.getStartPoint().getX());
    assertEquals(0, line.getStartPoint().getY());
    assertEquals(0, line.getEndPoint().getX());
    assertEquals(0, line.getEndPoint().getY());

    /*
    this must be false, since datahiding means to return copies of the element, so that other classes cannot directly change values
    assertEquals(xStartPoint, line.getStartPoint().getX());
    assertEquals(yStartPoint, line.getStartPoint().getY());
    assertEquals(xEndPoint, line.getEndPoint().getX());
    assertEquals(yEndPoint, line.getEndPoint().getY());
     */
  }
}