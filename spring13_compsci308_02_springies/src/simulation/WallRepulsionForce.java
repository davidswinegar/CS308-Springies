package simulation;

import java.awt.Dimension;
import java.util.List;

import util.GlobalForce;

public class WallRepulsionForce extends GlobalForce {
	
	double myDirection;
	double myMagnitude;
	double myExponent;
	
	// still TODO finish - maybe extend to make 4 classes, 1 for each wall?
	public WallRepulsionForce(double wallID, double magnitude, double exponent){
		myMagnitude = magnitude;
		myExponent = exponent;
		// converts wallID to angle
		myDirection = wallID * 90;
	}

	@Override
	public void update(List<Mass> massList, Dimension bounds) {
		for(Mass m : massList){
			
		}

	}

}
