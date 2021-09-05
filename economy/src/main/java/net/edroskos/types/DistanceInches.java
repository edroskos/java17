package net.edroskos.types;

public final class DistanceInches extends Distance {

  private static final double inchesToMeters = 0.0254;
  private static final double inchesToFeet = 0.0833333333;
  private static final double inchesToMillimeters = 25.4;

  public DistanceInches(NonNegativeDouble value) {
    super(value);
  }

  @Override public DistanceMeters asMeters() {
    return null;
  }

  @Override public DistanceFeet asFeet() {
    return null;
  }

  @Override public DistanceMillimeters asMillimeters() {
    return null;
  }

  @Override public DistanceInches asInches() {
    return null;
  }
}
