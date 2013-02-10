package simulation.globalforces;

import java.awt.Dimension;
import java.util.List;
import simulation.masses.Mass;
import util.Vector;

/**
 * Global force which pushes in a constant direction and magnitude specified by environment input.
 * 
 * @author David Le & David Winegar
 *
 */

public class Gravity implements GlobalForce {
    // Gravity vector
    Vector myGravityForce;

    public Gravity (double direction, double magnitude) {
        myGravityForce = new Vector(direction, magnitude);
    }

    /**
     * Iterates through each mass in the simulator and applies a constant force.
     */
    @Override
    public void update (List<Mass> massList, Dimension bounds) {
        for (Mass m : massList) {
            m.applyForce(myGravityForce);
        }
    }

}