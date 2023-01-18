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
public final class Newton4 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private Newton4() {
    }

    /**
     * Computes estimate of square root of x to within relative error 0.01%.
     *
     * @param x
     *            positive number to compute square root of
     * @param epsilon
     *            value of epsilon
     * @return estimate of square root
     */
    private static double sqrt(double x, double epsilon) {
        double estimate = x;

        while (((estimate * estimate) - x) / x > (epsilon * epsilon)) {
            estimate = (estimate + x / estimate) / 2.0;
        }

        return estimate;
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

        out.print("Enter a number for epsilon: ");
        double epsilon = in.nextDouble();

        out.print("Enter a number to compute the square root: ");
        double x = in.nextDouble();

        while (x > 0) {
            out.println("The square root of " + x + " is: " + sqrt(x, epsilon));
            out.println("Enter another number: ");
            x = in.nextDouble();
        }

        out.println("Have a nice day!");

        in.close();
        out.close();
    }

}
