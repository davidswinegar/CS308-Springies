package simulation.globalforces;

import java.awt.Dimension;
import simulation.Assembly;

/**
 * Global forces which are created depending on the environment input.
 * 
 * @author David Le & David Winegar
 *
 */
public interface GlobalForce {

    public abstract void update (Assembly assembly, Dimension bounds);

}
