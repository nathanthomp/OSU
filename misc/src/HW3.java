import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Blank program (clear of Checkstyle and FindBugs warnings).
 *
 * @author N. Thompson
 */
public final class HW3 {

    /**
     * Default constructor--private to prevent instantiation.
     */
    private HW3() {
        // no code needed here
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments; unused here
     */
    public static void main(String[] args) {
        SimpleWriter out = new SimpleWriter1L();

//        q1a(out);
//        q1b(out);
//        q1c(out);
//        q3(out);
//        q4(out);
        q5(out);
    }

    /**
     *
     *
     * @param out
     */
    public static void q5(SimpleWriter out) {
        int areaBound = 0, sum = 0, count = 0;

        while (areaBound > sum) {
            sum = (int) (sum + Math.pow(2, count));
            count++;
        }
        out.println(sum);
    }

    /**
     * Estimate pi using Gregory-Leibniz series.
     *
     * @param out
     */
    public static void q4(SimpleWriter out) {
        double pi = 0.0;
        int n = 10000;

        while (n >= 0) {
            pi = pi + 4 * (Math.pow(-1, n) / (2 * n + 1));
            n--;
        }

        out.println(pi);
    }

    /**
     * Print all squares less than n.
     *
     * @param out
     */
    public static void q1a(SimpleWriter out) {
        int n = 100, count = 0, square = 0;

        while (square < n) {
            square = count * count;
            count++;
            out.print(square + " ");
        }
    }

    /**
     * Print all numbers divisible by 10, less than n.
     *
     * @param out
     */
    public static void q1b(SimpleWriter out) {
        int n = 100, count = 0;

        while (count < n) {
            count++;
            if (count % 10 == 0) {
                out.print(count + " ");
            }
        }
    }

    /**
     * Print all powers of 2 less than n.
     *
     * @param out
     */
    public static void q1c(SimpleWriter out) {
        int n = 100, power = 0, i = 0;

        while (power < n) {
            power = (int) Math.pow(2, i);
            i++;
            out.print(power + " ");
        }

    }

    /**
     * Turn for loop into a while loop.
     *
     * @param out
     */
    public static void q3(SimpleWriter out) {
//        int s = 0;
//        for (int i = 1; i <= 10; i++) {
//            s = s + i;
//        }

        int s = 0, i = 1;
        while (i <= 10) {
            s = s + 1;
            i++;
        }
        out.println(s);

    }

}
