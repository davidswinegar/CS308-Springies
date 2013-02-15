package simulation.listeners;

import simulation.Model;

/**
 * Listener which listens for the clearing of all assemblies.
 * @author David Le
 * @author David Winegar
 */
public class ClearAssemblyListener implements Listener {

    private Model myModel;

    /**
     * Constructs new listener which will clear all assemblies
     * @param model to add the listener to
     */
    public ClearAssemblyListener (Model model) {
        myModel = model;
    }

    @Override
    public void takeAction () {
        myModel.clearAllAssemblies();

    }

}
