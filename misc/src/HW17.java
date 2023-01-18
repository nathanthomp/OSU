import components.queue.Queue;
import components.queue.Queue1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Blank program (clear of Checkstyle and FindBugs warnings).
 *
 * @author N. Thompson
 */
public final class HW17 {

    /**
     * Default constructor--private to prevent instantiation.
     */
    private HW17() {
        // no code needed here
    }

    /**
     * Reverses ("flips") {@code this}.
     *
     * @updates this
     * @ensures this = rev(#this)
     */
    public static void myFlip(Queue<Integer> q) {
        int temp = q.dequeue();
        if (!(q.length() == 0)) {
            myFlip(q);
        }
        q.enqueue(temp);

    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments; unused here
     */
    public static void main(String[] args) {
        SimpleWriter out = new SimpleWriter1L();
        Queue<Integer> q = new Queue1L<>();
        q.enqueue(1);
        q.enqueue(2);
        q.enqueue(3);
        q.enqueue(4);
        q.enqueue(5);
        q.enqueue(6);
        myFlip(q);
        out.println(q);

    }

}
