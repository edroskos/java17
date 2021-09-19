package net.edroskos.fp;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EitherTest {

  static class Animal {
    private final String typeName;
    private final String petName;

    Animal(String petName, String typeName) {
      this.petName = petName;
      this.typeName = typeName;
    }

    String getTypeName() {
      return typeName;
    }

    String getPetName() {
      return petName;
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

  @Test
  void testBasicRight() {
    Corgi mooie = new Corgi("Mooie");
    Corgi junior = new Corgi("Junior");
    Either<Exception, Animal> either = new Right<>(mooie);
    assertTrue(either.isRight());
    assertFalse(either.isLeft());
    assertEquals(new Right<>(mooie), new Right<>(mooie));
    assertNotEquals(new Right<>(mooie), new Right<>(junior));
    switch (either) {
      case Right<Exception, Animal> r -> assertEquals(r.value().getPetName(), "Mooie");
      case Left<Exception, Animal> ignore -> fail("Expected a 'right'");
    }
  }

  @Test
  void testBasicLeft() {
    Corgi mooie = new Corgi("Mooie");
    Corgi junior = new Corgi("Junior");
    Either<Animal, Double> either = new Left<>(mooie);
    assertFalse(either.isRight());
    assertTrue(either.isLeft());
    assertEquals(new Left<>(mooie), new Left<>(mooie));
    assertNotEquals(new Left<>(mooie), new Left<>(junior));
    switch (either) {
      case Right<Animal, Double> ignore -> fail("Expected a 'left'");
      case Left<Animal, Double> l -> assertEquals(l.value().getPetName(), "Mooie");
    }
  }


}
