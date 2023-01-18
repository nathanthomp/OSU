import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Blank program (clear of Checkstyle and FindBugs warnings).
 *
 * @author N. Thompson
 */
public final class HW10 {

    /**
     * Default constructor--private to prevent instantiation.
     */
    private HW10() {
        // no code needed here
    }

    /**
     * Squares a given {@code NaturalNumber}.
     *
     * @param n
     *            the number to square
     * @updates n
     * @ensures n = #n * #n
     */
    private static void square(NaturalNumber n) {
        NaturalNumber temp = new NaturalNumber2();
        temp.copyFrom(n);
        n.multiply(temp);

    }

    /**
     * Swaps the two given {@code NaturalNumber}s.
     *
     * @param n1
     *            the first {@code NaturalNumber}
     * @param n2
     *            the second {@code NaturalNumber}
     * @updates n1
     * @updates n2
     * @ensures n1 = #n2 and n2 = #n1
     */
    private static void swapNN(NaturalNumber n1, NaturalNumber n2) {
        NaturalNumber temp = new NaturalNumber2();

        // copyFrom
//        temp.copyFrom(n2);
//        n2.copyFrom(n1);
//        n1.copyFrom(temp);

        // transferFrom
        temp.transferFrom(n2);
        n2.transferFrom(n1);
        n1.transferFrom(temp);
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments; unused here
     */
    public static void main(String[] args) {
        SimpleWriter out = new SimpleWriter1L();
        NaturalNumber n1 = new NaturalNumber2(1), n2 = new NaturalNumber2(2);

//        swapNN(n1, n2);
//        out.println("n1:" + n1 + " n2:" + n2);

        square(n2);
        out.println("square: " + n2);

    }

}
