package net.edroskos.types;

import net.edroskos.validate.ClosedRangeEndpoint;
import net.edroskos.validate.RangeValidator;

import java.util.Comparator;
import java.util.Objects;

/**
 * A direction, as in compass direction, whose value is normalized to 0 to 360 degrees.
 */
public final class Direction {
  private final double degrees;

  private static final RangeValidator<Double> validator0to360 = RangeValidator.bounded(
      new ClosedRangeEndpoint<Double>(0.0),
      new ClosedRangeEndpoint<Double>(360.0),
      Comparator.<Double>naturalOrder()
  );
  private static final RangeValidator<Double> validatorM180to180 = RangeValidator.bounded(
      new ClosedRangeEndpoint<Double>(0.0),
      new ClosedRangeEndpoint<Double>(180.0),
      Comparator.<Double>naturalOrder()
  );

  private Direction(Double degrees) {
    this.degrees = degrees;
  }

  public Double degrees() {
    return degrees;
  }

  public Direction of(double degrees) {
    if (validator0to360.satisfies(degrees)) {
      return new Direction(degrees);
    }
    if (validatorM180to180.satisfies(degrees)) {
      return new Direction(degrees + 180.0);
    }
    throw new IllegalArgumentException("Invalid number of degrees for direction: " + degrees);
  }

  public DirectionLabel label() {
    if (degrees >= 337.5 || degrees <= 22.5) {
      return DirectionLabel.NORTH;
    } else if (degrees >= 22.5 && degrees <= 67.5) {
      return DirectionLabel.NORTH_EAST;
    } else if (degrees >= 67.5 && degrees <= 112.5) {
      return DirectionLabel.EAST;
    } else if (degrees >= 112.5 && degrees <= 157.5) {
      return DirectionLabel.SOUTH_EAST;
    } else if (degrees >= 157.5 && degrees <= 202.5) {
      return DirectionLabel.SOUTH;
    } else if (degrees >= 202.5 && degrees <= 247.5) {
      return DirectionLabel.SOUTH_WEST;
    } else if (degrees >= 247.5 && degrees <= 292.5) {
      return DirectionLabel.WEST;
    } else {
      return DirectionLabel.NORTH_WEST;
    }
  }

  @Override public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    Direction direction = (Direction) o;
    return degrees == direction.degrees;
  }

  @Override public int hashCode() {
    return Objects.hash(degrees);
  }

  @Override public String toString() {
    return "Direction{" +
        "degrees=" + degrees +
        '}';
  }
}
