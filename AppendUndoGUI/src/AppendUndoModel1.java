import components.stack.Stack;
import components.stack.Stack1L;

/**
 * Model class.
 *
 * @author Nathan Thompson
 */
public class AppendUndoModel1 implements AppendUndoModel {

    /**
     * Model variable input.
     */
    private String input;

    /**
     * Model variable output.
     */
    private Stack<String> output;

    /**
     * Default constructor.
     */
    public AppendUndoModel1() {
        /*
         * Initialize model; both variables start as empty
         */
        this.input = "";
        this.output = new Stack1L<String>();
    }

    /**
     * Returns the input.
     */
    @Override
    public String input() {
        return this.input;
    }

    /**
     * Updates the input.
     */
    @Override
    public void setInput(String input) {
        this.input = input;
    }

    /**
     * Returns the output.
     */
    @Override
    public Stack<String> output() {
        return this.output;
    }

}
