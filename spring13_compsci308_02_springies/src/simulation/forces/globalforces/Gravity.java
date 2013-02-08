package simulation.forces.globalforces;

import java.awt.Dimension;
import java.util.List;
import simulation.forces.Force;
import simulation.masses.Mass;

public class Gravity extends GlobalForce {
    // Gravity vector
    Force myGravityForce;

    public Gravity (double direction, double magnitude) {
        myGravityForce = new Force(direction, magnitude);
    }

    @Override
    public void update (List<Mass> massList, Dimension bounds) {
        for (Mass m : massList) {
            m.applyForce(myGravityForce);
        }
    }

}
