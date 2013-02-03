package simulation;

import java.awt.Dimension;
import java.util.List;

import util.GlobalForce;
import util.Vector;

/**
 * Creates a drag on mass.
 * @author David Winegar
 *
 */
public class ViscosityForce extends GlobalForce {

	// scale current vector by amount
	private double myScale;
	
	/**
	 * Sets state.
	 */
	public ViscosityForce (double scale){
		myScale = scale;
	}
	
	/**
	 * Gets current vector, scales and reverses it, and applies it.
	 */
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
