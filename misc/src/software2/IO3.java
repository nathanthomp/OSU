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
public final class IO3 {

    /**
     * Default constructor--private to prevent instantiation.
     */
    private IO3() {
        // no code needed here
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments; unused here
     */
    public static void main(String[] args) {
        /*
         * Create file.
         */
        PrintWriter out;
        try {
            out = new PrintWriter(new BufferedWriter(new FileWriter(args[1])));
        } catch (IOException e) {
            System.err.println("Error creating file");
            return;
        }
        /*
         * Open file.
         */
        BufferedReader in;
        try {
            in = new BufferedReader(new FileReader(args[0]));
        } catch (IOException e) {
            System.err.println("Error opening file.");
            out.close();
            return;
        }
        /*
         * Read and write to file.
         */
        try {
            String line = in.readLine();
            while (line != null) {
                out.println(line);
                line = in.readLine();
            }
        } catch (IOException e) {
            System.err.println("Error reading from file");
        }
        /*
         * Close files.
         */
        try {
            in.close();
        } catch (IOException e) {
            System.err.println("Error closing input file");
        }
        out.close();

    }

}
