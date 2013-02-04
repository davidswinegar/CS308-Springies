package simulation;

import java.awt.Dimension;
import java.util.List;
import util.GlobalForce;
import util.Vector;


public class Gravity extends GlobalForce {
    // Gravity vector
    Vector myGravityVector;

    public Gravity (double direction, double magnitude) {
        myGravityVector = new Vector(direction, magnitude);
    }

    @Override
    public void update (List<Mass> massList, Dimension bounds) {
        for (Mass m : massList) {
            m.applyForce(myGravityVector);
        }
    }

}
