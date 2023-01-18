/**
 * Package declaration (maximum of 1 allowed).
 */
package components.waitingline;

import java.util.Iterator;
import java.util.NoSuchElementException;

import components.sequence.Sequence;
import components.sequence.Sequence1L;

/**
 * WaitingLine implementation using {@code Sequence}.
 *
 * @param <T>
 *            type of {@code WaitingLineKernel} entries
 *
 * @author Nathan Thompson
 */
public class WaitingLine1L<T> extends WaitingLineSecondary<T> {

    /*
     * Private members --------------------------------------------------------
     */

    /**
     * Representation of {@code this}.
     */
    private Sequence<T> rep;

    /**
     * Creator of initial representation.
     */
    private void createNewRep() {
        this.rep = new Sequence1L<T>();
    }

    /*
     * Constructors -----------------------------------------------------------
     */

    /**
     * No-argument constructor.
     */
    public WaitingLine1L() {
        this.createNewRep();
    }

    /*
     * Kernel methods ---------------------------------------------------------
     */

    @Override
    public final void addToBackOfLine(T x) {
        this.rep.add(this.rep.length(), x);
    }

    @Override
    public final T removeFromFrontOfLine() {
        return this.rep.remove(0);
    }

    @Override
    public final int sizeOfLine() {
        return this.rep.length();
    }

    @Override
    public final boolean isAlreadyInLine(T x) {
        boolean result = false;
        for (int i = 0; i < this.rep.length() && !result; i++) {
            if (this.rep.entry(i).equals(x)) {
                result = true;
            }
        }
        return result;
    }

    @Override
    public final Iterator<T> iterator() {
        return new WaitingLine1LIterator();
    }

    /**
     * Implementation of {@code Iterator} interface for {@code WaitingLine1L}.
     */
    private final class WaitingLine1LIterator implements Iterator<T> {

        /**
         * Representation iterator.
         */
        private final Iterator<T> iterator;

        /**
         * No-argument constructor.
         */
        private WaitingLine1LIterator() {
            this.iterator = WaitingLine1L.this.rep.iterator();
        }

        @Override
        public boolean hasNext() {
            return this.iterator.hasNext();
        }

        @Override
        public T next() {
            if (!this.hasNext()) {
                throw new NoSuchElementException();
            }
            return this.iterator.next();
        }

    }

}
