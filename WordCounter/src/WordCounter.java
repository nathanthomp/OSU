import java.io.File;
import java.util.Comparator;

import components.map.Map;
import components.map.Map1L;
import components.queue.Queue;
import components.queue.Queue1L;
import components.set.Set;
import components.set.Set1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Program to count word occurrences in a text file.
 *
 * @author N. Thompson
 */
public final class WordCounter {

    /**
     * Compare {@code String}s in lexicographic order.
     */
    private static class StringLT implements Comparator<String> {
        @Override
        public int compare(String o1, String o2) {
            return o1.compareTo(o2);
        }
    }

    /**
     * Default constructor--private to prevent instantiation.
     */
    private WordCounter() {
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments; unused here
     */
    public static void main(String[] args) {

        // Get file name from user
        SimpleWriter out = new SimpleWriter1L();
        SimpleReader in = new SimpleReader1L();

        String fileName = "";
        File file = new File(fileName);

        while (!file.exists()) {
            out.println("Please enter the text file path (including .txt): ");
            fileName = in.nextLine();
            file = new File(fileName);
        }

        // Create map (word => wordCount)
        Map<String, Integer> map = createWordMap(fileName);

        // Create sorted list of words
        Queue<String> queue = createWordQueue(map);

        // Create HTML page
        createHtmlPage(map, queue, fileName);

        in.close();
        out.close();
    }

    /**
     * Reads in text file with name of fileName and creates a {@code Map} with
     * each instance of a word and its count of other occurrences.
     *
     * @param fileName
     *            the name of the file
     * @return a map of (word => wordCount)
     */
    private static Map<String, Integer> createWordMap(String fileName) {

        // Create map, reader, and separator set
        Map<String, Integer> result = new Map1L<>();
        SimpleReader in = new SimpleReader1L(fileName);
        final String separatorStr = " \t,. -";
        Set<Character> separatorSet = new Set1L<>();
        generateElements(separatorStr, separatorSet);

        // While there is still text to read
        while (!in.atEOS()) {

            // Get the line from the input
            String line = in.nextLine();
            int position = 0;

            // While there is still words to read
            while (position < line.length()) {

                // Get the next word
                String token = nextWordOrSeparator(line, position,
                        separatorSet);

                // If word is in map:
                //      find the value of the word in the map, and increment it
                // otherwise:
                //      add the word to map with value of 1
                if (result.hasKey(token)) {
                    int currentCount = result.value(token);
                    result.remove(token);
                    result.add(token, currentCount + 1);
                } else {
                    // if token is not in separator set
                    boolean isInSeparatorSet = false;
                    char[] tokenCharArray = token.toCharArray();
                    for (Character c : tokenCharArray) {
                        if (separatorSet.contains(c)) {
                            isInSeparatorSet = true;
                        }
                    }
                    if (!isInSeparatorSet) {
                        result.add(token, 1);
                    }
                }
                position += token.length();
            }
        }

        in.close();
        return result;
    }

    /**
     * Returns the first "word" (maximal length string of characters not in
     * {@code separators}) or "separator string" (maximal length string of
     * characters in {@code separators}) in the given {@code text} starting at
     * the given {@code position}.
     *
     * @param text
     *            the {@code String} from which to get the word or separator
     *            string
     * @param position
     *            the starting index
     * @param separators
     *            the {@code Set} of separator characters
     * @return the first word or separator string found in {@code text} starting
     *         at index {@code position}
     * @requires 0 <= position < |text|
     * @ensures <pre>
     * nextWordOrSeparator =
     *   text[position, position + |nextWordOrSeparator|)  and
     * if entries(text[position, position + 1)) intersection separators = {}
     * then
     *   entries(nextWordOrSeparator) intersection separators = {}  and
     *   (position + |nextWordOrSeparator| = |text|  or
     *    entries(text[position, position + |nextWordOrSeparator| + 1))
     *      intersection separators /= {})
     * else
     *   entries(nextWordOrSeparator) is subset of separators  and
     *   (position + |nextWordOrSeparator| = |text|  or
     *    entries(text[position, position + |nextWordOrSeparator| + 1))
     *      is not subset of separators)
     * </pre>
     */
    private static String nextWordOrSeparator(String text, int position,
            Set<Character> separators) {
        assert text != null : "Violation of: text is not null";
        assert separators != null : "Violation of: separators is not null";
        assert 0 <= position : "Violation of: 0 <= position";
        assert position < text.length() : "Violation of: position < |text|";
        String token = "";
        int index = position;
        boolean isWord;
        if (!separators.contains(text.charAt(index))) {
            isWord = true;
        } else {
            isWord = false;
        }
        if (isWord) {
            while (index < text.length()
                    && !separators.contains(text.charAt(index))) {
                token += text.charAt(index);
                index++;
            }
        } else {
            while (index < text.length()
                    && separators.contains(text.charAt(index))) {
                token += text.charAt(index);
                index++;
            }
        }
        return token;
    }

    /**
     * Generates the set of characters in the given {@code String} into the
     * given {@code Set}.
     *
     * @param str
     *            the given {@code String}
     * @param charSet
     *            the {@code Set} to be replaced
     * @replaces charSet
     * @ensures charSet = entries(str)
     */
    private static void generateElements(String str, Set<Character> charSet) {
        assert str != null : "Violation of: str is not null";
        assert charSet != null : "Violation of: charSet is not null";

        for (int i = 0; i < str.length(); i++) {
            char character = str.charAt(i);
            if (!charSet.contains(character)) {
                charSet.add(character);
            }
        }
    }

    /**
     * Adds the words (in alphabetic order) from the keys of {@code Map} into
     * the given {@code Queue}.
     *
     * @param wordMap
     *            the (word => wordCount) map
     * @return a queue of words in alphabetical order
     * @requires [all words are in wordMap]
     */
    private static Queue<String> createWordQueue(Map<String, Integer> wordMap) {
        assert wordMap != null : "Violation of: termMap is not null";

        Queue<String> result = new Queue1L<>();
        for (Map.Pair<String, Integer> pair : wordMap) {
            result.enqueue(pair.key());
        }
        Comparator<String> lt = new StringLT();
        sort(result, lt);

        return result;
    }

    /**
     * Sorts {@code termQueue} according to the ordering provided by the
     * {@code compare} method from {@code order}.
     *
     * @param wordQueue
     *            the queue of terms
     * @param order
     *            ordering by which to sort
     * @updates termQueue
     * @requires [the relation computed by order.compare is a total preorder]
     * @ensures <pre>
     *  termQueue = [#termQueue ordered by the relation computed by
     *  order.compare]
     * </pre>
     */
    private static void sort(Queue<String> wordQueue,
            Comparator<String> order) {
        assert wordQueue != null : "Violation of: termQueue is not null";
        assert order != null : "Violation of: order is not null";

        Queue<String> temp = new Queue1L<String>();
        while (wordQueue.length() > 0) {
            temp.enqueue(removeMin(wordQueue, order));
        }
        wordQueue.transferFrom(temp);
    }

    /**
     * Removes and returns the minimum value from {@code termQueue} according to
     * the ordering provided by the {@code compare} method from {@code order}.
     *
     * @param wordQueue
     *            the queue
     * @param order
     *            ordering by which to compare entries
     * @return the minimum value from {@code termQueue}
     * @updates termQueue
     * @requires <pre>
     * termQueue /= empty_string  and
     *  [the relation computed by order.compare is a total preorder]
     * </pre>
     * @ensures <pre>
     * (termQueue * <removeMin>) is permutation of #termQueue  and
     *  for all x: string of character
     *      where (x is in entries (termQueue))
     *    ([relation computed by order.compare method](removeMin, x))
     * </pre>
     */
    public static String removeMin(Queue<String> wordQueue,
            Comparator<String> order) {
        assert wordQueue != null : "Violation of: termQueue is not null";
        assert order != null : "Violation of: order is not null";
        String first = wordQueue.dequeue();
        String min = first;
        for (String s : wordQueue) {
            if (order.compare(s.toLowerCase(), min) < 0) {
                min = s;
            }
        }
        wordQueue.enqueue(first);
        String str;
        String result = "";
        int count = 0;
        while (count < wordQueue.length()) {
            str = wordQueue.dequeue();
            if (!(order.compare(str, min) == 0)) {
                wordQueue.enqueue(str);
            } else {
                result = str;
            }
            count++;
        }
        return result;
    }

    /**
     * Creates an Html page with a table of words and their word count.
     *
     * @param wordMap
     *            the (word => wordCount) map
     * @param wordQueue
     *            the queue of words in alphabetical order
     * @param fileName
     *            the name of the text file
     */
    private static void createHtmlPage(Map<String, Integer> wordMap,
            Queue<String> wordQueue, String fileName) {
        assert wordMap != null : "Violation of: termMap is not null";
        assert wordQueue != null : "Violation of: termMap is not null";

        SimpleWriter html = new SimpleWriter1L("data\\output.html");

        html.println("<html>");
        html.println("\t<head>");
        html.println("\t\t<title>Words Counted in " + fileName + "</title>");
        html.println("\t</head>");
        html.println("\t<body>");
        html.println("\t\t<h2>Words Counted in " + fileName + "</h2>");
        html.println("\t\t<hr>");
        html.println("\t\t<table border=\"1\">");
        html.println("\t\t\t<tbody>");
        html.println("\t\t\t\t<tr>");
        html.println("\t\t\t\t\t<th>Words</th>");
        html.println("\t\t\t\t\t<th>Counts</th>");
        html.println("\t\t\t\t</tr>");

        for (String word : wordQueue) {
            html.println("\t\t\t\t<tr>");
            html.println("\t\t\t\t\t<td>" + word + "</td>");
            html.println("\t\t\t\t\t<td>" + wordMap.value(word) + "</td>");
            html.println("\t\t\t\t</tr>");
        }

        html.println("\t\t\t</tbody>");
        html.println("\t\t</table>");
        html.println("\t</body>");
        html.println("</html>");

        html.close();
    }

}
