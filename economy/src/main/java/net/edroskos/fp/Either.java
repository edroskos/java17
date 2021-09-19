package net.edroskos.fp;

import java.util.function.Function;
import java.util.function.Predicate;

/**
 * A class for <code>Either</code> an <code>A</code> or a <code>B</code>, where <code>A</code> values are
 * {@link Left} instances and <code>B</code> values are {@link Right} instances.
 * <p/>
 * Based on the similar class from the Scala library.
 */
public sealed abstract class Either<A, B>
    permits Left, Right {

  /**
   * If this is a {@link Left} then apply <code>fa</code> to the value of the left, else apply <code>fb</code> to the
   * value of the {@link Right}.
   *
   * @param fa  The function to apply to our left value, if we are a left.
   * @param fb  The function to apply to our right value, if we are a right.
   * @param <C> The return type of our method.
   */
  public <C> C fold(Function<? super A, ? extends C> fa, Function<? super B, ? extends C> fb) {
    return switch (this) {
      case Left<A, B> l -> fa.apply(l.value());
      case Right<A, B> r -> fb.apply(r.value());
    };
  }

  /**
   * If this is a {@link Left}, then return a {@link Right} with the value from the left, and vice versa if this is a
   * {@link Right}, returning a {@link Left}.
   */
  public Either<B, A> swap() {
    return switch (this) {
      case Left<A, B> l -> new Right<>(l.value());
      case Right<A, B> r -> new Left<>(r.value());
    };
  }

  /**
   * Executes <code>fn</code> on our value if we are a <code>Right</code>.  The generic parameter <code>U</code> is only
   * used to specify the function type and is otherwise ignored.
   */
  public <U> void foreach(Function<? super B, ? extends U> fn) {
    switch (this) {
      case Left<A, B> l:
        break;
      case Right<A, B> r:
        fn.apply(r.value());
    }
  }

  /**
   * If this is a <code>Right</code>, return our value, otherwise return <code>other</code>.
   */
  public <B1 extends B> B getOrElse(B1 other) {
    return switch (this) {
      case Left<A, B> l -> other;
      case Right<A, B> r -> r.value();
    };
  }

  /**
   * Allows us to provide a different <code>Left</code> only if we are a <code>Left</code>.
   * <p/>
   * Returns <code>or</code>if this is a <code>Left</code>, else return our instance.
   */
  public Either<? super A, ? super B> orElse(Either<? super A, ? super B> or) {
    return switch (this) {
      case Left<A, B> ignored -> or;
      case Right<A, B> alsoIgnored -> this;
    };
  }

  /**
   * Returns <code>true</code> if this is a <code>Right</code> and its value is equal to <code>elem</code>
   * (as determined by <code>equals(...)</code>), otherwise returns <code>false</code>.
   *
   *  @param elem    the element to test.
   *  @return `true` if this is a `Right` value equal to `elem`.
   */
  public boolean contains(B elem) {
    return switch (this) {
      case Left<A, B> ignored -> false;
      case Right<A, B> r -> r.value().equals(elem);
    };
  }

  /**
   * Returns <code>true</code> if all elements in the container satisfy the <code>predicate</code>, where our
   * container is considered empty if we are a <code>Left</code>.  That is, return <code>true</code> if
   * we are a <code>Left</code> or returns the result of the application of <code>predicate</code> to the
   * <code>Right</code> value.
   */
  public boolean forall(Predicate<? super B> predicate) {
    return switch (this) {
      case Left<A, B> ignored -> true;
      case Right<A, B> r -> predicate.test(r.value());
    };
  }

  /**
   * Returns whether there exists an element exists that satisfies the <code>predicate</code>, where a
   * <code>Left</code> instance is considered an "empty container".  In other words, returns <code>false</code>
   * for a <code>Left</code> else the result of applying the <code>predicate</code> to the <code>Right</code>.
   */
  public boolean exists(Predicate<? super B> predicate) {
    return switch (this) {
      case Left<A, B> ignored -> false;
      case Right<A, B> r -> predicate.test(r.value());
    };
  }

  /**
   * Applies <code>fn</code> to the value of our <code>Right</code>, else returns our <code>Left</code>.
   */
  public <C> Either<? super A, C> flatMap(Function<? super B, Either<? super A, C>> fn) {
    return switch (this) {
      case Left<A, B> l -> new Left<>(l.value());  /* works around warning of unsafe cast */
      case Right<A, B> r -> fn.apply(r.value());
    };
  }

  /**
   * Applies the given <code>fn</code> over our elements, that is, if we are a <code>Right</code> then return the
   * <code>Right</code> of the result of applying the function to our value, else return our instance.
   */
  public <C> Either<A, C> map(Function<? super B, ? extends C> fn) {
    return switch (this) {
      case Left<A, B> l -> new Left<>(l.value());   /* works around warning of unsafe cast */
      case Right<A, B> r -> new Right<>(fn.apply(r.value()));
    };
  }

  /**
   * If we have a value (that is, we are a <code>Right</code>) but the <code>predicate</code> does not hold
   * on the value, then "filter it out" to <code>zero</code>, that is, return <code>Left(zero)</code>, otherwise,
   * just return our instance.
   */
  public Either<A, B> filterOrElse(Predicate<? super B> predicate, A zero) {
    return switch (this) {
      case Right<A, B> r && !predicate.test(r.value()) -> new Left(zero);
      default -> this;
    };
  }

  /**
   * Similar to the overloaded version, but instead of providing the the value of the <code>Left</code> in case
   * we filter out the value, we allow specification of the <code>Left</code>, allowing us to work around returning
   * a super-class of <code>A</code>, given Java's support only for contravariant wildcards and lack of support for
   * contravariant type parameters.
   */
  public Either<? super A, B> filterOrElse(Predicate<? super B> predicate, Left<? super A, B> zeroLeft) {
    return switch (this) {
      case Right<A, B> r && !predicate.test(r.value()) -> zeroLeft;
      default -> this;
    };
  }

  /**
   * Returns an <code>Optional</code> that is empty if we are a <code>Left</code>, otherwise contains the value
   * of our <code>Right</code>.
   */
  public Optional<B> toOptional() {
    return switch (this) {
      case Left<A, B> l -> Optional.empty();
      case Right<A, B> r -> Optional.of(r.value());
    };
  }

  public abstract boolean isLeft();

  public abstract boolean isRight();
}
