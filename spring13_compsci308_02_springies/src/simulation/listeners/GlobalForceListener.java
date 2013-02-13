package simulation.listeners;

import simulation.globalforces.GlobalForce;

public class GlobalForceListener implements Listener {

    private GlobalForce myGlobalForce;
    
    public GlobalForceListener (GlobalForce force) {
        myGlobalForce = force;
    }
    
    @Override
    public void takeAction () {
        myGlobalForce.toggle();
    }

}
