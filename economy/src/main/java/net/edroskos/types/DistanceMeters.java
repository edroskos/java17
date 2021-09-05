package net.edroskos.types;

public final class DistanceMeters extends Distance {

  private static final double metersToFeet = 3.28084;
  private static final double metersToMillimeters = 1000.0;
  private static final double metersToInches =39.37007874;

  public DistanceMeters(NonNegativeDouble value) {
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
