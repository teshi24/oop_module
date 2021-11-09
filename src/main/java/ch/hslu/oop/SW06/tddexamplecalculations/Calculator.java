package ch.hslu.oop.SW06.tddexamplecalculations;

public class Calculator implements Calculation {
  @Override
  public int addition(final int summandA, final int summandB) {
    return Math.addExact(summandA, summandB);
  }
}
