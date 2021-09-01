package net.edroskos.validate;

import org.jetbrains.annotations.NotNull;

import java.util.Comparator;

public class RangeValidator<T> {
  private final RangeEndpoint<T> minValueOrNull;
  private final RangeEndpoint<T> maxValueOrNull;
  private final Comparator<T> comparator;

  private RangeValidator(
      RangeEndpoint<T> minEndpointOrNull,
      RangeEndpoint<T> maxEndpointOrNull,
      Comparator<T> comparator) {
    this.minValueOrNull = minEndpointOrNull;
    this.maxValueOrNull = maxEndpointOrNull;
    this.comparator = comparator;
  }

  public static <T> RangeValidator<T> withLower(
      @NotNull RangeEndpoint<T> minEndpoint,
      @NotNull Comparator<T> comparator
  ) {
    return new RangeValidator<>(minEndpoint, null, comparator);
  }

  public static <T> RangeValidator<T> withUpper(
      @NotNull RangeEndpoint<T> maxEndpoint,
      @NotNull Comparator<T> comparator
  ) {
    return new RangeValidator<>(maxEndpoint, null, comparator);
  }

  public static <T> RangeValidator<T> bounded(
      @NotNull RangeEndpoint<T> minEndpoint,
      @NotNull RangeEndpoint<T> maxEndpoint,
      @NotNull Comparator<T> comparator
  ) {
    int cmp = comparator.compare(minEndpoint.value(), maxEndpoint.value());
    if (cmp > 0) {
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
