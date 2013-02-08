package simulation.forces.globalforces;

import java.awt.Dimension;
import java.util.List;
import simulation.forces.Force;
import simulation.masses.Mass;
import util.Vector;


/**
 * Creates a drag on mass.
 * 
 * @author David Winegar
 * 
 */
public class ViscosityForce extends GlobalForce {

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
            Force f = (Force) new Vector(m.getVelocity());
            f.negate();
            f.scale(myScale);
            m.applyForce(f);
        }
    }

}
