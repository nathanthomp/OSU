import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.set.Set;
import components.set.Set1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;

/**
 * StringReassembly test class.
 *
 * @author Nathan Thompson
 */
public class StringReassemblyTest {

    /**
     * Global SimpleReader object for file input.
     */
    private SimpleReader cheer = new SimpleReader1L("data/cheer-8-2.txt");

    /**
     * Challenging test of linesFromInput. Expect that any line that is a
     * substring of another line is not in the returned set
     */
    @Test
    public void testLinesFromInputNoSubStringBucks() {
        assert this.cheer.isOpen() : "Violation of: input is open";
        Set<String> strSet = StringReassembly.linesFromInput(this.cheer);
        boolean stringIsInStrSet = strSet.contains("Bucks");
        assertEquals(false, stringIsInStrSet);
    }

    /**
     * Challenging test of linesFromInput. Expect that any line that is a
     * substring of another line is not in the returned set
     */
    @Test
    public void testLinesFromInputNoSubStringIchigan() {
        assert this.cheer.isOpen() : "Violation of: input is open";
        Set<String> strSet = StringReassembly.linesFromInput(this.cheer);
        boolean stringIsInStrSet = strSet.contains("ichigan~");
        assertEquals(false, stringIsInStrSet);
    }

    /**
     * Boundary test of linesFromInput. Expect just one line added
     */
    @Test
    public void testLinesFromInputAddToEmptySet() {
        assert this.cheer.isOpen() : "Violation of: input is open";
        Set<String> strSet = new Set1L<>();
        StringReassembly.addToSetAvoidingSubstrings(strSet, "string");
        boolean stringIsInStrSet = strSet.contains("string");
        assertEquals(true, stringIsInStrSet);
    }

    /**
     * Routine test of combination. Expect the string "123456"
     */
    @Test
    public void testCombination() {
        String str1 = "1234", str2 = "3456";
        int overlap = 2;
        String stringExpected = "123456";
        String stringReturned = StringReassembly.combination(str1, str2,
                overlap);
        assertEquals(stringExpected, stringReturned);
    }
}
