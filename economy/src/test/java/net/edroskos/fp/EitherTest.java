package net.edroskos.fp;

import org.junit.jupiter.api.Test;

import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class EitherTest {

  static class Dwelling {
    public boolean hasPet() {
      return false;
    }
  }

  static class House extends Dwelling {
    private final Animal pet;

    House(Animal pet) {
      this.pet = pet;
    }

    @Override
    public boolean hasPet() {
      return true;
    }

    @Override
    public String toString() {
      return "House w/pet=" + pet.toString();
    }
  }

  static class Animal {
    private final String typeName;
    private final String petName;

    Animal(String petName, String typeName) {
      this.petName = petName;
      this.typeName = typeName;
    }

    public String getPetName() {
      return petName;
    }

    @Override
    public String toString() {
      return "Animal(" + typeName + ":" + petName;
    }
  }

  static class Dog extends Animal {
    Dog(String petName) {
      super(petName, "Dog");
    }

    protected Dog(String petName, String typeName) {
      super(petName, typeName);
    }
  }

  static class Cat extends Animal {
    Cat(String petName) {
      super(petName, "Cat");
    }
  }

  static class Corgi extends Dog {
    Corgi(String petName) {
      super(petName, "Corgi");
    }
  }

  static class ShibaInu extends Dog {
    ShibaInu(String petName) {
      super(petName, "Shiba Inu");
    }
  }

  static Corgi mooie = new Corgi("Mooie");
  static Corgi junior = new Corgi("Junior");
  static ShibaInu inu = new ShibaInu("Inu");
  static Either<Exception, Corgi> mooieRightEither = new Right<>(mooie);
  static Either<Corgi, Double> mooieLeftEither = new Left<>(mooie);
  static Function<Exception, Dwelling> exceptionToHouseFn = ex -> new House(inu);
  static Function<Double, Dwelling> doubleToHouseFn = d -> new House(inu);

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
