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
public final class HeapSort {

    /**
     * Default constructor--private to prevent instantiation.
     */
    private HeapSort() {
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

        // Create the tree
        //   2
        // 1   3
        BinaryTree<Integer> t = new BinaryTree1<>();
        BinaryTree<Integer> ls = new BinaryTree1<>();
        ls.assemble(1, new BinaryTree1<Integer>(), new BinaryTree1<Integer>());
        BinaryTree<Integer> rs = new BinaryTree1<>();
        rs.assemble(3, new BinaryTree1<Integer>(), new BinaryTree1<Integer>());
        t.assemble(2, ls, rs);
        out.println(satisfiesHeapOrdering(t));

        out.close();
    }

    /**
     * Checks if the given {@code BinaryTree<Integer>} satisfies the heap
     * ordering property according to the <= relation.
     *
     * @param t
     *            the binary tree
     * @return true if the given tree satisfies the heap ordering property;
     *         false otherwise
     * @ensures <pre>
     * satisfiesHeapOrdering = [t satisfies the heap ordering property]
     * </pre>
     */
    private static boolean satisfiesHeapOrdering(BinaryTree<Integer> t) {
        boolean result = false;

        Integer root;
        BinaryTree<Integer> ls = t.newInstance();
        BinaryTree<Integer> rs = t.newInstance();

        BinaryTree<Integer> test = t.newInstance();

        if (!t.equals(test)) {
            root = t.disassemble(ls, rs);

            if (ls.root() > root && rs.root() > root) {
                result = true;
            } else {
                result = satisfiesHeapOrdering(t);
            }
        }

        return result;
    }

    /**
     * Checks if the given {@code BinaryTree<T>} is complete.
     *
     * @param <T>
     *            type of {@code BinaryTree} entries
     * @param t
     *            the {@code BinaryTree}
     * @return true if the given tree is complete; false otherwise
     * @ensures isComplete = [t is complete]
     */
    private static <T> boolean isComplete(BinaryTree<T> t) {
        return true;
    }

}
