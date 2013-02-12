package simulation.globalforces;

import java.awt.Dimension;
import java.util.List;
import simulation.Assembly;
import simulation.masses.Mass;


/**
 * Creates a drag on mass.
 * 
 * @author David Winegar
 * 
 */
public class ViscosityForce implements GlobalForce {

    private static final double DEFAULT_SCALE = .5;
    // scale current vector by amount
    private double myScale;

    public ViscosityForce () {
        this(DEFAULT_SCALE);
    }
    
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
    public void update (Assembly assembly, Dimension bounds) {
        List<Mass> massList = assembly.getMassList();
        for (Mass m : massList) {
            m.scaleAcceleration(myScale);
        }
    }

}
