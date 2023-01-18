import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.utilities.Reporter;
import components.xmltree.XMLTree;
import components.xmltree.XMLTree1;

/**
 * Program to evaluate XMLTree expressions of {@code NaturalNumber}.
 *
 * @author Nathan Thompson
 *
 */
public final class XMLTreeNNExpressionEvaluator {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private XMLTreeNNExpressionEvaluator() {
    }

    /**
     * Evaluate the given expression.
     *
     * @param exp
     *            the {@code XMLTree} representing the expression
     * @return the value of the expression
     * @requires <pre>
     * [exp is a subtree of a well-formed XML arithmetic expression]  and
     *  [the label of the root of exp is not "expression"]
     * </pre>
     * @ensures evaluate = [the value of the expression]
     */
    private static NaturalNumber evaluate(XMLTree exp) {
        assert exp != null : "Violation of: exp is not null";
        NaturalNumber result = new NaturalNumber2();
        NaturalNumber zero = new NaturalNumber2();

        if (exp.numberOfChildren() > 0) {
            if (exp.label().equals("times")) {
                result.copyFrom(evaluate(exp.child(0)));
                result.multiply(evaluate(exp.child(1)));
            } else if (exp.label().equals("plus")) {
                result.copyFrom(evaluate(exp.child(0)));
                result.add(evaluate(exp.child(1)));
            } else if (exp.label().equals("minus")) {
                result.copyFrom(evaluate(exp.child(0)));
                NaturalNumber resultCopy = new NaturalNumber2();
                resultCopy.copyFrom(result);
                resultCopy.subtract(evaluate(exp.child(1)));
                if (resultCopy.compareTo(zero) < 0) {
                    Reporter.fatalErrorToConsole("Violation of: this > 0");
                }
                result.subtract(evaluate(exp.child(1)));
            } else {
                if (evaluate(exp.child(0)).isZero()
                        || evaluate(exp.child(1)).isZero()) {
                    Reporter.fatalErrorToConsole(
                            "Violation of: cannot divide by 0");
                }
                result.copyFrom(evaluate(exp.child(0)));
                result.divide(evaluate(exp.child(1)));
            }
            for (int i = 0; i < exp.numberOfChildren(); i++) {
                evaluate(exp.child(i));
            }
        } else {
            return new NaturalNumber2(
                    Integer.parseInt(exp.attributeValue("value")));
        }
        return result;
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();

        out.print("Enter the name of an expression XML file: ");
        String file = in.nextLine();
        while (!file.equals("")) {
            XMLTree exp = new XMLTree1(file);
            out.println(evaluate(exp.child(0)));
            out.print("Enter the name of an expression XML file: ");
            file = in.nextLine();
        }

        in.close();
        out.close();
    }

}
