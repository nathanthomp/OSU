import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.utilities.FormatChecker;

/**
 * Blank program (clear of Checkstyle and FindBugs warnings).
 *
 * @author N. Thompson
 */
public final class Review1 {

    /**
     * Default constructor--private to prevent instantiation.
     */
    private Review1() {
        // no code needed here
    }

// QUESTION 1
    /**
     * Repeatedly asks the user for a positive integer until the user enters
     * one. Returns the positive integer.
     *
     * @param in
     * @param out
     * @return a positive integer entered by the user
     */
    private static int getPositiveInteger(SimpleReader in, SimpleWriter out) {
        int posInt = 0;

        out.print("Enter a pos int (if you want): ");
        String input = in.nextLine();
        while (!FormatChecker.canParseInt(input)) {
            out.print("Try again: ");
            input = in.nextLine();
        }
        posInt = Integer.parseInt(input);

        return posInt;
    }

// QUESTION 3
    /**
     *
     * @return sum 2 - 100 (inclusive)
     */
    private static int sumAll() {
        int sum = 0;
        for (int count = 2; count <= 100; count++) {
            sum = sum + count;
        }

        return sum;
    }

    /**
     *
     * @return sum 2 - 100 (inclusive)
     */
    private static int sumAllSquares() {
        int sum = 0, square = 0;
        for (int count = 1; count <= 100; count++) {
            square = count * count;
            if (square <= 100) {
                sum = sum + square;
            }
        }

        return sum;
    }

    /**
     * sum 2^0 to 2^20 (inclusive)
     */
    private static void allTwos(SimpleWriter out) {

        for (int i = 0; i <= 20; i++) {
            out.print(Math.pow(2, i) + " ");
        }

    }

    /**
     *
     * @return sum
     */
    private static int sumAllOdd() {
        int sum = 0, a = 0, b = 4;

        for (int i = a; i < b; i++) {
            if (i % 2 != 0) {
                sum = sum + i;
            }
        }

        return sum;
    }

    /**
     *
     * @return sum
     */
    private static int sumAllOddPositions() {
        int sum = 0, n1 = 12345;

        String n2 = String.valueOf(n1);
        for (int i = 0; i < n2.length(); i++) {
            if (i % 2 != 0) {
                sum = sum + Integer.parseInt(String.valueOf(n2.charAt(i)));
            }
        }

        return sum;
    }

    /**
     *
     * @return sum
     */
    private static int sumAllOddPositionsReversed() {
        int sum = 0, n1 = 12345;

        String n2 = String.valueOf(n1);
        for (int i = n2.length(); i <= 0; i--) {
            if (i % 2 != 0) {
                sum = sum + Integer.parseInt(String.valueOf(n2.charAt(i)));
            }
        }

        return sum;
    }

// QUESTION 6

    /**
     * returning true if the arguments are all the same
     */
    boolean allTheSame(int x, int y, int z) {
        boolean result = false;

        if (x == y && y == z && z == x) {
            result = true;
        }

        return result;
    }

    /**
     * returning true if the arguments are all different
     */
    boolean allDifferent(int x, int y, int z) {
        boolean result = false;

        if (x != y && y != z && z != x) {
            result = true;
        }

        return result;
    }

    /**
     * returning true if the arguments are sorted with the smallest one coming
     * first
     */
    boolean sorted(int x, int y, int z) {
        boolean result = false;

        if (x < y && y < z) {
            result = true;
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
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();

//        out.println(getPositiveInteger(in, out));
//        out.println(sumAll());
//        out.println(sumAllSquares());

//        out.print(sumAllOdd());
//        out.print(sumAllOddPositions());
//        out.print(sumAllOddPositionsReversed());

        allTwos(out);

    }

}
