package net.edroskos.collection;

import java.util.Objects;

/**
 * Represents a non-empty <code>List</code> as a linked list node that knows a single element in the linked list.
 *
 * @param <E> The type of element contained in the list.
 */
public final class Cons<E> extends List<E> {
  private final E head;
  private final List<E> tailList;

  public Cons(E head, List<E> tailList) {
    this.head = head;
    this.tailList = tailList;
  }

  public E head() {
    return head;
  }

  public List<E> tail() {
    return tailList;
  }

  @Override
  public boolean isEmpty() {
    return false;
  }

  @Override
  public int size() {
    return 1+tailList.size();
  }

  @Override
  public String toString() {
    return "Cons{" +
        "head=" + head +
        ", tailList=" + tailList +
        '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    Cons<?> cons = (Cons<?>) o;
    return Objects.equals(head, cons.head) && Objects.equals(tailList, cons.tailList);
  }

  @Override
  public int hashCode() {
    return Objects.hash(head, tailList);
  }
}
