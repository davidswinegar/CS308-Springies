package simulation.listeners;

import simulation.Model;

/**
 * Listener which listens for the border decrease
 * @author David Le
 * @author David Winegar
 */
public class DecreaseBorderListener implements Listener {

    private static final int AMOUNT_TO_DECREASE = -10;
    private Model myModel;

    /**
     * Constructs listener
     * @param model to be added to
     */
    public DecreaseBorderListener (Model model) {
        myModel = model;
    }

    @Override
    public void takeAction () {
        myModel.changeBoundsSize(AMOUNT_TO_DECREASE);
    }

}
