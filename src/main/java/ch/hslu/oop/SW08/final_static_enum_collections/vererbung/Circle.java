package ch.hslu.oop.SW08.final_static_enum_collections.vererbung;

public final class Circle extends Shape {

  private int diameter;

  public Circle(final int x, final int y, final int diameter) {
    super(x, y);
    this.diameter = diameter;
  }

  public int getDiameter() {
    return diameter;
  }

  public void setDiameter(final int diameter) {
    this.diameter = diameter;
  }

  @Override
  public int getPerimeter() {
    return (int) (diameter * Math.PI);
  }

  @Override
  public int getArea() {
    final double radius = diameter / 2d;
    return (int) (Math.pow(radius, 2) * Math.PI);
  }
}
