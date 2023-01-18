package software2;

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
public final class Sorting {

    /**
     * Integer greater-than-or-equal-to Comparator. This effect is achieved by
     * reversing the natural ordering provided by interface Comparable's
     * compareTo, which Integer implements as less-than-or-equal-to.
     */
    private static class IntegerGE implements Comparator<Integer> {

        @Override
        public int compare(Integer o1, Integer o2) {
            return o2.compareTo(o1);
        }
    }

    /**
     * Default constructor--private to prevent instantiation.
     */
    private Sorting() {
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
        q.enqueue(3);
        q.enqueue(4);

        for (Integer i : q) {
            out.println(i);
        }

        sort(q, new IntegerGE());

        out.println();

        for (Integer i : q) {
            out.println(i);
        }

        out.close();
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

        Queue<T> temp = q.newInstance();
        boolean hasBeenInserted = false;

        if (q.length() == 0) {
            q.enqueue(x);
        } else {
            while (q.length() > 0) {
                if (!hasBeenInserted) {
                    T y = q.dequeue();
                    if (order.compare(y, x) <= 0) {
                        // y is less than or equal to x
                        temp.enqueue(x);
                        temp.enqueue(y);
                        hasBeenInserted = true;
                    } else if (order.compare(y, x) > 0) {
                        // y is greater than x
                        temp.enqueue(y);
                    }
                } else {
                    T y = q.dequeue();
                    temp.enqueue(y);
                }
            }
            q.transferFrom(temp);
        }
    }

    /**
     * Sorts {@code this} according to the ordering provided by the
     * {@code compare} method from {@code order}.
     *
     * @param order
     *            ordering by which to sort
     * @updates this
     * @requires IS_TOTAL_PREORDER([relation computed by order.compare method])
     * @ensures <pre>
     * perms(this, #this)  and
     * IS_SORTED(this, [relation computed by order.compare method])
     * </pre>
     */
    public static <T> void sort(Queue<T> q, Comparator<T> order) {

        Queue<T> temp = q.newInstance();

        while (q.length() > 0) {
            T x = q.dequeue();
            insertInOrder(temp, x, order);
        }

        q.transferFrom(temp);

    }

}
