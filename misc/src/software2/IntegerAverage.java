package software2;

/**
 * Blank program (clear of Checkstyle and FindBugs warnings).
 *
 * @author N. Thompson
 */
public final class IntegerAverage {

    /**
     * Default constructor--private to prevent instantiation.
     */
    private IntegerAverage() {
        // no code needed here
    }

    /**
     * Returns the integer average of two given {@code int}s.
     *
     * @param j
     *            the first of two integers to average
     * @param k
     *            the second of two integers to average
     * @return the integer average of j and k
     * @ensures average = (j+k)/2
     */
    public static int average(int j, int k) {

        int result = 0;

        if (j == Integer.MAX_VALUE && k == Integer.MAX_VALUE) {
            result = Integer.MAX_VALUE;
        } else {
            int newJ = j;
            if (!(j % 2 == 0 || k % 2 == 0)) {
                if (j > 0) {
                    newJ++;
                } else {
                    newJ--;
                }
            }
            int num1 = newJ / 2;
            int num2 = k / 2;
            result = num1 + num2;
        }
        return result;
    }

}
