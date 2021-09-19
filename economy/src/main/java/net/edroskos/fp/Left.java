package net.edroskos.fp;

import java.util.Objects;

public final class Left<A, B> extends Either<A, B> {
  private final A value;

  public Left(A value) {
    this.value = value;
  }

  static public Either<A, B> of(A value) {
    return new Left(value);
  }

  public A value() {
    return value;
  }

  @Override
  public boolean isLeft() {
    return true;
  }

  @Override
  public boolean isRight() {
    return false;
  }

  @Override
  public String toString() {
    return "Left{" +
        "value=" + value +
        '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    Left<?, ?> left = (Left<?, ?>) o;
    return Objects.equals(value, left.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(value);
  }
}
