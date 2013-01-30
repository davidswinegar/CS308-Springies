package simulation;

import java.awt.Dimension;
import java.util.List;

import util.GlobalForce;
import util.Vector;

public class ViscosityForce extends GlobalForce {

	double myScale;
	
	public ViscosityForce (double scale){
		myScale = scale;
	}
	
	@Override
	public void update(List<Mass> massList, Dimension bounds) {
		for(Mass m : massList){
			Vector v = new Vector(m.getVelocity());
			v.negate();
			v.scale(myScale);
			m.applyForce(v);
		}
	}

}
