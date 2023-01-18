import java.util.Comparator;

import components.queue.Queue;
import components.queue.Queue1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Blank program (clear of Checkstyle and FindBugs warnings).
 *
 * @author N. Thompson
 */
public final class HW18 {

    /**
     * String comparator nested class.
     */
    private static class StringComparator implements Comparator<String> {
        @Override
        public int compare(String s1, String s2) {
            return s1.compareTo(s2);
        }
    }

    /**
     * Default constructor--private to prevent instantiation.
     */
    private HW18() {
        // no code needed here
    }

    /**
     * Removes and returns the minimum value from {@code q} according to the
     * ordering provided by the {@code compare} method from {@code order}.
     *
     * @param q
     *            the queue
     * @param order
     *            ordering by which to compare entries
     * @return the minimum value from {@code q}
     * @updates q
     * @requires <pre>
     * q /= empty_string  and
     *  [the relation computed by order.compare is a total preorder]
     * </pre>
     * @ensures <pre>
     * perms(q * <removeMin>, #q)  and
     *  for all x: string of character
     *      where (x is in entries (q))
     *    ([relation computed by order.compare method](removeMin, x))
     * </pre>
     */
    private static String removeMin(Queue<String> q, Comparator<String> order) {
        String first = q.dequeue();
        String min = first;
        /*
         * Search for smallest string
         */
        for (String s : q) {
            if (order.compare(s, min) < 0) {
                min = s;
            }
        }
        q.enqueue(first);
        /*
         * Remove smallest string
         */
        String str;
        String result = "";
        int count = 0;
        while (count < q.length()) {
            str = q.dequeue();
            if (!(order.compare(str, min) == 0)) {
                q.enqueue(str);
            } else {
                result = str;
            }
            count++;
        }
        return result;
    }

    /**
     * Sorts {@code q} according to the ordering provided by the {@code compare}
     * method from {@code order}.
     *
     * @param q
     *            the queue
     * @param order
     *            ordering by which to sort
     * @updates q
     * @requires [the relation computed by order.compare is a total preorder]
     * @ensures q = [#q ordered by the relation computed by order.compare]
     */
    public static void sort(Queue<String> q, Comparator<String> order) {
        Queue<String> temp = new Queue1L<String>();
        while (q.length() > 0) {
            temp.enqueue(removeMin(q, order));
        }
        q.transferFrom(temp);
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments; unused here
     */
    public static void main(String[] args) {
        SimpleWriter out = new SimpleWriter1L();
        Queue<String> q = new Queue1L<>();
        Comparator<String> c = new StringComparator();
        q.enqueue("b");
        q.enqueue("c");
        q.enqueue("a");
        q.enqueue("d");
        q.enqueue("e");

        out.println(q);
        sort(q, c);
        out.println(q);

        out.close();
    }

}
