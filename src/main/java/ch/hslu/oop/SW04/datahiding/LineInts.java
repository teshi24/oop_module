package ch.hslu.oop.SW04.datahiding;

public class LineInts {
  private int xStartPoint;
  private int yStartPoint;
  private int xEndPoint;
  private int yEndPoint;

  public LineInts(final int xStartPoint, final int yStartPoint, final int xEndPoint, final int yEndPoint) {
    this.xStartPoint = xStartPoint;
    this.yStartPoint = yStartPoint;
    this.xEndPoint = xEndPoint;
    this.yEndPoint = yEndPoint;
  }

  public int getXStartPoint() {
    return xStartPoint;
  }

  public int getYStartPoint() {
    return yStartPoint;
  }

  public int getXEndPoint() {
    return xEndPoint;
  }

  public int getYEndPoint() {
    return yEndPoint;
  }

  public Point getStartPoint() {
    return new Point(xStartPoint, yStartPoint);
  }

  public Point getEndPoint() {
    return new Point(xEndPoint, yEndPoint);
  }

  public void setXStartPoint(final int xStartPoint) {
    this.xStartPoint = xStartPoint;
  }

  public void setYStartPoint(final int yStartPoint) {
    this.yStartPoint = yStartPoint;
  }

  public void setXEndPoint(final int xEndPoint) {
    this.xEndPoint = xEndPoint;
  }

  public void setYEndPoint(final int yEndPoint) {
    this.yEndPoint = yEndPoint;
  }

  public void setStartPoint(final Point startPoint) {
    this.xStartPoint = startPoint.getX();
    this.yStartPoint = startPoint.getY();
  }

  public void setEndPoint(final Point endPoint) {
    this.xEndPoint = endPoint.getX();
    this.yEndPoint = endPoint.getY();
  }
}
