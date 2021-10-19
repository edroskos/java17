package net.edroskos.fp;

import org.junit.jupiter.api.Test;

import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class ListTest {

  @Test
  void testBasicRight() {
    assertTrue(mooieRightEither.isRight());
    assertFalse(mooieRightEither.isLeft());
    assertEquals(mooieRightEither, new Right<>(mooie));
    assertNotEquals(mooieRightEither, new Right<>(junior));
    switch (mooieRightEither) {
      case Right<Exception, ? extends Animal> r -> assertEquals(r.value().getPetName(), "Mooie");
      case Left<Exception, ? extends Animal> ignore -> fail("Expected a 'right'");
    }
  }

  @Test
  void testBasicLeft() {
    assertFalse(mooieLeftEither.isRight());
    assertTrue(mooieLeftEither.isLeft());
    assertEquals(new Left<>(mooie), new Left<>(mooie));
    assertNotEquals(new Left<>(mooie), new Left<>(junior));
    switch (mooieLeftEither) {
      case Right<? extends Animal, Double> ignore -> fail("Expected a 'left'");
      case Left<? extends Animal, Double> l -> assertEquals(l.value().getPetName(), "Mooie");
    }
  }

  @Test
  void testFoldRight() {
    Dwelling dwelling = mooieRightEither.fold(exceptionToHouseFn, House::new);
    assertTrue(dwelling instanceof House);
    assertEquals(((House) dwelling).pet, mooie);
  }

  @Test
  void testFoldLeft() {
    Dwelling dwelling = mooieLeftEither.fold(House::new, doubleToHouseFn);
    assertTrue(dwelling instanceof House);
    assertEquals(((House) dwelling).pet, mooie);
  }
}
