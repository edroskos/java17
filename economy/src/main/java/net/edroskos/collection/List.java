package net.edroskos.collection;

import net.edroskos.fp.Left;
import net.edroskos.fp.Right;

import java.util.Arrays;
import java.util.Optional;
import java.util.function.Function;

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

  /**
   * Create a new List by applying function fn to each element in our list.
   */
  public abstract <B> List<B> map(Function<? super A, ? extends B> fn);

  public A[] toArray(A[] array) {
    int size = size();
    A[] toFill = array;
    if ((array.length >= size)) {
      for (int ii=size; ii < array.length; ++ii) array[ii] = null;
    } else {
      toFill = Arrays.copyOf(array, size);
    }

    List<A> ll = this;
    for (int ii=0; ii < size; ++ii) {
      ll = switch (ll) {
        case Cons<A> cons    -> setNextArrayElem(toFill, ii, cons);
        case Nil<A> ignoreMe -> ignoreMe;
      };
    }

    return toFill;
  }

  private List<A> setNextArrayElem(A[] toFill, int atIndex, Cons<A> elem) {
    toFill[atIndex] = elem.head();
    return elem.tail();
  }

//  protected abstract <T> void addToArray(T[] toFill, int fromIndex);

  /*

    public <T> T[] toArray(T[] a) {
        if (a.length < size)
            // Make a new array of a's runtime type, but my contents:
            return (T[]) Arrays.copyOf(elementData, size, a.getClass());
        System.arraycopy(elementData, 0, a, 0, size);
        if (a.length > size)
            a[size] = null;
        return a;

*/


  /**
   * Returns a new list that contains the given elements, in order.
   */
  @SafeVarargs
  public static <A> List<A> of(A ... elems) {
    return of(0, elems);
  }

  private static <A> List<A> of(int offset, A[] elems) {
    if (offset >= elems.length) {
      return new Nil<>();
    }

    return new Cons<A>(elems[offset], of(offset+1, elems));
  }
}
