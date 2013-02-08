package simulation.masses;

import simulation.forces.Force;

public class FixedMass extends Mass {

    public FixedMass (double x, double y, double mass) {
        super(x, y, mass);
    }

    /**
     * Because it is a fixed mass, applyForce should not do anything.
     */
    @Override
    public void applyForce (Force force) {
    }

}
