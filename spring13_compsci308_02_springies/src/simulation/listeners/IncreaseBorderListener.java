package simulation.listeners;

import simulation.Model;

public class IncreaseBorderListener implements Listener {
    private static final int AMOUNT_TO_INCREASE = 10;
    Model myModel;
    
    public IncreaseBorderListener (Model model) {
        myModel = model;
    }

    @Override
    public void takeAction () {
        myModel.changeBoundsSize(AMOUNT_TO_INCREASE);
    }
    
}
