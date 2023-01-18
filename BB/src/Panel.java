/**
 * Panel Class.
 *
 * @author Nathan Thompson
 */
public class Panel {

    /**
     * Name.
     */
    public static String name;
    /**
     * Height.
     */
    public static double height;
    /**
     * Length.
     */
    public static double length;
    /**
     * Width.
     */
    public static double width;
    /**
     * Weight.
     */
    public static double weight;
    /**
     * Bundle.
     */
    public static int bundle;

    /**
     * Default panel constructor.
     */
    Panel() {
    }

    /**
     * Panel constructor.
     *
     * @param name
     * @param height
     * @param length
     * @param width
     * @param weight
     */
    Panel(String name, double height, double length, double width,
            double weight) {
        this.name = name;
        this.height = height;
        this.length = length;
        this.width = width;
        this.weight = weight;
    }

    /**
     * Setter.
     *
     * @param bundle
     *            number
     */
    public void setBundle(int bundle) {
        this.bundle = bundle;
    }

    /**
     * Getter.
     *
     * @return bundle
     */
    public int getBundle() {
        return this.bundle;
    }

}
