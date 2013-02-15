package simulation;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import simulation.masses.Mass;
import simulation.springs.Spring;
import simulation.springs.UserSpring;

/**
 * Assembly of masses and springs which will be put into the model simulation
 * @author David Winegar
 * @author David Le
 *
 */
public class Assembly {

    private List<Mass> myMasses;
    private List<Spring> mySprings;
    private UserSpring myUserSpring;

    /**
     * Constructs the assembly and initializes myMasses and mySprings
     */
    public Assembly () {
        myMasses = new ArrayList<Mass>();
        mySprings = new ArrayList<Spring>();
    }

    /**
     * Draw all elements of the assembly.
     * @param pen to draw elements on canvas
     */
    public void paint (Graphics2D pen) {
        for (Spring s : mySprings) {
            s.paint(pen);
        }
        for (Mass m : myMasses) {
            m.paint(pen);
        }
    }

    /**
     * Update springs and masses for this moment, given the time since the last moment.
     * @param elapsedTime since last update
     * @param bounds of model
     */
    public void update (double elapsedTime, Dimension bounds) {
        for (Spring s : mySprings) {
            s.update(elapsedTime, bounds);
        }
        for (Mass m : myMasses) {
            m.update(elapsedTime, bounds);
        }
    }

    /**
     * Add given mass to this simulation.
     * @param mass to be added
     */
    public void add (Mass mass) {
        myMasses.add(mass);
    }

    /**
     * Add given spring to this simulation.
     * @param spring to be added
     */
    public void add (Spring spring) {
        mySprings.add(spring);
    }

    /**
     * Add given user spring to this simulation
     * @param uspring to be added
     */
    public void add (UserSpring uspring) {
        myUserSpring = uspring;
        mySprings.add(uspring);
    }

    /**
     * Returns mass list
     * @return myMasses
     */
    public List<Mass> getMassList () {
        return myMasses;
    }

    /**
     * Removes the user spring if it is contained in this assembly
     */
    public void removeUserSpring () {
        if (myUserSpring != null) {
            mySprings.remove(myUserSpring);
            myUserSpring = null;
        }
    }

    /**
     * Returns true if there are masses in this assembly
     * @return true if empty
     */
    public boolean hasMasses () {
        return !myMasses.isEmpty();
    }

}
