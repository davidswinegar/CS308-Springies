package simulation;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import simulation.globalforces.GlobalForce;
import simulation.listeners.ClearAssemblyListener;
import simulation.listeners.DecreaseBorderListener;
import simulation.listeners.IncreaseBorderListener;
import simulation.listeners.Listener;
import simulation.listeners.NewAssemblyListener;
import simulation.masses.Mass;
import simulation.springs.UserSpring;
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
    public static final int MAX_DISTANCE = 9999;

    // bounds and input for game
    private Canvas myView;
    // simulation state
    private List<GlobalForce> myGlobalForces;
    private List<Assembly> myAssemblies;
    private Map<Integer, Listener> myListenerMap;
    private Dimension myBounds;
    private UserSpring myUserSpring;

    /**
     * Create a game of the given size with the given display for its shapes.
     */
    public Model (Canvas canvas) {
        myView = canvas;
        myGlobalForces = new ArrayList<GlobalForce>();
        myAssemblies = new ArrayList<Assembly>();
        myListenerMap = new HashMap<Integer, Listener>();
        myBounds = myView.getSize();
        addAllListeners();
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
        getLastKeyAndCallListener();
        if (myView.getMouseClick()) {
            updateUserSpring();
        }
        else if (myUserSpring != null) {
            myUserSpring.getAssembly().removeUserSpring();
            myUserSpring = null;
        }
        for (Assembly a : myAssemblies) {
            a.updateSprings(elapsedTime, myBounds);
            for (GlobalForce f : myGlobalForces) {
                f.applyForceIfToggledOn(a, myBounds);
            }
            a.updateMasses(elapsedTime, myBounds);
        }
    }

    public void changeBoundsSize (int amount) {
        myBounds.setSize(myBounds.getWidth() + amount, myBounds.getHeight() + amount);
    }

    private void updateUserSpring () {
        Point mousePosition = myView.getLastMousePosition();
        if (myUserSpring == null) {
            double minDistance = MAX_DISTANCE;
            Assembly targetAssembly = null;
            Mass targetMass = null;
            for (Assembly a : myAssemblies) {
                for (Mass m : a.getMassList()) {
                    double distance = mousePosition.distance(m.getX(), m.getY());
                    if (distance < minDistance) {
                        minDistance = distance;
                        targetAssembly = a;
                        targetMass = m;
                    }
                }
            }
            myUserSpring = new UserSpring(targetMass, mousePosition, targetAssembly);
            targetAssembly.add(myUserSpring);
        }
        else {
            myUserSpring.getEnd().setCenter(mousePosition.getX(), mousePosition.getY());
        }
    }

    /**
     * Add given forces to this simulation.
     */
    public void addGlobalForces (List<GlobalForce> forces) {
        myGlobalForces = forces;
    }

    // TODO temp method, remove later
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
     * Add given listener to this simulation.
     */
    public void add (int key, Listener listener) {
        myListenerMap.put(key, listener);
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
        myListenerMap.put(KeyEvent.VK_UP, new IncreaseBorderListener(this));
        myListenerMap.put(KeyEvent.VK_DOWN, new DecreaseBorderListener(this));
        myListenerMap.put(KeyEvent.VK_N, new NewAssemblyListener(this));
        myListenerMap.put(KeyEvent.VK_C, new ClearAssemblyListener(this));
    }

    public void getMouseAndCallListener (Map<Integer, Listener> listenerMap) {
        myListenerMap = listenerMap;
    }
}
