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

    /**
     * Sends direction to superconstructor.
     */
    public TopWallRepulsionForce () {
        super(Sprite.DOWN_DIRECTION);
    }

    /**
     * Sends info to superconstructor.
     * @param magnitude used to calculate force
     * @param exponent used to calculate force
     */
    public TopWallRepulsionForce (double magnitude,
                                  double exponent) {
        super(Sprite.DOWN_DIRECTION, magnitude, exponent);
    }

    /**
     * Calculates distance between mass and top wall.
     * @param mass used to get location
     * @param bounds used to get distance from bounds
     */
    @Override
    public double getDistance (Mass mass, Dimension bounds) {
        return mass.getY();
    }

}
