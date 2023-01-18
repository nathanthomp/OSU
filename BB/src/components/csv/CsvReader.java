package components.csv;

import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Simple HelloWorld program (clear of Checkstyle and FindBugs warnings).
 *
 * @author Nathan Thompson
 */
public final class CsvReader {

    /**
     * Default constructor--private to prevent instantiation.
     */
    private CsvReader() {
        // no code needed here
    }

    /**
     *
     *
     * @param csv
     * @param out
     * @param column
     */
    public static void printAllColumn(SimpleReader csv, SimpleWriter out,
            int column) {
        while (!csv.atEOS()) {
            String[] lineArray = csv.nextLine().split(",");
            out.println(lineArray[column]);
        }
    }

    /**
     *
     *
     * @param csv
     * @param out
     */
    public static void printAll(SimpleReader csv, SimpleWriter out) {
        while (!csv.atEOS()) {
            out.println(csv.nextLine());
        }
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments; unused here
     */
    public static void main(String[] args) {
        SimpleWriter out = new SimpleWriter1L();

        String fileName = "data/test.csv";
        SimpleReader csv = new SimpleReader1L(fileName);

        /*
         * Split line into array.
         */
//        String line = csv.nextLine();
//        String[] lineArray = line.split(",");
//        for (int i = 0; i < lineArray.length; i++) {
//            out.println(lineArray[i]);
//        }
//        printAll(csv, out);
        out.println("----------");
        printAllColumn(csv, out, 0);

        out.close();
        csv.close();
    }

}
