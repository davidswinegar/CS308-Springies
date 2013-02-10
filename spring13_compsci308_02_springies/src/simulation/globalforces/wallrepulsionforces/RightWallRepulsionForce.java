package simulation.globalforces.wallrepulsionforces;

import java.awt.Dimension;
import simulation.masses.Mass;


/**
 * Creates the wall repulsion force for the right wall.
 * 
 * @author David Winegar
 * 
 */
public class RightWallRepulsionForce extends WallRepulsionForce {

    /**
     * Sends info to superconstructor.
     */
    public RightWallRepulsionForce (double wallID, double magnitude,
                                    double exponent) {
        super(wallID, magnitude, exponent);
    }

    /**
     * Calculates distance between mass and top wall.
     */
    @Override
    public double getDistance (Mass mass, Dimension bounds) {
        return bounds.getWidth() - mass.getX();
    }

}
