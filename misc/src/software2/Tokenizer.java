package software2;

import components.queue.Queue;
import components.queue.Queue1L;
import components.simplereader.SimpleReader;

/**
 * Blank program (clear of Checkstyle and FindBugs warnings).
 *
 * @author N. Thompson
 */
public final class Tokenizer {

    /**
     * Default constructor--private to prevent instantiation.
     */
    private Tokenizer() {
        // no code needed here
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments; unused here
     */
    public static void main(String[] args) {

    }

    /**
     * Tokenizes the entire input getting rid of all whitespace separators and
     * returning the non-separator tokens in a {@code Queue<String>}.
     *
     * @param in
     *            the input stream
     * @return the queue of tokens
     * @updates in.content
     * @requires in.is_open
     * @ensures <pre>
     * tokens =
     *   [the non-whitespace tokens in #in.content] * <END_OF_INPUT>  and
     * in.content = <>
     * </pre>
     */
    public static Queue<String> tokens(SimpleReader in) {
        Queue<String> result = new Queue1L<>();

        String line;
        String token;
        int index = 0;
        while (!in.atEOS()) {
            line = in.nextLine();
            while (index < line.length()) {
                token = nextWordOrSeparator(line, index);
                index += token.length();
                if (!token.contains(SEPARATORS)) {
                    result.enqueue(token);
                }
            }
        }

        result.enqueue(END_OF_INPUT);
        return result;
    }

}
