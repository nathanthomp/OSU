import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.xmltree.XMLTree;
import components.xmltree.XMLTree2;

/**
 * Blank program (clear of Checkstyle and FindBugs warnings).
 *
 * @author N. Thompson
 */
public final class Review3 {

    /**
     * Default constructor--private to prevent instantiation.
     */
    private Review3() {
        // no code needed here
    }

    /**
     * Returns the product of the digits of {@code n}.
     *
     * @param n
     *            {@code NaturalNumber} whose digits to multiply
     * @return the product of the digits of {@code n}
     * @clears n
     * @ensures productOfDigits1 = [product of the digits of n]
     */
    private static NaturalNumber productOfDigits1(NaturalNumber n) {
        int digit = n.divideBy10();
        if (n.isZero()) {
            return new NaturalNumber2(1);
        } else {
            return new NaturalNumber2(digit * productOfDigits1(n).toInt());
        }
    }

    /**
     * Returns the product of the digits of {@code n}.
     *
     * @param n
     *            {@code NaturalNumber} whose digits to multiply
     * @return the product of the digits of {@code n}
     * @ensures productOfDigits2 = [product of the digits of n]
     */
    private static NaturalNumber productOfDigits2(NaturalNumber n) {
        NaturalNumber nCopy = new NaturalNumber2();
        nCopy.copyFrom(n);
        int digit = nCopy.divideBy10();
        if (nCopy.isZero()) {
            return new NaturalNumber2(1);
        } else {
            return new NaturalNumber2(digit * productOfDigits1(nCopy).toInt());
        }
    }

    /**
     * Reports the value of {@code n} as an {@code int}, when {@code n} is small
     * enough.
     *
     * @param n
     *            the given {@code NaturalNumber}
     * @return the value
     * @requires n <= Integer.MAX_VALUE
     * @ensures toInt = n
     */
    private static int toInt(NaturalNumber n) {
        int digit = n.divideBy10();
        NaturalNumber nCopy = new NaturalNumber2();
        nCopy.copyFrom(n);

        if (!n.isZero()) {

            if (nCopy.divideBy10() != 0) {
                digit = nCopy.toInt() * 10;
            }
            digit += toInt(n);
        }
        return digit;
    }

    /**
     * Reports whether the given tag appears in the given {@code XMLTree}.
     *
     * @param xml
     *            the {@code XMLTree}
     * @param tag
     *            the tag name
     * @return true if the given tag appears in the given {@code XMLTree}, false
     *         otherwise
     * @ensures <pre>
     * findTag =
     *    [true if the given tag appears in the given {@code XMLTree}, false otherwise]
     * </pre>
     */
    private static boolean findTag(XMLTree xml, String tag) {

        boolean result = false;
        if (xml.isTag()) {
            if (xml.label().equals(tag)) {
                return true;
            }
            for (int i = 0; i < xml.numberOfChildren(); i++) {
                result = findTag(xml.child(i), tag);
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

        NaturalNumber n1 = new NaturalNumber2(12);

        String url = "http://web.cse.ohio-state.edu/software/2221/web-sw1/"
                + "extras/instructions/xmltree-model/album.xml";
        XMLTree xml = new XMLTree2(url);

//        out.println("Before: " + n1);
//        out.println(productOfDigits1(n1));
//        out.println("After: " + n1);
//
//        out.println();
//        NaturalNumber n2 = new NaturalNumber2(123);
//
//        out.println("Before: " + n2);
//        out.println(productOfDigits2(n2));
//        out.println("After: " + n2);

        out.println(toInt(n1));
//        out.println(findTag(xml, "album"));

        out.close();
    }

}
