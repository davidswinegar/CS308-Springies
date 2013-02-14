package simulation.springs;

import java.awt.Point;
import simulation.Assembly;
import simulation.masses.FixedMass;
import simulation.masses.Mass;


public class UserSpring extends Spring {

    public static final double DEFAULT_KVAL = .5;
    private Assembly myAssembly;

    public UserSpring (Mass start, Point mousePosition, Assembly assembly) {
        this(start, new FixedMass(mousePosition.getX(), mousePosition.getY(), -1),
             mousePosition.distance(start.getX(), start.getY()), DEFAULT_KVAL);
        myAssembly = assembly;
    }

    public UserSpring (Mass start, Mass end, double length, double kVal) {
        super(start, end, length, kVal);
    }

    public Assembly getAssembly () {
        return myAssembly;
    }

}
