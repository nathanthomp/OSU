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
public final class RecursiveBinary {

    /**
     * Default constructor--private to prevent instantiation.
     */
    private RecursiveBinary() {
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
        int root = 1;

        // Create the tree
        //   1
        // 2   3
        BinaryTree<Integer> t = new BinaryTree1<>();
        BinaryTree<Integer> ls = new BinaryTree1<>();
        ls.assemble(2, new BinaryTree1<Integer>(), new BinaryTree1<Integer>());
        BinaryTree<Integer> rs = new BinaryTree1<>();
        rs.assemble(3, new BinaryTree1<Integer>(), new BinaryTree1<Integer>());
        t.assemble(root, ls, rs);

        out.println(copy(t));
        out.print(t);

        out.close();
    }

    /*
     * Recursive size method.
     */

    /**
     * Returns the size of the given {@code BinaryTree<T>}.
     *
     * @param <T>
     *            the type of the {@code BinaryTree} node labels
     * @param t
     *            the {@code BinaryTree} whose size to return
     * @return the size of the given {@code BinaryTree}
     * @ensures size = |t|
     */
    public static <T> int size1(BinaryTree<T> t) {
        int result = 0;
        T root;
        BinaryTree<T> ls = t.newInstance();
        BinaryTree<T> rs = t.newInstance();

        BinaryTree<T> test = t.newInstance();

        if (!t.equals(test)) {
            root = t.disassemble(ls, rs);
            result++;

            result += size1(ls);
            result += size1(rs);

            t.assemble(root, ls, rs);
        }

        return result;
    }

    /*
     * Iterative size method.
     */

    /**
     * Returns the size of the given {@code BinaryTree<T>}.
     *
     * @param <T>
     *            the type of the {@code BinaryTree} node labels
     * @param t
     *            the {@code BinaryTree} whose size to return
     * @return the size of the given {@code BinaryTree}
     * @ensures size = |t|
     */
    public static <T> int size2(BinaryTree<T> t) {
        int result = 0;
        BinaryTree<T> tc = t.newInstance();
        tc.transferFrom(t);
        BinaryTree<T> ls = t.newInstance();
        BinaryTree<T> rs = t.newInstance();

        // if t is an empty tree, return 0
        while (!tc.toString().equals("empty_tree")) {
            T root = tc.disassemble(ls, rs);
            result++;
            if (!ls.toString().equals("empty_tree")) {
                result++;
            }
            if (!rs.toString().equals("empty_tree")) {
                result++;
            }
        }

        return result;
    }

    /*
     * Recursive treeToString method.
     */

    /**
     * Returns the {@code String} prefix representation of the given
     * {@code BinaryTree<T>}.
     *
     * @param <T>
     *            the type of the {@code BinaryTree} node labels
     * @param t
     *            the {@code BinaryTree} to convert to a {@code String}
     * @return the prefix representation of {@code t}
     * @ensures treeToString = [the String prefix representation of t]
     */
    public static <T> String treeToString(BinaryTree<T> t) {
        String result = "";
        T root;
        BinaryTree<T> ls = t.newInstance();
        BinaryTree<T> rs = t.newInstance();

        BinaryTree<T> test = t.newInstance();

        if (!t.equals(test)) {
            root = t.disassemble(ls, rs);
            result += root + "(";

            result += treeToString(ls);
            result += treeToString(rs);

            result += ")";

            t.assemble(root, ls, rs);

        } else {
            result += "()";
        }

        return result;
    }

    /**
     * Returns a copy of the the given {@code BinaryTree}.
     *
     * @param t
     *            the {@code BinaryTree} to copy
     * @return a copy of the given {@code BinaryTree}
     * @ensures copy = t
     */
    public static BinaryTree<Integer> copy(BinaryTree<Integer> t) {
        BinaryTree<Integer> result = new BinaryTree1<>();
        BinaryTree<Integer> test = new BinaryTree1<>();

        int root;
        BinaryTree<Integer> ls = t.newInstance();
        BinaryTree<Integer> rs = t.newInstance();

        if (!t.equals(test)) {
            root = t.disassemble(ls, rs);

            result.assemble(root, copy(ls), copy(rs));

            result.assemble(root, copy(ls), copy(rs));
            t.assemble(root, copy(ls), copy(rs));

        }
        return result;
    }

}
