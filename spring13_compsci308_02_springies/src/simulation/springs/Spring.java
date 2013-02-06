package simulation.springs;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import simulation.masses.Mass;
import util.Location;
import util.Pixmap;
import util.Sprite;
import util.Vector;


/**
 * Spring connects two masses and acts upon the masses based on Hooke's Law.
 * 
 * @author Robert C. Duvall
 */
public class Spring extends Sprite {
    // reasonable default values
    public static final Pixmap DEFUALT_IMAGE = new Pixmap("spring.gif");
    public static final int IMAGE_HEIGHT = 20;

    private Mass myStart;
    private Mass myEnd;
    private double myLength;
    private double myK;

    /**
     * Constructs the spring, telling the spring which mass is the start and end point, how long the
     * spring's rest length is, and the k-springiness constant of the spring.
     */
    public Spring (Mass start, Mass end, double length, double kVal) {
        super(DEFUALT_IMAGE, getCenter(start, end), getSize(start, end));
        myStart = start;
        myEnd = end;
        myLength = length;
        myK = kVal;
    }

    /**
     * Paints the spring on the canvas.
     */
    @Override
    public void paint (Graphics2D pen) {
        pen.setColor(getColor(myStart.distance(myEnd) - myLength));
        pen.drawLine((int) myStart.getX(), (int) myStart.getY(), (int) myEnd.getX(),
                     (int) myEnd.getY());
    }

    /**
     * Updates the spring and applies force to the masses which it is connected to based on Hooke's
     * Law.
     */
    @Override
    public void update (double elapsedTime, Dimension bounds) {
        double dx = myStart.getX() - myEnd.getX();
        double dy = myStart.getY() - myEnd.getY();
        // apply hooke's law to each attached mass
        Vector force = new Vector(Vector.angleBetween(dx, dy),
                                  myK * (myLength - Vector.distanceBetween(dx, dy)));
        myStart.applyForce(force);
        force.negate();
        myEnd.applyForce(force);
        // update sprite values based on attached masses
        setCenter(getCenter(myStart, myEnd));
        setSize(getSize(myStart, myEnd));
        setVelocity(Vector.angleBetween(dx, dy), 0);
    }

    /**
     * Returns the length of the spring.
     * 
     * @param length
     * @return
     */
    public double getLength (double length) {
        return myLength;
    }

    /**
     * Sets the length of the spring.
     * 
     * @param length
     */
    public void setLength (double length) {
        myLength = length;
    }

    /**
     * Convenience method.
     */
    protected Color getColor (double diff) {
        if (Vector.fuzzyEquals(diff, 0))
            return Color.BLACK;
        else if (diff < 0.0)
            return Color.BLUE;
        else return Color.RED;
    }

    // compute center of this spring
    private static Location getCenter (Mass start, Mass end) {
        return new Location((start.getX() + end.getX()) / 2, (start.getY() + end.getY()) / 2);
    }

    // compute size of this spring
    private static Dimension getSize (Mass start, Mass end) {
        return new Dimension((int) start.distance(end), IMAGE_HEIGHT);
    }
}
