import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Blank program (clear of Checkstyle and FindBugs warnings).
 *
 * @author N. Thompson
 */
public final class HW9 {

    /**
     * Default constructor--private to prevent instantiation.
     */
    private HW9() {
        // no code needed here
    }

    /**
     * Swapping for primitive types.
     *
     * @param i1
     * @param i2
     */
    private static void swap1(int i1, int i2, SimpleWriter out) {
        int tmp = i1;
        i1 = i2;
        i2 = tmp;
        out.println(i1 + " " + i2);
    }

    /**
     * Swapping for immutable types.
     *
     * @param s1
     * @param s2
     */
    private static void swap2(String s1, String s2, SimpleWriter out) {

        String tmp = s1;

        s1 = s2;

        s2 = tmp;

        out.println(s1 + " " + s2);
    }

    /**
     * Swapping for mutable types.
     *
     * @param n1
     * @param n2
     */
    private static void swap3(NaturalNumber n1, NaturalNumber n2,
            SimpleWriter out) {

        NaturalNumber tmp = n1;

        n1 = n2;

        n2 = tmp;

        out.println(n1 + " " + n2);
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments; unused here
     */
    public static void main(String[] args) {
        SimpleWriter out = new SimpleWriter1L();

//        NaturalNumber k = new NaturalNumber2(70);
//        NaturalNumber m = new NaturalNumber2(143);
//        m.transferFrom(k);
//
//        out.println(m);
//        out.println(k);

//        int x = 7, y = 12;
//        swap1(x, y, out);
//        out.println(x + " " + y);

//        String x = "legends", y = "leaders";
//        swap2(x, y, out);
//        out.println(x + " " + y);

        NaturalNumber x = new NaturalNumber2(41), y = new NaturalNumber2(78);
        swap3(x, y, out);
        out.println(x + " " + y);

    }

}
