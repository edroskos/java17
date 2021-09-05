package net.edroskos.types;

import net.edroskos.validate.ClosedRangeEndpoint;
import net.edroskos.validate.RangeValidator;

import java.util.Comparator;
import java.util.Objects;

public final class NonNegativeDouble {
  double value;

  private static final RangeValidator<Double> validator =
      RangeValidator.<Double>lowerBounded(
          new ClosedRangeEndpoint<Double>(0.0),
          Comparator.naturalOrder()
      );

  private NonNegativeDouble(double value) {
    this.value = value;
  }

  public double value() {
    return value;
  }

  public static NonNegativeDouble of(double value) {
    return of(value, null);
  }

  public static NonNegativeDouble of(double value, String description) {
    if (!validator.satisfies(value)) {
      var desc = description == null ? "" : (description + ": ");
      throw new IllegalArgumentException(
          desc + "NonNegativeDouble value must be >= 0: " + value
      );
    }
    return new NonNegativeDouble(value);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    NonNegativeDouble that = (NonNegativeDouble) o;
    return Double.compare(that.value, value) == 0;
  }

  @Override public int hashCode() {
    return Objects.hash(value);
  }
}
