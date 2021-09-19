package net.edroskos.fp;

import java.util.Objects;

public final class Right<A, B> extends Either<A, B> {
  private final B value;

  public Right(B value) {
    this.value = value;
  }

  public B value() {
    return value;
  }

  @Override
  public boolean isLeft() {
    return false;
  }

  @Override
  public boolean isRight() {
    return true;
  }

  @Override
  public String toString() {
    return "Right{" +
        "value=" + value +
        '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    Right<?, ?> right = (Right<?, ?>) o;
    return Objects.equals(value, right.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(value);
  }
}
