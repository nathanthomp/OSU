import components.statement.Statement;
import components.statement.Statement1;
import components.statement.StatementKernel;

/**
 * Utility class with method to count the number of calls to primitive
 * instructions (move, turnleft, turnright, infect, skip) in a given
 * {@code Statement}.
 *
 * @author Put your name here
 *
 */
public final class CountPrimitiveCalls {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private CountPrimitiveCalls() {
    }

    /**
     * Reports the number of calls to primitive instructions (move, turnleft,
     * turnright, infect, skip) in a given {@code Statement}.
     *
     * @param s
     *            the {@code Statement}
     * @return the number of calls to primitive instructions in {@code s}
     * @ensures <pre>
     * countOfPrimitiveCalls =
     *  [number of calls to primitive instructions in s]
     * </pre>
     */
    public static int countOfPrimitiveCalls(Statement s) {
        int count = 0;
        switch (s.kind()) {
            case BLOCK: {
                /*
                 * Add up the number of calls to primitive instructions in each
                 * nested statement in the BLOCK.
                 */

                for (int i = 0; i < s.lengthOfBlock(); i++) {
                    Statement s1 = s.removeFromBlock(i);
                    count += countOfPrimitiveCalls(s1);

                    s.addToBlock(i, s1);
                }

                break;
            }
            case IF: {
                /*
                 * Find the number of calls to primitive instructions in the
                 * body of the IF.
                 */

                Statement s1 = new Statement1();
                StatementKernel.Condition c = s.disassembleIf(s1);

                count += countOfPrimitiveCalls(s1);

                s.assembleIf(c, s1);

                break;
            }
            case IF_ELSE: {
                /*
                 * Add up the number of calls to primitive instructions in the
                 * "then" and "else" bodies of the IF_ELSE.
                 */

                Statement s1 = new Statement1();
                Statement s2 = new Statement1();
                StatementKernel.Condition c = s.disassembleIfElse(s1, s2);

                count += countOfPrimitiveCalls(s1);
                count += countOfPrimitiveCalls(s2);

                s.assembleIfElse(c, s1, s2);

                break;
            }
            case WHILE: {
                /*
                 * Find the number of calls to primitive instructions in the
                 * body of the WHILE.
                 */

                Statement s1 = new Statement1();
                StatementKernel.Condition c = s.disassembleWhile(s1);

                count += countOfPrimitiveCalls(s1);

                s.assembleWhile(c, s1);

                break;
            }
            case CALL: {
                /*
                 * This is a leaf: the count can only be 1 or 0. Determine
                 * whether this is a call to a primitive instruction or not.
                 */

                String[] callNames = new String[] { "move", "turnleft",
                        "turnright", "infect", "skip" };
                String callName = s.disassembleCall();

                for (String name : callNames) {
                    if (name.equals(callName)) {
                        count++;
                    }
                }

                s.assembleCall(callName);

                break;
            }
            default: {
                // this will never happen...can you explain why?
                break;
            }
        }
        return count;
    }

}
