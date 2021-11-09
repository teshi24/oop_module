package ch.hslu.oop.SW06.polymorphie;

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

  public Point(final Point point) {
    this(point.getX(), point.getY());
  }

  public void moveRelative(final int x, final int y) {
    this.x += x;
    this.y += y;
  }

  public void moveRelative(final Point point) {
    moveRelative(point.getX(), point.getY());
  }

  /**
   * ATTENTION: THIS OVERLOAD IS DANGEROUS CAUSE OF INT AND DOUBLE CHANGE
   * Vorschlag - anderer Name
   *
   * @param value Betrag der Polarkoordinaten
   * @param angle Windel der Polarkoordinaten
   */
  public void moveRelative(final int value, final double angle) {
    this.moveRelative(getRelativeCoordinatesOfAVector(value, angle));
  }

  private Point getRelativeCoordinatesOfAVector(final int value, final double angle) {
    return new Point((int) (value * Math.cos(Math.toRadians(angle))), (int) (value * Math.sin(Math.toRadians(angle))));
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
