package simulation;

import java.awt.Dimension;
import java.util.List;
import util.GlobalForce;
import util.Vector;


/**
 * Calculates the wall repulsion vector for one wall and applies to all masses.
 * 
 * @author David Winegar
 * 
 */
public abstract class WallRepulsionForce extends GlobalForce {

    // state used to determine force vector
    private double myDirection;
    private double myMagnitude;
    private double myExponent;

    /**
     * Sets state used to determine force vector.
     */
    public WallRepulsionForce (double wallID, double magnitude, double exponent) {
        myMagnitude = magnitude;
        myExponent = exponent;
        // converts wallID to angle
        myDirection = wallID * 90;
    }

    /**
     * Calculates force vector for each mass and applies it.
     */
    @Override
    public void update (List<Mass> massList, Dimension bounds) {
        for (Mass m : massList) {
            // magnitude = magnitude / (distance^exponent), because physics
            double magnitude = myMagnitude / Math.pow(getDistance(m, bounds), myExponent);
            m.applyForce(new Vector(myDirection, magnitude));
        }

    }

    /**
     * Finds distance between wall and mass. To be overriden for each wall.
     */
    public abstract double getDistance (Mass mass, Dimension bounds);

}
