package simulation.globalforces;

import java.awt.Dimension;
import java.util.List;
import simulation.masses.Mass;

/**
 * Global forces which are created depending on the environment input.
 * 
 * @author David Le & David Winegar
 *
 */
public interface GlobalForce {

    public abstract void update (List<Mass> massList, Dimension bounds);

}
