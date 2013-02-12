package simulation.listeners;

import simulation.globalforces.ViscosityForce;

public class ViscosityToggleListener implements Listener {

    private ViscosityForce myViscosity;
    
    public ViscosityToggleListener (ViscosityForce viscosity) {
        myViscosity = viscosity;
    }
    
    @Override
    public void takeAction () {
        myViscosity.toggle();
    }

}
