package util;

import java.awt.Dimension;
import java.util.List;
import simulation.Mass;


public abstract class GlobalForce {

    public abstract void update (List<Mass> massList, Dimension bounds);

}
