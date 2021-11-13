package ch.hslu.oop.SW07.objectsEqualsCompare.temperature;

public enum ElementOfPeriodSystem {
  N(-210.1, -196), Hg(-38.83, 357), Pb(327.43, 1744);

  private final double meltingPoint;
  private final double boilingPoint;

  ElementOfPeriodSystem(final double meltingPoint, final double boilingPoint) {
    this.meltingPoint = meltingPoint;
    this.boilingPoint = boilingPoint;
  }

  public StateOfAggregation getStateOfAggregationBasedOnTemperature(final double currentTemperature) {
    if (currentTemperature > meltingPoint && currentTemperature < boilingPoint) {
      return StateOfAggregation.LIQUID;
    }
    if (currentTemperature < meltingPoint) {
      return StateOfAggregation.SOLID;
    }
    return StateOfAggregation.GAS;
  }
}
