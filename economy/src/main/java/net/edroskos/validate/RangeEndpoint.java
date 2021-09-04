package net.edroskos.validate;

/**
 * A class that captures an endpoint of a range of "numbers", where a number can be any type <code>T</code> that
 * has a {@link java.util.Comparator}.
 *
 * @see RangeValidator
 */
public sealed interface RangeEndpoint<T>
permits ClosedRangeEndpoint, OpenRangeEndpoint {
  public T value();
  public boolean isOpen();
  public boolean isClosed();
}
