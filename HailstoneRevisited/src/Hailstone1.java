import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Put a short phrase describing the program here.
 *
 * @author Put your name here
 *
 */
public final class Hailstone1 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private Hailstone1() {
    }

    /**
     * Generates and outputs the Hailstone series starting with the given
     * {@code NaturalNumber}.
     *
     * @param n
     *            the starting natural number
     * @param out
     *            the output stream
     * @updates out.content
     * @requires n > 0 and out.is_open
     * @ensures out.content = #out.content * [the Hailstone series starting with
     *          n]
     */
    private static void generateSeries(NaturalNumber n, SimpleWriter out) {
        NaturalNumber nCopy = new NaturalNumber2(n); // copy or transfer from??
        NaturalNumber temp = new NaturalNumber2();
        final NaturalNumber one = new NaturalNumber2(1);
        final NaturalNumber two = new NaturalNumber2(2);
        final NaturalNumber three = new NaturalNumber2(3);

        out.print(n + " ");

        while (nCopy.compareTo(one) > 0) {
            temp.copyFrom(nCopy);

            if (temp.divide(two).isZero()) {
                nCopy.divide(two);
            } else {
                nCopy.multiply(three);
                nCopy.add(one);
            }
            out.print(nCopy + " ");
        }
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();
        out.println("Enter a staring number: ");

//        17 52 26 13 40 20 10 5 16 8 4 2 1

        NaturalNumber n = new NaturalNumber2(in.nextInteger());

        generateSeries(n, out);

        out.println();
        out.println("value of n after call: " + n);

        in.close();
        out.close();
    }

}
