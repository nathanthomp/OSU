import java.util.Arrays;

import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Blank program (clear of Checkstyle and FindBugs warnings).
 *
 * @author N. Thompson
 */
public final class Review2 {

    /**
     * Default constructor--private to prevent instantiation.
     */
    private Review2() {
        // no code needed here
    }

    private static void onlyUppercase(SimpleWriter out, SimpleReader in) {
        out.println("Enter String:");
        String input = in.nextLine();
        for (int i = 0; i < input.length(); i++) {
            if (Character.isUpperCase(input.charAt(i))) {
                out.print(input.charAt(i) + " ");
            }
        }
    }

    private static void secondLetter(SimpleWriter out, SimpleReader in) {
        out.println("Enter String:");
        String input = in.nextLine();
        for (int i = 0; i < input.length(); i++) {
            if (i % 2 != 0) {
                out.print(input.charAt(i) + " ");
            }
        }
    }

    private static void vowelToUnderscore(SimpleWriter out, SimpleReader in) {
        out.println("Enter String:");
        String input = in.nextLine();
        char[] vowels = { 'a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U' };

        for (int i = 0; i < input.length(); i++) {
            char index = input.charAt(i);
            for (int j = 0; j < vowels.length; j++) {
                if (vowels[j] == index) {
                    input = input.replace(input.charAt(i), '_');
                }
            }
        }
        out.println(input);
    }

    private static void countVowels(SimpleWriter out, SimpleReader in) {
        out.println("Enter String:");
        String input = in.nextLine();
        int count = 0;
        char[] vowels = { 'a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U' };

        for (int i = 0; i < input.length(); i++) {
            char index = input.charAt(i);
            for (int j = 0; j < vowels.length; j++) {
                if (vowels[j] == index) {
                    count++;
                }
            }
        }
        out.println(count);
    }

    private static void positionOfVowels(SimpleWriter out, SimpleReader in) {
        out.println("Enter String:");
        String input = in.nextLine();
        char[] vowels = { 'a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U' };

        for (int i = 0; i < input.length(); i++) {
            char index = input.charAt(i);
            for (int j = 0; j < vowels.length; j++) {
                if (vowels[j] == index) {
                    out.print(i + " ");
                }
            }
        }
    }

    private static void printArrayMaxMin(SimpleWriter out) {
        int[] a = { 1, 2, 3, 4, 5, 4, 3, 2, 1, 0 };
        int max = 0, min = 0;

        for (int i = 0; i < a.length; i++) {
            if (a[i] > max) {
                max = a[i];
            }
            if (a[i] < min) {
                min = a[i];
            }
        }
        out.println("Max: " + max + " Min: " + min);
    }

    private static void isArrayOrdered(SimpleWriter out) {
//        int[] a = { 1, 2, 3, 4, 5, 4, 3, 2, 1, 0 };
        int[] a = { 1, 2, 3, 4, 5 };
        boolean isOrdered = false;
        int index = 0;

        for (int i = 0; i < a.length; i++) {
            if (a[i] > index) {
                isOrdered = true;
            } else {
                isOrdered = false;
            }
        }
        out.println(isOrdered);
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments; unused here
     */
    public static void main(String[] args) {
        SimpleWriter out = new SimpleWriter1L();
        SimpleReader in = new SimpleReader1L();

//        XMLTree xml = new XMLTree1("");
        int[] a = { 1, 2, 3, 4, 5, 4, 3, 2, 1, 0 };

        int i = 1;
        while (i < 5) {
            a[i] = a[9 - i];
            i++;
        }
        out.print(Arrays.toString(a));
    }

}
