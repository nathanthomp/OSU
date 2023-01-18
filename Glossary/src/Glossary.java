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
 * Glossary program.
 *
 * @author Nathan Thompson
 */
public final class Glossary {

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
    private Glossary() {
    }

    /**
     * Inputs a "glossary" of terms and definitions from the given file and
     * stores them in the given {@code Map}.
     *
     * @param fileName
     *            the name of the input file
     * @param termMap
     *            the term -> definition map
     * @replaces termMap
     * @requires <pre>
     * [file named fileName exists but is not open, and has the
     *  format of one "term" (unique in the file) and one definition,
     *  with term and definition separated by a line break; the definition may
     *  spread over multiple lines. Each term and definition must be separated
     *  from another term and definition by a blank line]
     * </pre>
     * @ensures [termMap contains term -> definition mapping from file fileName]
     */
    private static void getTermMap(String fileName,
            Map<String, String> termMap) {
        assert fileName != null : "Violation of: fileName is not null";
        assert termMap != null : "Violation of: termMap is not null";
        SimpleReader in = new SimpleReader1L(fileName);
        while (!in.atEOS()) {
            String line = in.nextLine();
            String term = line;
            String definition = "";
            while (!line.equals("")) {
                line = in.nextLine();
                definition = definition.concat(line);
            }
            if (!termMap.hasKey(term)) {
                termMap.add(term, definition);
            }
        }
        in.close();
    }

    /**
     * Removes and returns the minimum value from {@code termQueue} according to
     * the ordering provided by the {@code compare} method from {@code order}.
     *
     * @param termQueue
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
    public static String removeMin(Queue<String> termQueue,
            Comparator<String> order) {
        assert termQueue != null : "Violation of: termQueue is not null";
        assert order != null : "Violation of: order is not null";
        String first = termQueue.dequeue();
        String min = first;
        for (String s : termQueue) {
            if (order.compare(s, min) < 0) {
                min = s;
            }
        }
        termQueue.enqueue(first);
        String str;
        String result = "";
        int count = 0;
        while (count < termQueue.length()) {
            str = termQueue.dequeue();
            if (!(order.compare(str, min) == 0)) {
                termQueue.enqueue(str);
            } else {
                result = str;
            }
            count++;
        }
        return result;
    }

    /**
     * Sorts {@code termQueue} according to the ordering provided by the
     * {@code compare} method from {@code order}.
     *
     * @param termQueue
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
    private static void sort(Queue<String> termQueue,
            Comparator<String> order) {
        assert termQueue != null : "Violation of: termQueue is not null";
        assert order != null : "Violation of: order is not null";
        Queue<String> temp = new Queue1L<String>();
        while (termQueue.length() > 0) {
            temp.enqueue(removeMin(termQueue, order));
        }
        termQueue.transferFrom(temp);
    }

    /**
     * Inputs the terms (in alphabetic order) from the keys of {@code termMap}
     * into the given {@code termQueue}.
     *
     * @param termMap
     *            the term -> definition map
     * @param termQueue
     *            the queue of terms
     * @replaces termQueue
     * @requires [all terms are in termMap]
     * @ensures [termQueue has all terms in termMap in alphabetic order]
     */
    private static void getTermQueue(Map<String, String> termMap,
            Queue<String> termQueue) {
        assert termMap != null : "Violation of: termMap is not null";
        assert termQueue != null : "Violation of: termQueue is not null";
        for (Map.Pair<String, String> pair : termMap) {
            termQueue.enqueue(pair.key());
        }
        Comparator<String> lt = new StringLT();
        sort(termQueue, lt);
    }

    /**
     * Outputs a html page with all the terms in alphabetic order.
     *
     * @param termQueue
     *            the queue of terms
     * @ensures [valid html page with all terms in alphabetical order]
     */
    private static void outputIndexFile(Queue<String> termQueue) {
        assert termQueue != null : "Violation of: termQueue is not null";
        SimpleWriter index = new SimpleWriter1L("doc/index.html");
        int count = termQueue.length();
        index.println("<html><head><title>Glossary</title></head><body>");
        index.println("<h2>Glossary: " + count + " terms</h2>");
        index.println("<hr>");
        index.println("<h3>Index</h3>");
        index.println("<ul>");
        for (String s : termQueue) {
            index.println("<li><a href=\"" + s + ".html\">" + s + "</a></li>");
        }
        index.println("</ul>");
        index.println("</body></html>");
        index.close();
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
     * Outputs an html page with the term and definition.
     *
     * @param pair
     *            the {@code Key} and {@code Value} in the {@code termMap}
     * @param termMap
     *            the term -> definition map
     * @requires TODO
     * @ensures TODO
     *
     */
    private static void outputTermFile(Map.Pair<String, String> pair,
            Map<String, String> termMap) {
        assert pair != null : "Violation of: pair is not null";
        assert termMap != null : "Violation of: termMap is not null";
        SimpleWriter term = new SimpleWriter1L("doc/" + pair.key() + ".html");
        term.println(
                "<html><head><title>" + pair.key() + "</title></head><body>");
        term.println("<h2><b><i><font color=\"red\">" + pair.key()
                + "</font></i></b></h2>");
        term.print("<blockquote>");

        final String separatorStr = " \t,. ";
        Set<Character> separatorSet = new Set1L<>();
        generateElements(separatorStr, separatorSet);
        int position = 0;
        while (position < pair.value().length()) {
            String token = nextWordOrSeparator(pair.value(), position,
                    separatorSet);
            if (termMap.hasKey(token)) {
                term.print("<a href=\"" + token + ".html\">" + token + "</a>");
            } else {
                term.print(token);
            }
            position += token.length();
        }
        term.println("</blockquote>");
        term.println("<hr>");
        term.println("<p>Return to <a href=\"index.html\">index</a>.</p>");
        term.println("</body></html>");
        term.close();
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments; unused here
     */
    public static void main(String[] args) {
        SimpleWriter out = new SimpleWriter1L();
        String fileName = "data/terms.txt";
        /*
         * Create termMap and termQueue
         */
        Map<String, String> termMap = new Map1L<>();
        getTermMap(fileName, termMap);
        Queue<String> termQueue = new Queue1L<>();
        getTermQueue(termMap, termQueue);
        /*
         * Create index html page
         */
        outputIndexFile(termQueue);
        /*
         * Create term html page
         */
        for (Map.Pair<String, String> pair : termMap) {
            outputTermFile(pair, termMap);
        }
        out.close();
    }

}
