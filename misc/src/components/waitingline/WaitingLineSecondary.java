/**
 * Package declaration (maximum of 1 allowed).
 */
package components.waitingline;

import java.util.Iterator;

/**
 * Layered implementations of secondary methods for {@code WaitingLine}.
 *
 * @param <T>
 *            type of {@code WaitingLineKernel} entries
 *
 * @author Nathan Thompson
 */
public abstract class WaitingLineSecondary<T> implements WaitingLine<T> {

    /*
     * Object methods ---------------------------------------------------------
     */

    @Override
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        /*
         * Ignore this bug warning because we have already checked for null
         * instance of $this.
         */
        if (!(this instanceof WaitingLine<?>)) {
            return false;
        }
        /*
         * Ignore this bug warning because we have already ensures that obj is
         * of type WaitingLine.
         */
        WaitingLine<?> wl = (WaitingLine<?>) obj;
        if (this.sizeOfLine() != wl.sizeOfLine()) {
            return false;
        }
        Iterator<T> it1 = this.iterator();
        Iterator<?> it2 = wl.iterator();
        while (it1.hasNext()) {
            T x1 = it1.next();
            Object x2 = it2.next();
            if (!x1.equals(x2)) {
                return false;
            }
        }
        return true;
    }

    // CHECKSTYLE: ALLOW THIS METHOD TO BE OVERRIDDEN
    @Override
    public int hashCode() {
        final int samples = 2;
        final int a = 37;
        final int b = 17;
        int result = 0;
        /*
         * This code makes hashCode run in O(1) time. It works because of the
         * iterator order string specification, which guarantees that the (at
         * most) samples entries returned by the it.next() calls are the same
         * when the two Queues are equal.
         */
        int n = 0;
        Iterator<T> it = this.iterator();
        while (n < samples && it.hasNext()) {
            n++;
            T x = it.next();
            result = a * result + b * x.hashCode();
        }
        return result;
    }

    // CHECKSTYLE: ALLOW THIS METHOD TO BE OVERRIDDEN
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("<");
        Iterator<T> it = this.iterator();
        while (it.hasNext()) {
            result.append(it.next());
            if (it.hasNext()) {
                result.append(",");
            }
        }
        result.append(">");
        return result.toString();
    }

    /*
     * Enhanced methods -------------------------------------------------------
     */

    @Override
    public final int positionInLine(T x) {
        int result = 0;
        for (int i = 0; i < this.sizeOfLine(); i++) {
            T y = this.removeFromFrontOfLine();
            if (y.equals(x)) {
                result = i;
            }
            this.addToBackOfLine(y);
        }
        return result;
    }

    @Override
    public final void removeFromLine(T x) {
        for (int i = 0; i < this.sizeOfLine(); i++) {
            T y = this.removeFromFrontOfLine();
            if (!x.equals(y)) {
                this.addToBackOfLine(y);
            }
        }
    }

    @Override
    public final T frontOfLine() {
        T result = this.removeFromFrontOfLine();
        this.addToBackOfLine(result);
        for (int i = 0; i < this.sizeOfLine() - 1; i++) {
            T x = this.removeFromFrontOfLine();
            this.addToBackOfLine(x);
        }
        return result;
    }

}
