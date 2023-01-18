import components.set.Set;
import components.set.Set1L;

/**
 * Layered implementations of secondary methods {@code add} and {@code remove}
 * for {@code Set}.
 *
 * @param <T>
 *            type of {@code Set} elements
 */
public final class SetSecondary1L<T> extends Set1L<T> {

    /**
     * No-argument constructor.
     */
    public SetSecondary1L() {
        super();
    }

    /**
     * Removes from this all elements of s that are also in this, leaving s
     * unchanged, and returns the elements actually removed.
     *
     * updates this, restores s
     *
     * ensures: this = #this \ s and remove = #this intersection s
     */
    @Override
    public Set<T> remove(Set<T> s) {
        assert s != null : "Violation of: s is not null";
        assert s != this : "Violation of: s is not this";
        /*
         * set temp set as a copy of s
         */
        Set<T> temp = s.newInstance();
        temp.transferFrom(s);
        /*
         * create result
         */
        Set<T> result = new Set1L<>();

        while (temp.size() > 0) {
            T x = temp.removeAny();
            if (s.contains(x)) {
                this.remove(x);
                result.add(x);
            }

        }

        return result;

    }

    /**
     * Adds to this all elements of s that are not already in this, also
     * removing just those elements from s.
     *
     * updates: this and s
     *
     * ensures: this = #this union #s and s = #this intersection #s
     */
    @Override
    public void add(Set<T> s) {
        assert s != null : "Violation of: s is not null";
        assert s != this : "Violation of: s is not this";

        // TODO - fill in body

    }

}
