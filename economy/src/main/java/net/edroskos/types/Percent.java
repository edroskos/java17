package net.edroskos.types;

import net.edroskos.validate.ClosedRangeEndpoint;
import net.edroskos.validate.RangeValidator;

import java.util.Comparator;
import java.util.Objects;

/**
 * A percentage, which is restricted to a value between 0 and 100.
 */
public final class Percent {
  private final double value;

  private static final RangeValidator<Double> validator = RangeValidator.bounded(
      new ClosedRangeEndpoint<Double>(0.0),
      new ClosedRangeEndpoint<Double>(100.0),
      Comparator.<Double>naturalOrder()
  );

  private Percent(double value) {
    this.value = value;
  }

  public double value() { return value; }

  /**
   * Factory method similar to the overloaded version but without a description.
   */
  public static Percent of(double value) {
    return of(value, null);
  }

  /**
   * Factory method given the <code>value</code> for the percentage, from 0 to 100.  If the value is not on this
   * range, an {@link IllegalArgumentException} is thrown, using <code>description</code> to help form the
   * message (if not <code>null</code>).
   */
  public static Percent of(double value, String description) {
    if (!validator.satisfies(value)) {
      var desc = description == null ? "" : (description + ": ");
      throw new IllegalArgumentException(desc + "Percent must be between 0 and 100: " + value);
    }
    return new Percent(value);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    Percent percent = (Percent) o;
    return Double.compare(percent.value, value) == 0;
  }

  @Override
  public int hashCode() {
    return Objects.hash(value);
  }

  @Override
  public String toString() {
    return value + "%";
  }
}
