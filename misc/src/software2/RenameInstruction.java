package software2;

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
import components.statement.StatementKernel;
import components.utilities.Tokenizer;

/**
 * Blank program (clear of Checkstyle and FindBugs warnings).
 *
 * @author N. Thompson
 */
public final class RenameInstruction {

    /**
     * Default constructor--private to prevent instantiation.
     */
    private RenameInstruction() {
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

        Program p = new Program1();

        Statement s = createFromArgs("data/test1.bl", true);

        p.

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
     * Refactors the given {@code Statement} by renaming every occurrence of
     * instruction {@code oldName} to {@code newName}. Every other statement is
     * left unmodified.
     *
     * @param s
     *            the {@code Statement}
     * @param oldName
     *            the name of the instruction to be renamed
     * @param newName
     *            the new name of the renamed instruction
     * @updates s
     * @requires [newName is a valid IDENTIFIER]
     * @ensures <pre>
     * s = [#s refactored so that every occurrence of instruction oldName
     *   is replaced by newName]
     * </pre>
     */
    public static void renameInstruction(Statement s, String oldName,
            String newName) {
        switch (s.kind()) {
            case BLOCK: {

                // Recursive call for every child of BLOCK.
                for (int i = 0; i < s.lengthOfBlock(); i++) {
                    Statement s1 = s.removeFromBlock(i);
                    renameInstruction(s1, oldName, newName);
                    s.addToBlock(i, s1);
                }

                break;
            }

            case IF: {

                // Disassemble IF. Recursive Call. Reassemble IF.
                Statement s1 = s.newInstance();
                StatementKernel.Condition c = s.disassembleIf(s1);
                renameInstruction(s1, oldName, newName);
                s.assembleIf(c, s1);

                break;
            }

            case IF_ELSE: {

                // Disassemble IF_ELSE. Recursive Call. Reassemble IF_ELSE.
                Statement s1 = s.newInstance();
                Statement s2 = s.newInstance();
                StatementKernel.Condition c = s.disassembleIfElse(s1, s2);
                renameInstruction(s1, oldName, newName);
                renameInstruction(s2, oldName, newName);
                s.assembleIfElse(c, s1, s2);

                break;
            }

            case WHILE: {

                // Disassemble WHILE. Recursive Call. Reassemble WHILE.
                Statement s1 = s.newInstance();
                StatementKernel.Condition c = s.disassembleIf(s1);
                renameInstruction(s1, oldName, newName);
                s.assembleIf(c, s1);

                break;
            }

            case CALL: {
                /*
                 * Get the name of the call and see if it is the oldName. If it
                 * is we need to change it when we reassemble.
                 */
                String callName = s.disassembleCall();

                if (callName.equals(oldName)) {
                    s.assembleCall(newName);
                } else {
                    s.assembleCall(callName);
                }

                break;
            }

            default: {
                // Will never happen. Here for best practice.
                break;
            }

        }

    }

    /**
     * Refactors the given {@code Program} by renaming instruction
     * {@code oldName}, and every call to it, to {@code newName}. Everything
     * else is left unmodified.
     *
     * @param p
     *            the {@code Program}
     * @param oldName
     *            the name of the instruction to be renamed
     * @param newName
     *            the new name of the renamed instruction
     * @updates p
     * @requires <pre>
     * oldName is in DOMAIN(p.context)  and
     * [newName is a valid IDENTIFIER]  and
     * newName is not in DOMAIN(p.context)
     * </pre>
     * @ensures <pre>
     * p = [#p refactored so that instruction oldName and every call
     *   to it are replaced by newName]
     * </pre>
     */
    public static void renameInstruction(Program p, String oldName,
            String newName) {
        Map<String, Statement> context = p.newContext();
        Map<String, Statement> c = p.newContext();
        p.swapContext(context);

        while (context.size() > 0) {
            Map.Pair<String, Statement> instr = context.removeAny();
            if (instr.key().equals(oldName)) {
                c.add(newName, instr.value());
            } else {
                renameInstruction(instr.value(), oldName, newName);
                c.add(instr.key(), instr.value());
            }
        }

        p.swapContext(c);

        Statement body1 = p.newBody();
        p.swapBody(body1);
        renameInstruction(body1, oldName, newName);
        p.swapBody(body1);
    }

}
