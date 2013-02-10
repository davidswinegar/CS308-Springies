package simulation.globalforces;

import java.awt.Dimension;
import java.util.List;
import simulation.masses.Mass;


/**
 * Creates a drag on mass.
 * 
 * @author David Winegar
 * 
 */
public class ViscosityForce implements GlobalForce {

    // scale current vector by amount
    private double myScale;

    /**
     * Sets state.
     */
    public ViscosityForce (double scale) {
        myScale = scale;
    }

    /**
     * Gets current vector, scales and reverses it, and applies it.
     */
    @Override
    public void update (List<Mass> massList, Dimension bounds) {
        for (Mass m : massList) {
            m.scaleAcceleration(myScale);
        }
    }

}
