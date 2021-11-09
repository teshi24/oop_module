package ch.hslu.oop.SW06.tddexamplecalculations;

/**
 * The interface provides methods for simple calculations.
 */
public interface Calculation {

  /**
   * Returns the sum of summandA and summandB.
   *
   * @param summandA first summand
   * @param summandB second summand
   * @return sum of summandA and summandB
   */
  int addition(int summandA, int summandB);
}
