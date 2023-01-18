import components.stack.Stack;

/**
 * Controller class.
 *
 * @author Nathan Thompson
 */
public class AppendUndoController1 implements AppendUndoController {

    /**
     * Model object.
     */
    private final AppendUndoModel model;

    /**
     * View object.
     */
    private final AppendUndoView view;

    /**
     * Updates view to display model.
     *
     * @param model
     *            the model
     * @param view
     *            the view
     */
    private static void updateViewToMatchModel(AppendUndoModel model,
            AppendUndoView view) {
        /*
         * Get model info
         */
        String input = model.input();
        Stack<String> output = model.output();
        /*
         * Update view to reflect changes in model
         */
        view.updateInputDisplay(input);
        view.updateOutputDisplay(output.toString());
    }

    /**
     * Constructor; connects {@code this} to the model and view it coordinates.
     *
     * @param model
     *            model to connect to
     * @param view
     *            view to connect to
     */
    public AppendUndoController1(AppendUndoModel model, AppendUndoView view) {
        this.model = model;
        this.view = view;
        /*
         * Update view to reflect initial value of model
         */
        updateViewToMatchModel(this.model, this.view);
    }

    /**
     * Processes event to reset model.
     *
     * @updates {@code this.model, this.view}
     * @ensures <pre>
     * {@code this.model.input = ""  and
     * this.model.output = <>  and
     * [this.view has been updated to match this.model]}
     * </pre>
     */
    @Override
    public void processResetEvent() {
        /*
         * Update model in response to this event
         */
        this.model.setInput("");
        this.model.output().clear();
        /*
         * Update view to reflect changes in model
         */
        updateViewToMatchModel(this.model, this.view);
    }

    /**
     * Processes event to append to output.
     *
     * @param input
     *            string to be appended
     *
     * @updates {@code this.model, this.view}
     * @ensures <pre>
     * {@code this.model.input = ""  and
     * this.model.output =  <input> * #this.model.output  and
     * [this.view has been updated to match this.model]}
     * </pre>
     */
    @Override
    public void processAppendEvent(String input) {
        /*
         * Update model in response to this event
         */
        this.model.output().push(input);

        /*
         * Update view to reflect changes in model
         */
        this.view.updateUndoAllowed(true);
        updateViewToMatchModel(this.model, this.view);
    }

    /**
     * Processes event to undo last append to output.
     *
     * @updates {@code this.model, this.view}
     * @requires <pre>
     * {@code this.model.output /= <>}
     * </pre>
     * @ensures <pre>
     * {@code #this.model.output = <this.model.input> * this.model.output  and
     * [this.view has been updated to match this.model]}
     * </pre>
     */
    @Override
    public void processUndoEvent() {
        /*
         * Update model in response to this event
         */
        if (this.model.output().length() > 0) {
            this.model.output().pop();
        } else {
            this.view.updateUndoAllowed(false);
        }

        /*
         * Update view to reflect changes in model
         */
        updateViewToMatchModel(this.model, this.view);
    }

}
