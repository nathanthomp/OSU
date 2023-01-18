import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Component that aids in the generation of a web site with a Tag Cloud given an
 * input file and number of most frequent words to display.
 *
 * @author Luke Serraglio
 * @author Nathan Thompson
 */
public class TagCloudGenerator {

    /**
     * Compare {@code Pair<String,Integer>}s by integer values in decreasing
     * order.
     */
    private static class IntegerPairComparator
            implements Comparator<Map.Entry<String, Integer>> {
        @Override
        public int compare(Entry<String, Integer> o1,
                Entry<String, Integer> o2) {

            int ans;

            if (o1.getValue() < o2.getValue()) {
                //return a positive number if value1 is less than value2
                ans = 1;
            } else if (o1.getValue() > o2.getValue()) {
                //return a negative number if value1 is greater than value2
                ans = -1;
            } else {
                //return 0 if value1 is equal to value2
                ans = 0;
            }

            return ans;
        }
    }

    /**
     * The map of word => wordCount.
     */
    private SortedMap<String, Integer> wordMap;

    /**
     * Input file stream.
     */
    private BufferedReader fileIn;

    /**
     * Output file stream.
     */
    private PrintWriter fileOut;

    /**
     * Creates the new representation of $this.
     *
     * @param fileIn
     *            the input file stream
     * @param fileOut
     *            the output file stream
     */
    private void createNewRep(BufferedReader fileIn, PrintWriter fileOut) {
        this.wordMap = new TreeMap<String, Integer>();
        this.fileIn = fileIn;
        this.fileOut = fileOut;
    }

    /**
     * Constructor.
     *
     * @param fileIn
     *            the input file stream
     * @param fileOut
     *            the output file stream
     */
    public TagCloudGenerator(BufferedReader fileIn, PrintWriter fileOut) {
        this.createNewRep(fileIn, fileOut);
    }

    /**
     * Creates a mapping of word => word count.
     *
     * @param numOfWords
     *            number of words to account for
     * @throws IOException
     *             exception thrown by $this.fileIn
     */
    public void createCloud(int numOfWords) throws IOException {
        /*
         * Create separators.
         */
        String separatorString = " \t\n\r-,.?;:\"!(){}[]/";
        Set<Character> separators = new HashSet<Character>();
        for (int i = 0; i < separatorString.length(); i++) {
            separators.add(separatorString.charAt(i));
        }
        /*
         * Place all words into the map with their corresponding count. If word
         * is already in map, increment its count. Else add the new word.
         */
        int currentIndex = 0;
        String line = this.fileIn.readLine();
        while (line != null) {
            for (int i = 0; i < line.length(); i++) {

                if (separators.contains(line.charAt(i))) {
                    String foundWord = line.substring(currentIndex, i)
                            .toLowerCase();
                    if (this.wordMap.containsKey(foundWord)) {
                        int newCount = this.wordMap.remove(foundWord) + 1;
                        this.wordMap.put(foundWord, newCount);
                    } else {
                        this.wordMap.put(foundWord, 1);
                    }
                    currentIndex = i + 1;
                }
            }
            currentIndex = 0;
            line = this.fileIn.readLine();
        }
        /*
         * Reduce map to the greatest count in numOfWords entries.
         */
        List<Map.Entry<String, Integer>> entryList = new LinkedList<>();
        for (Map.Entry<String, Integer> word : this.wordMap.entrySet()) {
            entryList.add(word);
        }
        entryList.sort(new IntegerPairComparator());
        this.wordMap.clear();
        for (int i = 0; i <= numOfWords; i++) {
            this.wordMap.put(entryList.remove(i).getKey(),
                    entryList.remove(i).getValue());
        }
    }

    /**
     * Exports the cloud tag to a html file.
     *
     * @param numOfWords
     * @param inputFileName
     *            name of the input file
     * @throws IOException
     *             exception thrown by $this.fileOut
     */
    public void export(int numOfWords, String inputFileName)
            throws IOException {
        /*
         * Print header to output stream.
         */
        String title = "Top " + numOfWords + " words in " + inputFileName;
        this.fileOut.println("<html>");
        this.fileOut.println("<head>");
        this.fileOut.println("<title>");
        this.fileOut.println(title);
        this.fileOut.println("</title>");
        this.fileOut.println(
                "<link href=\"http://web.cse.ohio-state.edu/software/2231/web-sw2"
                        + "/assignments/projects/tag-cloud-generator/data"
                        + "/tagcloud.css\" rel=\"stylesheet\" type=\"text/css\">");
        this.fileOut.println(
                "<link href=\"tagcloud.css\" rel=\"stylesheet\" type=\"text/css\">");
        this.fileOut.println("</head>");
        this.fileOut.println("<body>");
        this.fileOut.println("<h2>" + title + "</h2>");
        this.fileOut.println("<hr>");
        /*
         * Print cloud to output stream.
         */
        this.fileOut.println("<div class=\"cdiv\">");
        this.fileOut.println("<p class=\"cbox\">");

        final int minFontSize = 11, maxFontSize = 48;

        Set<Entry<String, Integer>> words = this.wordMap.entrySet();
        for (Entry<String, Integer> word : words) {

            int minCount = this.wordMap.get(this.wordMap.lastKey());
            int maxCount = this.wordMap.get(this.wordMap.firstKey());
            double normalizedSize = (word.getValue() - minCount)
                    / (double) (maxCount - minCount);
            int fontSize = (int) ((maxFontSize - minFontSize) * normalizedSize
                    + minFontSize);

            this.fileOut.print("<span style=\"cursor:default\" class=\"f"
                    + fontSize + "\" title=\"count: " + word.getValue() + "\">"
                    + word.getKey() + "</span>\n");

        }

        this.fileOut.println("</p>");
        this.fileOut.println("</div>");
        /*
         * Print footer to output stream.
         */
        this.fileOut.println("</body>");
        this.fileOut.println("</html>");
    }
}
