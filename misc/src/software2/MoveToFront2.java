package software2;

import components.map.Map;
import components.map.Map.Pair;
import components.queue.Queue;
import components.queue.Queue1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Blank program (clear of Checkstyle and FindBugs warnings).
 *
 * @author N. Thompson
 */
public final class MoveToFront2 {

    /**
     * Default constructor--private to prevent instantiation.
     */
    private MoveToFront2() {
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

        Queue<Pair<Integer, String>> q = new Queue1L<>();
        Map.Pair<Integer, String> pair1 = new Map.Pair<Integer, String>() {

            @Override
            public Integer key() {
                return 1;
            }

            @Override
            public String value() {
                return "a";
            }
        };
        q.enqueue(pair1);

        Map.Pair<Integer, String> pair2 = new Map.Pair<Integer, String>() {

            @Override
            public Integer key() {
                return 2;
            }

            @Override
            public String value() {
                return "b";
            }
        };
        q.enqueue(pair2);

        for (Pair<Integer, String> pair : q) {
            out.println(pair.toString());
        }

        moveToFront(q, 2);

        out.println();

        for (Pair<Integer, String> pair : q) {
            out.println(pair.key() + ", " + pair.value());
        }

        out.close();
    }

    /**
     * Finds pair with first component {@code key} and, if such exists, moves it
     * to the front of {@code q}.
     *
     * @param <K>
     *            type of {@code Pair} key
     * @param <V>
     *            type of {@code Pair} value
     * @param q
     *            the {@code Queue} to be searched
     * @param key
     *            the key to be searched for
     * @updates q
     * @ensures <pre>
     * perms(q, #q)  and
     * if there exists value: V (<(key, value)> is substring of q)
     *  then there exists value: V (<(key, value)> is prefix of q)
     * </pre>
     */
    private static <K, V> void moveToFront(Queue<Pair<K, V>> q, K key) {

        boolean hasKey = false;
        Pair<K, V> hasKeyPair = null;
        for (int i = 0; i < q.length(); i++) {
            Pair<K, V> pair = q.dequeue();
            if (pair.key() == key) {
                hasKey = true;
                hasKeyPair = pair;
            }
            q.enqueue(pair);
        }

        if (hasKey) {
            q.enqueue(hasKeyPair);
            for (int i = 0; i < q.length(); i++) {
                Pair<K, V> pair = q.dequeue();
                if (!pair.equals(hasKeyPair)) {
                    q.enqueue(pair);
                }

            }
        }
    }

}
