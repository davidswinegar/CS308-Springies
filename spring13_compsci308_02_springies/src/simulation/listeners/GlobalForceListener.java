package simulation.listeners;

import simulation.globalforces.GlobalForce;

/**
 * Listener used to toggle all global forces
 * @author David Le
 * @author David Winegar
 */
public class GlobalForceListener implements Listener {

    private GlobalForce myGlobalForce;

    /**
     * Construct global force listener
     * @param force to modify when triggered
     */
    public GlobalForceListener (GlobalForce force) {
        myGlobalForce = force;
    }

    @Override
    public void takeAction () {
        myGlobalForce.toggle();
    }

}
