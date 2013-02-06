package simulation.forces;

import java.awt.Dimension;
import java.util.List;
import simulation.masses.Mass;
import util.Vector;


/**
 * Calculates the center of mass of all the masses and applies a force towards that point.
 * 
 * @author David Winegar
 * 
 */
public class CenterOfMassForce extends GlobalForce {

    // state used to determine force vector
    private double myMagnitude;
    private double myExponent;

    /**
     * Sets state.
     */
    public CenterOfMassForce (double magnitude, double exponent) {
        myMagnitude = magnitude;
        myExponent = exponent;
    }

    /**
     * Finds center of mass, calculates angle and magnitude vector for each mass, and applies it to
     * each mass.
     */
    @Override
    public void update (List<Mass> massList, Dimension bounds) {
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
            // magnitude = magnitude / (distance^exponent), because physics
            double magnitude =
                    myMagnitude /
                            Math.pow(Vector.distanceBetween(m.getX() - x, m.getY() - y), myExponent);
            m.applyForce(new Vector(angle, magnitude));
        }
    }

}
