package net.edroskos.validate;

import org.junit.jupiter.api.Test;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;

public class RangeValidatorTest {

  @Test
  void testRangeValidatorLower() {
    RangeValidator<Long> openValidator =
        RangeValidator.lowerBounded(new OpenRangeEndpoint<>(10L), Comparator.<Long>naturalOrder());
    assertTrue(openValidator.satisfies(11L));
    assertFalse(openValidator.satisfies(10L));
    assertFalse(openValidator.satisfies(9L));
    assertTrue(openValidator.satisfies(50L));
    assertFalse(openValidator.satisfies(-50L));

    RangeValidator<Long> closedValidator =
        RangeValidator.lowerBounded(new ClosedRangeEndpoint<>(10L), Comparator.<Long>naturalOrder());
    assertTrue(closedValidator.satisfies(11L));
    assertTrue(closedValidator.satisfies(10L));
    assertFalse(closedValidator.satisfies(9L));
    assertTrue(closedValidator.satisfies(50L));
    assertFalse(closedValidator.satisfies(-50L));
  }

  @Test
  void testRangeValidatorUpper() {
    RangeValidator<Long> openValidator =
        RangeValidator.upperBounded(new OpenRangeEndpoint<>(10L), Comparator.<Long>naturalOrder());
    assertFalse(openValidator.satisfies(11L));
    assertFalse(openValidator.satisfies(10L));
    assertTrue(openValidator.satisfies(9L));
    assertFalse(openValidator.satisfies(50L));
    assertTrue(openValidator.satisfies(-50L));

    RangeValidator<Long> closedValidator =
        RangeValidator.upperBounded(new ClosedRangeEndpoint<>(10L), Comparator.<Long>naturalOrder());
    assertFalse(closedValidator.satisfies(11L));
    assertTrue(closedValidator.satisfies(10L));
    assertTrue(closedValidator.satisfies(9L));
    assertFalse(closedValidator.satisfies(50L));
    assertTrue(closedValidator.satisfies(-50L));
  }

  @Test
  void testRangeValidatorBound() {
    RangeValidator<Long> openClosedValidator =
        RangeValidator.bounded(
            new OpenRangeEndpoint<>(10L),
            new ClosedRangeEndpoint<>(20L),
            Comparator.<Long>naturalOrder()
        );
    assertTrue(openClosedValidator.satisfies(11L));
    assertFalse(openClosedValidator.satisfies(10L));
    assertFalse(openClosedValidator.satisfies(9L));
    assertFalse(openClosedValidator.satisfies(21L));
    assertTrue(openClosedValidator.satisfies(20L));
    assertTrue(openClosedValidator.satisfies(19L));
    assertTrue(openClosedValidator.satisfies(16L));
    assertFalse(openClosedValidator.satisfies(0L));

    RangeValidator<Long> closedOpenValidator =
        RangeValidator.bounded(
            new ClosedRangeEndpoint<>(10L),
            new OpenRangeEndpoint<>(20L),
            Comparator.<Long>naturalOrder()
        );
    assertTrue(closedOpenValidator.satisfies(11L));
    assertTrue(closedOpenValidator.satisfies(10L));
    assertFalse(closedOpenValidator.satisfies(9L));
    assertFalse(closedOpenValidator.satisfies(21L));
    assertFalse(closedOpenValidator.satisfies(20L));
    assertTrue(closedOpenValidator.satisfies(19L));
    assertTrue(closedOpenValidator.satisfies(16L));
    assertFalse(closedOpenValidator.satisfies(0L));
  }

  @Test
  void testRangeValidatorInvalidBounds() {
    assertThrows(
        IllegalArgumentException.class,
        () -> RangeValidator.bounded(
            new ClosedRangeEndpoint<>(20L),
            new OpenRangeEndpoint<>(10L),
            Comparator.<Long>naturalOrder()
        ));
    assertThrows(
        IllegalArgumentException.class,
        () -> RangeValidator.bounded(
            new ClosedRangeEndpoint<>(20L),
            new OpenRangeEndpoint<>(20L),
            Comparator.<Long>naturalOrder()
        ));
    assertThrows(
        IllegalArgumentException.class,
        () -> RangeValidator.bounded(
            new OpenRangeEndpoint<>(20L),
            new ClosedRangeEndpoint<>(20L),
            Comparator.<Long>naturalOrder()
        ));
    assertThrows(
        IllegalArgumentException.class,
        () -> RangeValidator.bounded(
            new OpenRangeEndpoint<>(20L),
            new OpenRangeEndpoint<>(20L),
            Comparator.<Long>naturalOrder()
        ));
  }

  @Test
  void testRangeValidatorOneValue() {
    RangeValidator<Long> validator = RangeValidator.bounded(
        new ClosedRangeEndpoint<>(20L),
        new ClosedRangeEndpoint<>(20L),
        Comparator.<Long>naturalOrder()
    );

    assertTrue(validator.satisfies(20L));
    assertFalse(validator.satisfies(19L));
    assertFalse(validator.satisfies(21L));
  }
}
