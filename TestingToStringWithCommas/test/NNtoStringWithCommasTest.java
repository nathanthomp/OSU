import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;

/**
 *
 * @author nathan thompson
 *
 */
public class NNtoStringWithCommasTest {

    /**
     * Calls the method under test.
     *
     * @param n
     *            the number to pass to the method under test
     * @return the {@code String} returned by the method under test
     * @ensures <pre>
     * redirectToMethodUnderTest = [String returned by the method under test]
     * </pre>
     */
    private static String redirectToMethodUnderTest(NaturalNumber n) {
        return NNtoStringWithCommas5.toStringWithCommas(n);
    }

    /**
     * ToStringWithCommas boundry test of 0.
     *
     */
    @Test
    public void testToStringWithCommas0() {
        /*
         * Set up variables and call method under test
         */
        NaturalNumber n = new NaturalNumber2();
        String s = redirectToMethodUnderTest(n);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals("0", s);
        assertEquals(new NaturalNumber2(0), n);
    }

    /**
     * ToStringWithCommas routine test of 12.
     *
     */
    @Test
    public void testToStringWithCommas12() {
        /*
         * Set up variables and call method under test
         */
        NaturalNumber n = new NaturalNumber2(12);
        String s = redirectToMethodUnderTest(n);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals("12", s);
        assertEquals(new NaturalNumber2(12), n);
    }

    /**
     * ToStringWithCommas challenging test of 123.
     *
     */
    @Test
    public void testToStringWithCommas123() {
        /*
         * Set up variables and call method under test
         */
        NaturalNumber n = new NaturalNumber2(123);
        String s = redirectToMethodUnderTest(n);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals("123", s);
        assertEquals(new NaturalNumber2(123), n);
    }

    /**
     * ToStringWithCommas routine test of 12345.
     *
     */
    @Test
    public void testToStringWithCommas12345() {
        /*
         * Set up variables and call method under test
         */
        NaturalNumber n = new NaturalNumber2(12345);
        String s = redirectToMethodUnderTest(n);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals("12,345", s);
        assertEquals(new NaturalNumber2(12345), n);
    }

    /**
     * ToStringWithCommas routine test of 12345.
     *
     */
    @Test
    public void testToStringWithCommas123456789() {
        /*
         * Set up variables and call method under test
         */
        NaturalNumber n = new NaturalNumber2(123456789);
        String s = redirectToMethodUnderTest(n);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals("123,456,789", s);
        assertEquals(new NaturalNumber2(123456789), n);
    }

    /**
     * ToStringWithCommas routine test of 999.
     *
     */
    @Test
    public void testToStringWithCommas999() {
        /*
         * Set up variables and call method under test
         */
        NaturalNumber n = new NaturalNumber2(999);
        String s = redirectToMethodUnderTest(n);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals("999", s);
        assertEquals(new NaturalNumber2(999), n);
    }

    /**
     * ToStringWithCommas challenging test of 000.
     *
     */
    @Test
    public void testToStringWithCommas000() {
        /*
         * Set up variables and call method under test
         */
        NaturalNumber n = new NaturalNumber2(000);
        String s = redirectToMethodUnderTest(n);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals("0", s);
        assertEquals(new NaturalNumber2(000), n);
    }

    /**
     * ToStringWithCommas routine test of 9.
     *
     */
    @Test
    public void testToStringWithCommas9() {
        /*
         * Set up variables and call method under test
         */
        NaturalNumber n = new NaturalNumber2(9);
        String s = redirectToMethodUnderTest(n);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals("9", s);
        assertEquals(new NaturalNumber2(9), n);
    }

    /**
     * ToStringWithCommas routine test of 1000.
     *
     */
    @Test
    public void testToStringWithCommas1000() {
        /*
         * Set up variables and call method under test
         */
        NaturalNumber n = new NaturalNumber2(1000);
        String s = redirectToMethodUnderTest(n);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals("1,000", s);
        assertEquals(new NaturalNumber2(1000), n);
    }

    /**
     * ToStringWithCommas routine test of 100000.
     *
     */
    @Test
    public void testToStringWithCommas100000() {
        /*
         * Set up variables and call method under test
         */
        NaturalNumber n = new NaturalNumber2(100000);
        String s = redirectToMethodUnderTest(n);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals("100,000", s);
        assertEquals(new NaturalNumber2(100000), n);
    }

    /**
     * ToStringWithCommas routine test of 10000.
     *
     */
    @Test
    public void testToStringWithCommas10000() {
        /*
         * Set up variables and call method under test
         */
        NaturalNumber n = new NaturalNumber2(10000);
        String s = redirectToMethodUnderTest(n);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals("10,000", s);
        assertEquals(new NaturalNumber2(10000), n);
    }

    /**
     * ToStringWithCommas routine test of 1.
     *
     */
    @Test
    public void testToStringWithCommas1() {
        /*
         * Set up variables and call method under test
         */
        NaturalNumber n = new NaturalNumber2(1);
        String s = redirectToMethodUnderTest(n);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals("1", s);
        assertEquals(new NaturalNumber2(1), n);
    }

    /**
     * ToStringWithCommas challenging test of 5000.
     *
     */
    @Test
    public void testToStringWithCommas5000Alias() {
        /*
         * Set up variables and call method under test
         */
        NaturalNumber n = new NaturalNumber2(5000);
        NaturalNumber n1 = n;
        String s = redirectToMethodUnderTest(n1);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals("5,000", s);
        assertEquals(new NaturalNumber2(5000), n1);
    }

    /**
     * ToStringWithCommas challenging test of 2147483647.
     *
     */
    @Test
    public void testToStringWithCommas2147483647() {
        /*
         * Set up variables and call method under test
         */
        NaturalNumber n = new NaturalNumber2(Integer.MAX_VALUE);
        String s = redirectToMethodUnderTest(n);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals("2,147,483,647", s);
        assertEquals(new NaturalNumber2(2147483647), n);
    }

    /**
     * ToStringWithCommas challenging test of 10.
     *
     */
    @Test
    public void testToStringWithCommas10() {
        /*
         * Set up variables and call method under test
         */
        NaturalNumber n = new NaturalNumber2(NaturalNumber.RADIX);
        String s = redirectToMethodUnderTest(n);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals("10", s);
        assertEquals(new NaturalNumber2(10), n);
    }

}
