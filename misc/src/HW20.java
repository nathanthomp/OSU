import components.sequence.Sequence;
import components.sequence.Sequence1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.stack.Stack;
import components.stack.Stack1L;

/**
 * Blank program (clear of Checkstyle and FindBugs warnings).
 *
 * @author N. Thompson
 */
public final class HW20 {

    /**
     * Default constructor--private to prevent instantiation.
     */
    private HW20() {
        // no code needed here
    }

    /**
     * Reverses ("flips") {@code this}.
     *
     * @updates this
     * @ensures this = rev(#this)
     */
    public static void myFlip(Stack<Integer> stack) {
        Stack<Integer> temp = new Stack1L<>();
        while (stack.length() != 0) {
            int t = stack.pop();
            temp.push(t);
        }
        stack.transferFrom(temp);
    }

    /**
     * Reverses ("flips") {@code this}.
     *
     * @updates this
     * @ensures this = rev(#this)
     */
    public static void myFlip(Sequence<Integer> seq) {
        int temp = seq.remove(0);
        if (seq.length() != 0) {
            myFlip(seq);
        }
        seq.add(seq.length(), temp);
    }

    /**
     * Reverses ("flips") {@code this}.
     *
     * @updates this
     * @ensures this = rev(#this)
     */
    public static void myFlip2(Sequence<Integer> seq) {
        Sequence<Integer> temp = new Sequence1L<>();
        int count = 0;
        for (int i = seq.length() - 1; i >= 0; i--) {
            int t = seq.entry(i);
            temp.add(count, t);
            count++;
        }
        seq.transferFrom(temp);
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments; unused here
     */
    public static void main(String[] args) {
        SimpleWriter out = new SimpleWriter1L();
//        Stack<Integer> stack = new Stack1L<>();
//        stack.push(1);
//        stack.push(2);
//        stack.push(3);
//        out.print(stack);
//        myFlip(stack);
//        out.print(stack);

        Sequence<Integer> seq = new Sequence1L<>();
        seq.add(0, 1);
        seq.add(1, 2);
        seq.add(2, 3);
        out.print(seq);
        myFlip2(seq);
        out.print(seq);

    }

}
