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
    
    /**
     * Sends information to overloaded constructor and toggles force off.
     * @param angle direction force goes
     */
    public WallRepulsionForce (double angle) {
        this(angle, DEFAULT_MAGNITUDE, DEFAULT_EXPONENT);
        toggle();
    }

    /**
     * Sets state used to determine force vector.
     * @param angle angle direction force goes
     * @param magnitude magnitude of force
     * @param exponent used to calculate decay based on distance of force size
     */
    public WallRepulsionForce (double angle, double magnitude, double exponent) {
        myMagnitude = magnitude;
        myExponent = exponent;
        myDirection = angle;
    }

    /**
     * Calculates force vector for each mass and applies it.
     * @param assembly input assembly containing masses
     * @param bounds bounds of area.
     */
    @Override
    public void applyForce (Assembly assembly, Dimension bounds) {
        List<Mass> massList = assembly.getMassList();
        for (Mass m : massList) {
            // force magnitude = magnitude / (distance^exponent)
            double magnitude = myMagnitude / Math.pow(getDistance(m, bounds), myExponent);
            m.applyForce(new Vector(myDirection, magnitude));
        }

    }

    /**
     * Finds distance between wall and mass.
     * @param mass used to get location
     * @param bounds used to get distance from bounds
     * @return distance between wall and mass
     */
    public abstract double getDistance (Mass mass, Dimension bounds);

}
