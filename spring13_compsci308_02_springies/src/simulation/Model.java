package simulation;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import simulation.globalforces.GlobalForce;
import view.Canvas;


/**
 * The model is responsible for containing all of the objects in the physics simulator and calls
 * them
 * to update themselves through each iteration cycle.
 * 
 * @author Robert C. Duvall
 * @author David Winegar
 * @Author David Le
 */
public class Model {
    // bounds and input for game
    private Canvas myView;
    // simulation state
    private List<GlobalForce> myGlobalForces;
    private List<Assembly> myAssemblies;

    /**
     * Create a game of the given size with the given display for its shapes.
     */
    public Model (Canvas canvas) {
        myView = canvas;
        myGlobalForces = new ArrayList<GlobalForce>();
        myAssemblies = new ArrayList<Assembly>();
    }

    /**
     * Draw all elements of the simulation.
     */
    public void paint (Graphics2D pen) {
        for (Assembly a : myAssemblies) { 
            a.paint(pen);
        }
    }

    /**
     * Update simulation for this moment, given the time since the last moment.
     */
    public void update (double elapsedTime) {
        Dimension bounds = myView.getSize();
        for (Assembly a : myAssemblies) { 
            for (GlobalForce f : myGlobalForces) {
                f.update(a, bounds);
            }
            a.update(elapsedTime, bounds);
        }
    }

    /**
     * Add given force to this simulation.
     */
    public void add (GlobalForce force) {
        myGlobalForces.add(force);
    }
    
    /**
     * Add given assembly to this simulation.
     */
    public void add (Assembly assembly) {
        myAssemblies.add(assembly);
    }
    
    /**
     * Removes all assemblies from the simulation.
     */
    public void removeAllAssemblies () {
        myAssemblies = new ArrayList<Assembly>();
    }
    
    public void getLastKeyAndCallListener () {
        int keyPressed = myView.getLastKeyPressed();
        if (keyPressed != myView.NO_KEY_PRESSED) { 
            
        }
    }
}
