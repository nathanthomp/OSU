import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

/**
 * Program to copy a text file into another file.
 *
 * @author Put your name here
 *
 */
public final class FilterFileStdJava {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private FilterFileStdJava() {
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments: input-file-name output-file-name
     */
    public static void main(String[] args) {
        String inputFile = args[0];
        String outputFile = args[1];
        String filterFile = args[2];
        /*
         * Create file.
         */
        PrintWriter out;
        try {
            out = new PrintWriter(
                    new BufferedWriter(new FileWriter(outputFile)));
        } catch (IOException e) {
            System.err.println("Error creating file: " + outputFile);
            return;
        }
        /*
         * Open input file.
         */
        BufferedReader in;
        try {
            in = new BufferedReader(new FileReader(inputFile));
        } catch (IOException e) {
            System.err.println("Error opening file: " + inputFile);
            out.close();
            return;
        }
        /*
         * Open filter file
         */
        BufferedReader filter;
        try {
            filter = new BufferedReader(new FileReader(filterFile));
        } catch (IOException e) {
            System.err.println("Error opening file: " + filterFile);
            out.close();
            // Why dis
            return;
        }
        /*
         * Collect all lines in filterFile.
         */
        List<String> filterLines = new LinkedList<String>();
        try {
            String line = in.readLine();
            while (line != null) {
                filterLines.add(line);
                line = in.readLine();
            }
        } catch (IOException e) {
            System.err.println("Error reading from file: " + filterFile);
        }
        /*
         * Read and write to file.
         */
        try {
            String line = in.readLine();
            while (line != null) {
                if (filterLines.contains(line)) {
                    out.println(line);
                }
                line = in.readLine();

            }
        } catch (IOException e) {
            System.err.println("Error reading from file: " + inputFile);
        }
        /*
         * Close files.
         */
        try {
            in.close();
        } catch (IOException e) {
            System.err.println("Error closing file: " + inputFile);
        }
        try {
            filter.close();
        } catch (IOException e) {
            System.err.println("Error closing file: " + filterFile);
        }
        out.close();

    }

}
