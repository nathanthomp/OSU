package software2;

import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Blank program (clear of Checkstyle and FindBugs warnings).
 *
 * @author N. Thompson
 */
public final class IO1 {

    /**
     * Default constructor--private to prevent instantiation.
     */
    private IO1() {
        // no code needed here
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments; unused here
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L(args[0]);
        SimpleWriter out = new SimpleWriter1L(args[1]);
        while (!in.atEOS()) {
            String line = in.nextLine();
            out.println(line);
        }
        in.close();
        out.close();
    }

}
