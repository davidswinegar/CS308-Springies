package simulation.listeners;

import simulation.Model;


/**
 * Listener which listens for the border increase
 * 
 * @author David Le
 * @author David Winegar
 */
public class IncreaseBorderListener implements Listener {
    private static final int AMOUNT_TO_INCREASE = 10;

    private Model myModel;

    /**
     * Constructs listener
     * 
     * @param model to be added to
     */
    public IncreaseBorderListener (Model model) {
        myModel = model;
    }

    @Override
    public void takeAction () {
        myModel.changeBoundsSize(AMOUNT_TO_INCREASE);
    }

}
