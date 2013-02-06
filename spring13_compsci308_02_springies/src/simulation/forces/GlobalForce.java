package simulation.forces;

import java.awt.Dimension;
import java.util.List;
import simulation.masses.Mass;


public abstract class GlobalForce {

    public abstract void update (List<Mass> massList, Dimension bounds);

}
