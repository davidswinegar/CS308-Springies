package simulation.forces.globalforces;

import java.awt.Dimension;
import java.util.List;
import simulation.forces.Force;
import simulation.masses.Mass;

/**
 * Global force which pushes in a constant direction and magnitude specified by environment input.
 * 
 * @author David Le & David Winegar
 *
 */

public class Gravity extends GlobalForce {
    // Gravity vector
    Force myGravityForce;

    public Gravity (double direction, double magnitude) {
        myGravityForce = new Force(direction, magnitude);
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
