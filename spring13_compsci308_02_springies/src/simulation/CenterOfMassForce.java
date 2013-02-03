package simulation;

import java.awt.Dimension;
import java.util.List;

import util.GlobalForce;
import util.Vector;

/**
 * Calculates the center of mass of all the masses and applies a force towards that point.
 * @author David Winegar
 *
 */
public class CenterOfMassForce extends GlobalForce {
	
	// state used to determine force vector
	private double myMagnitude;
	private double myExponent;
	
	public CenterOfMassForce(double magnitude, double exponent){
		myMagnitude = magnitude;
		myExponent = exponent;
	}

	@Override
	public void update(List<Mass> massList, Dimension bounds) {
		double x = 0;
		double y = 0;
		
		// find center of mass
		for(Mass m : massList){
			x += m.getX();
			y += m.getY();
		}
		x = x / massList.size();
		y = y / massList.size();
		
		for(Mass m : massList){
			// calculate angle between center of mass and this mass
			double angle = Vector.angleBetween(m.getX() - x, m.getY() - y);
			// magnitude = magnitude / (distance^exponent), because physics
			double magnitude = myMagnitude / Math.pow(Vector.distanceBetween(m.getX() - x, m.getY() - y), myExponent);
			m.applyForce(new Vector(angle, magnitude));
		}
	}

}
