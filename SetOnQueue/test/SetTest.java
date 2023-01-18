import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.set.Set;

/**
 * JUnit test fixture for {@code Set<String>}'s constructor and kernel methods.
 *
 * @author Put your name here
 *
 */
public abstract class SetTest {

    /**
     * Invokes the appropriate {@code Set} constructor and returns the result.
     *
     * @return the new set
     * @ensures constructorTest = {}
     */
    protected abstract Set<String> constructorTest();

    /**
     * Invokes the appropriate {@code Set} constructor and returns the result.
     *
     * @return the new set
     * @ensures constructorRef = {}
     */
    protected abstract Set<String> constructorRef();

    /**
     * Creates and returns a {@code Set<String>} of the implementation under
     * test type with the given entries.
     *
     * @param args
     *            the entries for the set
     * @return the constructed set
     * @requires [every entry in args is unique]
     * @ensures createFromArgsTest = [entries in args]
     */
    private Set<String> createFromArgsTest(String... args) {
        Set<String> set = this.constructorTest();
        for (String s : args) {
            assert !set.contains(
                    s) : "Violation of: every entry in args is unique";
            set.add(s);
        }
        return set;
    }

    /**
     * Creates and returns a {@code Set<String>} of the reference implementation
     * type with the given entries.
     *
     * @param args
     *            the entries for the set
     * @return the constructed set
     * @requires [every entry in args is unique]
     * @ensures createFromArgsRef = [entries in args]
     */
    private Set<String> createFromArgsRef(String... args) {
        Set<String> set = this.constructorRef();
        for (String s : args) {
            assert !set.contains(
                    s) : "Violation of: every entry in args is unique";
            set.add(s);
        }
        return set;
    }

    @Test
    public final void testNoArgumentConstructor() {
        /*
         * Set up variables and call method under test
         */
        Set<String> s = this.constructorTest();
        Set<String> sExpected = this.constructorRef();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
    }

    @Test
    public final void testAddNonEmpty() {
        /*
         * Set up variables and call method under test
         */
        Set<String> s = this.createFromArgsTest("red", "blue");
        /*
         * Call method under test
         */
        s.add("green");
        boolean result = s.contains("green");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(true, result);
    }

    @Test
    public final void testAddEmpty() {
        /*
         * Set up variables and call method under test
         */
        Set<String> s = this.createFromArgsTest();
        Set<String> sExpected = this.createFromArgsRef("");
        /*
         * Call method under test
         */
        s.add("green");
        boolean result = s.contains("green");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(true, result);
    }

    @Test
    public final void testRemoveLeavingEmpty() {
        /*
         * Set up variables and call method under test
         */
        Set<String> s = this.createFromArgsTest("green");
        Set<String> sExpected = this.createFromArgsRef();
        /*
         * Call method under test
         */
        String x = s.remove("green");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals("green", x);
        assertEquals(sExpected, s);
    }

//    @Test
//    public final void testRemoveAny() {
//        /*
//         * Set up variables and call method under test
//         */
//        Set<String> s = this.createFromArgsTest("green", "red");
//        Set<String> sExpected = s
//        /*
//         * Call method under test
//         */
//        String x = s.removeAny();
//        /*
//         * Assert that values of variables match expectations
//         */
//        assertEquals("green", x);
//        assertEquals(sExpected, s);
//    }

    @Test
    public final void testContainsGreenFalse() {
        /*
         * Set up variables and call method under test
         */
        Set<String> s = this.createFromArgsTest("red", "blue");
        /*
         * Call method under test
         */
        s.add("green");
        boolean result = s.contains("green");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(false, result);
    }

    @Test
    public final void testContainsRedTrue() {
        /*
         * Set up variables and call method under test
         */
        Set<String> s = this.createFromArgsTest("red", "blue");
        /*
         * Call method under test
         */
        boolean result = s.contains("red");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(true, result);
    }

    @Test
    public final void testLengthNonEmpty() {
        /*
         * Set up variables and call method under test
         */
        Set<String> s = this.createFromArgsTest("green");
        Set<String> sExpected = this.createFromArgsRef("green");
        /*
         * Call method under test
         */
        int i = s.size();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(1, i);
        assertEquals(sExpected, s);
    }

    @Test
    public final void testLengthEmpty() {
        /*
         * Set up variables and call method under test
         */
        Set<String> s = this.createFromArgsTest();
        Set<String> sExpected = this.createFromArgsRef();
        /*
         * Call method under test
         */
        int i = s.size();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(0, i);
        assertEquals(sExpected, s);
    }

}
