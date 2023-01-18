import components.set.Set;
import components.set.Set1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Blank program (clear of Checkstyle and FindBugs warnings).
 *
 * @author N. Thompson
 */
public final class HW19 {

    /**
     * Default constructor--private to prevent instantiation.
     */
    private HW19() {
        // no code needed here
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

        String token = "";
        int index = position;

        /*
         * Determine if the substring of text starting at position is going to
         * be a "word" or "separator string"
         */
        boolean isWord;
        if (!separators.contains(text.charAt(index))) {
            isWord = true;
        } else {
            isWord = false;
        }

        /*
         * If we are extracting a word: nextWordOrSeparator must be a string of
         * characters such that no characters are in the character set of
         * separators.
         *
         *
         * If we are extracting a separator string: must be a string of
         * characters such that all characters are in the character set of
         * separators.
         */
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
     * Main method.
     *
     * @param args
     *            the command line arguments; unused here
     */
    public static void main(String[] args) {
        SimpleWriter out = new SimpleWriter1L();
        final String separatorStr = " \t, ";
        Set<Character> separatorSet = new Set1L<>();
        generateElements(separatorStr, separatorSet);
        String testStr = "this is a test, string";
        int position = 0;
        while (position < testStr.length()) {
            out.println(position);
            String token = nextWordOrSeparator(testStr, position, separatorSet);
            if (separatorSet.contains(token.charAt(0))) {
                out.print("  Separator: <");
            } else {
                out.print("  Word: <");
            }
            out.println(token + ">");
            position += token.length();
        }
        out.close();
    }

}
