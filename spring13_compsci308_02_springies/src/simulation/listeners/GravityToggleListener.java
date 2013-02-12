package simulation.listeners;

import simulation.globalforces.Gravity;

public class GravityToggleListener implements Listener {

    private Gravity myGravity;
    
    public GravityToggleListener (Gravity gravity) {
        myGravity = gravity;
    }
    
    public void takeAction () {
        myGravity.toggle();
    }

}
