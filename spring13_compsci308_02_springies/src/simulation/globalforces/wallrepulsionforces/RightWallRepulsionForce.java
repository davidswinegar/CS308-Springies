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

    /**
     * Sends direction to superconstructor.
     */
    public RightWallRepulsionForce () {
        super(Sprite.LEFT_DIRECTION);
    }

    /**
     * Sends info to superconstructor.
     * @param magnitude used to calculate force
     * @param exponent used to calculate force
     */
    public RightWallRepulsionForce (double magnitude,
                                    double exponent) {
        super(Sprite.LEFT_DIRECTION, magnitude, exponent);
    }

    /**
     * Calculates distance between mass and right wall.
     * @param mass used to get location
     * @param bounds used to get distance from bounds
     */
    @Override
    public double getDistance (Mass mass, Dimension bounds) {
        return bounds.getWidth() - mass.getX();
    }

}
