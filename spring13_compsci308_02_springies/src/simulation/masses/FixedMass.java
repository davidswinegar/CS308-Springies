package simulation.masses;

import util.Vector;

/**
 * A mass which does not move/respond to forces acting upon it.
 * @author David Le
 *
 */
public class FixedMass extends Mass {

    public FixedMass (double x, double y, double mass) {
        super(x, y, mass);
    }

    /**
     * Because it is a fixed mass, applyForce should not do anything.
     */
    @Override
    public void applyForce (Vector force) {
    }

}
