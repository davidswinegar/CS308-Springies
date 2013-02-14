package simulation.globalforces.wallrepulsionforces;

import java.awt.Dimension;
import simulation.masses.Mass;
import util.Sprite;


/**
 * Creates the wall repulsion force for the left wall.
 * 
 * @author David Winegar
 * 
 */
public class LeftWallRepulsionForce extends WallRepulsionForce {

    public LeftWallRepulsionForce () {
        super(Sprite.RIGHT_DIRECTION);
    }
    
    /**
     * Sends info to superconstructor.
     */
    public LeftWallRepulsionForce (double magnitude,
                                   double exponent) {
        super(Sprite.RIGHT_DIRECTION, magnitude, exponent);
    }

    /**
     * Calculates distance between mass and left wall.
     */
    @Override
    public double getDistance (Mass mass, Dimension bounds) {
        return mass.getX();
    }

}
