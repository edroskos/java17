package net.edroskos.types;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class NonNegativeDoubleTest {

  @Test
  void testNonNegativeDouble() {
    NonNegativeDouble nnd1 = NonNegativeDouble.of(50.0);
    assertEquals(nnd1.value(), 50.0);

    NonNegativeDouble nnd2 = NonNegativeDouble.of(0.0);
    assertEquals(nnd2.value(), 0.0);

    IllegalArgumentException iae1 = assertThrows(IllegalArgumentException.class, () -> {
      NonNegativeDouble.of(-0.0);
    });
    assertEquals(iae1.getMessage(), "NonNegativeDouble value must be >= 0: -0.0");

    IllegalArgumentException iae2 = assertThrows(IllegalArgumentException.class, () -> {
      NonNegativeDouble.of(-0.1);
    });
    assertEquals(iae2.getMessage(), "NonNegativeDouble value must be >= 0: -0.1");

    IllegalArgumentException iae3 = assertThrows(IllegalArgumentException.class, () -> {
      NonNegativeDouble.of(-0.1, "The Big Test");
    });
    assertTrue(iae3.getMessage().startsWith("The Big Test: NonNegativeDouble value must be >= 0: -0.1"));
  }
}
