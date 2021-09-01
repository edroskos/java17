package net.edroskos.validate;

public record OpenRangeEndpoint<T>(T value) implements RangeEndpoint<T> {
  @Override public boolean isOpen() { return true; }

  @Override public boolean isClosed() { return false; }
}
