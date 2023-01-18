import static org.junit.Assert.assertEquals;

import org.junit.Test;

import software2.IntegerAverage;

/**
 * Sample JUnit test fixture for SequenceSmooth.
 *
 * @author Put your name here
 *
 */
public final class IntegerAverageTest {

    /**
     * Test average.
     */
    @Test
    public void test1() {
        /*
         * Set up variables and call method under test
         */
        int j = 5;
        int k = 8;
        int expectedResult = 6;
        int result = IntegerAverage.average(j, k);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(expectedResult, result);
    }

    /**
     * Test average.
     */
    @Test
    public void test2() {
        /*
         * Set up variables and call method under test
         */
        int j = -5;
        int k = -8;
        int expectedResult = -6;
        int result = IntegerAverage.average(j, k);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(expectedResult, result);
    }

    /**
     * Test average.
     */
    @Test
    public void test3() {
        /*
         * Set up variables and call method under test
         */
        int j = 11;
        int k = -4;
        int expectedResult = 3;
        int result = IntegerAverage.average(j, k);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(expectedResult, result);
    }

    /**
     * Test average.
     */
    @Test
    public void test4() {
        /*
         * Set up variables and call method under test
         */
        int j = -3;
        int k = 2;
        int expectedResult = 0;
        int result = IntegerAverage.average(j, k);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(expectedResult, result);
    }

    /**
     * Test average.
     */
    @Test
    public void test5() {
        /*
         * Set up variables and call method under test
         */
        int j = 3;
        int k = 5;
        int expectedResult = 4;
        int result = IntegerAverage.average(j, k);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(expectedResult, result);
    }

    /**
     * Test average.
     */
    @Test
    public void test6() {
        /*
         * Set up variables and call method under test
         */
        int j = -3;
        int k = -5;
        int expectedResult = -4;
        int result = IntegerAverage.average(j, k);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(expectedResult, result);
    }

    /**
     * Test average.
     */
    @Test
    public void test7() {
        /*
         * Set up variables and call method under test
         */
        int j = Integer.MAX_VALUE;
        int k = Integer.MAX_VALUE - 1;
        int expectedResult = Integer.MAX_VALUE - 1;
        int result = IntegerAverage.average(j, k);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(expectedResult, result);
    }

    /**
     * Test average.
     */
    @Test
    public void test8() {
        /*
         * Set up variables and call method under test
         */
        int j = Integer.MIN_VALUE;
        int k = Integer.MIN_VALUE + 1;
        int expectedResult = Integer.MIN_VALUE + 1;
        int result = IntegerAverage.average(j, k);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(expectedResult, result);
    }

    /**
     * Test average.
     */
    @Test
    public void test9() {
        /*
         * Set up variables and call method under test
         */
        int j = Integer.MIN_VALUE;
        int k = Integer.MIN_VALUE;
        int expectedResult = Integer.MIN_VALUE;
        int result = IntegerAverage.average(j, k);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(expectedResult, result);
    }

    /**
     * Test average.
     */
    @Test
    public void test10() {
        /*
         * Set up variables and call method under test
         */
        int j = Integer.MAX_VALUE;
        int k = Integer.MAX_VALUE;
        int expectedResult = Integer.MAX_VALUE;
        int result = IntegerAverage.average(j, k);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(expectedResult, result);
    }

}
