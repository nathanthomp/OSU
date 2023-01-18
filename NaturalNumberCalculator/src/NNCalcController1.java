import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;

/**
 * Controller class.
 *
 * @author Nathan Thompson
 */
public final class NNCalcController1 implements NNCalcController {

    /**
     * Model object.
     */
    private final NNCalcModel model;

    /**
     * View object.
     */
    private final NNCalcView view;

    /**
     * Useful constants.
     */
    private static final NaturalNumber TWO = new NaturalNumber2(2),
            INT_LIMIT = new NaturalNumber2(Integer.MAX_VALUE);

    /**
     * Updates this.view to display this.model, and to allow only operations
     * that are legal given this.model.
     *
     * @param model
     *            the model
     * @param view
     *            the view
     * @ensures [view has been updated to be consistent with model]
     */
    private static void updateViewToMatchModel(NNCalcModel model,
            NNCalcView view) {
        /*
         * Get model info
         */
        NaturalNumber top = model.top();
        NaturalNumber bottom = model.bottom();

        /*
         * top < bottom: Can't subtract
         */
        if (top.compareTo(bottom) < 0) {
            view.updateSubtractAllowed(false);
            view.updateRootAllowed(true);
            view.updateDivideAllowed(true);
        }
        /*
         * top > bottom
         */
        if (top.compareTo(bottom) > 0) {
            view.updateSubtractAllowed(true);
        }
        /*
         * top = bottom
         */
        if (top.compareTo(bottom) == 0) {
            view.updateSubtractAllowed(true);
            view.updateDivideAllowed(true);
        }
        /*
         * bottom < 2: Can't root; bottom > 2: Can root
         */
        if (bottom.compareTo(TWO) < 0) {
            view.updateRootAllowed(false);
        } else {
            view.updateRootAllowed(true);
        }
        /*
         * bottom = 0: Can't divide; bottom > 0: Can divide
         */
        if (bottom.isZero()) {
            view.updateDivideAllowed(false);
        } else {
            view.updateDivideAllowed(true);
        }
        /*
         * top > INT_LIMIT: Can't root or power
         */
        if (top.compareTo(INT_LIMIT) > 0) {
            view.updateRootAllowed(false);
            view.updatePowerAllowed(false);
        }
        /*
         * Update view to reflect changes in model
         */
        view.updateTopDisplay(top);
        view.updateBottomDisplay(bottom);
    }

    /**
     * Constructor.
     *
     * @param model
     *            model to connect to
     * @param view
     *            view to connect to
     */
    public NNCalcController1(NNCalcModel model, NNCalcView view) {
        this.model = model;
        this.view = view;
        updateViewToMatchModel(model, view);
    }

    @Override
    public void processClearEvent() {
        /*
         * Get alias to bottom from model
         */
        NaturalNumber bottom = this.model.bottom();
        /*
         * Update model in response to this event
         */
        bottom.clear();
        /*
         * Update view to reflect changes in model
         */
        updateViewToMatchModel(this.model, this.view);
    }

    @Override
    public void processSwapEvent() {
        /*
         * Get aliases to top and bottom from model
         */
        NaturalNumber top = this.model.top();
        NaturalNumber bottom = this.model.bottom();
        /*
         * Update model in response to this event
         */
        NaturalNumber temp = top.newInstance();
        temp.transferFrom(top);
        top.transferFrom(bottom);
        bottom.transferFrom(temp);
        /*
         * Update view to reflect changes in model
         */
        updateViewToMatchModel(this.model, this.view);
    }

    @Override
    public void processEnterEvent() {
        /*
         * Update model in response to this event
         */
        this.model.top().copyFrom(this.model.bottom());
        /*
         * Update view to reflect changes in model
         */
        updateViewToMatchModel(this.model, this.view);
    }

    @Override
    public void processAddEvent() {
        /*
         * Update model in response to this event
         */
        this.model.top().add(this.model.bottom());
        /*
         * Update view to reflect changes in model
         */
        updateViewToMatchModel(this.model, this.view);
    }

    @Override
    public void processSubtractEvent() {
        /*
         * Update model in response to this event
         */
        this.model.top().subtract(this.model.bottom());
        /*
         * Update view to reflect changes in model
         */
        updateViewToMatchModel(this.model, this.view);
    }

    @Override
    public void processMultiplyEvent() {
        /*
         * Update model in response to this event
         */

        this.model.top().multiply(this.model.bottom());
        /*
         * Update view to reflect changes in model
         */
        updateViewToMatchModel(this.model, this.view);
    }

    @Override
    public void processDivideEvent() {
        /*
         * Update model in response to this event
         */

        this.model.top().divide(this.model.bottom());
        /*
         * Update view to reflect changes in model
         */
        updateViewToMatchModel(this.model, this.view);
    }

    @Override
    public void processPowerEvent() {
        /*
         * Update model in response to this event
         */

        this.model.top().power(this.model.bottom().toInt());
        /*
         * Update view to reflect changes in model
         */
        updateViewToMatchModel(this.model, this.view);
    }

    @Override
    public void processRootEvent() {
        /*
         * Update model in response to this event
         */

        this.model.top().root(this.model.bottom().toInt());
        /*
         * Update view to reflect changes in model
         */
        updateViewToMatchModel(this.model, this.view);
    }

    @Override
    public void processAddNewDigitEvent(int digit) {
        /*
         * Update model in response to this event
         */
        this.model.bottom().multiplyBy10(digit);

        /*
         * Update view to reflect changes in model
         */
        updateViewToMatchModel(this.model, this.view);

    }

}
