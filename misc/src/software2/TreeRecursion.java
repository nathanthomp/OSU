package software2;

import components.sequence.Sequence;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.tree.Tree;
import components.tree.Tree1;

/**
 * Blank program (clear of Checkstyle and FindBugs warnings).
 *
 * @author N. Thompson
 */
public final class TreeRecursion {

    /**
     * Default constructor--private to prevent instantiation.
     */
    private TreeRecursion() {
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
        Tree<Integer> t = new Tree1<>();
        Tree<Integer> t2 = new Tree1<>();
        int root = 1;
        Sequence<Tree<Integer>> children = t.newSequenceOfTree();
        Sequence<Tree<Integer>> children2 = t.newSequenceOfTree();
        children2.add(0, new Tree1<Integer>());
        t2.assemble(2, children2);
        children.add(0, t2);
        children.add(1, new Tree1<Integer>());
        children.add(2, new Tree1<Integer>());

        t.assemble(root, children);

        out.println(t);
        out.println(t);
        out.println(max(t));

        out.close();
    }

    /**
     * Returns the size of the given {@code Tree<T>}.
     *
     * @param <T>
     *            the type of the {@code Tree} node labels
     * @param t
     *            the {@code Tree} whose size to return
     * @return the size of the given {@code Tree}
     * @ensures size = |t|
     */
    public static <T> int size(Tree<T> t) {
        int result = 0;
        Sequence<Tree<T>> children = t.newSequenceOfTree();

        /*
         * If we do not have an empty tree.
         */
        if (!t.equals(new Tree1<T>())) {

            /*
             * Disassemble the tree and increment count.
             */
            T root = t.disassemble(children);
            result++;

            /*
             * Call size on each of the children trees.
             */
            for (Tree<T> child : children) {
                result += size(child);
            }

            t.assemble(root, children);
        }

        return result;
    }

    /**
     * Returns the height of the given {@code Tree<T>}.
     *
     * @param <T>
     *            the type of the {@code Tree} node labels
     * @param t
     *            the {@code Tree} whose height to return
     * @return the height of the given {@code Tree}
     * @ensures height = ht(t)
     */
    public static <T> int height(Tree<T> t) {
        int result = 0;
        Sequence<Tree<T>> children = t.newSequenceOfTree();

        /*
         * If we do not have an empty tree.
         */
        if (!t.equals(new Tree1<T>())) {
            /*
             * Disassemble the tree and increment count.
             */
            T root = t.disassemble(children);
            result++;

            int current = 0;
            for (Tree<T> child : children) {
                current = height(child);
                if (current > result) {
                    result = current;
                }
                t.assemble(root, children);
            }
        }

        return result;
    }

    /**
     * Returns the largest integer in the given {@code Tree<Integer>}.
     *
     * @param t
     *            the {@code Tree<Integer>} whose largest integer to return
     * @return the largest integer in the given {@code Tree<Integer>}
     * @requires |t| > 0
     * @ensures <pre>
     * max is in labels(t)  and
     * for all i: integer where (i is in labels(t)) (i <= max)
     * </pre>
     */
    public static int max(Tree<Integer> t) {
        Sequence<Tree<Integer>> children = t.newSequenceOfTree();
        int result = t.disassemble(children);

        /*
         * If there are children.
         */
        if (children.length() > 0) {
            int current = 0;
            for (Tree<Integer> child : children) {
                if (!child.equals(new Tree1<Integer>())) {
                    current = max(child);
                    if (current > result) {
                        result = current;
                    }
                }
            }
        }
        /*
         * Need to restore t.
         */

        return result;
    }

}
