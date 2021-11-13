package ch.hslu.oop.SW07.objectsEqualsCompare;

import java.util.Objects;

/**
 * Holds a Point on the x and y axis.
 */
public class Point implements Comparable<Point> {
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

  /**
   * The given object is only equals to this if the x and y values are the same.
   *
   * @param obj
   * @return true if obj is a point and has the same x and y values
   */
  @Override
  public final boolean equals(final Object obj) {
    if (obj == this) {
      return true;
    }
    if (!(obj instanceof final Point point)) {
      return false;
    }
    return x == point.x && y == point.y;
  }

  @Override
  public final int hashCode() {
    return Objects.hash(x, y);
  }

  /**
   * Compares the values of the points and indicates which one is bigger - with favor of the x value.
   *
   * @param point object
   * @return 0 if the x and y values are the same, -1 if the values of the given points are bigger (with favor of x
   * value), 1 in the other cases
   */
  @Override
  public int compareTo(final Point point) {
    if (x > point.getX()) {
      return 1;
    } else if (x < point.getX()) {
      return -1;
    }

    return Integer.compare(y, point.getY());
  }
}
