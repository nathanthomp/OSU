import components.queue.Queue;
import components.queue.Queue1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Blank program (clear of Checkstyle and FindBugs warnings).
 *
 * @author N. Thompson
 */
public final class HW15 {

    /**
     * Default constructor--private to prevent instantiation.
     */
    private HW15() {
        // no code needed here
    }

    /**
     * Reports the smallest integer in the given {@code Queue<Integer>}.
     *
     * @param q
     *            the queue of integer
     * @return the smallest integer in the given queue
     * @requires q /= empty_string
     * @ensures <pre>
     * min is in entries(q) and
     * for all x: integer
     *     where (x is in entries(q))
     *   (min <= x)
     * </pre>
     */
    private static int min(Queue<Integer> q) {
        int first = q.dequeue();
        int min = first;
        for (Integer n : q) {
            if (n <= min) {
                min = n;
            }
        }
        q.enqueue(first);

        return min;
    }

    /**
     * Reports an array of two {@code int}s with the smallest and the largest
     * integer in the given {@code Queue<Integer>}.
     *
     * @param q
     *            the queue of integer
     * @return an array of two {@code int}s with the smallest and the largest
     *         integer in the given queue
     * @requires q /= empty_string
     * @ensures <pre>
     * { minAndMax[0], minAndMax[1] } is subset of entries(q) and
     * for all x: integer
     *     where (x in in entries(q))
     *   (minAndMax[0] <= x <= minAndMax[1])
     * </pre>
     */
    private static int[] minAndMax(Queue<Integer> q) {
        int[] result = new int[2];
        int first = q.dequeue();
        int min = first;
        int max = first;

        for (Integer n : q) {
            if (n <= min) {
                min = n;
            }
            if (n >= max) {
                max = n;
            }
        }
        q.enqueue(first);
        result[0] = min;
        result[1] = max;
        return result;
    }

    /**
     * Reports an array of two {@code int}s with the smallest and the largest
     * integer in the given {@code Queue<Integer>}.
     *
     * @param q
     *            the queue of integer
     * @return an array of two {@code int}s with the smallest and the largest
     *         integer in the given queue
     * @requires q /= empty_string
     * @ensures <pre>
     * { minAndMax[0], minAndMax[1] } is subset of entries(q) and
     * for all x: integer
     *     where (x in in entries(q))
     *   (minAndMax[0] <= x <= minAndMax[1])
     * </pre>
     */
    private static int[] minAndMaxNA(Queue<Integer> q) {
        int[] result = new int[2];
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;

        for (int i = 1; i <= q.length() / 2; i++) {
            result[0] = q.dequeue();
            result[1] = q.dequeue();
            if (result[0] > result[1]) {
                if (result[0] >= max) {
                    max = result[0];
                }
            } else {
                if (result[1] < min) {
                    min = result[1];
                }
            }
            q.enqueue(result[0]);
            q.enqueue(result[1]);
        }
        result[0] = min;
        result[1] = max;
        return result;
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
        q.enqueue(9);
        q.enqueue(7);
        q.enqueue(2);
        q.enqueue(3);
        q.enqueue(11);
        q.enqueue(4);

        out.println(q);
        out.println(min(q));
//        int[] array = minAndMaxNA(q);
//        out.println(array[0] + " " + array[1]);
        out.println(q);

        out.close();
    }

}
