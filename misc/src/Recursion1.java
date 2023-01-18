import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Blank program (clear of Checkstyle and FindBugs warnings).
 *
 * @author N. Thompson
 */
public final class Recursion1 {

    /**
     * Default constructor--private to prevent instantiation.
     */
    private Recursion1() {
        // no code needed here
    }

    /**
     * Returns the number of digits of {@code n}.
     *
     * @param n
     *            {@code NaturalNumber} whose digits to count
     * @return the number of digits of {@code n}
     * @ensures numberOfDigits = [number of digits of n]
     */
    private static int numberOfDigits(NaturalNumber n) {
        if (!n.isZero()) {
            n.divideBy10();
            return numberOfDigits(n) + 1;
        }
        return 0;
    }

    /**
     * Returns the sum of the digits of {@code n}.
     *
     * @param n
     *            {@code NaturalNumber} whose digits to add
     * @return the sum of the digits of {@code n}
     * @ensures sumOfDigits = [sum of the digits of n]
     */
    private static int sumOfDigits(NaturalNumber n) {
        if (n.isZero()) {
            return 0;
        } else {
            return n.divideBy10() + sumOfDigits(n);
        }
    }

    /**
     * Returns the sum of the digits of {@code n}.
     *
     * @param n
     *            {@code NaturalNumber} whose digits to add
     * @return the sum of the digits of {@code n}
     * @ensures sumOfDigits = [sum of the digits of n]
     */
    private static NaturalNumber sumOfNNDigits(NaturalNumber n) {
        final NaturalNumber ten = new NaturalNumber2(10);
        if (n.isZero()) {
            return new NaturalNumber2(0);
        } else {
            return new NaturalNumber2(
                    n.divideBy10() + sumOfNNDigits(n).toInt());
        }
    }

    /**
     * Divides {@code n} by 2.
     *
     * @param n
     *            {@code NaturalNumber} to be divided
     * @updates n
     * @ensures 2 * n <= #n < 2 * (n + 1)
     */
    private static void divideBy2(NaturalNumber n) {
        final int adjustment = 5;
        int remainder = n.divideBy10() / 2;
        NaturalNumber nCopy = new NaturalNumber2();
        nCopy.copyFrom(n);

        if (!n.isZero()) {
            if (nCopy.divideBy10() % 2 != 0) {
                remainder += adjustment;
            }
            divideBy2(n);
        }
        n.multiplyBy10(remainder);
    }

    /**
     * Checks whether a {@code String} is a palindrome.
     *
     * @param s
     *            {@code String} to be checked
     * @return true if {@code s} is a palindrome, false otherwise
     * @ensures isPalindrome = (s = rev(s))
     */
    private static boolean isPalindrome(String s) {
        if (s.length() == 1) {
            return false;
        } else if (s.charAt(0) == s.charAt(s.length() - 1)) {
            String smaller = s.substring(1, s.length() - 2);
            return isPalindrome(smaller);
        } else {
            return false;
        }
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments; unused here
     */
    public static void main(String[] args) {
        SimpleWriter out = new SimpleWriter1L();
        NaturalNumber n = new NaturalNumber2(33);
//        out.println(numberOfDigits(n));
//        out.println(sumOfDigits(n));
//        out.println(sumOfNNDigits(n));
//        divideBy2(n);
//        out.println(n);
        String s = "abcba";
        out.println(isPalindrome(s));

    }

}
