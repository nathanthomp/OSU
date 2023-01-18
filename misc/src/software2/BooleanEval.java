package software2;

import components.queue.Queue;
import components.queue.Queue1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Blank program (clear of Checkstyle and FindBugs warnings).
 *
 * @author N. Thompson
 */
public final class BooleanEval {

    /**
     * Default constructor--private to prevent instantiation.
     */
    private BooleanEval() {
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

        Queue<String> tokens = new Queue1L<>();
        tokens.enqueue("NOT");
        tokens.enqueue("(");
        tokens.enqueue("T");
        tokens.enqueue("AND");
        tokens.enqueue("T");
        tokens.enqueue(")");
//        tokens.enqueue("(");
//        tokens.enqueue("NOT");
//        tokens.enqueue("(");
//        tokens.enqueue("F");
//        tokens.enqueue(")");
//        tokens.enqueue(")");
//        tokens.enqueue(")");
        tokens.enqueue("### END OF INPUT ###");

        out.println("Original queue: " + tokens);
        out.println("Value: " + valueOfBoolExpr(tokens));
        out.println("Updated queue: " + tokens);

        out.close();
    }

    /**
     * Evaluates a Boolean expression and returns its value.
     *
     * @param tokens
     *            the {@code Queue<String>} that starts with a bool-expr string
     * @return value of the expression
     * @updates tokens
     * @requires [a bool-expr string is a prefix of tokens]
     * @ensures <pre>
     * valueOfBoolExpr =
     *   [value of longest bool-expr string at start of #tokens]  and
     * #tokens = [longest bool-expr string at start of #tokens] * tokens
     * </pre>
     */
    public static boolean valueOfBoolExpr(Queue<String> tokens) {

        boolean result = false;
        /*
         * While we have a valid BoolExpr token.
         */
        while (tokens.front().equals("T") || tokens.front().equals("F")
                || tokens.front().equals("NOT") || tokens.front().equals("(")
                || tokens.front().equals("AND")
                || tokens.front().equals("OR")) {
            String op = tokens.dequeue();
            switch (op) {
                case "NOT": {
                    result = !valueOfBoolExpr(tokens);
                    break;
                }
                case "OR": {
                    result = result || valueOfBoolExpr(tokens);
                    break;
                }
                case "AND": {
                    result = result && valueOfBoolExpr(tokens);
                    break;
                }
                case "(": {
                    tokens.dequeue();
                    result = valueOfBoolExpr(tokens);
                    tokens.dequeue();
                    break;
                }
                case "T": {
                    result = true;
                    break;
                }
                case "F": {
                    result = false;
                    break;
                }
                default: {
                    break;
                }
            }
        }
        return result;
    }

}
