package simulation.globalforces;

import java.awt.Dimension;
import java.util.List;
import simulation.Assembly;
import simulation.masses.Mass;
import util.Sprite;
import util.Vector;

/**
 * Global force which pushes in a constant direction and magnitude specified by environment input.
 * 
 * @author David Le & David Winegar
 *
 */

public class Gravity extends GlobalForce {
    private static final int DEFAULT_MAGNITUDE = -10;
    // Gravity vector
    Vector myGravityForce;
    
    
    public Gravity () {
        this(Sprite.DOWN_DIRECTION, DEFAULT_MAGNITUDE);
        toggle();
    }
    
    public Gravity (double direction, double magnitude) {
        myGravityForce = new Vector(direction, magnitude);
    }

    /**
     * Iterates through each mass in the simulator and applies a constant force.
     */
    @Override
    public void update (Assembly assembly, Dimension bounds) {
        super.update(assembly, bounds);
        List<Mass> massList = assembly.getMassList();
        for (Mass m : massList) {
            m.applyForce(myGravityForce);
        }
    }

}
