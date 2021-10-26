package ch.hslu.oop.SW03;

/**
 * Holds a Point on the x and y axis.
 */
public class Point {
  private int x;
  private int y;

  /**
   * Creates a new Point
   *
   * @param x value on x axis
   * @param y value on y axis
   */
  public Point(int x, int y) {
    this.x = x;
    this.y = y;
  }

  /**
   * @return quadrant of the coordinate system for the position of the point
   */
  public int getQuadrant() {
    if (x > 0 && y > 0) {
      return 1;
    }
    if (x < 0 && y > 0) {
      return 2;
    }
    if (x < 0 && y < 0) {
      return 3;
    }
    if (x > 0 && y < 0) {
      return 4;
    }
    return 0;
  }

  public int getX() {
    return x;
  }

  public void setX(int x) {
    this.x = x;
  }

  public int getY() {
    return y;
  }

  public void setY(int y) {
    this.y = y;
  }
}
