package net.edroskos.validate;

/**
 * An endpoint for a numeric range, for example as used in {@link RangeValidator}, where compared values can also
 * equal our <code>value</code>.
 */
public record ClosedRangeEndpoint<T>(T value) implements RangeEndpoint<T> {
  @Override public boolean isOpen() { return false; }

  @Override public boolean isClosed() { return true; }
}
