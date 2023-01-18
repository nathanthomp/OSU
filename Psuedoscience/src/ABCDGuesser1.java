import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.utilities.FormatChecker;

/**
 * Simple HelloWorld program (clear of Checkstyle and FindBugs warnings).
 *
 * @author N. Thompson
 */
public final class ABCDGuesser1 {

    /**
     * Default constructor--private to prevent instantiation.
     */
    private ABCDGuesser1() {
        // no code needed here
    }

    /**
     * Repeatedly asks the user for a positive real number until the user enters
     * one. Returns the positive real number.
     *
     * @param in
     *            the input stream
     * @param out
     *            the output stream
     * @return a positive real number entered by the user
     */
    private static double getPositiveDouble(SimpleReader in, SimpleWriter out) {
        double result = 0;

        out.println("Enter a positive real number: ");
        String input = in.nextLine();

        while (!FormatChecker.canParseDouble(input)
                || Double.parseDouble(input) < 0) {
            out.println("Try again: ");
            input = in.nextLine();
        }

        result = Double.parseDouble(input);

        return result;
    }

    /**
     * Repeatedly asks the user for a positive real number not equal to 1.0
     * until the user enters one. Returns the positive real number.
     *
     * @param in
     *            the input stream
     * @param out
     *            the output stream
     * @return a positive real number not equal to 1.0 entered by the user
     */
    private static double getPositiveDoubleNotOne(SimpleReader in,
            SimpleWriter out) {
        double result = 0;
        out.println("Enter a positive real number for μ: ");
        String input = in.nextLine();

        while (!FormatChecker.canParseDouble(input)
                || Double.parseDouble(input) <= 1) {
            out.println("Try again: ");
            input = in.nextLine();
        }

        result = Double.parseDouble(input);

        return result;
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments; unused here
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();

        final double[] exponents = { -5, -4, -3, -2, -1, -1 / 2, -1 / 3, -1 / 4,
                0, 1 / 4, 1 / 3, 1 / 2, 1, 2, 3, 4, 5 };

        double max1 = 0, max2 = 0, max3 = 0, max4 = 0, currentEstimate = 0,
                bestEstimate = 0, a = 0, b = 0, c = 0, d = 0;

        double mu = getPositiveDoubleNotOne(in, out);
        double w = getPositiveDouble(in, out);
        double x = getPositiveDouble(in, out);
        double y = getPositiveDouble(in, out);
        double z = getPositiveDouble(in, out);

        int i = 0;
        while (i < exponents.length) {
            max1 = Math.pow(w, exponents[i]);

            int j = 0;
            while (j < exponents.length) {
                max2 = Math.pow(x, exponents[j]);

                int k = 0;
                while (k < exponents.length) {
                    max3 = Math.pow(y, exponents[k]);

                    int l = 0;
                    while (l < exponents.length) {
                        max4 = Math.pow(z, exponents[l]);

                        currentEstimate = max1 * max2 * max3 * max4;

                        if (Math.abs(mu - currentEstimate) < Math
                                .abs(mu - bestEstimate)) {
                            bestEstimate = currentEstimate;
                            a = exponents[i];
                            b = exponents[j];
                            c = exponents[k];
                            d = exponents[l];

                        }
                        l++;
                    }
                    k++;
                }
                j++;
            }
            i++;
        }

        final double relativeError = (bestEstimate - mu) / mu;

        out.println("a = " + a + ", b = " + b + ", c = " + c + ", d = " + d);
        out.println("Best approximation = " + bestEstimate);
        out.print("Relative error = ");
        out.print(relativeError, 2, false);

        out.close();
        in.close();
    }

}
