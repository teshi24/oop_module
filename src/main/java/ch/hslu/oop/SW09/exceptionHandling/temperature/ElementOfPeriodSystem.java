package ch.hslu.oop.SW09.exceptionHandling.temperature;

public enum ElementOfPeriodSystem {
  N("nitrogen", -210.1, -196), //
  Hg("mercury", -38.83, 357), //
  Pb("lead", 327.43, 1744);

  private final String name;
  private final double meltingPoint;
  private final double boilingPoint;

  ElementOfPeriodSystem(final String name, final double meltingPoint, final double boilingPoint) {
    this.name = name;
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
    return StateOfAggregation.GASEOUS;
  }

  @Override
  public String toString() {
    return "ElementOfPeriodSystem{" + "name='" + name + '\'' + ", meltingPoint=" + meltingPoint + ", boilingPoint=" + boilingPoint + '}';
  }
}
