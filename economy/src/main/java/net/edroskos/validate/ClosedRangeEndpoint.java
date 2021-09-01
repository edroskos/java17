package net.edroskos.validate;

public record ClosedRangeEndpoint<T>(T value) implements RangeEndpoint<T> {
  @Override public boolean isOpen() { return false; }

  @Override public boolean isClosed() { return true; }
}
