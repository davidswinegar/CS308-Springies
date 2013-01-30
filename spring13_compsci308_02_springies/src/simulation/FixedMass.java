package simulation;

import util.Vector;

public class FixedMass extends Mass {
	
    public FixedMass(double x, double y, double mass) {
		super(x, y, mass);
	}

    /**
     * Because it is a fixed mass, applyForce should not do anything.
     */
    public void applyForce (Vector force) {
    }
    
}
