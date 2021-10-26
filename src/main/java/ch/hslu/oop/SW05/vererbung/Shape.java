package ch.hslu.oop.SW05.vererbung;

public abstract class Shape {
  private int x;
  private int y;

  protected Shape(final int x, final int y) {
    this.x = x;
    this.y = y;
  }

  public final void move(final int x, final int y) {
    this.x = x;
    this.y = y;
  }

  public abstract int getPerimeter();

  public abstract int getArea();
}
