package net.edroskos.validate;

import org.jetbrains.annotations.NotNull;

import java.util.Comparator;

/**
 * A class for validating that a given "number" is between the given "min" and "max" bounds, where the bounds
 * themselves are optional in any combination.  A "number" in this sense is anything for which a {@link Comparator}
 * exists.
 *
 * @param <T> The type of the "numbers" that you want to verify against a range.
 */
public class RangeValidator<T> {
  private final RangeEndpoint<T> minValueOrNull;
  private final RangeEndpoint<T> maxValueOrNull;
  @NotNull private final Comparator<T> comparator;

  private RangeValidator(
      RangeEndpoint<T> minEndpointOrNull,
      RangeEndpoint<T> maxEndpointOrNull,
      @NotNull Comparator<T> comparator) {
    this.minValueOrNull = minEndpointOrNull;
    this.maxValueOrNull = maxEndpointOrNull;
    this.comparator = comparator;
  }

  /**
   * Whether the <code>value</code> is "within range".  If either an endpoint, that is min or max, is missing, that
   * endpoint is always satisfied.  A <code>null</code> value is never satisfied.
   */
  public boolean satisfies(T value) {
    return value != null && satisfiesLower(value) && satisfiesUpper(value);
  }

  /**
   * Factory method for a range that only has a lower bound.
   */
  public static <T> RangeValidator<T> lowerBounded(
      @NotNull RangeEndpoint<T> minEndpoint,
      @NotNull Comparator<T> comparator
  ) {
      return new RangeValidator<>(minEndpoint, null, comparator);
  }

  /**
   * Factory method for a range that only has an upper bound.
   */
  public static <T> RangeValidator<T> upperBounded(
      @NotNull RangeEndpoint<T> maxEndpoint,
      @NotNull Comparator<T> comparator
  ) {
    return new RangeValidator<>(null, maxEndpoint, comparator);
  }

  /**
   * Factory method for a range that has both an upper and lower bound.
   */
  public static <T> RangeValidator<T> bounded(
      @NotNull RangeEndpoint<T> minEndpoint,
      @NotNull RangeEndpoint<T> maxEndpoint,
      @NotNull Comparator<T> comparator
  ) {
    int cmp = comparator.compare(minEndpoint.value(), maxEndpoint.value());
    if (cmp > 0 || (cmp == 0 && (minEndpoint.isOpen() || maxEndpoint.isOpen()))) {
      var msg = String.format("Require %s <= %s", minEndpoint, maxEndpoint);
      throw new IllegalArgumentException(msg);
    }
    return new RangeValidator<>(minEndpoint, maxEndpoint, comparator);
  }

  private boolean satisfiesLower(T value) {
    return switch (minValueOrNull) {
      case null -> true;
      case OpenRangeEndpoint<T> open ->
          comparator.compare(value, open.value()) > 0;
      case ClosedRangeEndpoint<T> closed ->
          comparator.compare(value, closed.value()) >= 0;
    };
  }

  private boolean satisfiesUpper(T value) {
    return switch (maxValueOrNull) {
      case null -> true;
      case OpenRangeEndpoint<T> open ->
          comparator.compare(value, open.value()) < 0;
      case ClosedRangeEndpoint<T> closed ->
          comparator.compare(value, closed.value()) <= 0;
    };
  }
}
