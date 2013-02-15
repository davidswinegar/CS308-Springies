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

    /**
     * Sends direction to superconstructor.
     */
    public LeftWallRepulsionForce () {
        super(Sprite.RIGHT_DIRECTION);
    }

    /**
     * Sends info to superconstructor.
     * 
     * @param magnitude used to calculate force
     * @param exponent used to calculate force
     */
    public LeftWallRepulsionForce (double magnitude,
                                   double exponent) {
        super(Sprite.RIGHT_DIRECTION, magnitude, exponent);
    }

    /**
     * Calculates distance between mass and left wall.
     * 
     * @param mass used to get location
     * @param bounds used to get distance from bounds
     */
    @Override
    public double getDistance (Mass mass, Dimension bounds) {
        return mass.getX();
    }

}
