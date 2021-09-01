package net.edroskos.validate;

public sealed interface RangeEndpoint<T>
permits ClosedRangeEndpoint, OpenRangeEndpoint {
  public T value();
  public boolean isOpen();
  public boolean isClosed();
}
