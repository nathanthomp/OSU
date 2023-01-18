import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.set.Set;

/**
 * JUnit test fixture for {@code Set<String>}'s constructor and kernel methods.
 *
 * @author Nathan Thompson
 * @author Luke Serraglio
 *
 */
public abstract class SetTest {

    /**
     * Invokes the appropriate {@code Set} constructor for the implementation
     * under test and returns the result.
     *
     * @return the new set
     * @ensures constructorTest = {}
     */
    protected abstract Set<String> constructorTest();

    /**
     * Invokes the appropriate {@code Set} constructor for the reference
     * implementation and returns the result.
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
    public final void constructorTestEmpty() {
        /*
         * Set up variables and call method under test
         */
        Set<String> s = this.constructorTest();
        Set<String> sExpected = this.constructorRef();

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(s, sExpected);
    }

    @Test
    public final void constructorTestNotEmpty() {
        /*
         * Set up variables and call method under test
         */
        Set<String> s = this.createFromArgsTest("when", "the", "impostor", "is",
                "sus");
        Set<String> sExpected = this.createFromArgsRef("when", "the",
                "impostor", "is", "sus");

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(s, sExpected);
    }

    @Test
    public final void constructorAddEmpty() {
        /*
         * Set up variables and call method under test
         */
        Set<String> s = this.constructorTest();
        Set<String> sExpected = this.createFromArgsRef("hello");

        s.add("hello");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(s, sExpected);
    }

    @Test
    public final void constructorAddThree() {
        /*
         * Set up variables and call method under test
         */
        Set<String> s = this.createFromArgsTest("eat", "hot chip", "lie");
        Set<String> sExpected = this.createFromArgsRef("eat", "hot chip", "and",
                "lie");

        s.add("and");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(s, sExpected);
    }

    @Test
    public final void constructorAddFour() {
        /*
         * Set up variables and call method under test
         */
        Set<String> s = this.createFromArgsTest("walls", "your", "in", "I");
        Set<String> sExpected = this.createFromArgsRef("I", "am", "in", "your",
                "walls");

        s.add("am");

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(s, sExpected);
    }

    @Test
    public final void constructorAddSeven() {
        /*
         * Set up variables and call method under test
         */
        Set<String> s = this.createFromArgsTest("plagueis", "Have", "wise",
                "you", "of", "?", "the", "heard");
        Set<String> sExpected = this.createFromArgsRef("Have", "you", "heard",
                "of", "darth", "plagueis", "the", "wise", "?");

        s.add("darth");

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(s, sExpected);
    }

    @Test
    public final void constructorRemoveOne() {
        /*
         * Set up variables and call method under test
         */
        Set<String> s = this.createFromArgsTest("kebab");
        Set<String> sExpected = this.createFromArgsRef();

        String removed = s.remove("kebab");
        String removedExpected = "kebab";

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(s, sExpected);
        assertEquals(removed, removedExpected);
    }

    @Test
    public final void constructorRemoveFour() {
        /*
         * Set up variables and call method under test
         */
        Set<String> s = this.createFromArgsTest("eat", "hot chip", "and",
                "lie");
        Set<String> sExpected = this.createFromArgsRef("eat", "hot chip",
                "and");

        String removed = s.remove("lie");
        String removedExpected = "lie";

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
        assertEquals(removedExpected, removed);
    }

    @Test
    public final void constructorRemoveSeven() {
        /*
         * Set up variables and call method under test
         */
        Set<String> s = this.createFromArgsTest("Have", "you", "heard", "of",
                "darth", "plagueis", "the wise?");
        Set<String> sExpected = this.createFromArgsRef("Have", "you", "heard",
                "darth", "plagueis", "the wise?");

        String removed = s.remove("of");
        String removedExpected = "of";

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
        assertEquals(removedExpected, removed);
    }

    @Test
    public final void constructorRemoveAnyOne() {
        /*
         * Set up variables and call method under test
         */
        Set<String> s = this.createFromArgsTest("beans");
        Set<String> sExpected = this.createFromArgsRef();

        String removed = s.removeAny();
        String removedExpected = "beans";

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
        assertEquals(removedExpected, removed);
    }

    @Test
    public final void constructorRemoveAnyFour() {
        /*
         * Set up variables and call method under test
         */
        Set<String> s = this.createFromArgsTest("eat", "hot chip", "and",
                "lie");
        final int sizeExpected = 3;

        s.removeAny();

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sizeExpected, s.size());
    }

    @Test
    public final void constructorRemoveAnySeven() {
        /*
         * Set up variables and call method under test
         */
        Set<String> s = this.createFromArgsTest("Have", "you", "heard", "darth",
                "plagueis", "the wise?");
        final int sizeExpected = 5;

        s.removeAny();

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sizeExpected, s.size());
    }

    @Test
    public final void constructorContainsSevenTrue() {
        /*
         * Set up variables and call method under test
         */
        Set<String> s = this.createFromArgsTest("Have", "you", "heard", "darth",
                "plagueis", "the wise?");
        Set<String> sExpected = this.createFromArgsRef("Have", "you", "heard",
                "darth", "plagueis", "the wise?");
        boolean containsWord = s.contains("heard");

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(true, containsWord);
        assertEquals(sExpected, s);
    }

    @Test
    public final void constructorContainsSevenFalse() {
        /*
         * Set up variables and call method under test
         */
        Set<String> s = this.createFromArgsTest("Have", "you", "heard", "of",
                "darth", "plagueis", "the wise?");
        Set<String> sExpected = this.createFromArgsRef("Have", "you", "heard",
                "of", "darth", "plagueis", "the wise?");

        boolean containsWord = s.contains("banana");

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(false, containsWord);
        assertEquals(sExpected, s);
    }

    @Test
    public final void constructorContainsFourTrue() {
        /*
         * Set up variables and call method under test
         */
        Set<String> s = this.createFromArgsTest("Have", "you", "heard", "of");
        Set<String> sExpected = this.createFromArgsRef("Have", "you", "heard",
                "of");

        boolean containsWord = s.contains("of");

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(true, containsWord);
        assertEquals(sExpected, s);
    }

    @Test
    public final void constructorContainsFourFalse() {
        /*
         * Set up variables and call method under test
         */
        Set<String> s = this.createFromArgsTest("Have", "you", "heard", "of");
        Set<String> sExpected = this.createFromArgsRef("Have", "you", "heard",
                "of");

        boolean containsWord = s.contains("amogus");

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(false, containsWord);
        assertEquals(sExpected, s);
    }

    @Test
    public final void constructorContainsOneTrue() {
        /*
         * Set up variables and call method under test
         */
        Set<String> s = this.createFromArgsTest("Have");
        Set<String> sExpected = this.createFromArgsRef("Have");

        boolean containsWord = s.contains("Have");

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(true, containsWord);
        assertEquals(sExpected, s);
    }

    @Test
    public final void constructorContainsOneFalse() {
        /*
         * Set up variables and call method under test
         */
        Set<String> s = this.createFromArgsTest("Have");
        Set<String> sExpected = this.createFromArgsRef("Have");

        boolean containsWord = s.contains("bananfeafawbeifawoieubaowirvb");

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(false, containsWord);
        assertEquals(sExpected, s);
    }

    @Test
    public final void constructorSizeOne() {
        /*
         * Set up variables and call method under test
         */
        Set<String> s = this.createFromArgsTest("the");
        Set<String> sExpected = this.createFromArgsRef("the");
        final int sizeExpected = 1;

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sizeExpected, s.size());
        assertEquals(sExpected, s);
    }

    @Test
    public final void constructorSizeFour() {
        /*
         * Set up variables and call method under test
         */
        Set<String> s = this.createFromArgsTest("Have", "you", "heard", "of");
        Set<String> sExpected = this.createFromArgsRef("Have", "you", "heard",
                "of");
        final int sizeExpected = 4;

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sizeExpected, s.size());
        assertEquals(sExpected, s);
    }
}
