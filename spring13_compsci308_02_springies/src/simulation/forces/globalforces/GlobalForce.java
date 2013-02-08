package simulation.forces.globalforces;

import java.awt.Dimension;
import java.util.List;
import simulation.masses.Mass;
import simulation.forces.Force;


public abstract class GlobalForce extends Force {

    public abstract void update (List<Mass> massList, Dimension bounds);

}
