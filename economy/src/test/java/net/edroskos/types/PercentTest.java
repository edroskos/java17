package net.edroskos.types;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PercentTest {

  @Test
  void testPercent() {
    Percent percent = Percent.of(50);
    assertEquals(percent.value(), 50.0);

    IllegalArgumentException iae1 = assertThrows(IllegalArgumentException.class, () -> {
      Percent.of(-0.1);
    });
    assertTrue(iae1.getMessage().startsWith("Percent must be between 0 and 100:"));

    IllegalArgumentException iae2 = assertThrows(IllegalArgumentException.class, () -> {
      Percent.of(100.1, "The Big Test");
    });
    assertTrue(iae2.getMessage().startsWith("The Big Test: Percent must be between 0 and 100:"));

  }

}

