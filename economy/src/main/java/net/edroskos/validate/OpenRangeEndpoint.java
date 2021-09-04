package net.edroskos.validate;

/**
 * An endpoint for a numeric range, for example as used in {@link RangeValidator}, where compared values must be
 * strictly greater than (lower bound), or less than (greater than), our <code>value</code>.
 */
public record OpenRangeEndpoint<T>(T value) implements RangeEndpoint<T> {
  @Override public boolean isOpen() { return true; }

  @Override public boolean isClosed() { return false; }
}
