package simulation.listeners;

import simulation.globalforces.CenterOfMassForce;

public class CenterOfMassToggleListener implements Listener {

    private CenterOfMassForce myCenterOfMass;
    
    public CenterOfMassToggleListener (CenterOfMassForce centerOfMass) {
        myCenterOfMass = centerOfMass;
    }
    
    @Override
    public void takeAction () {
        myCenterOfMass.toggle();
    }

}
