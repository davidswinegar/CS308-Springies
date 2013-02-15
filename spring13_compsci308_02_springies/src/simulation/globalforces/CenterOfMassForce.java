package simulation.globalforces;

import java.awt.Dimension;
import java.util.List;
import simulation.Assembly;
import simulation.masses.Mass;
import util.Vector;


/**
 * Calculates the center of mass of all the masses and applies a force towards that point.
 * 
 * @author David Winegar
 * 
 */
public class CenterOfMassForce extends GlobalForce {

    private static final int DEFAULT_EXPONENT = -1;
    private static final double DEFAULT_MAGNITUDE = 1;
    // state used to determine force vector
    private double myMagnitude;
    private double myExponent;

    /**
     * Sends default magnitude and default exponent to overloaded constructor and toggles force off.
     */
    public CenterOfMassForce () {
        this(DEFAULT_MAGNITUDE, DEFAULT_EXPONENT);
        toggle();
    }

    /**
     * Sets state magnitude and exponent values.
     * 
     * @param magnitude used to determine force vector for each assembly
     * @param exponent used to determine decay of force the farther away mass gets from center of
     *        mass.
     */
    public CenterOfMassForce (double magnitude, double exponent) {
        myMagnitude = magnitude;
        myExponent = exponent;
    }

    /**
     * Finds center of mass in assembly, calculates angle and magnitude vector for each mass, and
     * applies it to
     * each mass.
     * 
     * @param assembly assembly containing masses
     * @param bounds bounds of area.
     */
    @Override
    public void applyForce (Assembly assembly, Dimension bounds) {
        List<Mass> massList = assembly.getMassList();
        double x = 0;
        double y = 0;

        // find center of mass
        for (Mass m : massList) {
            x += m.getX();
            y += m.getY();
        }
        x = x / massList.size();
        y = y / massList.size();

        for (Mass m : massList) {
            // calculate angle between center of mass and this mass
            double angle = Vector.angleBetween(m.getX() - x, m.getY() - y);
            // force magnitude = magnitude / (distance^exponent)
            double magnitude =
                    myMagnitude /
                            Math.pow(Vector.distanceBetween(m.getX() - x, m.getY() - y), myExponent);
            m.applyForce(new Vector(angle, magnitude));
        }
    }

}
