package software2;

import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Blank program (clear of Checkstyle and FindBugs warnings).
 *
 * @author N. Thompson
 */
public final class Hashing2 {

    /**
     * Default constructor--private to prevent instantiation.
     */
    private Hashing2() {
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

        out.println(hashCode2("123-4567"));

        out.close();
    }

    public static int hashCode2(String str) {
        int result = 0;
        for (int i = 0; i < 8; i++) {
            result += Character.digit(str.charAt(i), 10);
        }
        return result;
    }

}
