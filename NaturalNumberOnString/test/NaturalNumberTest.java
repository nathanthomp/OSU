import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber1L;

/**
 * JUnit test fixture for {@code NaturalNumber}'s constructors and kernel
 * methods.
 *
 * @author Nathan Thompson
 * @author Luke Serraglio
 *
 */
public abstract class NaturalNumberTest {

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * implementation under test and returns the result.
     *
     * @return the new number
     * @ensures constructorTest = 0
     */
    protected abstract NaturalNumber constructorTest();

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * implementation under test and returns the result.
     *
     * @param i
     *            {@code int} to initialize from
     * @return the new number
     * @requires i >= 0
     * @ensures constructorTest = i
     */
    protected abstract NaturalNumber constructorTest(int i);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * implementation under test and returns the result.
     *
     * @param s
     *            {@code String} to initialize from
     * @return the new number
     * @requires there exists n: NATURAL (s = TO_STRING(n))
     * @ensures s = TO_STRING(constructorTest)
     */
    protected abstract NaturalNumber constructorTest(String s);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * implementation under test and returns the result.
     *
     * @param n
     *            {@code NaturalNumber} to initialize from
     * @return the new number
     * @ensures constructorTest = n
     */
    protected abstract NaturalNumber constructorTest(NaturalNumber n);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * reference implementation and returns the result.
     *
     * @return the new number
     * @ensures constructorRef = 0
     */
    protected abstract NaturalNumber constructorRef();

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * reference implementation and returns the result.
     *
     * @param i
     *            {@code int} to initialize from
     * @return the new number
     * @requires i >= 0
     * @ensures constructorRef = i
     */
    protected abstract NaturalNumber constructorRef(int i);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * reference implementation and returns the result.
     *
     * @param s
     *            {@code String} to initialize from
     * @return the new number
     * @requires there exists n: NATURAL (s = TO_STRING(n))
     * @ensures s = TO_STRING(constructorRef)
     */
    protected abstract NaturalNumber constructorRef(String s);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * reference implementation and returns the result.
     *
     * @param n
     *            {@code NaturalNumber} to initialize from
     * @return the new number
     * @ensures constructorRef = n
     */
    protected abstract NaturalNumber constructorRef(NaturalNumber n);

    // TODO - add test cases for multiplyBy10, divideBy10, isZero

    /*
     * Test cases for constructors
     */

    @Test
    public final void testNoArgumentConstructor() {
        /*
         * Set up variables and call method under test
         */
        NaturalNumber q = this.constructorTest();
        NaturalNumber qExpected = this.constructorRef();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(qExpected, q);
    }

    @Test
    public final void testIntConstructor() {
        /*
         * Set up variables and call method under test
         */
        int i = 1;
        NaturalNumber q = this.constructorTest(i);
        NaturalNumber qExpected = this.constructorRef(i);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(qExpected, q);
    }

    @Test
    public final void testStringConstructor() {
        /*
         * Set up variables and call method under test
         */
        String s = "1";
        NaturalNumber q = this.constructorTest(s);
        NaturalNumber qExpected = this.constructorRef(s);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(qExpected, q);
    }

    @Test
    public final void testNaturalNumberConstructor() {
        /*
         * Set up variables and call method under test
         */
        NaturalNumber n = new NaturalNumber1L(1);
        NaturalNumber q = this.constructorTest(n);
        NaturalNumber qExpected = this.constructorRef(n);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(qExpected, q);
    }

    /*
     * Test cases for multiplyBy10
     */
    @Test
    public final void testMultiplyBy10Empty() {
        /*
         * Set up variables and call method under test
         */
        NaturalNumber n = this.constructorTest();
        n.multiplyBy10(8);
        NaturalNumber nExpected = this.constructorTest(8);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(nExpected, n);
    }

    @Test
    public final void testMultiplyBy10NotEmpty() {
        /*
         * Set up variables and call method under test
         */
        NaturalNumber n = this.constructorTest(867530);
        n.multiplyBy10(9);
        NaturalNumber nExpected = this.constructorTest(8675309);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(nExpected, n);
    }

    /*
     * Test cases for divideBy10
     */

    @Test
    public final void testDivideBy10OneDigit() {
        /*
         * Set up variables and call method under test
         */
        NaturalNumber n = this.constructorTest(6);
        int rem = n.divideBy10(), remExpected = 6;
        NaturalNumber nExpected = this.constructorTest();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(nExpected, n);
        assertEquals(remExpected, rem);
    }

    @Test
    public final void testMultiplyBy10MoreThan10() {
        /*
         * Set up variables and call method under test
         */
        NaturalNumber n = this.constructorTest(42069);
        int rem = n.divideBy10(), remExpected = 9;
        NaturalNumber nExpected = this.constructorTest(4206);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(nExpected, n);
        assertEquals(remExpected, rem);
    }

    /*
     * Test cases for isZero
     */
    @Test
    public final void testIsZeroFalse() {
        /*
         * Set up variables and call method under test
         */
        NaturalNumber n = this.constructorTest(0);
        NaturalNumber nExpected = this.constructorTest(0);
        boolean result = n.isZero(), expectedResult = true;

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(nExpected, n);
        assertEquals(expectedResult, result);
    }

    @Test
    public final void testIsZeroTrue() {
        /*
         * Set up variables and call method under test
         */
        NaturalNumber n = this.constructorTest(666);
        NaturalNumber nExpected = this.constructorTest(666);
        boolean result = n.isZero(), expectedResult = false;

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(nExpected, n);
        assertEquals(expectedResult, result);
    }
}
