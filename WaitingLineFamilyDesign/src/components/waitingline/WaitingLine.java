/**
 * Package declaration (maximum of 1 allowed).
 */
package components.waitingline;

/**
 * Javadoc comment explaining the purpose of this interface. This interface will
 * define the contract(s) for all other method(s) for the type. This interface
 * can be used to define instance methods that are default public abstract
 * (private instance methods and public/private static methods are allowed, but
 * cannot be abstract) and public static final variables.
 *
 * [additional information including; (params, mathsubtypes, mathdefinitions)]
 *
 * Additional information for WaitingLineKernel:
 *
 * @param <T>
 *            type of {@code WaitingLineKernel} entries
 *
 * @author Nathan Thompson
 */
public interface WaitingLine<T> extends WaitingLineKernel<T> {

    /*
     * How do we go about designing the enhanced interface? We define any other
     * methods that we deem useful for the component family that are not in the
     * kernel interface.
     */

    /**
     * Returns the position of {@code x} in this.
     *
     * @param x
     *            entry to find position of
     * @return the position of x
     * @requires x is in this
     */
    int position(T x);

    /**
     * Removes and returns the entry at position {@code pos} in {@code this}.
     *
     * @param pos
     *            position to remove entry
     * @return the entry at position pos
     * @updates this
     * @requires 0 <= pos and pos < |this|
     * @ensures <pre>
     * this = #this[0, pos) * #this[pos+1, |#this|)  and
     * <remove> = #this[pos, pos+1)
     * </pre>
     */
    T removeAtPosition(int pos);
}
