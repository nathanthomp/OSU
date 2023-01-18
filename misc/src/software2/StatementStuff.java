package software2;

import components.queue.Queue;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.statement.Statement;
import components.statement.Statement1;
import components.statement.StatementKernel;
import components.statement.StatementKernel.Condition;
import components.utilities.Tokenizer;

/**
 * Blank program (clear of Checkstyle and FindBugs warnings).
 *
 * @author N. Thompson
 */
public final class StatementStuff {

    /**
     * Default constructor--private to prevent instantiation.
     */
    private StatementStuff() {
        // no code needed here
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments; unused here
     */
    public static void main(String[] args) {
        SimpleWriter out = new SimpleWriter1L();

        Statement s = createFromArgs("data/test1.bl", true);

        prettyPrint(out, 0, s);

        out.close();
    }

    /**
     * Creates and returns a {@code Statement} constructed from a given input
     * file.
     *
     * @param fileName
     *            the name of the file containing the statement
     * @param block
     *            flag to indicate whether to read an entire BLOCK (sequence of
     *            statements) or a single statement
     * @return the constructed statement
     * @requires <pre>
     * [fileName is the name of a file containing zero, one, or more
     *  valid BL statements]
     * </pre>
     * @ensures createFromArgs = [statement(s) from file fileName]
     */
    private static Statement createFromArgs(String fileName, boolean block) {
        SimpleReader in = new SimpleReader1L(fileName);
        Queue<String> tokens = Tokenizer.tokens(in);
        in.close();
        Statement s = new Statement1();
        if (block) {
            s.parseBlock(tokens);
        } else {
            s.parse(tokens);
        }
        return s;
    }

    /**
     * Refactors the given {@code Statement} so that every IF_ELSE statement
     * with a negated condition (NEXT_IS_NOT_EMPTY, NEXT_IS_NOT_ENEMY,
     * NEXT_IS_NOT_FRIEND, NEXT_IS_NOT_WALL) is replaced by an equivalent
     * IF_ELSE with the opposite condition and the "then" and "else" BLOCKs
     * switched. Every other statement is left unmodified.
     *
     * @param s
     *            the {@code Statement}
     * @updates s
     * @ensures <pre>
     * s = [#s refactored so that IF_ELSE statements with "not"
     *   conditions are simplified so the "not" is removed]
     * </pre>
     */
    public static void simplifyIfElse(Statement s) {

        /*
         * The instance we are looking for is only in the IF_ELSE case
         */

        switch (s.kind()) {
            case BLOCK: {

                for (int i = 0; i < s.lengthOfBlock(); i++) {
                    Statement s1 = s.removeFromBlock(i);
                    simplifyIfElse(s1);
                    s.addToBlock(i, s1);
                }

                break;
            }
            case IF: {

                Statement s1 = new Statement1();
                StatementKernel.Condition c = s.disassembleIf(s1);

                simplifyIfElse(s1);

                s.assembleIf(c, s1);

                break;
            }
            case IF_ELSE: {

                Statement s1 = new Statement1();
                Statement s2 = new Statement1();

                StatementKernel.Condition c = s.disassembleIfElse(s1, s2);

                /*
                 * Determine whether or not the condition is a NOT.
                 */

                String[] notConditions = new String[] { "NEXT_IS_NOT_EMPTY",
                        "NEXT_IS_NOT_ENEMY", "NEXT_IS_NOT_FRIEND",
                        "NEXT_IS_NOT_WALL" };
                boolean isANotCondition = false;
                for (String notCondition : notConditions) {
                    if (c.name().equals(notCondition)) {
                        isANotCondition = true;
                    }
                }

                /*
                 * If the IF_ELSE is a not condition, switch the condition,
                 * switch the contents of the blocks.
                 */
                if (isANotCondition) {
                    /*
                     * Switch the condition.
                     */
                    switch (c.name()) {
                        case "NEXT_IS_NOT_EMPTY":
                            c = StatementKernel.Condition.NEXT_IS_EMPTY;
                            break;
                        case "NEXT_IS_NOT_ENEMY":
                            c = StatementKernel.Condition.NEXT_IS_ENEMY;
                            break;
                        case "NEXT_IS_NOT_FRIEND":
                            c = StatementKernel.Condition.NEXT_IS_FRIEND;
                            break;
                        case "NEXT_IS_NOT_WALL":
                            c = StatementKernel.Condition.NEXT_IS_WALL;
                            break;
                        default:
                            break;
                    }
                    /*
                     * Switch the contents of the blocks
                     */
                }

                simplifyIfElse(s1);
                simplifyIfElse(s2);

                s.assembleIfElse(c, s2, s1);

                break;
            }
            case WHILE: {

                Statement s1 = new Statement1();
                StatementKernel.Condition c = s.disassembleWhile(s1);

                simplifyIfElse(s1);

                s.assembleWhile(c, s1);

                break;
            }
            case CALL: {
                // nothing to do here...can you explain why?
                break;
            }
            default: {
                // this will never happen...can you explain why?
                break;
            }
        }
    }

    /**
     * Prints the given number of spaces to the given output stream.
     *
     * @param out
     *            the output stream
     * @param numSpaces
     *            the number of spaces to print
     * @updates out.content
     * @requires out.is_open and spaces >= 0
     * @ensures out.content = #out.content * [numSpaces spaces]
     */
    private static void printSpaces(SimpleWriter out, int numSpaces) {
        for (int i = 0; i < numSpaces; i++) {
            out.print(' ');
        }
    }

    /**
     * Converts c into the corresponding BL condition.
     *
     * @param c
     *            the Condition to convert
     * @return the BL condition corresponding to c
     * @ensures toStringCondition = [BL condition corresponding to c]
     */
    private static String toStringCondition(Condition c) {
        String result;
        switch (c) {
            case NEXT_IS_EMPTY: {
                result = "next-is-empty";
                break;
            }
            case NEXT_IS_NOT_EMPTY: {
                result = "next-is-not-empty";
                break;
            }
            case NEXT_IS_ENEMY: {
                result = "next-is-enemy";
                break;
            }
            case NEXT_IS_NOT_ENEMY: {
                result = "next-is-not-enemy";
                break;
            }
            case NEXT_IS_FRIEND: {
                result = "next-is-friend";
                break;
            }
            case NEXT_IS_NOT_FRIEND: {
                result = "next-is-not-friend";
                break;
            }
            case NEXT_IS_WALL: {
                result = "next-is-wall";
                break;
            }
            case NEXT_IS_NOT_WALL: {
                result = "next-is-not-wall";
                break;
            }
            case RANDOM: {
                result = "random";
                break;
            }
            case TRUE: {
                result = "true";
                break;
            }
            default: {
                // this will never happen...
                result = "";
                break;
            }
        }
        return result;
    }

    /**
     * Pretty prints {@code this} to the given stream {@code out} {@code offset}
     * spaces from the left margin using
     * {@link components.program.Program#INDENT_SIZE Program.INDENT_SIZE} spaces
     * for each indentation level.
     *
     * @param out
     *            the output stream
     * @param offset
     *            the number of spaces to be placed before every nonempty line
     *            of output; nonempty lines of output that are indented further
     *            will, of course, continue with even more spaces
     * @updates out.content
     * @requires out.is_open and 0 <= offset
     * @ensures <pre>
     * out.content =
     *   #out.content * [this pretty printed offset spaces from the left margin
     *                   using Program.INDENT_SIZE spaces for indentation]
     * </pre>
     */
    public static void prettyPrint(SimpleWriter out, int offset, Statement s) {
        switch (s.kind()) {
            case BLOCK: {

                /*
                 * Recursive call on every call in this block.
                 */
                for (int i = 0; i < s.lengthOfBlock(); i++) {
                    Statement s1 = s.removeFromBlock(i);
                    prettyPrint(out, offset, s1);
                    s.addToBlock(i, s1);
                }

                break;
            }
            case IF: {

                /*
                 * Disassemble statement.
                 */
                Statement s1 = s.newInstance();
                StatementKernel.Condition c = s.disassembleIf(s1);

                /*
                 * Print top line.
                 */
                out.println();
                printSpaces(out, offset);
                out.print("IF " + toStringCondition(c) + " THEN");

                /*
                 * Recursive call.
                 */
                prettyPrint(out, offset + 4, s1);

                /*
                 * Print bottom line.
                 */
                out.println();
                printSpaces(out, offset);
                out.print("END IF");

                /*
                 * Reassemble statement.
                 */
                s.assembleIf(c, s1);

                //out.println();

                break;
            }
            case IF_ELSE: {

                /*
                 * Disassemble statement.
                 */
                Statement s1 = s.newInstance();
                Statement s2 = s.newInstance();
                StatementKernel.Condition c = s.disassembleIfElse(s1, s2);

                /*
                 * Print IF line.
                 */
                out.println();
                printSpaces(out, offset);
                out.print("IF " + toStringCondition(c) + " THEN");

                /*
                 * Recursive call for first BLOCK.
                 */
                prettyPrint(out, offset + 4, s1);

                /*
                 * Print ELSE line.
                 */
                out.println();
                printSpaces(out, offset);
                out.print("ELSE");

                /*
                 * Recursive call for second BLOCK.
                 */
                prettyPrint(out, offset + 4, s2);

                /*
                 * Print END line.
                 */
                out.println();
                printSpaces(out, offset);
                out.print("END IF");

                /*
                 * Reassemble statement.
                 */
                s.assembleIfElse(c, s1, s2);

                //out.println();

                break;
            }
            case WHILE: {

                /*
                 * Disassemble statement.
                 */
                Statement s1 = s.newInstance();
                StatementKernel.Condition c = s.disassembleWhile(s1);

                /*
                 * Print top line.
                 */
                out.println();
                printSpaces(out, offset);
                out.print("WHILE " + toStringCondition(c) + " THEN");

                /*
                 * Recursive call.
                 */
                prettyPrint(out, offset + 4, s1);

                /*
                 * Print bottom line.
                 */
                out.println();
                printSpaces(out, offset);
                out.print("END WHILE");

                /*
                 * Reassemble statement.
                 */
                s.assembleWhile(c, s1);

                break;
            }
            case CALL: {

                /*
                 * Disassemble statement.
                 */
                String callResult = s.disassembleCall();

                /*
                 * Print line.
                 */
                out.println();
                printSpaces(out, offset);
                out.print(callResult);

                /*
                 * Reassemble statement.
                 */
                s.assembleCall(callResult);

                break;
            }
            default: {
                // this will never happen...
                break;
            }
        }
    }

}
