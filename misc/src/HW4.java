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
public final class HW4 {

    /**
     * Default constructor--private to prevent instantiation.
     */
    private HW4() {
        // no code needed here
    }

    /**
     * Generates n pseudo-random points in the [0.0,2.0) x [0.0,2.0) square and
     * returns the number that fall in the circle of radius 1.0 centered at the
     * point (1.0, 1.0).
     *
     * @param n
     *            the number of points to generate
     * @return the number of points that fall in the circle
     */
    private static int numberOfPointsInCircle(int n) {
        SimpleWriter out = new SimpleWriter1L();

        int result = 0;
        int numberOfPoints = n;

        Random rnd = new Random1L();

        while (numberOfPoints > 0) {

            double xCoord = 2 * rnd.nextDouble();
            double yCoord = 2 * rnd.nextDouble();

            if (pointIsInCircle(xCoord, yCoord)) {
                result++;
            }

            numberOfPoints--;
        }

        double estimate = 4.0 * (1.0 * result / n);

        out.println("Estimate of area is: " + estimate);

        return result;
    }

    /**
     * Checks whether the given point (xCoord, yCoord) is inside the circle of
     * radius 1.0 centered at the point (1.0, 1.0).
     *
     * @param xCoord
     *            the x coordinate of the point
     * @param yCoord
     *            the y coordinate of the point
     * @return true if the point is inside the circle, false otherwise
     */
    private static boolean pointIsInCircle(double xCoord, double yCoord) {
        boolean result = false;

        double distance = Math.sqrt((xCoord - 1.0) * (xCoord - 1.0)
                + (yCoord - 1.0) * (yCoord - 1.0));

        if (distance < 1) {
            result = true;
        }

        return result;

    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments; unused here
     */
    public static void main(String[] args) {
        SimpleWriter out = new SimpleWriter1L();
        SimpleReader in = new SimpleReader1L();

        out.print("Enter number of points: ");
        int n = in.nextInteger();

        numberOfPointsInCircle(n);

        out.close();
        in.close();
    }

}
