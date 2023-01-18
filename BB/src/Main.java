import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Main.
 *
 * @author Nathan Thompson
 */
public final class Main extends Bundle {

    /**
     * Default constructor--private to prevent instantiation.
     */
    private Main() {
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments; unused here
     */
    public static void main(String[] args) {
        SimpleWriter out = new SimpleWriter1L();

        Panel e1 = new Panel("E1", 120, 48, 6, 800);
        e1.setBundle(1);
        out.println(e1.getBundle());
        out.println(e1.name);

        out.println();
        out.close();
    }

}
