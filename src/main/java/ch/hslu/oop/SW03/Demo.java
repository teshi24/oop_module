package ch.hslu.oop.SW03;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class Demo {
  public int max(int a, int b) {
    if (a > b) {
      return a;
    }
    return b;
  }

  public int min(int a, int b) {
    return Math.min(a, b);
  }

  public int maxReuseMethod(int a, int b, int c) {
    return max(max(a, b), c);
  }

  public int maxIfElse(int a, int b, int c) {
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

  public int maxList(int... ints) {
    return Arrays.stream(ints).max().orElseThrow(NoSuchElementException::new);
  }

  public void print0To10For() {
    for (int i = 0; i < 11; i++) {
      System.out.println(i);
    }
  }

  public void print0To10While() {
    int i = 0;
    while (i < 11) {
      System.out.println(i);
      i++;
    }
  }

  public void print0To10DoWhile() {
    int i = 0;
    do {
      System.out.println(i);
      i++;
    } while (i < 11);
  }

  public void whileFloat() {
    float test = 0.9f;
    int i = 1;
    while (test < 1f) {
      test += 0.000025f;
      System.out.println(i);
      i++;
    }
  }

  public void forFloat() {
    float test = 0.9f;
    for (int i = 0; i < 4000; i++) {
      test += 0.000025f;
      System.out.println(i);
    }
    System.out.println(test);
  }

  private boolean shouldPrintAHashtag(final int dividend, final int divisor) {
    return divisor == 1 || dividend % (divisor - 1) == 0;
  }

  private boolean lineIsHeaderOrFooter(final int i, final int height) {
    return shouldPrintAHashtag(i, height);
  }

  public void printBox(final int height, final int width) {
    for (int i = 0; i < height; i++) {
      if (lineIsHeaderOrFooter(i, height)) {
        for (int j = 0; j < width; j++) {
          System.out.print('#');
        }
      } else {
        for (int j = 0; j < width; j++) {
          char sign = ' ';
          if (shouldPrintAHashtag(j, width)) {
            sign = '#';
          }
          System.out.print(sign);
        }
      }
      System.out.println();
    }
  }

  public void printBox2(final int height, final int width) {
    for (int i = 0; i < height; i++) {
      char defaultSign = ' ';
      if (shouldPrintAHashtag(i, height)) {
        defaultSign = '#';
      }
      for (int j = 0; j < width; j++) {
        char sign = defaultSign;
        if (shouldPrintAHashtag(j, width)) {
          sign = '#';
        }
        System.out.print(sign);
      }
      System.out.println();
    }
  }

  public void printBox3(final int height, final int width) {
    StringBuilder headerOrFooterLine = new StringBuilder();
    StringBuilder bodyLine = new StringBuilder();
    for (int i = 0; i < width; i++) {
      headerOrFooterLine.append('#');
      bodyLine.append(' ');
    }
    bodyLine.replace(0, 1, "#");
    if (width > 0) {
      bodyLine.replace(width - 1, width, "#");
    }

    for (int i = 0; i < height; i++) {
      if (lineIsHeaderOrFooter(i, height)) {
        System.out.println(headerOrFooterLine);
      } else {
        System.out.println(bodyLine);
      }
    }
  }

  private char alternateSign(final char sign, final char space, final char hashtag) {
    return sign == space ? hashtag : space;
  }
}
