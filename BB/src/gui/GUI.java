package gui;

/**
 * Very simple graphical user interface.
 *
 * @author Nathan Thompson
 */
public final class GUI {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private GUI() {
    }

    /**
     * Main program that sets up main application window and starts user
     * interaction.
     *
     * @param args
     *            command-line arguments; not used
     */
    public static void main(String[] args) {
        /*
         * Create instances of the model, view, and controller objects;
         * controller needs to know about model and view, and view needs to know
         * about controller
         */
        View view = new View();

        // TODO: Instantiate Model and View classes.
        // TODO: Register observer in view class.
    }

}
