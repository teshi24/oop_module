package ch.hslu.oop.SW06.unittesting;

public class DemoTesting {
  public static int maxIfElse(int a, int b, int c) {
    if (a > b) {
      if (a > c) {
        return a;
      }
      return c;
    }
    if (b > c) {
      return b;
    }
    return c;
  }
}
