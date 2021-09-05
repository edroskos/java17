package net.edroskos.types;

public final class DistanceMillimeters extends Distance {

  private static final double millimetersToMeters = 0.001;
  private static final double millimetersToFeet = 0.00328084;
  private static final double millimetersToInches = 0.039370;

  public DistanceMillimeters(NonNegativeDouble value) {
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
