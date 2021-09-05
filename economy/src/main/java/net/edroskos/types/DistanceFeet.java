package net.edroskos.types;

public final class DistanceFeet extends Distance {

  private static final double feetToMeters = 0.3048;
  private static final double feetToMillimeters = 304.8;
  private static final double feetToInches = 12.0;

  public DistanceFeet(NonNegativeDouble value) {
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
