package net.edroskos.collection;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ListTest {

  @Test
  void testNil() {
    // cardinality checks
    List<String> emptyList = new Nil<>();
    assertEquals(0, emptyList.size());
    assertTrue(emptyList.isEmpty());
    assertFalse(emptyList.nonEmpty());

    // toArray
    String[] smallArray = new String[0];
    String[] smallResult = emptyList.toArray(smallArray);
    assertEquals(System.identityHashCode(smallArray), System.identityHashCode(smallResult));
    String[] bigArray = new String[10];
    for (int ii=0; ii < 10; ++ii) bigArray[ii] = "should-be-set-null";
    String[] bigResult = emptyList.toArray(bigArray);
    assertEquals(System.identityHashCode(bigArray), System.identityHashCode(bigResult));
    for (int ii=0; ii < 10; ++ii)
      assertNull(bigArray[ii], "Index " + ii);

    // map
    List<Integer> emptyIntList = emptyList.map( (String s) -> 42 );
    assertEquals(0, emptyIntList.size());
    assertTrue(emptyIntList.isEmpty());
    assertFalse(emptyIntList.nonEmpty());
  }

  @Test
  void testOneElem() {
    // cardinality checks
    List<String> singletonList = List.of("one");
    assertEquals(1, singletonList.size());
    assertFalse(singletonList.isEmpty());
    assertTrue(singletonList.nonEmpty());

    // toArray
    String[] smallArray = new String[0];
    String[] smallResult = singletonList.toArray(smallArray);
    assertNotEquals(System.identityHashCode(smallArray), System.identityHashCode(smallResult));
    String[] bigArray = new String[10];
    for (int ii=0; ii < 10; ++ii) bigArray[ii] = "should-be-set-null";
    String[] bigResult = singletonList.toArray(bigArray);
    assertEquals(System.identityHashCode(bigArray), System.identityHashCode(bigResult));
    for (int ii=1; ii < 10; ++ii)
      assertNull(bigArray[ii], "Index " + ii);
    assertEquals("one", bigArray[0]);

    // map
    List<Integer> singletonIntList = singletonList.map( (String s) -> 42 );
    assertEquals(1, singletonIntList.size());
    assertFalse(singletonIntList.isEmpty());
    assertTrue(singletonIntList.nonEmpty());
  }

  @Test
  void testManyElem() {
    // cardinality checks
    List<String> manyList = List.of("one", "two", "three", "four");
    assertEquals(4, manyList.size());
    assertFalse(manyList.isEmpty());
    assertTrue(manyList.nonEmpty());

    // toArray
    String[] smallArray = new String[0];
    String[] smallResult = manyList.toArray(smallArray);
    assertNotEquals(System.identityHashCode(smallArray), System.identityHashCode(smallResult));
    String[] bigArray = new String[10];
    for (int ii=0; ii < 10; ++ii) bigArray[ii] = "should-be-set-null";
    String[] bigResult = manyList.toArray(bigArray);
    assertEquals(System.identityHashCode(bigArray), System.identityHashCode(bigResult));
    for (int ii=4; ii < 10; ++ii)
      assertNull(bigArray[ii], "Index " + ii);
    assertEquals("one", bigArray[0]);
    assertEquals("two", bigArray[1]);
    assertEquals("three", bigArray[2]);
    assertEquals("four", bigArray[3]);

    // map
    List<Integer> manyIntList = manyList.map(String::length);
    assertFalse(manyIntList.isEmpty());
    assertTrue(manyIntList.nonEmpty());
    assertEquals(4, manyIntList.size());
    Integer[] intArray = manyIntList.toArray(new Integer[0]);
    assertEquals(3, intArray[0]);
    assertEquals(3, intArray[1]);
    assertEquals(5, intArray[2]);
    assertEquals(4, intArray[3]);
  }
}
