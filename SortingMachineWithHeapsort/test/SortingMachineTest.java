import static org.junit.Assert.assertEquals;

import java.util.Comparator;

import org.junit.Test;

import components.sortingmachine.SortingMachine;

/**
 * JUnit test fixture for {@code SortingMachine<String>}'s constructor and
 * kernel methods.
 *
 * @author Nathan Thompson
 * @author Luke Serraglio
 *
 */
public abstract class SortingMachineTest {

    /**
     * Invokes the appropriate {@code SortingMachine} constructor for the
     * implementation under test and returns the result.
     *
     * @param order
     *            the {@code Comparator} defining the order for {@code String}
     * @return the new {@code SortingMachine}
     * @requires IS_TOTAL_PREORDER([relation computed by order.compare method])
     * @ensures constructorTest = (true, order, {})
     */
    protected abstract SortingMachine<String> constructorTest(
            Comparator<String> order);

    /**
     * Invokes the appropriate {@code SortingMachine} constructor for the
     * reference implementation and returns the result.
     *
     * @param order
     *            the {@code Comparator} defining the order for {@code String}
     * @return the new {@code SortingMachine}
     * @requires IS_TOTAL_PREORDER([relation computed by order.compare method])
     * @ensures constructorRef = (true, order, {})
     */
    protected abstract SortingMachine<String> constructorRef(
            Comparator<String> order);

    /**
     *
     * Creates and returns a {@code SortingMachine<String>} of the
     * implementation under test type with the given entries and mode.
     *
     * @param order
     *            the {@code Comparator} defining the order for {@code String}
     * @param insertionMode
     *            flag indicating the machine mode
     * @param args
     *            the entries for the {@code SortingMachine}
     * @return the constructed {@code SortingMachine}
     * @requires IS_TOTAL_PREORDER([relation computed by order.compare method])
     * @ensures <pre>
     * createFromArgsTest = (insertionMode, order, [multiset of entries in args])
     * </pre>
     */
    private SortingMachine<String> createFromArgsTest(Comparator<String> order,
            boolean insertionMode, String... args) {
        SortingMachine<String> sm = this.constructorTest(order);
        for (int i = 0; i < args.length; i++) {
            sm.add(args[i]);
        }
        if (!insertionMode) {
            sm.changeToExtractionMode();
        }
        return sm;
    }

    /**
     *
     * Creates and returns a {@code SortingMachine<String>} of the reference
     * implementation type with the given entries and mode.
     *
     * @param order
     *            the {@code Comparator} defining the order for {@code String}
     * @param insertionMode
     *            flag indicating the machine mode
     * @param args
     *            the entries for the {@code SortingMachine}
     * @return the constructed {@code SortingMachine}
     * @requires IS_TOTAL_PREORDER([relation computed by order.compare method])
     * @ensures <pre>
     * createFromArgsRef = (insertionMode, order, [multiset of entries in args])
     * </pre>
     */
    private SortingMachine<String> createFromArgsRef(Comparator<String> order,
            boolean insertionMode, String... args) {
        SortingMachine<String> sm = this.constructorRef(order);
        for (int i = 0; i < args.length; i++) {
            sm.add(args[i]);
        }
        if (!insertionMode) {
            sm.changeToExtractionMode();
        }
        return sm;
    }

    /**
     * Comparator<String> implementation to be used in all test cases. Compare
     * {@code String}s in lexicographic order.
     */
    private static class StringLT implements Comparator<String> {

        @Override
        public int compare(String s1, String s2) {
            return s1.compareToIgnoreCase(s2);
        }

    }

    /**
     * Comparator instance to be used in all test cases.
     */
    private static final StringLT ORDER = new StringLT();

    /*
     * Sample test cases.
     */

    @Test
    public final void testConstructor() {
        SortingMachine<String> m = this.constructorTest(ORDER);
        SortingMachine<String> mExpected = this.constructorRef(ORDER);
        assertEquals(mExpected, m);
    }

    @Test
    public final void testAddEmpty() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true);
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "green");
        m.add("green");
        assertEquals(mExpected, m);
    }

    // TODO - add test cases for add, changeToExtractionMode, removeFirst,
    // isInInsertionMode, order, and size

    @Test
    public final void testAddSizeOne() {
        /*
         * Set up variables and call method under test
         */
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true,
                "green");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "green", "red");
        /*
         * Assert that values of variables match expectations
         */
        m.add("red");
        assertEquals(mExpected, m);
    }

    @Test
    public final void testAddSizeTwo() {
        /*
         * Set up variables and call method under test
         */
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "green",
                "red");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "green", "red", "orange");
        /*
         * Assert that values of variables match expectations
         */
        m.add("orange");

        assertEquals(mExpected, m);
    }

    @Test
    public final void testAddSizeSeven() {
        /*
         * Set up variables and call method under test
         */
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "Have",
                "you", "heard", "of", "darth plagueis", "the wise");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "Have", "you", "heard", "of", "darth plagueis", "the wise",
                "?");
        /*
         * Assert that values of variables match expectations
         */
        m.add("?");

        assertEquals(mExpected, m);
    }

    @Test
    public final void testChangeToExtractionModeSizeOne() {
        /*
         * Set up variables and call method under test
         */
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "beef");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false,
                "beef");

        m.changeToExtractionMode();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
    }

    @Test
    public final void testChangeToExtractionModeSizeSeven() {
        /*
         * Set up variables and call method under test
         */
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "Have",
                "you", "heard", "of", "darth plagueis", "the wise");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false,
                "Have", "you", "heard", "of", "darth plagueis", "the wise");

        m.changeToExtractionMode();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
    }

    @Test
    public final void testRemoveFirstEight() {
        /*
         * Set up variables and call method under test
         */
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false, "Have",
                "you", "heard", "of", "darth plagueis", "the wise", "?");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false,
                "Have", "you", "heard", "of", "darth plagueis", "the wise");
        String removedExpected = "?";

        /*
         * Assert that values of variables match expectations
         */
        String removed = m.removeFirst();

        assertEquals(removedExpected, m);
        assertEquals(mExpected, m);

    }

    @Test
    public final void testIsInInsertionModeFalse() {
        /*
         * Set up variables and call method under test
         */
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false, "Have",
                "you", "heard", "of", "darth plagueis", "the wise");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false,
                "Have", "you", "heard", "of", "darth plagueis", "the wise");

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(m.isInInsertionMode(), false);
        assertEquals(mExpected, m);
    }

    @Test
    public final void testIsInInsertionModeTrue() {
        /*
         * Set up variables and call method under test
         */
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "Have",
                "you", "heard", "of", "darth plagueis", "the wise");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "Have", "you", "heard", "of", "darth plagueis", "the wise");

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(m.isInInsertionMode(), true);
        assertEquals(mExpected, m);
    }

    @Test
    public final void testOrder() {
        /*
         * Set up variables and call method under test
         */
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "Have",
                "you", "heard", "of", "darth plagueis", "the wise", "?");

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(ORDER, m.order());
    }

    @Test
    public final void testSizeOne() {
        /*
         * Set up variables and call method under test
         */
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false,
                "Have");

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(1, m.size());
    }

    @Test
    public final void testSizeFour() {
        /*
         * Set up variables and call method under test
         */
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false, "Have",
                "you", "heard", "of");

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(4, m.size());
    }

    @Test
    public final void testSizeSeven() {
        /*
         * Set up variables and call method under test
         */
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false, "Have",
                "you", "heard", "of", "darth plagueis", "the wise", "?");

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(7, m.size());
    }

}
