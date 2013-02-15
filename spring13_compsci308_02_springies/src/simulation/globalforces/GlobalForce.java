package simulation.globalforces;

import java.awt.Dimension;
import simulation.Assembly;


/**
 * Global forces which are created depending on the environment input.
 * 
 * @author David Le
 * @author David Winegar
 */
public abstract class GlobalForce {

    private boolean myForceIsCurrentlyOn = true;

    /**
     * Checks to see if force is currently toggled on and then calls applyForce if it is.
     * 
     * @param assembly input assembly containing masses
     * @param bounds bounds of area
     */
    public void applyForceIfToggledOn (Assembly assembly, Dimension bounds) {
        if (isToggledOn()) {
            applyForce(assembly, bounds);
        }
    }

    /**
     * Calculates force vector for each mass and applies it.
     * 
     * @param assembly input assembly containing masses
     * @param bounds bounds of area
     */
    public abstract void applyForce (Assembly assembly, Dimension bounds);

    /**
     * Toggles force on and off.
     */
    public void toggle () {
        myForceIsCurrentlyOn = !myForceIsCurrentlyOn;
    }

    /**
     * Checks whether force is currently toggled on.
     * 
     * @return returns true if force is toggled on
     */
    public boolean isToggledOn () {
        return myForceIsCurrentlyOn;
    }
}
