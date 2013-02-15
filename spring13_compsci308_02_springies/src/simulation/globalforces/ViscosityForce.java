package simulation.globalforces;

import java.awt.Dimension;
import java.util.List;
import simulation.Assembly;
import simulation.masses.Mass;
import util.Vector;


/**
 * Creates a drag on the velocity of masses.
 * 
 * @author David Winegar
 * 
 */
public class ViscosityForce extends GlobalForce {

    private static final double DEFAULT_SCALE = .95;
    // scale current vector by amount
    private double myScale;

    /**
     * Sends default scale value to overloaded constructor and toggles off.
     */
    public ViscosityForce () {
        this(DEFAULT_SCALE);
        toggle();
    }

    /**
     * Sets scale value.
     * 
     * @param scale value to scale velocity by
     */
    public ViscosityForce (double scale) {
        myScale = scale;
    }

    /**
     * Finds velocity vector, negates it, scales it down, and applies it as a force for each mass in
     * assembly.
     * 
     * @param assembly assembly containing masses
     * @param bounds bounds of area.
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
