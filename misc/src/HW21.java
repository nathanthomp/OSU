import components.map.Map;
import components.map.Map1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Blank program (clear of Checkstyle and FindBugs warnings).
 *
 * @author N. Thompson
 */
public final class HW21 {

    /**
     * Default constructor--private to prevent instantiation.
     */
    private HW21() {
        // no code needed here
    }

    /**
     * Inputs a "menu" of words (items) and their prices from the given file and
     * stores them in the given {@code Map}.
     *
     * @param fileName
     *            the name of the input file
     * @param priceMap
     *            the word -> price map
     * @replaces priceMap
     * @requires <pre>
     * [file named fileName exists but is not open, and has the
     *  format of one "word" (unique in the file) and one price (in cents)
     *  per line, with word and price separated by ','; the "word" may
     *  contain whitespace but no ',']
     * </pre>
     * @ensures [priceMap contains word -> price mapping from file fileName]
     */
    private static void getPriceMap(String fileName,
            Map<String, Integer> priceMap) {
        SimpleReader in = new SimpleReader1L(fileName);

        while (!in.atEOS()) {
            String line = in.nextLine();
            String[] lineArray = line.split(",");
            if (!priceMap.hasKey(lineArray[0])) {
                priceMap.add(lineArray[0], Integer.parseInt(lineArray[1]));
            }
        }
        in.close();
    }

    /**
     * Input one pizza order and compute and return the total price.
     *
     * @param input
     *            the input stream
     * @param sizePriceMap
     *            the size -> price map
     * @param toppingPriceMap
     *            the topping -> price map
     * @return the total price (in cents)
     * @updates input
     * @requires <pre>
     * input.is_open and
     * [input.content begins with a pizza order consisting of a size
     *  (something defined in sizePriceMap) on the first line, followed
     *  by zero or more toppings (something defined in toppingPriceMap)
     *  each on a separate line, followed by an empty line]
     * </pre>
     * @ensures <pre>
     * input.is_open and
     * #input.content = [one pizza order (as described
     *              in the requires clause)] * input.content and
     * getOneOrder = [total price (in cents) of that pizza order]
     * </pre>
     */
    private static int getOneOrder(SimpleReader input,
            Map<String, Integer> sizePriceMap,
            Map<String, Integer> toppingPriceMap) {

        int total = 0;

        String line = input.nextLine();
        while (!line.equals("")) {
            if (sizePriceMap.hasKey(line)) {
                total += sizePriceMap.value(line);
            }
            if (toppingPriceMap.hasKey(line)) {
                total += toppingPriceMap.value(line);
            }
            line = input.nextLine();
        }
        return total;
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments; unused here
     */
    public static void main(String[] args) {
        SimpleWriter out = new SimpleWriter1L();

        Map<String, Integer> sizesPriceMap = new Map1L<>();
        getPriceMap("data/sizes.txt", sizesPriceMap);
//        for (Map.Pair<String, Integer> pair : sizesPriceMap) {
//            out.println(pair);
//        }

        Map<String, Integer> toppingsPriceMap = new Map1L<>();
        getPriceMap("data/toppings.txt", toppingsPriceMap);

        SimpleReader input = new SimpleReader1L("data/orders.txt");
        out.println("total: "
                + getOneOrder(input, sizesPriceMap, toppingsPriceMap));

        out.close();
    }

}
