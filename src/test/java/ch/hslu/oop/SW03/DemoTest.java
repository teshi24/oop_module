package ch.hslu.oop.SW03;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DemoTest {

  Demo demo = new Demo();

  @Test
  void maxList() {
    Assertions.assertEquals(10, demo.maxList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
  }

  @Test
  void print0To10For() {
    System.out.println(Thread.currentThread().getStackTrace()[1]);
    demo.print0To10For();
  }

  @Test
  void print0To10While() {
    System.out.println(Thread.currentThread().getStackTrace()[1]);
    demo.print0To10While();
  }

  @Test
  void print0To10DoWhile() {
    System.out.println(Thread.currentThread().getStackTrace()[1]);
    demo.print0To10DoWhile();
  }

  @Test
  void whileFloat() {
    System.out.println(Thread.currentThread().getStackTrace()[1]);
    demo.whileFloat();
  }

  @Test
  void forFloat() {
    System.out.println(Thread.currentThread().getStackTrace()[1]);
    demo.forFloat();
  }

  @Test
  void printBox() {
    System.out.println(Thread.currentThread().getStackTrace()[1]);
    printBox(4, 10);
    System.out.println();
    for (int i = 0; i < 11; i++) {
      System.out.println("i = " + i);
      printBox(i, i);
    }
  }

  private void printBox(final int height, final int width) {
    System.out.println("Box 1");
    demo.printBox(height, width);
    System.out.println("Box 2");
    demo.printBox2(height, width);
    System.out.println("Box 3");
    demo.printBox3(height, width);
  }
}