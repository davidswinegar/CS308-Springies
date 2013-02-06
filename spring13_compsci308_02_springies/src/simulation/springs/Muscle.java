package simulation.springs;

import java.awt.Dimension;
import simulation.masses.Mass;
import view.Canvas;


public class Muscle extends Spring {

    public static final int OSCILLATION_TIME = 5 * Canvas.FRAMES_PER_SECOND;

    private double myAmplitude;
    private double myInitialLength;
    private int myCounter;

    public Muscle (Mass start, Mass end, double length, double kVal, double amplitude) {
        super(start, end, length, kVal);
        myAmplitude = amplitude;
        myInitialLength = length;
    }

    @Override
    public void update (double elapsedTime, Dimension bounds) {
        myCounter++;
        setLength(myInitialLength + myAmplitude *
                  Math.sin((myCounter * Math.PI) / (OSCILLATION_TIME / 2)));
        super.update(elapsedTime, bounds);
    }

}
