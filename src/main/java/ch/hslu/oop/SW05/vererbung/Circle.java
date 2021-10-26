package ch.hslu.oop.SW05.vererbung;

public class Circle extends Shape {

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
    final int radius = diameter / 2;
    return (int) (Math.sqrt(radius) * Math.PI);
  }
}
