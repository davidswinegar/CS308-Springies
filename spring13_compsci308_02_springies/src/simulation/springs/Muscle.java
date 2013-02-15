package simulation.springs;

import java.awt.Dimension;
import simulation.masses.Mass;
import view.Canvas;


/**
 * Connector between two masses which not only acts like a spring but also oscillates based on
 * amplitude
 * input in the environment.
 * 
 * @author David Le
 * @author David Winegar
 */
public class Muscle extends Spring {

    /**
     * Oscillation time for muscle
     */
    public static final int OSCILLATION_TIME = 5 * Canvas.FRAMES_PER_SECOND;

    private double myAmplitude;
    private double myInitialLength;
    private int myCounter;

    /**
     * Constructs muscle which will expand and contract
     * 
     * @param start mass to be connected to
     * @param end mass to be connected to
     * @param length of muscle
     * @param kVal springiness constant
     * @param amplitude of oscillation
     */
    public Muscle (Mass start, Mass end, double length, double kVal, double amplitude) {
        super(start, end, length, kVal);
        myAmplitude = amplitude;
        myInitialLength = length;
    }

    /**
     * Applies for on the two ends of the muscle as well as updates length to provide oscillation.
     * 
     * @param elapsedTime since last update
     * @param bounds of model
     */
    @Override
    public void update (double elapsedTime, Dimension bounds) {
        myCounter++;
        setLength(myInitialLength + myAmplitude *
                  Math.sin((myCounter * Math.PI) / (OSCILLATION_TIME / 2)));
        super.update(elapsedTime, bounds);
    }

}
