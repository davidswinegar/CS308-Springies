package simulation.globalforces;

import java.awt.Dimension;
import java.util.List;
import simulation.Assembly;
import simulation.masses.Mass;
import util.Vector;


/**
 * Creates a drag on mass.
 * 
 * @author David Winegar
 * 
 */
public class ViscosityForce extends GlobalForce {

    private static final double DEFAULT_SCALE = .95;
    // scale current vector by amount
    private double myScale;

    public ViscosityForce () {
        this(DEFAULT_SCALE);
        toggle();
    }

    /**
     * Sets state.
     */
    public ViscosityForce (double scale) {
        myScale = scale;
    }

    /**
     * Sets amount to scale by in Mass.
     */
    @Override
    public void applyForce (Assembly assembly, Dimension bounds) {
        List<Mass> massList = assembly.getMassList();
        for (Mass m : massList) {
            Vector viscosityForce = new Vector(m.getVelocity());
            viscosityForce.negate();
            viscosityForce.scale(1 - myScale);
            m.applyForce(viscosityForce);
        }
    }

}
