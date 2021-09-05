package net.edroskos.types;

public sealed interface Distance
    permits DistanceMeters, DistanceFeet, DistanceMillimeters, DistanceInches {

}
