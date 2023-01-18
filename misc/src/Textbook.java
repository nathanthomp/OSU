import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Blank program (clear of Checkstyle and FindBugs warnings).
 *
 * @author N. Thompson
 */
public final class Textbook {

    /**
     * Default constructor--private to prevent instantiation.
     */
    private Textbook() {
        // no code needed here
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments; unused here
     */
    public static void main(String[] args) {
        SimpleWriter output = new SimpleWriter1L();

        for (int i = 0; i < 100; i++) {
            output.println(i);
            int j = i;
            output.println(j);
        }

        output.close();
    }

}
