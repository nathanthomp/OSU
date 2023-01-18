import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.map.Map;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;

/**
 * JUnit test fixture for {@code Map<String, String>}'s constructor and kernel
 * methods.
 *
 * @author Put your name here
 *
 */
public abstract class MapTest {

    /**
     * Invokes the appropriate {@code Map} constructor for the implementation
     * under test and returns the result.
     *
     * @return the new map
     * @ensures constructorTest = {}
     */
    protected abstract Map<String, String> constructorTest();

    /**
     * Invokes the appropriate {@code Map} constructor for the reference
     * implementation and returns the result.
     *
     * @return the new map
     * @ensures constructorRef = {}
     */
    protected abstract Map<String, String> constructorRef();

    /**
     *
     * Creates and returns a {@code Map<String, String>} of the implementation
     * under test type with the given entries.
     *
     * @param args
     *            the (key, value) pairs for the map
     * @return the constructed map
     * @requires <pre>
     * [args.length is even]  and
     * [the 'key' entries in args are unique]
     * </pre>
     * @ensures createFromArgsTest = [pairs in args]
     */
    private Map<String, String> createFromArgsTest(String... args) {
        assert args.length % 2 == 0 : "Violation of: args.length is even";
        Map<String, String> map = this.constructorTest();
        for (int i = 0; i < args.length; i += 2) {
            assert !map.hasKey(args[i]) : ""
                    + "Violation of: the 'key' entries in args are unique";
            map.add(args[i], args[i + 1]);
        }
        return map;
    }

    /**
     *
     * Creates and returns a {@code Map<String, String>} of the reference
     * implementation type with the given entries.
     *
     * @param args
     *            the (key, value) pairs for the map
     * @return the constructed map
     * @requires <pre>
     * [args.length is even]  and
     * [the 'key' entries in args are unique]
     * </pre>
     * @ensures createFromArgsRef = [pairs in args]
     */
    private Map<String, String> createFromArgsRef(String... args) {
        assert args.length % 2 == 0 : "Violation of: args.length is even";
        Map<String, String> map = this.constructorRef();
        for (int i = 0; i < args.length; i += 2) {
            assert !map.hasKey(args[i]) : ""
                    + "Violation of: the 'key' entries in args are unique";
            map.add(args[i], args[i + 1]);
        }
        return map;
    }

    // TODO - add test cases for constructor, add, remove, removeAny, value,
    // hasKey, and size

    @Test
    public final void testNoArgumentConstructor() {
        /*
         * Set up variables and call method under test
         */
        Map<String, String> m = this.constructorTest();
        Map<String, String> mExpected = this.constructorRef();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
    }

    @Test
    public final void testConstructorWithSinglePair() {
        /*
         * Set up variables and call method under test
         */
        Map<String, String> m = this.createFromArgsTest("69",
                "vnweilvnawiueon");
        Map<String, String> mExpected = this.createFromArgsRef("69",
                "vnweilvnawiueon");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
    }

    @Test
    public final void testConstructorWithTwoPairs() {
        /*
         * Set up variables and call method under test
         */
        Map<String, String> m = this.createFromArgsTest("weaf", "vrbe", "hello",
                "bacon");
        Map<String, String> mExpected = this.createFromArgsRef("weaf", "vrbe",
                "hello", "bacon");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
    }

    @Test
    public final void testConstructorWithManyPairs() {
        //open text file with 10,000 strings (from random.org)
        SimpleReader in = new SimpleReader1L("data/random.txt");

        // I could probably get this to be more generalized, but whatever

        final int amountOfStrings = 10000;
        String[] arguments = new String[amountOfStrings];
        for (int i = 0; i < amountOfStrings; i++) {
            arguments[i] = in.nextLine();
        }
        in.close();
        /*
         * Set up variables and call method under test
         */
        Map<String, String> m = this.createFromArgsTest(arguments);
        Map<String, String> mExpected = this.createFromArgsRef(arguments);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
    }

    @Test
    public final void testSizeEmpty() {
        /*
         * Set up variables and call method under test
         */
        Map<String, String> m = this.createFromArgsTest();
        int size = m.size();
        int sizeExpected = 0;
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(size, sizeExpected);
    }

    @Test
    public final void testSizeOne() {
        /*
         * Set up variables and call method under test
         */
        Map<String, String> m = this.createFromArgsTest("a", "b");
        int size = m.size();
        int sizeExpected = 1;
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(size, sizeExpected);
    }

    @Test
    public final void testSizeTwo() {
        /*
         * Set up variables and call method under test
         */
        Map<String, String> m = this.createFromArgsTest("a", "b", "c", "d");
        int size = m.size();
        int sizeExpected = 2;
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(size, sizeExpected);
    }

    @Test
    public final void testSize5000() {
        //open text file with 10,000 strings (from random.org)
        SimpleReader in = new SimpleReader1L("data/random.txt");

        // I could probably get this to be more generalized, but whatever

        final int amountOfStrings = 10000;
        String[] arguments = new String[amountOfStrings];
        for (int i = 0; i < amountOfStrings; i++) {
            arguments[i] = in.nextLine();
        }
        in.close();
        /*
         * Set up variables and call method under test
         */
        Map<String, String> m = this.createFromArgsTest(arguments);
        int size = m.size();
        int sizeExpected = amountOfStrings / 2;
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(size, sizeExpected);
    }

    @Test
    public final void testHasKeyBigMapTrue() {
        //open text file with 10,000 strings (from random.org)
        SimpleReader in = new SimpleReader1L("data/random.txt");

        // I could probably get this to be more generalized, but whatever

        final int amountOfStrings = 10000;
        String[] arguments = new String[amountOfStrings];
        for (int i = 0; i < amountOfStrings; i++) {
            arguments[i] = in.nextLine();
        }
        in.close();
        /*
         * Set up variables and call method under test
         */
        Map<String, String> m = this.createFromArgsTest(arguments);
        boolean has = m.hasKey("rn4CE6mfiNs8WUQ9P3iv");
        boolean hasExpected = true;
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(has, hasExpected);
    }

    @Test
    public final void testHasKeyBigMapFalse() {
        //open text file with 10,000 strings (from random.org)
        SimpleReader in = new SimpleReader1L("data/random.txt");

        // I could probably get this to be more generalized, but whatever

        final int amountOfStrings = 10000;
        String[] arguments = new String[amountOfStrings];
        for (int i = 0; i < amountOfStrings; i++) {
            arguments[i] = in.nextLine();
        }
        in.close();
        /*
         * Set up variables and call method under test
         */
        Map<String, String> m = this.createFromArgsTest(arguments);
        boolean has = m.hasKey("pee is stored in the balls");
        boolean hasExpected = false;
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(has, hasExpected);
    }

    @Test
    public final void testHasKeyFalseBecauseValue() {
        //open text file with 10,000 strings (from random.org)
        SimpleReader in = new SimpleReader1L("data/random.txt");

        // I could probably get this to be more generalized, but whatever

        final int amountOfStrings = 10000;
        String[] arguments = new String[amountOfStrings];
        for (int i = 0; i < amountOfStrings; i++) {
            arguments[i] = in.nextLine();
        }
        in.close();
        /*
         * Set up variables and call method under test
         */
        Map<String, String> m = this.createFromArgsTest(arguments);
        boolean has = m.hasKey("X3027JCiabnBcawU1ymP");
        boolean hasExpected = false;
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(has, hasExpected);
    }

    @Test
    public final void testHasKeyFalseBecauseSubstring() {
        //open text file with 10,000 strings (from random.org)
        SimpleReader in = new SimpleReader1L("data/random.txt");

        // I could probably get this to be more generalized, but whatever

        final int amountOfStrings = 10000;
        String[] arguments = new String[amountOfStrings];
        for (int i = 0; i < amountOfStrings; i++) {
            arguments[i] = in.nextLine();
        }
        in.close();
        /*
         * Set up variables and call method under test
         */
        Map<String, String> m = this.createFromArgsTest(arguments);
        boolean has = m.hasKey("E6mfiNs8WUQ");
        boolean hasExpected = false;
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(has, hasExpected);
    }

    @Test
    public final void testHasKeyEmptyMapFalse() {
        /*
         * Set up variables and call method under test
         */
        Map<String, String> m = this.createFromArgsTest();
        boolean has = m.hasKey("");
        boolean hasExpected = false;
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(has, hasExpected);
    }

    @Test
    public final void testHasKeySmallMapTrue() {
        /*
         * Set up variables and call method under test
         */
        Map<String, String> m = this.createFromArgsTest("never gonna",
                "give you up", "CSE 2231", "epic");
        boolean has = m.hasKey("never gonna");
        boolean hasExpected = true;
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(has, hasExpected);
    }

    @Test
    public final void testHasKeySmallMapFalse() {
        /*
         * Set up variables and call method under test
         */
        Map<String, String> m = this.createFromArgsTest("never gonna",
                "give you up", "CSE 2231", "epic");
        boolean has = m.hasKey("epic");
        boolean hasExpected = false;
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(has, hasExpected);
    }

    @Test
    public final void testValueSmallMap() {
        /*
         * Set up variables and call method under test
         */
        Map<String, String> m = this.createFromArgsTest("never gonna",
                "give you up", "CSE 2231", "epic");
        String value = m.value("never gonna");
        String valueExpected = "give you up";
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(value, valueExpected);
    }

    @Test
    public final void testValueLargeMap() {
        //open text file with 10,000 strings (from random.org)
        SimpleReader in = new SimpleReader1L("data/random.txt");

        // I could probably get this to be more generalized, but whatever

        final int amountOfStrings = 10000;
        String[] arguments = new String[amountOfStrings];
        for (int i = 0; i < amountOfStrings; i++) {
            arguments[i] = in.nextLine();
        }
        in.close();

        /*
         * Set up variables and call method under test
         */
        Map<String, String> m = this.createFromArgsTest(arguments);
        String value = m.value("rH1gr7Rec8ddVrisWNYL");
        String valueExpected = "X0dgXhU52fTjJ1eNK0JH";
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(value, valueExpected);
    }

    @Test
    public final void testAddToEmptyMap() {
        /*
         * Set up variables and call method under test
         */
        Map<String, String> m = this.createFromArgsTest();
        Map<String, String> mExpected = this.createFromArgsTest("impostor",
                "sus");
        m.add("impostor", "sus");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(m, mExpected);
    }

    @Test
    public final void testAddToSmallMap() {
        /*
         * Set up variables and call method under test
         */
        Map<String, String> m = this.createFromArgsTest("funny number", "69",
                "favorite sport", "sportsball");
        Map<String, String> mExpected = this.createFromArgsTest("funny number",
                "69", "favorite sport", "sportsball", "impostor", "sus");
        m.add("impostor", "sus");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(m, mExpected);
    }

    @Test
    public final void testAddToLargeMap() {
        //open text file with 10,000 strings (from random.org)
        SimpleReader in = new SimpleReader1L("data/random.txt");

        // I could probably get this to be more generalized, but whatever

        final int amountOfStrings = 10000;
        String[] arguments = new String[amountOfStrings];
        String[] arguments2 = new String[amountOfStrings + 2];
        for (int i = 0; i < amountOfStrings; i++) {
            arguments[i] = in.nextLine();
            arguments2[i] = arguments[i];
        }
        //add extra pair
        arguments2[amountOfStrings] = "impostor";
        arguments2[amountOfStrings + 1] = "sus";
        in.close();

        /*
         * Set up variables and call method under test
         */
        Map<String, String> m = this.createFromArgsTest(arguments);
        Map<String, String> mExpected = this.createFromArgsTest(arguments2);
        m.add("impostor", "sus");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(m, mExpected);
    }

    @Test
    public final void testAddEmptyKey() {
        /*
         * Set up variables and call method under test
         */
        Map<String, String> m = this.createFromArgsTest("amogus", "sus");
        Map<String, String> mExpected = this.createFromArgsTest("amogus", "sus",
                "", "sus");
        m.add("", "sus");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(m, mExpected);
    }

}
