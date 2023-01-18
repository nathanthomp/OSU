import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.stack.Stack;

/**
 * JUnit test fixture for {@code Stack<String>}'s constructor and kernel
 * methods.
 *
 * @author Put your name here
 *
 */
public abstract class StackTest {

    /**
     * Invokes the appropriate {@code Stack} constructor for the implementation
     * under test and returns the result.
     *
     * @return the new stack
     * @ensures constructorTest = <>
     */
    protected abstract Stack<String> constructorTest();

    /**
     * Invokes the appropriate {@code Stack} constructor for the reference
     * implementation and returns the result.
     *
     * @return the new stack
     * @ensures constructorRef = <>
     */
    protected abstract Stack<String> constructorRef();

    /**
     *
     * Creates and returns a {@code Stack<String>} of the implementation under
     * test type with the given entries.
     *
     * @param args
     *            the entries for the stack
     * @return the constructed stack
     * @ensures createFromArgsTest = [entries in args]
     */
    private Stack<String> createFromArgsTest(String... args) {
        Stack<String> stack = this.constructorTest();
        for (String s : args) {
            stack.push(s);
        }
        stack.flip();
        return stack;
    }

    /**
     *
     * Creates and returns a {@code Stack<String>} of the reference
     * implementation type with the given entries.
     *
     * @param args
     *            the entries for the stack
     * @return the constructed stack
     * @ensures createFromArgsRef = [entries in args]
     */
    private Stack<String> createFromArgsRef(String... args) {
        Stack<String> stack = this.constructorRef();
        for (String s : args) {
            stack.push(s);
        }
        stack.flip();
        return stack;
    }

    @Test
    public final void testNoArgumentConstructor() {
        /*
         * Set up variables and call method under test
         */
        Stack<String> m = this.constructorTest();
        Stack<String> mExpected = this.constructorRef();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
    }

    @Test
    public final void testPushEmpty() {
        /*
         * Set up variables and call method under test
         */
        Stack<String> m = this.createFromArgsTest();
        Stack<String> mExpected = this.createFromArgsRef("one");
        m.push("one");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
    }

    @Test
    public final void testPushNonEmpty() {
        /*
         * Set up variables and call method under test
         */
        Stack<String> m = this.createFromArgsTest("one");
        Stack<String> mExpected = this.createFromArgsRef("one", "two");
        m.push("two");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
    }

    @Test
    public final void testPopNonEmpty() {
        /*
         * Set up variables and call method under test
         */
        Stack<String> m = this.createFromArgsTest("one", "two");
        Stack<String> mExpected = this.createFromArgsRef("one");
        String n = m.pop();
        final String nExpected = "two";
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(nExpected, n);
        assertEquals(mExpected, m);
    }

    @Test
    public final void testLengthEmpty() {
        /*
         * Set up variables and call method under test
         */
        Stack<String> m = this.createFromArgsTest();
        Stack<String> mExpected = this.createFromArgsRef();
        final int nExpected = 0;
        int n = m.length();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(nExpected, n);
        assertEquals(mExpected, m);
    }

    @Test
    public final void testLengthNonEmpty() {
        /*
         * Set up variables and call method under test
         */
        Stack<String> m = this.createFromArgsTest("one", "two");
        Stack<String> mExpected = this.createFromArgsRef("one", "two");
        final int nExpected = 2;
        int n = m.length();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(nExpected, n);
        assertEquals(mExpected, m);
    }

}
