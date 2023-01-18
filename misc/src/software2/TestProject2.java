package software2;

import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Blank program (clear of Checkstyle and FindBugs warnings).
 *
 * @author N. Thompson
 */
public final class TestProject2 {

    /**
     * Default constructor--private to prevent instantiation.
     */
    private TestProject2() {
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
        NaturalNumber n = new NaturalNumber1L();
        out.println(n.toString());

        out.close();
    }

}
