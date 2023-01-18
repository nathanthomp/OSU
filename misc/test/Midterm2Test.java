import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;

/**
 *
 * @author natha
 *
 */
public class Midterm2Test {

    /**
     * Test digitAt1234.
     */
    @Test
    public void testDigitAt1234() {
        /*
         * Set up variables and call method under test
         */
        NaturalNumber n = new NaturalNumber2(1234);
        int result = Midterm2.digitAt(n, 1);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(3, result);
    }

}
