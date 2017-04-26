package jsolitaire;

import java.io.Serializable;
import java.util.Objects;

/**
 * Represents a tuple, a pair of two values
 * 
 * @author Jakub Zarybnický (xzaryb00)
 * @author Jiří Záleský (xzales12)
 * @param <T0> The first value's type
 * @param <T1> The second value's type
 */
public class Pair<T0, T1> implements Serializable {

    private static final long serialVersionUID = 1L;

    private final T0 first;
    private final T1 second;

    /**
     * Constructs a Pair
     * 
     * @param first First value
     * @param second Second value
     */
    public Pair(T0 first, T1 second) {
        this.first = first;
        this.second = second;
    }

    /**
     * Returns the first value
     * 
     * @return The first value
     */
    public T0 getFirst() {
        return first;
    }

    /**
     * Returns the second value
     * 
     * @return The second value
     */
    public T1 getSecond() {
        return second;
    }

    /**
     * Constructs a pair of two values (in a shorter notation).
     * 
     * @param <A> The first value's type
     * @param <B> The second value's type
     * @param x The first value
     * @param y The second value
     * @return The resulting Pair
     */
    public static <A, B> Pair<A, B> of(A x, B y) {
        return new Pair<>(x, y);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Pair) {
            return first.equals(((Pair) obj).first) && second.equals(((Pair) obj).second);
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.first);
        hash = 79 * hash + Objects.hashCode(this.second);
        return hash;
    }
}
