package simulation.listeners;

import simulation.Model;

public class ClearAssemblyListener implements Listener {

    Model myModel;
    
    public ClearAssemblyListener (Model model) {
        myModel = model;
    }

    @Override
    public void takeAction () {
        myModel.clearAllAssemblies();
        
    }

}
