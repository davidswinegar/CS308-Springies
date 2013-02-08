package simulation.forces.globalforces;

import java.awt.Dimension;
import java.util.List;
import simulation.masses.Mass;
import simulation.forces.Force;

/**
 * Global forces which are created depending on the environment input.
 * 
 * @author David Le & David Winegar
 *
 */
public abstract class GlobalForce extends Force {

    public abstract void update (List<Mass> massList, Dimension bounds);

}
