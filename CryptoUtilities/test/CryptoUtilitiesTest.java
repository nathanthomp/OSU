import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;

/**
 * Unit tests for CryptoUtilities.
 *
 * @author Nathan Thompson
 */
public class CryptoUtilitiesTest {

    /*
     * Tests of reduceToGCD
     */

    /**
     * Boundary test.
     */
    @Test
    public void testReduceToGCD_0_0() {
        NaturalNumber n = new NaturalNumber2(0);
        NaturalNumber nExpected = new NaturalNumber2(0);
        NaturalNumber m = new NaturalNumber2(0);
        NaturalNumber mExpected = new NaturalNumber2(0);
        CryptoUtilities.reduceToGCD(n, m);
        assertEquals(nExpected, n);
        assertEquals(mExpected, m);
    }

    /**
     * Routine test.
     */
    @Test
    public void testReduceToGCD_30_21() {
        NaturalNumber n = new NaturalNumber2(30);
        NaturalNumber nExpected = new NaturalNumber2(3);
        NaturalNumber m = new NaturalNumber2(21);
        NaturalNumber mExpected = new NaturalNumber2(0);
        CryptoUtilities.reduceToGCD(n, m);
        assertEquals(nExpected, n);
        assertEquals(mExpected, m);
    }

    /**
     * Challenging test.
     */
    @Test
    public void testReduceToGCD_10_1() {
        NaturalNumber n = new NaturalNumber2(10);
        NaturalNumber nExpected = new NaturalNumber2(1);
        NaturalNumber m = new NaturalNumber2(1);
        NaturalNumber mExpected = new NaturalNumber2(0);
        CryptoUtilities.reduceToGCD(n, m);
        assertEquals(nExpected, n);
        assertEquals(mExpected, m);
    }

    /*
     * Tests of isEven
     */

    /**
     * Boundary test.
     */
    @Test
    public void testIsEven_0() {
        NaturalNumber n = new NaturalNumber2(0);
        NaturalNumber nExpected = new NaturalNumber2(0);
        boolean result = CryptoUtilities.isEven(n);
        assertEquals(nExpected, n);
        assertEquals(true, result);
    }

    /**
     * Routine test.
     */
    @Test
    public void testIsEven_1() {
        NaturalNumber n = new NaturalNumber2(1);
        NaturalNumber nExpected = new NaturalNumber2(1);
        boolean result = CryptoUtilities.isEven(n);
        assertEquals(nExpected, n);
        assertEquals(false, result);
    }

    /**
     * Challenging test.
     */
    @Test
    public void testIsEven_1000() {
        NaturalNumber n = new NaturalNumber2(1000);
        NaturalNumber nExpected = new NaturalNumber2(1000);
        boolean result = CryptoUtilities.isEven(n);
        assertEquals(nExpected, n);
        assertEquals(true, result);
    }

    /*
     * Tests of powerMod
     */

    /**
     * Boundary test.
     */
    @Test
    public void testPowerMod_0_0_2() {
        NaturalNumber n = new NaturalNumber2(0);
        NaturalNumber nExpected = new NaturalNumber2(1);
        NaturalNumber p = new NaturalNumber2(0);
        NaturalNumber pExpected = new NaturalNumber2(0);
        NaturalNumber m = new NaturalNumber2(2);
        NaturalNumber mExpected = new NaturalNumber2(2);
        CryptoUtilities.powerMod(n, p, m);
        assertEquals(nExpected, n);
        assertEquals(pExpected, p);
        assertEquals(mExpected, m);
    }

    /**
     * Routine test.
     */
    @Test
    public void testPowerMod_17_18_19() {
        NaturalNumber n = new NaturalNumber2(17);
        NaturalNumber nExpected = new NaturalNumber2(1);
        NaturalNumber p = new NaturalNumber2(18);
        NaturalNumber pExpected = new NaturalNumber2(18);
        NaturalNumber m = new NaturalNumber2(19);
        NaturalNumber mExpected = new NaturalNumber2(19);
        CryptoUtilities.powerMod(n, p, m);
        assertEquals(nExpected, n);
        assertEquals(pExpected, p);
        assertEquals(mExpected, m);
    }

    /*
     * Tests of isWitnessToCompositeness
     */
    /**
     * Boundary test.
     */
    @Test
    public void testIsWitnessToCompositeness_2_4() {
        NaturalNumber w = new NaturalNumber2(2);
        NaturalNumber wExpected = new NaturalNumber2(2);
        NaturalNumber n = new NaturalNumber2(4);
        NaturalNumber nExpected = new NaturalNumber2(4);
        boolean result = CryptoUtilities.isWitnessToCompositeness(w, n);
        assertEquals(nExpected, n);
        assertEquals(wExpected, w);
        assertEquals(true, result);
    }

    /**
     * Routine test.
     */
    @Test
    public void testIsWitnessToCompositeness_9_18() {
        NaturalNumber w = new NaturalNumber2(9);
        NaturalNumber wExpected = new NaturalNumber2(9);
        NaturalNumber n = new NaturalNumber2(11);
        NaturalNumber nExpected = new NaturalNumber2(11);
        boolean result = CryptoUtilities.isWitnessToCompositeness(w, n);
        assertEquals(nExpected, n);
        assertEquals(wExpected, w);
        assertEquals(false, result);
    }

    /**
     * Challenging test.
     */
    @Test
    public void testIsWitnessToCompositeness_3_8() {
        NaturalNumber w = new NaturalNumber2(3);
        NaturalNumber wExpected = new NaturalNumber2(3);
        NaturalNumber n = new NaturalNumber2(8);
        NaturalNumber nExpected = new NaturalNumber2(8);
        boolean result = CryptoUtilities.isWitnessToCompositeness(w, n);
        assertEquals(nExpected, n);
        assertEquals(wExpected, w);
        assertEquals(true, result);
    }

    /*
     * Tests of isPrime2
     */

    /**
     * Boundary test.
     */
    @Test
    public void testIsPrime2_2() {
        NaturalNumber n = new NaturalNumber2(2);
        NaturalNumber nExpected = new NaturalNumber2(2);
        boolean result = CryptoUtilities.isPrime2(n);
        assertEquals(nExpected, n);
        assertEquals(true, result);
    }

    /**
     * Routine test.
     */
    @Test
    public void testIsPrime2_50() {
        NaturalNumber n = new NaturalNumber2(50);
        NaturalNumber nExpected = new NaturalNumber2(50);
        boolean result = CryptoUtilities.isPrime2(n);
        assertEquals(nExpected, n);
        assertEquals(false, result);
    }

    /**
     * Routine test.
     */
    @Test
    public void testIsPrime2_17() {
        NaturalNumber n = new NaturalNumber2(17);
        NaturalNumber nExpected = new NaturalNumber2(17);
        boolean result = CryptoUtilities.isPrime2(n);
        assertEquals(nExpected, n);
        assertEquals(true, result);
    }

    /*
     * Tests of generateNextLikelyPrime
     */
    /**
     * Boundary test.
     */
    @Test
    public void generateNextLikelyPrime_4() {
        NaturalNumber n = new NaturalNumber2(4);
        NaturalNumber nExpected = new NaturalNumber2(5);
        CryptoUtilities.generateNextLikelyPrime(n);
        assertEquals(nExpected, n);
    }

    /**
     * Routine test.
     */
    @Test
    public void generateNextLikelyPrime_68() {
        NaturalNumber n = new NaturalNumber2(68);
        NaturalNumber nExpected = new NaturalNumber2(71);
        CryptoUtilities.generateNextLikelyPrime(n);
        assertEquals(nExpected, n);
    }

}
