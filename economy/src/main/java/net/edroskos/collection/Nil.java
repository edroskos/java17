package net.edroskos.collection;

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
