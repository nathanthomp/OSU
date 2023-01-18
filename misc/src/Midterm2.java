import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Blank program (clear of Checkstyle and FindBugs warnings).
 *
 * @author N. Thompson
 */
public final class Midterm2 {

    /**
     * Default constructor--private to prevent instantiation.
     */
    private Midterm2() {
        // no code needed here
    }

    /**
     * Reports the digit at position pos in NaturalNumber n.
     *
     * @param n
     * @param pos
     * @return digitAt [pos]
     */
    public static int digitAt(NaturalNumber n, int pos) {
        int digit = n.divideBy10();
        if (pos != 0) {
            digit = digitAt(n, pos - 1);
        }
        return digit;
    }

    /**
     *
     * @param n
     * @return true is n is even, otherwise false
     */
    public static boolean isEven(NaturalNumber n) {
        boolean result = false;
        if (!(n.divideBy10() % 2 != 0)) {
            result = true;
        }
        return result;
    }

    /**
     *
     * @param nums
     * @return if decreasing
     */
    public static boolean isDecreasing(NaturalNumber[] nums) {
        boolean result = false;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i].compareTo(nums[i - 1]) < 0) {
                result = true;
            }
        }
        return result;
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments; unused here
     */
    public static void main(String[] args) {
        SimpleWriter out = new SimpleWriter1L();
        NaturalNumber n = new NaturalNumber2(1234);

        NaturalNumber[] nums = new NaturalNumber[4];
        nums[0] = new NaturalNumber2(4);
        nums[1] = new NaturalNumber2(3);
        nums[2] = new NaturalNumber2(2);
        nums[3] = new NaturalNumber2(1);

        out.println(digitAt(n, 0));
//        out.println(isEven(n));

//        out.println("n1=" + n1);
//        out.println("n2=" + n2);
//        out.println("n3=" + n3);
//        out.println(isDecreasing(nums));

        out.close();
    }

}
