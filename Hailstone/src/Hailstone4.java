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
public final class Hailstone4 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private Hailstone4() {
    }

    /**
     * Generates and outputs the Hailstone series starting with the given
     * integer.
     *
     * @param n
     *            the starting integer
     * @param out
     *            the output stream
     */
    private static void generateSeries(int n, SimpleWriter out) {
        int count = 1, max = 0;

        out.print(n + " ");

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

        }

        out.println();
        out.println("Length of the series: " + count);
        out.println("Max of the series: " + max);

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

        out.println("Would you like to calculate a hailstone series? (y/n)");
        String response = in.nextLine();

        while (response.equals("y")) {
            out.println("What should the starting value be? ");
            int input = in.nextInteger();

            generateSeries(input, out);
            out.println("Would you like to calculate another series? (y/n)");
            response = in.nextLine();
        }

        in.close();
        out.close();
    }

}
