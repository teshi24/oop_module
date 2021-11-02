package ch.hslu.oop.SW05.vererbung;

public class Square extends Shape {
  private int sideLength;

  public Square(final int x, final int y, final int sideLength) {
    super(x, y);
    this.sideLength = sideLength;
  }

  @Override
  public int getPerimeter() {
    return sideLength * 4;
  }

  @Override
  public int getArea() {
    return (int) Math.pow(sideLength, 2);
  }

  public int getSideLength() {
    return sideLength;
  }

  public void setSideLength(final int sideLength) {
    this.sideLength = sideLength;
  }
}
