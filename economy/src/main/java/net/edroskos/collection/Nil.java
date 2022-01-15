package net.edroskos.collection;

import java.util.function.Function;

/**
 * Represents an empty <code>List</code>.
 *
 * @param <E> The type of elements contained in the list, which for <code>Nil</code> is essentially ignored.
 */
public final class Nil<E> extends List<E> {
  @Override
  public boolean isEmpty() {
    return true;
  }

  @Override
  public int size() {
    return 0;
  }

  @Override
  public <B> List<B> map(Function<? super E, ? extends B> fn) {
    return new Nil<>();
  }

  @Override
  public String toString() {
    return "Nil";
  }

  @Override
  public int hashCode() {
    return 777;
  }

  @Override
  public boolean equals(Object o) {
    return (this == o) || (o != null && getClass() == o.getClass());
  }
}
