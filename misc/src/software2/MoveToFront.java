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
public final class MoveToFront {

    /**
     * Default constructor--private to prevent instantiation.
     */
    private MoveToFront() {
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
        Queue<Integer> q = new Queue1L<>();
        q.enqueue(1);
//        q.enqueue(2);
//        q.enqueue(3);
//        q.enqueue(4);
        for (Integer i : q) {
            out.println(i);
        }

        moveToFront(q, 1);
        out.println();
        for (Integer i : q) {
            out.println(i);
        }

        out.close();
    }

    /**
     * Finds {@code x} in {@code q} and, if such exists, moves it to the front
     * of {@code q}.
     *
     * @param <T>
     *            type of {@code Queue} entries
     * @param q
     *            the {@code Queue} to be searched
     * @param x
     *            the entry to be searched for
     * @updates q
     * @ensures <pre>
     * perms(q, #q)  and
     * if <x> is substring of q
     *  then <x> is prefix of q
     * </pre>
     */
    private static <T> void moveToFront(Queue<T> q, T x) {
        // Try to find x in q
        // if found, remove it from q
        boolean foundX = false;
        for (int i = 0; i < q.length(); i++) {
            T y = q.dequeue();
            if (y.equals(x)) {
                foundX = true;
            }
            q.enqueue(y);
        }
        if (foundX) {
            q.enqueue(x);
            for (int i = 0; i < q.length(); i++) {
                T y = q.dequeue();
                if (!y.equals(x)) {
                    q.enqueue(y);
                }
            }
        }
    }

}
