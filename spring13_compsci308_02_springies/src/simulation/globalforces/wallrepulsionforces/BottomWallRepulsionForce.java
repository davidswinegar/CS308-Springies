package simulation.globalforces.wallrepulsionforces;

import java.awt.Dimension;
import simulation.masses.Mass;
import util.Sprite;


/**
 * Creates the wall repulsion force for the bottom wall.
 * 
 * @author David Winegar
 * 
 */
public class BottomWallRepulsionForce extends WallRepulsionForce {

    public BottomWallRepulsionForce () {
        super(Sprite.UP_DIRECTION);
    }
    
    /**
     * Sends info to superconstructor.
     */
    public BottomWallRepulsionForce (double magnitude,
                                     double exponent) {
        super(Sprite.UP_DIRECTION, magnitude, exponent);
    }

    /**
     * Calculates distance between mass and bottom wall.
     */
    @Override
    public double getDistance (Mass mass, Dimension bounds) {
        return bounds.getHeight() - mass.getY();
    }

}
