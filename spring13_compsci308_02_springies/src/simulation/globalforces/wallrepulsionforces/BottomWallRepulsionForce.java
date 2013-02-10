package simulation.globalforces.wallrepulsionforces;

import java.awt.Dimension;
import simulation.masses.Mass;


/**
 * Creates the wall repulsion force for the bottom wall.
 * 
 * @author David Winegar
 * 
 */
public class BottomWallRepulsionForce extends WallRepulsionForce {

    /**
     * Sends info to superconstructor.
     */
    public BottomWallRepulsionForce (double wallID, double magnitude,
                                     double exponent) {
        super(wallID, magnitude, exponent);
    }

    /**
     * Calculates distance between mass and bottom wall.
     */
    @Override
    public double getDistance (Mass mass, Dimension bounds) {
        return bounds.getHeight() - mass.getY();
    }

}
