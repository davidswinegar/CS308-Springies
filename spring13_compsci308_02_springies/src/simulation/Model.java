package simulation;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import simulation.globalforces.GlobalForce;
import simulation.listeners.Listener;
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
    private Map<Integer, Listener> myListenerMap;
    private Dimension myBounds;

    /**
     * Create a game of the given size with the given display for its shapes.
     */
    public Model (Canvas canvas) {
        myView = canvas;
        myGlobalForces = new ArrayList<GlobalForce>();
        myAssemblies = new ArrayList<Assembly>();
        myListenerMap = new HashMap<Integer, Listener>();
        myBounds = myView.getSize();
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
        for (Assembly a : myAssemblies) { 
            for (GlobalForce f : myGlobalForces) {
                f.update(a, myBounds);
            }
            a.update(elapsedTime, myBounds);
        }
    }
    
    public void changeBoundsSize (int amount) {
        myBounds.setSize(myBounds.getWidth() + amount, myBounds.getHeight() + amount);
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
    public void clearAllAssemblies () {
        myAssemblies = new ArrayList<Assembly>();
    }
    
    public void getLastKeyAndCallListener () {
        int keyPressed = myView.getLastKeyPressed();
        if (myListenerMap.containsKey(keyPressed)) { 
            myListenerMap.get(keyPressed).takeAction();
        }
    }
    
    public void addAllListeners () {
        
    }
    
    public void getMouseAndCallListener (Map<Integer, Listener> listenerMap) {
        myListenerMap = listenerMap;
    }
}
