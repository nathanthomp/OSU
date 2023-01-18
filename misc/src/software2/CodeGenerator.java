package software2;

import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Blank program (clear of Checkstyle and FindBugs warnings).
 *
 * @author N. Thompson
 */
public final class CodeGenerator {

    /**
     * Default constructor--private to prevent instantiation.
     */
    private CodeGenerator() {
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

        out.close();
    }

    /**
     * Returns the location of the next primitive instruction to execute in
     * compiled program {@code cp} given what the bug sees {@code wbs} and
     * starting from location {@code pc}.
     *
     * @param cp
     *            the compiled program
     * @param wbs
     *            the {@code CellState} indicating what the bug sees
     * @param pc
     *            the program counter
     * @return the location of the next primitive instruction to execute
     * @requires <pre>
     * [cp is a valid compiled BL program]  and
     * 0 <= pc < cp.length  and
     * [pc is the location of an instruction byte code in cp, that is, pc
     *  cannot be the location of an address]
     * </pre>
     * @ensures <pre>
     * [return the address of the next primitive instruction that
     *  should be executed in program cp given what the bug sees wbs and
     *  starting execution at address pc in program cp]
     * </pre>
     */
    public static int nextPrimitiveInstructionAddress(int[] cp, CellState wbs,
            int pc) {
        int result = 0;
        boolean foundPrinitiveInstruction = false;
        while (!foundPrinitiveInstruction) {
            if (isPrimitiveInstructionByteCode(cp[pc])) {
                foundPrinitiveInstruction = true;
            } else {
                if (conditionalJumpCondition(wbs, cp[pc])) {
                    isPrimitiveInstruction(cp, wbs, cp[pc]);
                } else {
                    isPrimitiveInstruction(cp, wbs, pc++);
                }
            }
            result++;
        }
        return result;
    }

}
