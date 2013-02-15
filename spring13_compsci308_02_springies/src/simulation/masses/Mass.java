package simulation.masses;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import util.Location;
import util.Pixmap;
import util.Sprite;
import util.Vector;


/**
 * Mass object which is acted upon by various forces in this physics simulator.
 * 
 * @author Robert C. Duvall
 */
public class Mass extends Sprite {
    // reasonable default values
    public static final Dimension DEFAULT_SIZE = new Dimension(16, 16);
    public static final Pixmap DEFUALT_IMAGE = new Pixmap("mass.gif");

    private double myMass;
    private Vector myTotalForce;
    private double myForceScale;

    /**
     * Constructs the Mass given its position and mass.
     */
    public Mass (double x, double y, double mass) {
        super(DEFUALT_IMAGE, new Location(x, y), DEFAULT_SIZE);
        myMass = mass;
        myTotalForce = new Vector();
    }

    /**
     * Updates the mass to move according to forces and be redrawn.
     */
    @Override
    public void update (double elapsedTime, Dimension bounds) {
        applyForce(getBounce(bounds));
        // scale force
       // myTotalForce.scale(myForceScale);
        // convert force into acceleration  and then Mover's velocity
        //myTotalForce.scale(1/myMass);
        getVelocity().sum(myTotalForce);
        myTotalForce.reset();
        // move mass by velocity
        super.update(elapsedTime, bounds);
    }

    /**
     * Redraws mass.
     */
    @Override
    public void paint (Graphics2D pen) {
        pen.setColor(Color.BLACK);
        pen.fillOval((int) getLeft(), (int) getTop(), (int) getWidth(), (int) getHeight());
    }

    /**
     * Use the given force to change this mass's acceleration.
     */
    public void applyForce (Vector force) {
        myTotalForce.sum(force);
    }

    /**
     * Scales the acceleration by the amount passed in.
     * 
     * @param change
     */
    public void scaleAcceleration (double change) {
        myForceScale = change;
    }

    /**
     * Convenience method.
     */
    public double distance (Mass other) {
        // this is a little awkward, so hide it
        return new Location(getX(), getY()).distance(new Location(other.getX(), other.getY()));
    }

    // check for move out of bounds
    private Vector getBounce (Dimension bounds) {
        final double IMPULSE_MAGNITUDE = 2;
        Vector impulse = new Vector();
        if (getLeft() < 0) {
            impulse = new Vector(RIGHT_DIRECTION, IMPULSE_MAGNITUDE);
        }
        else if (getRight() > bounds.width) {
            impulse = new Vector(LEFT_DIRECTION, IMPULSE_MAGNITUDE);
        }
        if (getTop() < 0) {
            impulse = new Vector(DOWN_DIRECTION, IMPULSE_MAGNITUDE);
        }
        else if (getBottom() > bounds.height) {
            impulse = new Vector(UP_DIRECTION, IMPULSE_MAGNITUDE);
        }
        impulse.scale(getVelocity().getRelativeMagnitude(impulse));
        return impulse;
    }
}
