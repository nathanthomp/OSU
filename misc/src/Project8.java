import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Blank program (clear of Checkstyle and FindBugs warnings).
 *
 * @author N. Thompson
 */
public final class Project8 {

    /**
     * Default constructor--private to prevent instantiation.
     */
    private Project8() {
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
        int result = 0;
        int w = 9, n = 11;

        result = (w * w) % n;
        if (result == 1) {
            out.println("1.composite");
        }

        result = (int) Math.pow(w, n - 1);
        result = result % n;
        if (result != 1) {
            out.println("2.composite");
        }

    }

}
