package simulation;

import java.awt.Dimension;
import java.util.List;

import util.GlobalForce;
import util.Vector;

public class CenterOfMassForce extends GlobalForce {
	
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
		for(Mass m : massList){
			x += m.getX();
			y += m.getY();
		}
		x = x / massList.size();
		y = y / massList.size();
		
		for(Mass m : massList){
			double angle = Vector.angleBetween(m.getX() - x, m.getY() - y);
			double magnitude = myMagnitude / Math.pow(Vector.distanceBetween(m.getX() - x, m.getY() - y), myExponent);
			m.applyForce(new Vector(angle, magnitude));
		}
	}

}
