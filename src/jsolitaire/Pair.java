/*
 * License
 */

package jsolitaire;

import java.util.Objects;

public class Pair<T0, T1> {
    private final T0 first;
    private final T1 second;

    public Pair(T0 first, T1 second) {
        this.first = first;
        this.second = second;
    }

    public T0 getFirst() {
        return first;
    }

    public T1 getSecond() {
        return second;
    }
    
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
