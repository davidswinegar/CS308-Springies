package simulation.listeners;

import simulation.Model;

public class DecreaseBorderListener implements Listener {

    private static final int AMOUNT_TO_DECREASE = -10;
    Model myModel;
    
    public DecreaseBorderListener (Model model) {
        myModel = model;
    }

    @Override
    public void takeAction () {
        myModel.changeBoundsSize(AMOUNT_TO_DECREASE);
    }

}
