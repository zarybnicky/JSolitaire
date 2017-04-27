package jsolitaire;

import java.util.Optional;
import java.util.function.Consumer;

/**
 * Represents a two-state union type, contains either <code>L</code> or
 * <code>R</code>.
 *
 * Left usually represents an error value, Right a normal value.
 *
 * @param <L> The Left type
 * @param <R> The Right type
 * @author Jakub Zarybnický (xzaryb00)
 * @author Jiří Záleský (xzales12)
 */
public class Either<L, R> {

    private final Optional<L> left;
    private final Optional<R> right;

    /**
     * Constructs an Either from two Optionals
     *
     * @param left The left Optional
     * @param right The right Optional
     */
    private Either(Optional<L> left, Optional<R> right) {
        this.left = left;
        this.right = right;
    }

    /**
     * Executes a function on the value inside, if it's left then lFunc, rFunc
     * otherwise.
     *
     * @param lFunc The function to execute if this is a Left
     * @param rFunc The function to execute if this is a Right
     */
    public void apply(Consumer<? super L> lFunc, Consumer<? super R> rFunc) {
        left.ifPresent(lFunc);
        right.ifPresent(rFunc);
    }

    /**
     * Constructs a Left
     *
     * @param <L> The Left type
     * @param <R> The Right type
     * @param value The value to insert into Left
     * @return The resulting Either
     */
    public static <L, R> Either<L, R> left(L value) {
        return new Either<>(Optional.of(value), Optional.empty());
    }

    /**
     * Constructs a Right
     *
     * @param <L> The Left type
     * @param <R> The Right type
     * @param value The value to insert into Right
     * @return The resulting Either
     */
    public static <L, R> Either<L, R> right(R value) {
        return new Either<>(Optional.empty(), Optional.of(value));
    }
}
