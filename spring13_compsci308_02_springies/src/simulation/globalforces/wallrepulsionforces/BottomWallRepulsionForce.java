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

    /**
     * Sends direction to superconstructor.
     */
    public BottomWallRepulsionForce () {
        super(Sprite.UP_DIRECTION);
    }

    /**
     * Sends info to superconstructor.
     * @param magnitude used to calculate force
     * @param exponent used to calculate force
     */
    public BottomWallRepulsionForce (double magnitude,
                                     double exponent) {
        super(Sprite.UP_DIRECTION, magnitude, exponent);
    }

    /**
     * Calculates distance between mass and bottom wall.
     * @param mass used to get location
     * @param bounds used to get distance from bounds
     */
    @Override
    public double getDistance (Mass mass, Dimension bounds) {
        return bounds.getHeight() - mass.getY();
    }

}
