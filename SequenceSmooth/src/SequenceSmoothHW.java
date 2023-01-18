import components.sequence.Sequence;
import components.sequence.Sequence1L;

/**
 * Implements method to smooth a {@code Sequence<Integer>}.
 *
 * @author Put your name here
 *
 */
public final class SequenceSmoothHW {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private SequenceSmoothHW() {
    }

    /**
     * Smooths a given {@code Sequence<Integer>}.
     *
     * @param s1
     *            the sequence to smooth
     * @param s2
     *            the resulting sequence
     * @requires |s1| >= 1
     * @ensures <pre>
     * |s2| = |s1| - 1  and
     *  for all i, j: integer, a, b: string of integer
     *      where (s1 = a * <i> * <j> * b)
     *    (there exists c, d: string of integer
     *       (|c| = |a|  and
     *        s2 = c * <(i+j)/2> * d))
     * </pre>
     */
    public static Sequence<Integer> smooth(Sequence<Integer> s1,
            Sequence<Integer> s2) {
        assert s1 != null : "Violation of: s1 is not null";
        assert s2 != null : "Violation of: s2 is not null";
        assert s1 != s2 : "Violation of: s1 is not s2";
        assert s1.length() >= 1 : "Violation of: |s1| >= 1";

//        Sequence<Integer> results = new Sequence1L<>();
//        for (int i = 0; i < s1.length() - 1; i++) {
//            int num1 = s1.entry(i);
//            int num2 = s1.entry(i + 1);
//            int result = (num1 + num2) / 2;
//            results.add(results.length(), result);
//        }
//        return results;

        Sequence<Integer> results = new Sequence1L<>();
        int num1 = s1.remove(0);
        if (s1.length() > 0) {
            int num2 = s1.entry(0);
            int result = (num1 + num2) / 2;
            results = smooth(s1, s2);
            results.add(results.length(), result);
        }
        s1.add(0, num1);
        return results;
    }

}
