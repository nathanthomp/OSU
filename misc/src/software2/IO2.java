package software2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Blank program (clear of Checkstyle and FindBugs warnings).
 *
 * @author N. Thompson
 */
public final class IO2 {

    /**
     * Default constructor--private to prevent instantiation.
     */
    private IO2() {
        // no code needed here
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments; unused here
     */
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader(args[0]));
        PrintWriter out = new PrintWriter(
                new BufferedWriter(new FileWriter(args[1])));
        String line = in.readLine();
        while (line != null) {
            line = in.readLine();
            out.println(line);
        }
        in.close();
        out.close();
    }

}
