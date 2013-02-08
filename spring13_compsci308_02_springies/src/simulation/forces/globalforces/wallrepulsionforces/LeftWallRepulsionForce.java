package simulation.forces.globalforces.wallrepulsionforces;

import java.awt.Dimension;
import simulation.masses.Mass;


/**
 * Creates the wall repulsion force for the left wall.
 * 
 * @author David Winegar
 * 
 */
public class LeftWallRepulsionForce extends WallRepulsionForce {

    /**
     * Sends info to superconstructor.
     */
    public LeftWallRepulsionForce (double wallID, double magnitude,
                                   double exponent) {
        super(wallID, magnitude, exponent);
    }

    /**
     * Calculates distance between mass and top wall.
     */
    @Override
    public double getDistance (Mass mass, Dimension bounds) {
        return mass.getX();
    }

}
