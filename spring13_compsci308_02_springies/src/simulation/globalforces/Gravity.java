package simulation.globalforces;

import java.awt.Dimension;
import java.util.List;
import simulation.Assembly;
import simulation.masses.Mass;
import util.Sprite;
import util.Vector;


/**
 * Global force which pushes in a constant direction and magnitude.
 * 
 * @author David Le
 * @author David Winegar
 */

public class Gravity extends GlobalForce {
    private static final int DEFAULT_MAGNITUDE = 15;
    // Gravity vector
    private Vector myGravityForce;

    /**
     * Sends default magnitude and default (down) direction to overloaded constructor and toggles force off.
     */
    public Gravity () {
        this(Sprite.DOWN_DIRECTION, DEFAULT_MAGNITUDE);
        toggle();
    }

    /**
     * Creates gravity vector from direction and magnitude.
     * 
     * @param direction angle gravity vector is applied on
     * @param magnitude magnitude of gravity vector
     */
    public Gravity (double direction, double magnitude) {
        myGravityForce = new Vector(direction, magnitude);
    }

    /**
     * Applies gravity force vector to each mass in assembly.
     * 
     * @param assembly input assembly containing masses
     * @param bounds bounds of area
     */
    @Override
    public void applyForce (Assembly assembly, Dimension bounds) {
        List<Mass> massList = assembly.getMassList();
        for (Mass m : massList) {
            m.applyForce(myGravityForce);
        }
    }

}
