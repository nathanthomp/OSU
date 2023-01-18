import components.queue.Queue;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.statement.Statement;
import components.statement.Statement1;
import components.utilities.Reporter;
import components.utilities.Tokenizer;

/**
 * Layered implementation of secondary methods {@code parse} and
 * {@code parseBlock} for {@code Statement}.
 *
 * @author Nathan Thompson
 * @author Luke Serraglio
 *
 */
public final class Statement1Parse1 extends Statement1 {

    /*
     * Private members --------------------------------------------------------
     */

    /**
     * Converts {@code c} into the corresponding {@code Condition}.
     *
     * @param c
     *            the condition to convert
     * @return the {@code Condition} corresponding to {@code c}
     * @requires [c is a condition string]
     * @ensures parseCondition = [Condition corresponding to c]
     */
    private static Condition parseCondition(String c) {
        assert c != null : "Violation of: c is not null";
        assert Tokenizer
                .isCondition(c) : "Violation of: c is a condition string";
        return Condition.valueOf(c.replace('-', '_').toUpperCase());
    }

    /**
     * Parses an IF or IF_ELSE statement from {@code tokens} into {@code s}.
     *
     * @param tokens
     *            the input tokens
     * @param s
     *            the parsed statement
     * @replaces s
     * @updates tokens
     * @requires <pre>
     * [<"IF"> is a prefix of tokens]  and
     *  [<Tokenizer.END_OF_INPUT> is a suffix of tokens]
     * </pre>
     * @ensures <pre>
     * if [an if string is a proper prefix of #tokens] then
     *  s = [IF or IF_ELSE Statement corresponding to if string at start of #tokens]  and
     *  #tokens = [if string at start of #tokens] * tokens
     * else
     *  [reports an appropriate error message to the console and terminates client]
     * </pre>
     */
    private static void parseIf(Queue<String> tokens, Statement s) {
        assert tokens != null : "Violation of: tokens is not null";
        assert s != null : "Violation of: s is not null";
        assert tokens.length() > 0 && tokens.front().equals("IF") : ""
                + "Violation of: <\"IF\"> is proper prefix of tokens";
        /*
         * Dequeue the first token because we know that it is a IF Keyword.
         */
        tokens.dequeue();
        /*
         * Make sure that the first token is a valid Condition, dequeue and
         * parse it.
         */
        Reporter.assertElseFatalError(Tokenizer.isCondition(tokens.front()),
                "The Current token is not a valid Condition");
        Condition c = parseCondition(tokens.dequeue());
        /*
         * Make sure that the first token is a THEN Keyword and dequeue it.
         */
        Reporter.assertElseFatalError(
                Tokenizer.isKeyword(tokens.front())
                        && tokens.front().equals("THEN"),
                "The Current token is not a THEN Keyword");
        tokens.dequeue();
        /*
         * Parse the first BLOCK.
         */
        Statement s1 = new Statement1();
        s1.parseBlock(tokens);
        /*
         * Make sure that the first token is an ELSE or END Keyword.
         */
        Reporter.assertElseFatalError(
                Tokenizer.isKeyword(tokens.front())
                        && (tokens.front().equals("ELSE")
                                || tokens.front().equals("END")),
                "The Current token is not an ELSE or END Keyword");
        /*
         * If the first token is an END Keyword, assemble the IF statement.
         * Else, parse the second BLOCK and assemble the IF_ELSE statement.
         */
        if (tokens.front().equals("END")) {
            /*
             * Dequeue the END Keyword.
             */
            tokens.dequeue();
            /*
             * Make sure the the first token is a IF Keyword and dequeue it.
             */
            Reporter.assertElseFatalError(
                    Tokenizer.isKeyword(tokens.front())
                            && tokens.front().equals("IF"),
                    "The Current token is not a IF Keyword");
            tokens.dequeue();
            /*
             * Assemble IF statement.
             */
            s.assembleIf(c, s1);

        } else {
            /*
             * Dequeue the ELSE Keyword
             */
            tokens.dequeue();
            /*
             * Parse the second BLOCK.
             */
            Statement s2 = new Statement1();
            s2.parseBlock(tokens);
            /*
             * Make sure the the first token is an END Keyword and dequeue it.
             */
            Reporter.assertElseFatalError(
                    Tokenizer.isKeyword(tokens.front())
                            && tokens.front().equals("END"),
                    "The Current token is not an END Keyword");
            tokens.dequeue();
            /*
             * Make sure the the first token is a IF Keyword and dequeue it.
             */
            Reporter.assertElseFatalError(
                    Tokenizer.isKeyword(tokens.front())
                            && tokens.front().equals("IF"),
                    "The Current token is not a IF Keyword");
            tokens.dequeue();
            /*
             * Assemble IF_ELSE statement.
             */
            s.assembleIfElse(c, s1, s2);
        }
    }

    /**
     * Parses a WHILE statement from {@code tokens} into {@code s}.
     *
     * @param tokens
     *            the input tokens
     * @param s
     *            the parsed statement
     * @replaces s
     * @updates tokens
     * @requires <pre>
     * [<"WHILE"> is a prefix of tokens]  and
     *  [<Tokenizer.END_OF_INPUT> is a suffix of tokens]
     * </pre>
     * @ensures <pre>
     * if [a while string is a proper prefix of #tokens] then
     *  s = [WHILE Statement corresponding to while string at start of #tokens]  and
     *  #tokens = [while string at start of #tokens] * tokens
     * else
     *  [reports an appropriate error message to the console and terminates client]
     * </pre>
     */
    private static void parseWhile(Queue<String> tokens, Statement s) {
        assert tokens != null : "Violation of: tokens is not null";
        assert s != null : "Violation of: s is not null";
        assert tokens.length() > 0 && tokens.front().equals("WHILE") : ""
                + "Violation of: <\"WHILE\"> is proper prefix of tokens";
        /*
         * Dequeue the first token because we know that it is a WHILE Keyword.
         */
        tokens.dequeue();
        /*
         * Make sure that the first token is a valid Condition, dequeue and
         * parse it.
         */
        Reporter.assertElseFatalError(Tokenizer.isCondition(tokens.front()),
                "The Current token is not a valid Condition");
        Condition c = parseCondition(tokens.dequeue());
        /*
         * Make sure that the first token is a DO Keyword and dequeue it.
         */
        Reporter.assertElseFatalError(
                Tokenizer.isKeyword(tokens.front())
                        && tokens.front().equals("DO"),
                "The Current token is not a DO Keyword");
        tokens.dequeue();
        /*
         * Parse the BLOCK statement.
         */
        Statement block = new Statement1();
        block.parseBlock(tokens);
        /*
         * Make sure the the first token is an END Keyword and dequeue it.
         */
        Reporter.assertElseFatalError(
                Tokenizer.isKeyword(tokens.front())
                        && tokens.front().equals("END"),
                "The Current token is not an END Keyword");
        tokens.dequeue();
        /*
         * Make sure the the first token is a WHILE Keyword and dequeue it.
         */
        Reporter.assertElseFatalError(
                Tokenizer.isKeyword(tokens.front())
                        && tokens.front().equals("WHILE"),
                "The Current token is not a WHILE Keyword");
        tokens.dequeue();
        /*
         * Assemble WHILE.
         */
        s.assembleWhile(c, block);
    }

    /**
     * Parses a CALL statement from {@code tokens} into {@code s}.
     *
     * @param tokens
     *            the input tokens
     * @param s
     *            the parsed statement
     * @replaces s
     * @updates tokens
     * @requires [identifier string is a proper prefix of tokens]
     * @ensures <pre>
     * s =
     *   [CALL Statement corresponding to identifier string at start of #tokens]  and
     *  #tokens = [identifier string at start of #tokens] * tokens
     * </pre>
     */
    private static void parseCall(Queue<String> tokens, Statement s) {
        assert tokens != null : "Violation of: tokens is not null";
        assert s != null : "Violation of: s is not null";
        assert tokens.length() > 0
                && Tokenizer.isIdentifier(tokens.front()) : ""
                        + "Violation of: identifier string is proper prefix of tokens";
        s.assembleCall(tokens.dequeue());
    }

    /*
     * Constructors -----------------------------------------------------------
     */

    /**
     * No-argument constructor.
     */
    public Statement1Parse1() {
        super();
    }

    /*
     * Public methods ---------------------------------------------------------
     */

    @Override
    public void parse(Queue<String> tokens) {
        assert tokens != null : "Violation of: tokens is not null";
        assert tokens.length() > 0 : ""
                + "Violation of: Tokenizer.END_OF_INPUT is a suffix of tokens";
        this.parseBlock(tokens);
    }

    @Override
    public void parseBlock(Queue<String> tokens) {
        assert tokens != null : "Violation of: tokens is not null";
        assert tokens.length() > 0 : ""
                + "Violation of: Tokenizer.END_OF_INPUT is a suffix of tokens";
        /*
         * Make sure that
         */
        while (!(tokens.front().equals("END") || tokens.front().equals("ELSE")
                || tokens.front().equals(Tokenizer.END_OF_INPUT))) {
            /*
             * Create a new statement
             */
            Statement s = this.newInstance();
            /*
             * Make sure that the first token is either a Keyword or Identifier.
             */
            Reporter.assertElseFatalError(
                    (Tokenizer.isKeyword(tokens.front())
                            || Tokenizer.isIdentifier(tokens.front())),
                    "The Current token is not a valid Condition");
            /*
             * Call the appropriate parse method on the tokens.
             */
            if (tokens.front().equals("WHILE")) {
                parseWhile(tokens, s);
            } else if (tokens.front().equals("IF")) {
                parseIf(tokens, s);
            } else {
                parseCall(tokens, s);
            }
            this.addToBlock(this.lengthOfBlock(), s);
        }
    }

    /*
     * Main test method -------------------------------------------------------
     */

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();
        /*
         * Get input file name
         */
        out.print("Enter valid BL statement(s) file name: ");
        String fileName = in.nextLine();
        /*
         * Parse input file
         */
        out.println("*** Parsing input file ***");
        Statement s = new Statement1Parse1();
        SimpleReader file = new SimpleReader1L(fileName);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        s.parse(tokens); // replace with parseBlock to test other method
        /*
         * Pretty print the statement(s)
         */
        out.println("*** Pretty print of parsed statement(s) ***");
        s.prettyPrint(out, 0);

        in.close();
        out.close();
    }

}
