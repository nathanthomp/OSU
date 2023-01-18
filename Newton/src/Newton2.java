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
public final class Newton2 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private Newton2() {
    }

    /**
     * Computes estimate of square root of x to within relative error 0.01%.
     *
     * @param x
     *            positive number to compute square root of
     * @return estimate of square root
     */
    private static double sqrt(double x) {
        double estimate = x;
        final double epsilon = 0.0001;

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

        out.print("Enter a number to compute the square root: ");
        double x = in.nextDouble();

        String response = "y";

        while (response.equals("y")) {
            out.println("The square root of " + x + " is: " + sqrt(x));
            out.println("Would you like to compute another square root? (y/n)");
            response = in.nextLine();
            if (response.equals("y")) {
                out.println("Enter a new number to compute the square root: ");
                x = in.nextDouble();
            }

        }

        out.println("Have a nice day!");

        in.close();
        out.close();
    }

}
