package net.edroskos.collection;

public sealed abstract class List<A>
    permits Cons, Nil {

  /**
   * Whether the list is empty, that is, contains no elements.
   */
  public abstract boolean isEmpty();

  /**
   * Whether the list has an element in it.
   */
  public boolean nonEmpty() {
    return !isEmpty();
  }

  /**
   * Returns the number of elements in the list.
   */
  public abstract int size();
}
