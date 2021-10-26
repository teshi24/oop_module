package ch.hslu.oop.SW04.datahiding;

public class Line {
  private Point startPoint;
  private Point endPoint;

  public Line() {
    this.startPoint = new Point(0, 0);
    this.endPoint = new Point(0, 0);
  }

  public Line(final Point startPoint, final Point endPoint) {
    this.startPoint = startPoint;
    this.endPoint = endPoint;
  }

  public Point getStartPoint() {
    return new Point(startPoint.getX(), startPoint.getY());
  }

  public Point getEndPoint() {
    return new Point(endPoint.getX(), endPoint.getY());
  }

  public void setStartPoint(final Point startPoint) {
    this.startPoint.setX(startPoint.getX());
    this.startPoint.setY(startPoint.getY());
  }

  public void setEndPoint(final Point endPoint) {
    this.endPoint.setX(endPoint.getX());
    this.endPoint.setY(endPoint.getY());
  }
}
