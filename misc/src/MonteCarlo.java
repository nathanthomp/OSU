import components.random.Random;
import components.random.Random1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Blank program (clear of Checkstyle and FindBugs warnings).
 *
 * @author N. Thompson
 */
public final class MonteCarlo {

    /**
     * Default constructor--private to prevent instantiation.
     */
    private MonteCarlo() {
        // no code needed here
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
            double x = rnd.nextDouble();
            ptsInInterval++;
            if (x < 0.5) {
                ptsInSubinterval++;
            }
        }

        double estimate = (100.0 * ptsInSubinterval) / ptsInInterval;
        output.println("Estimate of percentage: " + estimate + "%");
        output.println("points in subinterval: " + ptsInSubinterval);
        output.println("points in Interval: " + ptsInInterval);

        input.close();
        output.close();
    }

}
