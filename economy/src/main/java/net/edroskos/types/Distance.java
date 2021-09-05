package net.edroskos.types;

public sealed abstract class Distance
    permits DistanceMeters, DistanceFeet, DistanceMillimeters, DistanceInches {

  protected final NonNegativeDouble value;

  protected Distance(NonNegativeDouble value) {
    this.value = value;
  }

  public NonNegativeDouble value() {
    return value;
  }

  public abstract DistanceMeters asMeters();
  public abstract DistanceFeet asFeet();
  public abstract DistanceMillimeters asMillimeters();
  public abstract DistanceInches asInches();
}
