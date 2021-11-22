package ch.hslu.oop.SW08.final_static_enum_collections.vererbung;

public final class SquareWithRectangle extends Shape {
  private Rectangle rectangle;

  public SquareWithRectangle(final int x, final int y, final int sideLength) {
    super(x, y);
    rectangle = new Rectangle(x, y, sideLength, sideLength);
  }

  @Override
  public int getPerimeter() {
    return rectangle.getPerimeter();
  }

  @Override
  public int getArea() {
    return rectangle.getArea();
  }

  public int getSideLength() {
    return rectangle.getWidth();
  }

  public void setSideLength(final int sideLength) {
    rectangle.changeDimension(sideLength, sideLength);
  }
}
