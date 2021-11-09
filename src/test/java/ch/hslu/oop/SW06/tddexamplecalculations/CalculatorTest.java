package ch.hslu.oop.SW06.tddexamplecalculations;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CalculatorTest {

  private Calculation calculator;

  @BeforeEach
  void setup() {
    calculator = new Calculator();
  }

  @Test
  void addition_0plus0_returns0() {
    assertEquals(0, calculator.addition(0, 0));
  }

  @Test
  void addition_positivePlusPositive_returnsResult() {
    assertEquals(3, calculator.addition(1, 2));
  }

  @Test
  void addition_positivePlusNegative_returnsResult() {
    assertEquals(-1, calculator.addition(3, -4));
    assertEquals(1, calculator.addition(-5, 6));
  }

  @Test
  void addition_negativePlusNegative_returnsResult() {
    assertEquals(-15, calculator.addition(-7, -8));
  }

  @Test
  void addition_0PlusMaxValue_returnsMaxValue() {
    assertEquals(Integer.MAX_VALUE, calculator.addition(0, Integer.MAX_VALUE));
    assertEquals(Integer.MAX_VALUE, calculator.addition(Integer.MAX_VALUE, 0));
  }

  @Test
  void addition_negativeNrPlusMaxValue_returnsResult() {
    assertEquals(-1, calculator.addition(Integer.MIN_VALUE, Integer.MAX_VALUE));
    assertEquals(-1, calculator.addition(Integer.MAX_VALUE, Integer.MIN_VALUE));
  }

  @Test
  void addition_positiveNrPlusMaxValue_throwsArithmeticException() {
    assertThrows(ArithmeticException.class, () -> calculator.addition(1, Integer.MAX_VALUE));
    assertThrows(ArithmeticException.class, () -> calculator.addition(Integer.MAX_VALUE, 1));
    assertThrows(ArithmeticException.class, () -> calculator.addition(Integer.MAX_VALUE, Integer.MAX_VALUE));
  }
  
  
  @Test
  void addition_0PlusMinValue_returnsMinValue() {
    assertEquals(Integer.MIN_VALUE, calculator.addition(0, Integer.MIN_VALUE));
    assertEquals(Integer.MIN_VALUE, calculator.addition(Integer.MIN_VALUE, 0));
  }

  @Test
  void addition_negativeNrPlusMinValue_throwsArithmeticException() {
    assertThrows(ArithmeticException.class, () -> calculator.addition(-1, Integer.MIN_VALUE));
    assertThrows(ArithmeticException.class, () -> calculator.addition(Integer.MIN_VALUE, -1));
    assertThrows(ArithmeticException.class, () -> calculator.addition(Integer.MIN_VALUE, Integer.MIN_VALUE));
  }
}