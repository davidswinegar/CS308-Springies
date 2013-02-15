package simulation.springs;

import java.awt.Point;
import simulation.Assembly;
import simulation.masses.FixedMass;
import simulation.masses.Mass;


/**
 * User spring created upon mouse click to interact with assembly of the closest mass
 * 
 * @author David Le
 * @author David Winegar
 */
public class UserSpring extends Spring {

    /**
     * Default springiness constant of user spring
     */
    public static final double DEFAULT_KVAL = .5;
    private Assembly myAssembly;

    /**
     * Formats the construction to be passed in the format of the parent spring class
     * 
     * @param start mass
     * @param mousePosition to attach spring to
     * @param assembly which the start mass is attached to
     */
    public UserSpring (Mass start, Point mousePosition, Assembly assembly) {
        this(start, new FixedMass(mousePosition.getX(), mousePosition.getY(), -1),
             mousePosition.distance(start.getX(), start.getY()), DEFAULT_KVAL);
        myAssembly = assembly;
    }

    /**
     * Constructs the user spring and attaches it to assembly of the closest mass
     * 
     * @param start mass
     * @param end mass
     * @param length of spring
     * @param kVal springiness constant
     */
    public UserSpring (Mass start, Mass end, double length, double kVal) {
        super(start, end, length, kVal);
    }

    /**
     * Returns the assembly which the user spring is connected to
     * 
     * @return
     */
    public Assembly getAssembly () {
        return myAssembly;
    }

}
