package simulation.masses;

import util.Vector;


/**
 * A mass which does not move/respond to forces acting upon it.
 * 
 * @author David Le
 * @author David Winegar
 */
public class FixedMass extends Mass {

    /**
     * Constructs fixed mass to be used in simulation
     * @param x coordinate of fixed mass
     * @param y coordinate of fixed mass
     * @param mass weight
     */
    public FixedMass (double x, double y, double mass) {
        super(x, y, mass);
    }

    /**
     * Because it is a fixed mass, applyForce should not do anything.
     * @param force to be applied (not!)
     */
    @Override
    public void applyForce (Vector force) {
    }

}
