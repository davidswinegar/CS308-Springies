package simulation;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import simulation.masses.Mass;
import simulation.springs.Spring;

public class Assembly {

    private List<Mass> myMasses;
    private List<Spring> mySprings;
    
    public Assembly () {
        myMasses = new ArrayList<Mass>();
        mySprings = new ArrayList<Spring>();
    }

    /**
     * Draw all elements of the assembly.
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
     * Update assembly for this moment, given the time since the last moment.
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
     */
    public void add (Mass mass) {
        myMasses.add(mass);
    }

    /**
     * Add given spring to this simulation.
     */
    public void add (Spring spring) {
        mySprings.add(spring);
    }
    
    public List<Mass> getMassList () {
        return myMasses;
    }
    
}