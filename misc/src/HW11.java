import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Blank program (clear of Checkstyle and FindBugs warnings).
 *
 * @author N. Thompson
 */
public final class HW11 {

    /**
     * Default constructor--private to prevent instantiation.
     */
    private HW11() {
        // no code needed here
    }

    /**
     * Performs interval halving algorithm.
     *
     * @param out
     * @param in
     */
    private static void intervalHalvingAlgorithm(SimpleWriter out,
            SimpleReader in) {
        out.println("Enter a secret number: ");
        int secretNumber = in.nextInteger();

        int lowEnough = 0, guessNumber = 0;
        int tooHigh = 1001, numberOfGuesses = 0;

        while (guessNumber != secretNumber) {
            guessNumber = (lowEnough + tooHigh) / 2;
            if (secretNumber < guessNumber) {
                tooHigh = guessNumber;
            } else {
                lowEnough = guessNumber;
            }
            numberOfGuesses++;
        }
        out.println(numberOfGuesses);

        out.println("guess: " + guessNumber);
    }

    /**
     * Returns {@code n} to the power {@code p}.
     *
     * @param n
     *            the number to which we want to apply the power
     * @param p
     *            the power
     * @return the number to the power
     * @requires Integer.MIN_VALUE <= n ^ (p) <= Integer.MAX_VALUE and p >= 0
     * @ensures power = n ^ (p)
     */
    private static int power(int n, int p) {
        int result = n;

        for (int i = 1; i < p; i++) {
            result = result * n;
        }

        return result;
    }

    /**
     * Returns the {@code r}-th root of {@code n}.
     *
     * @param n
     *            the number to which we want to apply the root
     * @param r
     *            the root
     * @return the root of the number
     * @requires n >= 0 and r > 0
     * @ensures root ^ (r) <= n < (root + 1) ^ (r)
     */
    private static int root(int n, int r) {
        int root = 0;
        int lowEnough = 0, tooHigh = n;

        while (!(power(root, r) <= n) && !(power(root + 1, r) > n)) {
            if (power(root, r) > n) {
                tooHigh = root;
            } else {
                lowEnough = root;
            }
        }
        return root;
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
        /*
         * Methods: intervalHalvingAlgorithm(out,in); out.println(power(2, 3));
         */
        out.println(root(16, 2));

//        int n = 82, r = 3, root = 4;

//        double lowEnough = Math.pow(root, r), tooHigh = Math.pow(root + 1, r);
//        while (guessNumber != secretNumber) {
//            guessNumber = (lowEnough + tooHigh) / 2;
//            if (secretNumber < guessNumber) {
//                tooHigh = guessNumber;
//            } else {
//                lowEnough = guessNumber;
//            }
//        }
//
//        out.println("root:" + root);

    }

}
