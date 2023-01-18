import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Blank program (clear of Checkstyle and FindBugs warnings).
 *
 * @author N. Thompson
 */
public final class RecursionPractice {

    /**
     * Default constructor--private to prevent instantiation.
     */
    private RecursionPractice() {
        // no code needed here
    }

    /**
     * Recursive method to raise n to the power of p.
     *
     */
    private static int power(int n, int p) {
//        int power = 1;
        if (p > 0) {
            int power = n * n;
            p = p - 1;
            power(n, p);

            return power;
        }

        return -1;
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments; unused here
     */
    public static void main(String[] args) {
        SimpleWriter out = new SimpleWriter1L();
        out.println(power(3, 3));
    }

}
