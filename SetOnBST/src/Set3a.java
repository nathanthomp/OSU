import java.util.Iterator;

import components.binarytree.BinaryTree;
import components.binarytree.BinaryTree1;
import components.set.Set;
import components.set.SetSecondary;

/**
 * {@code Set} represented as a {@code BinaryTree} (maintained as a binary
 * search tree) of elements with implementations of primary methods.
 *
 * @param <T>
 *            type of {@code Set} elements
 * @mathdefinitions <pre>
 * IS_BST(
 *   tree: binary tree of T
 *  ): boolean satisfies
 *  [tree satisfies the binary search tree properties as described in the
 *   slides with the ordering reported by compareTo for T, including that
 *   it has no duplicate labels]
 * </pre>
 * @convention IS_BST($this.tree)
 * @correspondence this = labels($this.tree)
 *
 * @author Nathan Thompson
 * @author Luke Serraglio
 *
 */
public class Set3a<T extends Comparable<T>> extends SetSecondary<T> {

    /*
     * Private members --------------------------------------------------------
     */

    /**
     * Elements included in {@code this}.
     */
    private BinaryTree<T> tree;

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
    private static <T extends Comparable<T>> boolean isInTree(BinaryTree<T> t,
            T x) {
        assert t != null : "Violation of: t is not null";
        assert x != null : "Violation of: x is not null";

        boolean result = false;
        T root;
        BinaryTree<T> ls = t.newInstance();
        BinaryTree<T> rs = t.newInstance();

        if (!t.equals(new BinaryTree1<>())) {
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
    private static <T> T removeSmallest(BinaryTree<T> t) {
        assert t != null : "Violation of: t is not null";
        assert t.size() > 0 : "Violation of: |t| > 0";

        BinaryTree<T> ls = t.newInstance();
        BinaryTree<T> rs = t.newInstance();

        T root = t.disassemble(ls, rs);
        T result = root;

        if (ls.size() > 0) {
            // there is still more to go through
            result = removeSmallest(ls);

        } else {
            // there is no more to go through and this is the smallest
            t.transferFrom(rs);
        }

        if (!root.equals(result)) {
            t.assemble(root, ls, rs);
        }

        return result;
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
                t.transferFrom(ls);
            } else {
                root = removeSmallest(rs);
                t.assemble(root, ls, rs);
            }

        } else {
            /*
             * If x is less than the root, we call removeFromTree on the left
             * tree, otherwise we call removeFromTree on the right tree.
             */
            if (x.compareTo(root) <= 0) {
                removeFromTree(ls, x);
            } else {
                removeFromTree(rs, x);
            }

            t.assemble(root, ls, rs);
        }

        return x;
    }

    /**
     * Creator of initial representation.
     */
    private void createNewRep() {
        this.tree = new BinaryTree1<>();
    }

    /*
     * Constructors -----------------------------------------------------------
     */

    /**
     * No-argument constructor.
     */
    public Set3a() {
        this.createNewRep();
    }

    /*
     * Standard methods -------------------------------------------------------
     */

    @SuppressWarnings("unchecked")
    @Override
    public final Set<T> newInstance() {
        try {
            return this.getClass().getConstructor().newInstance();
        } catch (ReflectiveOperationException e) {
            throw new AssertionError(
                    "Cannot construct object of type " + this.getClass());
        }
    }

    @Override
    public final void clear() {
        this.createNewRep();
    }

    @Override
    public final void transferFrom(Set<T> source) {
        assert source != null : "Violation of: source is not null";
        assert source != this : "Violation of: source is not this";
        assert source instanceof Set3a<?> : ""
                + "Violation of: source is of dynamic type Set3<?>";
        /*
         * This cast cannot fail since the assert above would have stopped
         * execution in that case: source must be of dynamic type Set3a<?>, and
         * the ? must be T or the call would not have compiled.
         */
        Set3a<T> localSource = (Set3a<T>) source;
        this.tree = localSource.tree;
        localSource.createNewRep();
    }

    /*
     * Kernel methods ---------------------------------------------------------
     */

    @Override
    public final void add(T x) {
        assert x != null : "Violation of: x is not null";
        assert !this.contains(x) : "Violation of: x is not in this";

        /*
         * By calling private static method insertInTree, we do not violate
         * kernel purity because kernel purity states that a kernel method
         * cannot call another kernel public method.
         */
        insertInTree(this.tree, x);
    }

    @Override
    public final T remove(T x) {
        assert x != null : "Violation of: x is not null";
        assert this.contains(x) : "Violation of: x is in this";

        /*
         * By calling private static method removeFromTree, we do not violate
         * kernel purity because kernel purity states that a kernel method
         * cannot call another kernel public method.
         */
        return removeFromTree(this.tree, x);
    }

    @Override
    public final T removeAny() {
        assert this.size() > 0 : "Violation of: this /= empty_set";

        /*
         * By calling private static method removeSmallest, we do not violate
         * kernel purity because kernel purity states that a kernel method
         * cannot call another kernel public method.
         */
        return removeSmallest(this.tree);
    }

    @Override
    public final boolean contains(T x) {
        assert x != null : "Violation of: x is not null";

        /*
         * By calling private static method isInTree, we do not violate kernel
         * purity because kernel purity states that a kernel method cannot call
         * another kernel public method.
         */
        return isInTree(this.tree, x);
    }

    @Override
    public final int size() {
        return this.tree.size();
    }

    @Override
    public final Iterator<T> iterator() {
        return this.tree.iterator();
    }

}
