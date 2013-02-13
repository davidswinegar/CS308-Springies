package simulation.globalforces;

import java.awt.Dimension;
import simulation.Assembly;

/**
 * Global forces which are created depending on the environment input.
 * 
 * @author David Le & David Winegar
 *
 */
public abstract class GlobalForce {
    
    private boolean isCurrentlyOn = true;

    public void applyForceIfToggledOn (Assembly assembly, Dimension bounds) {
        if(isToggledOn()) {
            applyForce(assembly,bounds);
        }
    }
    
    public void applyForce (Assembly assembly, Dimension bounds){
        
    }

    public void toggle () {
        isCurrentlyOn = !isCurrentlyOn;
    }
    
    public boolean isToggledOn () {
        return isCurrentlyOn;
    }
}
