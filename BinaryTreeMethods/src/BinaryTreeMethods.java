import components.binarytree.BinaryTree;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Utility class with implementation of {@code BinaryTree} static, generic
 * methods height and isInTree.
 *
 * @author Put your name here
 *
 */
public final class BinaryTreeMethods {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private BinaryTreeMethods() {
    }

    /**
     * Returns the height of the given {@code BinaryTree<T>}.
     *
     * @param <T>
     *            the type of the {@code BinaryTree} node labels
     * @param t
     *            the {@code BinaryTree} whose height to return
     * @return the height of the given {@code BinaryTree}
     * @ensures height = ht(t)
     */
    public static <T> int height(BinaryTree<T> t) {
        assert t != null : "Violation of: t is not null";

        int height = 0;

        //check if the tree is not empty
        if (t.size() == 1) {
            //if it only has 1 node, set the height to 1 and don't find children
            height = 1;
        } else if (t.size() > 1) {
            BinaryTree<T> left = t.newInstance();
            BinaryTree<T> right = t.newInstance();

            //if the tree can be separated, the height should be incremented
            height = 1;

            //separate the tree into left and right
            T root = t.disassemble(left, right);

            //recursively call the height method
            int lHeight = height(left);
            int rHeight = height(right);

            //add the maximum height of each subtree to the current height
            if (rHeight > lHeight) {
                height += rHeight;
            } else {
                height += lHeight;
            }

            //repair the tree
            t.assemble(root, left, right);
        }
        return height;
    }

    /**
     * Returns true if the given {@code T} is in the given {@code BinaryTree<T>}
     * or false otherwise.
     *
     * @param <T>
     *            the type of the {@code BinaryTree} node labels
     * @param t
     *            the {@code BinaryTree} to search
     * @param x
     *            the {@code T} to search for
     * @return true if the given {@code T} is in the given {@code BinaryTree},
     *         false otherwise
     * @ensures isInTree = [true if x is in t, false otherwise]
     */
    public static <T> boolean isInTree(BinaryTree<T> t, T x) {
        assert t != null : "Violation of: t is not null";
        assert x != null : "Violation of: x is not null";

        boolean inRoot = false;
        //check if the value of x is the root value if the tree isn't empty
        if (t.size() > 0) {
            inRoot = t.root().equals(x);
        }

        //if not, check the subtrees for the root
        if (t.size() > 1 && !inRoot) {

            //instantiate the left and right subtrees
            BinaryTree<T> left = t.newInstance();
            BinaryTree<T> right = t.newInstance();

            //separate the tree into left and right
            T root = t.disassemble(left, right);

            //check each subtree to see if x was found
            inRoot = inRoot | isInTree(left, x) | isInTree(right, x);

            //repair the tree
            t.assemble(root, left, right);
        }
        return inRoot;
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();

        out.print("Input a tree (or just press Enter to terminate): ");
        String str = in.nextLine();
        while (str.length() > 0) {
            BinaryTree<String> t = BinaryTreeUtility.treeFromString(str);
            out.println("Tree = " + BinaryTreeUtility.treeToString(t));
            out.println("Height = " + height(t));
            out.print("  Input a label to search "
                    + "(or just press Enter to input a new tree): ");
            String label = in.nextLine();
            while (label.length() > 0) {
                if (isInTree(t, label)) {
                    out.println("    \"" + label + "\" is in the tree");
                } else {
                    out.println("    \"" + label + "\" is not in the tree");
                }
                out.print("  Input a label to search "
                        + "(or just press Enter to input a new tree): ");
                label = in.nextLine();
            }
            out.println();
            out.print("Input a tree (or just press Enter to terminate): ");
            str = in.nextLine();
        }

        in.close();
        out.close();
    }

}
