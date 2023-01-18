import components.random.Random;
import components.random.Random1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Monte Carlo Estimate: compute percentage of pseudo-random points in [0.0,1.0)
 * interval that fall in the left half subinterval [0.0,0.5).
 */
public final class MonteCarlo {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private MonteCarlo() {
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments; unused here
     */
    public static void main(String[] args) {

        SimpleReader input = new SimpleReader1L();
        SimpleWriter output = new SimpleWriter1L();

        output.print("Number of points: ");
        int n = input.nextInteger();

        int ptsInInterval = 0, ptsInSubinterval = 0;

        Random rnd = new Random1L();

        while (ptsInInterval < n) {

            double x = 2 * rnd.nextDouble();
            double y = 2 * rnd.nextDouble();

            ptsInInterval++;

            double distance = Math.sqrt((x - 1.0) * (x - 1.0))
                    + ((y - 1.0) * (y - 1.0));

            if (distance < 1) {
                ptsInSubinterval++;
            }
        }
        /*
         * Area needs calculated
         */
        double area = 4.0 * (1.0 * ptsInSubinterval / ptsInInterval);
        output.println("The estimated area is: " + area);
        /*
         * Close input and output streams
         */
        input.close();
        output.close();
    }

}
