import java.util.Comparator;

import components.queue.Queue;
import components.queue.Queue1L;

/**
 * Layered implementations of secondary method {@code sort} for
 * {@code Queue<String>}.
 */
public final class Queue1LSort1 extends Queue1L<String> {

    /**
     * No-argument constructor.
     */
    public Queue1LSort1() {
        super();
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
     * (q * <removeMin>) is permutation of #q  and
     *  for all x: string of character
     *      where (x is in entries (q))
     *    ([relation computed by order.compare method](removeMin, x))
     * </pre>
     */
    private static String removeMin(Queue<String> q, Comparator<String> order) {
        assert q != null : "Violation of: q is not null";
        assert order != null : "Violation of: order is not null";

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

    @Override
    public void sort(Comparator<String> order) {
        assert order != null : "Violation of: order is not null";

        Queue<String> temp = new Queue1L<String>();
        while (this.length() > 0) {
            temp.enqueue(removeMin(this, order));
        }
        this.transferFrom(temp);

    }

}
