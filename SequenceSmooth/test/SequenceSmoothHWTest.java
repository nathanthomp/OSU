import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.sequence.Sequence;
import components.sequence.Sequence1L;

/**
 * Sample JUnit test fixture for SequenceSmooth.
 *
 * @author Put your name here
 *
 */
public final class SequenceSmoothHWTest {

    /**
     * Constructs and returns a sequence of the integers provided as arguments.
     *
     * @param args
     *            0 or more integer arguments
     * @return the sequence of the given arguments
     * @ensures createFromArgs= [the sequence of integers in args]
     */
    private Sequence<Integer> createFromArgs(Integer... args) {
        Sequence<Integer> s = new Sequence1L<Integer>();
        for (Integer x : args) {
            s.add(s.length(), x);
        }
        return s;
    }

    /**
     * Test smooth with s1 = <2, 4, 6> and s2 = <-5, 12>.
     */
    @Test
    public void test1() {
        /*
         * Set up variables and call method under test
         */
        Sequence<Integer> seq1 = this.createFromArgs(2, 4, 6);
        Sequence<Integer> expectedSeq1 = this.createFromArgs(2, 4, 6);
        Sequence<Integer> seq2 = this.createFromArgs(-5, 12);
        Sequence<Integer> result = SequenceSmoothHW.smooth(seq1, seq2);
        Sequence<Integer> expectedSeq2 = this.createFromArgs(3, 5);

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(expectedSeq1, seq1);
        assertEquals(expectedSeq2, result);
    }

    /**
     * Test smooth with s1 = <7> and s2 = <13, 17, 11>.
     */
    @Test
    public void test2() {
        /*
         * Set up variables and call method under test
         */
        Sequence<Integer> seq1 = this.createFromArgs(7);
        Sequence<Integer> expectedSeq1 = this.createFromArgs(7);
        Sequence<Integer> seq2 = this.createFromArgs(13, 17, 11);
        Sequence<Integer> expectedSeq2 = this.createFromArgs();
        Sequence<Integer> result = SequenceSmoothHW.smooth(seq1, seq2);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(expectedSeq1, seq1);
        assertEquals(expectedSeq2, result);
    }

    /**
     * Test smooth with s1 = <7, 21> and s2 = <1, 5, 9>.
     */
    @Test
    public void test3() {
        /*
         * Set up variables and call method under test
         */
        Sequence<Integer> seq1 = this.createFromArgs(7, 21);
        Sequence<Integer> expectedSeq1 = this.createFromArgs(7, 21);
        Sequence<Integer> seq2 = this.createFromArgs(1, 5, 9);
        Sequence<Integer> expectedSeq2 = this.createFromArgs(14);
        Sequence<Integer> result = SequenceSmoothHW.smooth(seq1, seq2);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(expectedSeq1, seq1);
        assertEquals(expectedSeq2, result);
    }

    /**
     * Test smooth with s1 = <7, 21, 2, 10> and s2 = <11, 6, 5>.
     */
    @Test
    public void test4() {
        /*
         * Set up variables and call method under test
         */
        Sequence<Integer> seq1 = this.createFromArgs(7, 21, 2, 10);
        Sequence<Integer> expectedSeq1 = this.createFromArgs(7, 21, 2, 10);
        Sequence<Integer> seq2 = this.createFromArgs(11, 6, 5);
        Sequence<Integer> expectedSeq2 = this.createFromArgs(14, 11, 6);
        Sequence<Integer> result = SequenceSmoothHW.smooth(seq1, seq2);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(expectedSeq1, seq1);
        assertEquals(expectedSeq2, result);
    }

    /**
     * Test smooth with s1 = <4, 6, 9, 6, 2> and s2 = <1, 2, 3>.
     */
    @Test
    public void test5() {
        /*
         * Set up variables and call method under test
         */
        Sequence<Integer> seq1 = this.createFromArgs(4, 6, 9, 6, 2);
        Sequence<Integer> expectedSeq1 = this.createFromArgs(4, 6, 9, 6, 2);
        Sequence<Integer> seq2 = this.createFromArgs(1, 2, 3);
        Sequence<Integer> expectedSeq2 = this.createFromArgs(5, 7, 7, 4);
        Sequence<Integer> result = SequenceSmoothHW.smooth(seq1, seq2);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(expectedSeq1, seq1);
        assertEquals(expectedSeq2, result);
    }

}
