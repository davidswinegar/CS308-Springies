package simulation.globalforces.wallrepulsionforces;

import java.awt.Dimension;
import simulation.masses.Mass;
import util.Sprite;


/**
 * Creates the wall repulsion force for the right wall.
 * 
 * @author David Winegar
 * 
 */
public class RightWallRepulsionForce extends WallRepulsionForce {

    public RightWallRepulsionForce () {
        super(Sprite.LEFT_DIRECTION);
    }

    /**
     * Sends info to superconstructor.
     */
    public RightWallRepulsionForce (double magnitude,
                                    double exponent) {
        super(Sprite.LEFT_DIRECTION, magnitude, exponent);
    }

    /**
     * Calculates distance between mass and right wall.
     */
    @Override
    public double getDistance (Mass mass, Dimension bounds) {
        return bounds.getWidth() - mass.getX();
    }

}
