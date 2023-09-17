import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;

/**
 * Controller class.
 *
 * @author Put your name here
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

        //makes the view adjust based on arguments of method
        NaturalNumber top = model.top();
        NaturalNumber bottom = model.bottom();
        view.updateDivideAllowed(!bottom.isZero());
        view.updateSubtractAllowed(top.compareTo(INT_LIMIT) <= 0);
        view.updatePowerAllowed(bottom.compareTo(INT_LIMIT) <= 0);
        view.updateRootAllowed(
                bottom.compareTo(INT_LIMIT) <= 0 && bottom.compareTo(TWO) >= 0);

        view.updateTopDisplay(model.top());
        view.updateBottomDisplay(model.bottom());
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

        //aliases NNs (Not an issue for this project)
        NaturalNumber top = this.model.top();
        NaturalNumber bottom = this.model.bottom();

        //copies bottom and updates the view
        top.copyFrom(bottom);
        updateViewToMatchModel(this.model, this.view);

    }

    @Override
    public void processAddEvent() {

        //aliases NNs (Not an issue for this project)
        NaturalNumber top = this.model.top();
        NaturalNumber bottom = this.model.bottom();

        //top is added to bottom and cleared after
        bottom.add(top);
        top.clear();

        //updates view
        updateViewToMatchModel(this.model, this.view);
    }

    @Override
    public void processSubtractEvent() {

        //aliases NNs (Not an issue for this project)
        NaturalNumber top = this.model.top();
        NaturalNumber bottom = this.model.bottom();

        //bottom is subtracted from the top and transfer to the bottom

        top.subtract(bottom);
        bottom.transferFrom(top);

        updateViewToMatchModel(this.model, this.view);

    }

    @Override
    public void processMultiplyEvent() {

        //aliases NNs (Not an issue for this project)
        NaturalNumber top = this.model.top();
        NaturalNumber bottom = this.model.bottom();

        //top gets multiplied by bottom and then transfered down
        top.multiply(bottom);
        bottom.transferFrom(top);

        updateViewToMatchModel(this.model, this.view);

    }

    @Override
    public void processDivideEvent() {

        //aliases NNs (Not an issue for this project)
        NaturalNumber top = this.model.top();
        NaturalNumber bottom = this.model.bottom();

        //stores remainder
        NaturalNumber remainder = top.divide(bottom);

        bottom.transferFrom(top);
        //returns remainder
        top.transferFrom(remainder);

        updateViewToMatchModel(this.model, this.view);

    }

    @Override
    public void processPowerEvent() {

        //aliases NNs (Not an issue for this project)
        NaturalNumber top = this.model.top();
        NaturalNumber bottom = this.model.bottom();

        //bottom is powered to top and converted to an Int
        bottom.power(top.toInt());
        top.clear();

        updateViewToMatchModel(this.model, this.view);

    }

    @Override
    public void processRootEvent() {

        //aliases NNs (Not an issue for this project)
        NaturalNumber top = this.model.top();
        NaturalNumber bottom = this.model.bottom();

        //bottom's root of top and it gets converted to an Int
        top.root(bottom.toInt());
        bottom.transferFrom(top);

        updateViewToMatchModel(this.model, this.view);

    }

    @Override
    public void processAddNewDigitEvent(int digit) {

        //aliases NNs (Not an issue for this project)
        NaturalNumber top = this.model.top();
        NaturalNumber bottom = this.model.bottom();

        bottom.multiplyBy10(digit);

        updateViewToMatchModel(this.model, this.view);

    }

}
