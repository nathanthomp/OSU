import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Put a short phrase describing the program here.
 *
 * @author Nathan Thompson
 *
 */
public final class Hailstone5 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private Hailstone5() {
    }

    /**
     * Generates and outputs the Hailstone series starting with the given
     * integer.
     *
     * @param n
     *            the starting integer
     * @param out
     *            the output stream
     * @param in
     *            the input stream
     */
    private static void generateSeries(int n, SimpleWriter out,
            SimpleReader in) {
        int count = 1, max = 0;

        out.print(n + " ");

        out.println();
        out.println("Would you like to calculate another series? (y/n)");
        String response = in.nextLine();

        while (response == "y") {

            while (n > 1) {
                if (n % 2 == 0) {
                    n = n / 2;
                } else {
                    n = 3 * n + 1;
                }

                out.print(n + " ");
                count++;

                if (n > max) {
                    max = n;
                }

                out.println(
                        "Would you like to calculate another series? (y/n)");
                response = in.nextLine();
            }

        }

        out.println();
        out.println("Length of the series: " + count);
        out.println("Max of the series: " + max);

    }

    /**
     * Repeatedly asks the user for a positive integer until the user enters
     * one. Returns the positive integer.
     *
     * @param in
     *            the input stream
     * @param out
     *            the output stream
     * @return a positive integer entered by the user
     */
    private static int getPositiveInteger(SimpleReader in, SimpleWriter out) {

        return 1;
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

        out.println("What should the starting value be? ");
        int input = in.nextInteger();

        generateSeries(input, out, in);

        in.close();
        out.close();
    }

}
