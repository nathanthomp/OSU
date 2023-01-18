import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Program that generates a web site with a Tag Cloud given an input file and
 * number of most frequent words to display.
 *
 * @author Luke Serraglio
 * @author Nathan Thompson
 *
 */
public final class TagCloudGeneratorMain {

    /**
     * Prevent instantiation.
     */
    private TagCloudGeneratorMain() {
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     * @throws IOException
     *             exception thrown by either BufferedReader or PrintWriter
     */
    public static void main(String[] args) {
        /*
         * Get input file name, output file name, and number of words.
         */
        Scanner keyboardIn = new Scanner(System.in);
        System.out.println("Enter location of input file: ");
        String inputFileName = keyboardIn.nextLine();
        System.out.println("Enter location of output file: ");
        String outputFileName = keyboardIn.nextLine();
        System.out.println("Enter number of words to show in tag cloud: ");
        int numOfWords = Integer.parseInt(keyboardIn.nextLine());
        /*
         * Create file output stream.
         */
        PrintWriter fileOut;
        try {
            fileOut = new PrintWriter(
                    new BufferedWriter(new FileWriter(outputFileName)));
        } catch (IOException e) {
            System.err.println("Error creating output file");
            keyboardIn.close();
            return;
        }
        /*
         * Create file input stream.
         */
        BufferedReader fileIn;
        try {
            fileIn = new BufferedReader(new FileReader(inputFileName));
        } catch (IOException e) {
            System.err.println("Error creating input file");
            fileOut.close();
            keyboardIn.close();
            return;
        }
        /*
         * Create the TagCloudGenerator. Call its public method createCloud and
         * export.
         */
        TagCloudGenerator generator = new TagCloudGenerator(fileIn, fileOut);
        try {
            generator.createCloud(numOfWords);
        } catch (IOException e) {
            System.err.println("Error reading from file: " + inputFileName);
        }
        try {
            generator.export(numOfWords, inputFileName);
        } catch (IOException e) {
            System.err.println("Error writing to file: " + outputFileName);
        }
        /*
         * Close streams.
         */
        keyboardIn.close();
        try {
            fileIn.close();
        } catch (IOException e) {
            System.err.println("Error closing input file");
            fileOut.close();
            return;
        }
        fileOut.close();
    }

}
