/**
 * Package declaration (maximum of 1 allowed).
 */
package components.waitingline;

import components.standard.Standard;

/**
 * Javadoc comment explaining the purpose of the interface. This interface will
 * define the mathematical model for the type, contract(s) for the constructors,
 * contract(s) for the kernel methods, and contract(s) for methods inherited
 * that do not have their own contract specifications. This interface can be
 * used to define instance methods that are default public abstract (private
 * instance methods and public/private static methods are allowed, but cannot be
 * abstract) and public static final variables.
 *
 * [additional information including; (params, mathsubtypes, mathdefinitions,
 * mathmodel, initially, iterator)]
 *
 * Additional information for WaitingLineKernel:
 *
 * @param <T>
 *            type of {@code WaitingLineKernel} entries
 * @mathdefinitions <pre>
 * UNIQUE(x):
 *  exemplar x
 *  constraint x is not in this
 * </pre>
 * @mathmodel type WaitingLineKernel is modeled by finite String of T
 * @initially <pre>
 * ():
 *  ensures
 *      this = <>
 * </pre>
 * @iterator ~this.seen * ~this.unseen = this
 *
 * @author Nathan Thompson
 */
public interface WaitingLineKernel<T>
        extends Standard<WaitingLine<T>>, Iterable<T> {

    /*
     * How do we go about designing a kernel interface? First, choose an
     * appropriate mathematical model to describe the kernel's type being
     * defined. Then, we want the kernel methods to be minimal and functionally
     * complete.
     */

    /**
     * Adds {@code x} to the back of the line.
     *
     * @param x
     *            the entry to be added
     * @updates this
     * @requires UNIQUE(x)
     * @ensures this = #this * <x>
     */
    void add(T x);

    /**
     * Removes and returns the entry at the front of {@code this}.
     *
     * @return the entry removed
     * @updates this
     * @requires this /= <>
     * @ensures #this = <remove> * this
     */
    T remove();

    /**
     * Reports the length of {@code this}.
     *
     * @return the length of {@code this}
     * @ensures length = |this|
     */
    int size();
}
