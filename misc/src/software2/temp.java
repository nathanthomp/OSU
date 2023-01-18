package software2;

import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Blank program (clear of Checkstyle and FindBugs warnings).
 *
 * @author N. Thompson
 */
public final class temp {

    /**
     * Default constructor--private to prevent instantiation.
     */
    private temp() {
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
        StringBuilder sb = new StringBuilder();
        String s = "abc123abc";

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                sb.append('-');
            } else {
                sb.append(c);
            }
        }

        out.println(sb);
        out.close();
    }

}
