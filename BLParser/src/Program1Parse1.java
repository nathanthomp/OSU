import components.map.Map;
import components.program.Program;
import components.program.Program1;
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
 * Layered implementation of secondary method {@code parse} for {@code Program}.
 *
 * @author Nathan Thompson
 * @author Luke Serraglio
 *
 */
public final class Program1Parse1 extends Program1 {

    /*
     * Private members --------------------------------------------------------
     */

    /**
     * Parses a single BL instruction from {@code tokens} returning the
     * instruction name as the value of the function and the body of the
     * instruction in {@code body}.
     *
     * @param tokens
     *            the input tokens
     * @param body
     *            the instruction body
     * @return the instruction name
     * @replaces body
     * @updates tokens
     * @requires <pre>
     * [<"INSTRUCTION"> is a prefix of tokens]  and
     *  [<Tokenizer.END_OF_INPUT> is a suffix of tokens]
     * </pre>
     * @ensures <pre>
     * if [an instruction string is a proper prefix of #tokens]  and
     *    [the beginning name of this instruction equals its ending name]  and
     *    [the name of this instruction does not equal the name of a primitive
     *     instruction in the BL language] then
     *  parseInstruction = [name of instruction at start of #tokens]  and
     *  body = [Statement corresponding to the block string that is the body of
     *          the instruction string at start of #tokens]  and
     *  #tokens = [instruction string at start of #tokens] * tokens
     * else
     *  [report an appropriate error message to the console and terminate client]
     * </pre>
     */
    private static String parseInstruction(Queue<String> tokens,
            Statement body) {
        assert tokens != null : "Violation of: tokens is not null";
        assert body != null : "Violation of: body is not null";
        assert tokens.length() > 0 && tokens.front().equals("INSTRUCTION") : ""
                + "Violation of: <\"INSTRUCTION\"> is proper prefix of tokens";
        /*
         * Dequeue the first token because we know that it is a valid
         * INSTRUCTION Keyword.
         */
        tokens.dequeue();
        /*
         * Make sure the first token is a valid Identifier and dequeue it.
         */
        Reporter.assertElseFatalError(Tokenizer.isIdentifier(tokens.front()),
                "The Current token is not a valid Identifier");
        String beginningIdentifier = tokens.dequeue();
        /*
         * Make sure the first token is a IS Keyword and dequeue it.
         */
        Reporter.assertElseFatalError(
                Tokenizer.isKeyword(tokens.front())
                        && tokens.front().equals("IS"),
                "The Current token is not an IS Keyword");
        tokens.dequeue();
        /*
         * Parse and add the BODY to $this.
         */
        Statement s = new Statement1();
        s.parseBlock(tokens);
        body.transferFrom(s);
        /*
         * Make sure the first token is an END Keyword and dequeue it.
         */
        Reporter.assertElseFatalError(
                Tokenizer.isKeyword(tokens.front())
                        && tokens.front().equals("END"),
                "The Current token is not an END Keyword");
        tokens.dequeue();
        /*
         * Make sure the first token is a valid Identifier and dequeue it.
         */
        Reporter.assertElseFatalError(Tokenizer.isIdentifier(tokens.front()),
                "The Current token is not a valid Indentifier");
        String endingIdentifier = tokens.dequeue();
        /*
         * Make sure the beginning and ending PROGRAM identifiers are the same
         * and set $this.name.
         */
        Reporter.assertElseFatalError(
                beginningIdentifier.equals(endingIdentifier),
                "The beginning and ending PROGRAM identifiers are not the same");
        /*
         * Make sure that the instruction name is not a name of a primitive
         * call.
         */
        boolean hasSameNameOfPrimitiveCall = false;
        for (Instruction c : Instruction.values()) {
            if (c.name().replace('-', '_').toUpperCase()
                    .equals(beginningIdentifier)) {
                hasSameNameOfPrimitiveCall = true;
            }
        }
        Reporter.assertElseFatalError(!hasSameNameOfPrimitiveCall,
                "Instruction name cannot be a name of a primitive call");
        return beginningIdentifier;
    }

    /*
     * Constructors -----------------------------------------------------------
     */

    /**
     * No-argument constructor.
     */
    public Program1Parse1() {
        super();
    }

    /*
     * Public methods ---------------------------------------------------------
     */

    @Override
    public void parse(SimpleReader in) {
        assert in != null : "Violation of: in is not null";
        assert in.isOpen() : "Violation of: in.is_open";
        Queue<String> tokens = Tokenizer.tokens(in);
        this.parse(tokens);
    }

    @Override
    public void parse(Queue<String> tokens) {
        assert tokens != null : "Violation of: tokens is not null";
        assert tokens.length() > 0 : ""
                + "Violation of: Tokenizer.END_OF_INPUT is a suffix of tokens";
        /*
         * Make sure the first token is a PROGRAM Keyword and dequeue it.
         */
        Reporter.assertElseFatalError(
                Tokenizer.isKeyword(tokens.front())
                        && tokens.front().equals("PROGRAM"),
                "The Current token is not a PROGRAM Keyword");
        tokens.dequeue();
        /*
         * Make sure the first token is an Identifier and dequeue it.
         */
        Reporter.assertElseFatalError(Tokenizer.isIdentifier(tokens.front()),
                "The Current token is not a valid Identifier");
        String beginningIdentifier = tokens.dequeue();
        /*
         * Make sure the first token is a IS Keyword and dequeue it.
         */
        Reporter.assertElseFatalError(
                Tokenizer.isKeyword(tokens.front())
                        && tokens.front().equals("IS"),
                "The Current token is not an IS Keyword");
        tokens.dequeue();
        /*
         * Make sure first token is a Keyword (either INSTRUCTION or BEGIN).
         */
        Reporter.assertElseFatalError(
                Tokenizer.isKeyword(tokens.front())
                        && (tokens.front().equals("INSTRUCTION")
                                || tokens.front().equals("BEGIN")),
                "The Current token is not an INSTRUCTION or BEGIN Keyword");
        /*
         * If first token is a INSTRUCTION Keyword, parse each instruction.
         */
        Map<String, Statement> context = this.newContext();
        while (Tokenizer.isKeyword(tokens.front())
                && tokens.front().equals("INSTRUCTION")) {
            /*
             * Parse the instruction to get the instructions body and and
             * identifier.
             */
            Statement s = new Statement1();
            String instructionName = parseInstruction(tokens, s);
            context.add(instructionName, s);
        }
        /*
         * Add context to $this.
         */
        this.swapContext(context);
        /*
         * Make sure the first token is a BEGIN Keyword and dequeue it.
         */
        Reporter.assertElseFatalError(
                Tokenizer.isKeyword(tokens.front())
                        && tokens.front().equals("BEGIN"),
                "The Current token is not a BEGIN Keyword");
        tokens.dequeue();
        /*
         * Parse and add the BODY to $this.
         */
        Statement body = new Statement1();
        body.parseBlock(tokens);
        this.swapBody(body);
        /*
         * Make sure the first token is an END Keyword and dequeue it.
         */
        Reporter.assertElseFatalError(
                Tokenizer.isKeyword(tokens.front())
                        && tokens.front().equals("END"),
                "The Current token is not an END Keyword");
        tokens.dequeue();
        /*
         * Make sure the first token is a valid Identifier and dequeue it.
         */
        Reporter.assertElseFatalError(Tokenizer.isIdentifier(tokens.front()),
                "The Current token is not a valid Identifier");
        String endingIdentifier = tokens.dequeue();
        /*
         * Make sure the beginning and ending PROGRAM identifiers are the same
         * and set $this.name.
         */
        Reporter.assertElseFatalError(
                beginningIdentifier.equals(endingIdentifier),
                "The beginning and ending PROGRAM identifiers are not the same");
        this.setName(beginningIdentifier);
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
        out.print("Enter valid BL program file name: ");
        String fileName = in.nextLine();
        /*
         * Parse input file
         */
        out.println("*** Parsing input file ***");
        Program p = new Program1Parse1();
        SimpleReader file = new SimpleReader1L(fileName);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        p.parse(tokens);
        /*
         * Pretty print the program
         */
        out.println("*** Pretty print of parsed program ***");
        p.prettyPrint(out);

        in.close();
        out.close();
    }

}
