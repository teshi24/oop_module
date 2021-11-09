package ch.hslu.oop.SW06.unittesting;

import org.junit.jupiter.api.Test;

import static ch.hslu.oop.SW06.unittesting.DemoTesting.maxIfElse;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DemoTestingTest {

  @Test
  void maxIfElse_allTheSame_ReturnNr() {
    assertEquals(0, maxIfElse(0, 0, 0));
  }

  @Test
  void maxIfElse_firstHighestSecondAndThirdEquals_ReturnFirst() {
    assertEquals(1, maxIfElse(1, 0, 0));
  }

  @Test
  void maxIfElse_firstHighestSecondAndThirdDifferent_ReturnFirst() {
    assertEquals(4, maxIfElse(4, 2, 3));
  }

  @Test
  void maxIfElse_firstHighestAndSecondSameThirdDifferent_ReturnFirst() {
    assertEquals(6, maxIfElse(6, 6, 5));
  }

  @Test
  void maxIfElse_secondHighestFirstAndThirdEquals_ReturnSecond() {
    assertEquals(8, maxIfElse(7, 8, 7));
  }

  @Test
  void maxIfElse_secondHighestFirstAndThirdDifferent_ReturnSecond() {
    assertEquals(11, maxIfElse(9, 11, 10));
  }

  @Test
  void maxIfElse_secondHighestFirstSameThirdDifferent_ReturnSecond() {
    assertEquals(13, maxIfElse(12, 13, 13));
  }

  @Test
  void maxIfElse_thirdHighestFirstAndSecondEquals_ReturnThird() {
    assertEquals(15, maxIfElse(14, 14, 15));
  }

  @Test
  void maxIfElse_thirdHighestFirstAndSecondDifferent_ReturnThird() {
    assertEquals(18, maxIfElse(16, 17, 18));
  }

  @Test
  void maxIfElse_thirdHighestFirstSameSecondDifferent_ReturnThird() {
    assertEquals(20, maxIfElse(20, 19, 20));
  }
}