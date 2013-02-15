package simulation.globalforces.wallrepulsionforces;

import java.awt.Dimension;
import java.util.List;
import simulation.Assembly;
import simulation.globalforces.GlobalForce;
import simulation.masses.Mass;
import util.Vector;


/**
 * Calculates the wall repulsion vector for one wall and applies to all masses.
 * 
 * @author David Winegar
 * 
 */
public abstract class WallRepulsionForce extends GlobalForce {

    private static final int DEFAULT_EXPONENT = -1;
    private static final double DEFAULT_MAGNITUDE = .1;

    // state used to determine force vector
    private double myDirection;
    private double myMagnitude;
    private double myExponent;

    public WallRepulsionForce (double angle) {
        this(angle, DEFAULT_MAGNITUDE, DEFAULT_EXPONENT);
        toggle();
    }

    /**
     * Sets state used to determine force vector.
     */
    public WallRepulsionForce (double angle, double magnitude, double exponent) {
        myMagnitude = magnitude;
        myExponent = exponent;
        myDirection = angle;
    }

    /**
     * Calculates force vector for each mass and applies it.
     */
    @Override
    public void applyForce (Assembly assembly, Dimension bounds) {
        List<Mass> massList = assembly.getMassList();
        for (Mass m : massList) {
            // magnitude = magnitude / (distance^exponent), because physics
            double magnitude = myMagnitude / Math.pow(getDistance(m, bounds), myExponent);
            m.applyForce(new Vector(myDirection, magnitude));
        }

    }

    /**
     * Finds distance between wall and mass.
     */
    public abstract double getDistance (Mass mass, Dimension bounds);

}
