package simulation.globalforces.wallrepulsionforces;

import java.awt.Dimension;
import simulation.masses.Mass;
import util.Sprite;


/**
 * Creates the wall repulsion force for the top wall.
 * 
 * @author David Winegar
 * 
 */
public class TopWallRepulsionForce extends WallRepulsionForce {

    public TopWallRepulsionForce () {
        super(Sprite.DOWN_DIRECTION);
    }
    
    /**
     * Sends info to superconstructor.
     */
    public TopWallRepulsionForce (double wallID, double magnitude,
                                  double exponent) {
        super(wallID, magnitude, exponent);
    }

    /**
     * Calculates distance between mass and top wall.
     */
    @Override
    public double getDistance (Mass mass, Dimension bounds) {
        return mass.getY();
    }

}
