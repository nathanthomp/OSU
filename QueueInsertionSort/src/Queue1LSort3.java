import java.util.Comparator;

import components.queue.Queue;
import components.queue.Queue1L;

/**
 * Layered implementations of secondary method {@code sort} for
 * {@code Queue<String>}.
 *
 * @param <T>
 *            type of {@code Queue} entries
 * @mathdefinitions <pre>
 * IS_TOTAL_PREORDER (
 *   r: binary relation on T
 *  ) : boolean is
 *  for all x, y, z: T
 *   ((r(x, y) or r(y, x))  and
 *    (if (r(x, y) and r(y, z)) then r(x, z)))
 *
 * IS_SORTED (
 *   s: string of T,
 *   r: binary relation on T
 *  ) : boolean is
 *  for all x, y: T where (<x, y> is substring of s) (r(x, y))
 * </pre>
 */
public final class Queue1LSort3<T> extends Queue1L<T> {

    /**
     * No-argument constructor.
     */
    public Queue1LSort3() {
        super();
    }

    /**
     * Inserts the given {@code T} in the {@code Queue<T>} sorted according to
     * the given {@code Comparator<T>} and maintains the {@code Queue<T>}
     * sorted.
     *
     * @param <T>
     *            type of {@code Queue} entries
     * @param q
     *            the {@code Queue} to insert into
     * @param x
     *            the {@code T} to insert
     * @param order
     *            the {@code Comparator} defining the order for {@code T}
     * @updates q
     * @requires <pre>
     * IS_TOTAL_PREORDER([relation computed by order.compare method])  and
     * IS_SORTED(q, [relation computed by order.compare method])
     * </pre>
     * @ensures <pre>
     * perms(q, #q * <x>)  and
     * IS_SORTED(q, [relation computed by order.compare method])
     * </pre>
     */
    private static <T> void insertInOrder(Queue<T> q, T x,
            Comparator<T> order) {
        assert q != null : "Violation of: q is not null";
        assert x != null : "Violation of: x is not null";
        assert order != null : "Violation of: order is not null";

        //initialize variables
        Queue<T> tempQ = new Queue1L<T>();
        T lastValue = x;

        //add stuff to the temporary queue until x can be added in order
        while (order.compare(x, lastValue) <= 0) {
            tempQ.enqueue(q.dequeue());
        }

        //add x
        tempQ.enqueue(x);

        //add the rest of the stuff to the temporary queue
        while (q.length() > 0) {
            tempQ.enqueue(q.dequeue());
        }

        //move the elements of the temporary queue back to the original queue
        while (tempQ.length() > 0) {
            q.enqueue(tempQ.dequeue());
        }

    }

    @Override
    public void sort(Comparator<T> order) {
        assert order != null : "Violation of: order is not null";

        Queue<T> temp = this.newInstance();

        for (int i = 0; i < this.length(); i++) {
            T x = this.dequeue();
            insertInOrder(temp, x, order);
        }

        this.transferFrom(temp);

    }

}
