package software2;

import components.binarytree.BinaryTree;
import components.binarytree.BinaryTree1;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Blank program (clear of Checkstyle and FindBugs warnings).
 *
 * @author N. Thompson
 */
public final class RecursiveBinarySearchTree {

    /**
     * Default constructor--private to prevent instantiation.
     */
    private RecursiveBinarySearchTree() {
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
        int root = 2;

        // Create the tree
        //   2
        // 1   3
        BinaryTree<Integer> t = new BinaryTree1<>();
        BinaryTree<Integer> ls = new BinaryTree1<>();
        ls.assemble(1, new BinaryTree1<Integer>(), new BinaryTree1<Integer>());
        BinaryTree<Integer> rs = new BinaryTree1<>();
        rs.assemble(3, new BinaryTree1<Integer>(), new BinaryTree1<Integer>());
        t.assemble(root, ls, rs);

        out.println(t);
        out.println(removeFromTree(t, 1));
        out.println(t);
        out.close();
    }

    /**
     * Returns whether {@code x} is in {@code t}.
     *
     * @param <T>
     *            type of {@code BinaryTree} labels
     * @param t
     *            the {@code BinaryTree} to be searched
     * @param x
     *            the label to be searched for
     * @return true if t contains x, false otherwise
     * @requires IS_BST(t)
     * @ensures isInTree = (x is in labels(t))
     */
    public static <T extends Comparable<T>> boolean isInTree(BinaryTree<T> t,
            T x) {

        boolean result = false;
        T root;
        BinaryTree<T> ls = t.newInstance();
        BinaryTree<T> rs = t.newInstance();

        BinaryTree<T> test = t.newInstance();

        if (!t.equals(test)) {
            root = t.disassemble(ls, rs);

            if (root.compareTo(x) == 0) {
                // root = x
                result = true;

            } else if (root.compareTo(x) > 0) {
                // root > x
                result = isInTree(ls, x);

            } else if (root.compareTo(x) < 0) {
                // root < x
                result = isInTree(rs, x);

            }

            t.assemble(root, ls, rs);
        }

        return result;
    }

    /**
     * Removes and returns the smallest (left-most) label in {@code t}.
     *
     * @param <T>
     *            type of {@code BinaryTree} labels
     * @param t
     *            the {@code BinaryTree} from which to remove the label
     * @return the smallest label in the given {@code BinaryTree}
     * @updates t
     * @requires IS_BST(t) and |t| > 0
     * @ensures <pre>
     * IS_BST(t)  and  removeSmallest = [the smallest label in #t]  and
     *  labels(t) = labels(#t) \ {removeSmallest}
     * </pre>
     */
    public static <T> T removeSmallest(BinaryTree<T> t) {

        BinaryTree<T> ls = t.newInstance();
        BinaryTree<T> rs = t.newInstance();

        T root = t.disassemble(ls, rs);
        T result = root;

        if (ls.size() > 0) {
            // there is still more to go through
            result = removeSmallest(ls);

        } else {
            // there is no more to go through and this is the smallest
            ls.clear();
        }

        if (!root.equals(result)) {
            t.assemble(root, ls, rs);
        }

        return result;
    }

    /**
     * Inserts {@code x} in {@code t}.
     *
     * @param <T>
     *            type of {@code BinaryTree} labels
     * @param t
     *            the {@code BinaryTree} to be searched
     * @param x
     *            the label to be inserted
     * @aliases reference {@code x}
     * @updates t
     * @requires IS_BST(t) and x is not in labels(t)
     * @ensures IS_BST(t) and labels(t) = labels(#t) union {x}
     */
    private static <T extends Comparable<T>> void insertInTree(BinaryTree<T> t,
            T x) {
        assert t != null : "Violation of: t is not null";
        assert x != null : "Violation of: x is not null";

        /*
         * Recursively disassemble the tree, then when reconstructing place x
         * where it belongs by using the comparator on type T.
         */

        BinaryTree<T> ls = t.newInstance();
        BinaryTree<T> rs = t.newInstance();

        /*
         * If the tree has elements in it, find correct location to place x,
         * otherwise just assemble a tree with x as its root.
         */
        if (!t.equals(new BinaryTree1<>())) {

            T root = t.disassemble(ls, rs);

            /*
             * If x is less than the root, we call insertInTree on the left
             * tree, otherwise we call insertInTree on the right tree.
             */
            if (x.compareTo(root) < 0) {
                insertInTree(ls, x);
            } else {
                insertInTree(rs, x);
            }

            t.assemble(root, ls, rs);

        } else {
            t.assemble(x, ls, rs);
        }

    }

    /**
     * Finds label {@code x} in {@code t}, removes it from {@code t}, and
     * returns it.
     *
     * @param <T>
     *            type of {@code BinaryTree} labels
     * @param t
     *            the {@code BinaryTree} from which to remove label {@code x}
     * @param x
     *            the label to be removed
     * @return the removed label
     * @updates t
     * @requires IS_BST(t) and x is in labels(t)
     * @ensures <pre>
     * IS_BST(t)  and  removeFromTree = x  and
     *  labels(t) = labels(#t) \ {x}
     * </pre>
     */
    private static <T extends Comparable<T>> T removeFromTree(BinaryTree<T> t,
            T x) {
        assert t != null : "Violation of: t is not null";
        assert x != null : "Violation of: x is not null";
        assert t.size() > 0 : "Violation of: x is in labels(t)";

        /*
         * Tree is assumed to be non-empty (per slides).
         */

        BinaryTree<T> ls = t.newInstance();
        BinaryTree<T> rs = t.newInstance();

        T root = t.disassemble(ls, rs);

        /*
         * If x is the root: If the right subtree is empty then make the left
         * subtree the new value of the tree. If the right subtree is not empty
         * then replace the root with the smallest label in the right tree.
         */
        /*
         * If x is not the root: If x is less than the root call removeFromTree
         * on the left tree. If x is greater than the root call removeFromTree
         * on the right tree.
         */
        if (x.equals(root)) {
            if (rs.equals(new BinaryTree1<>())) {
                /*
                 * Per slides: "If the right subtree is empty, then make the
                 * left subtree the new value of t."
                 */
                t = ls;
            } else {
                t.assemble(removeSmallest(rs), ls, rs);
            }

        } else {
            /*
             * If x is less than the root, we call removeFromTree on the left
             * tree, otherwise we call removeFromTree on the right tree.
             */
            if (x.compareTo(root) < 0) {
                removeFromTree(ls, x);
            } else {
                removeFromTree(rs, x);
            }

            t.assemble(root, ls, rs);
        }

        return root;
    }

}
