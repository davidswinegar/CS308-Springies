package simulation.forces;

import util.Vector;

/**
 * General force class that is applied to masses to move them.
 * 
 * @author David Le
 *
 */
public class Force extends Vector {
    
    public Force() {
        this(0,0);
    }
    
    public Force (double angle, double magnitude) {
        super(angle, magnitude);
    }
    
}
