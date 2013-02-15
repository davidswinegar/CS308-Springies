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
 * @author David Le
 * @author David Winegar
 */
public class Mass extends Sprite {
    // reasonable default values
    /**
     * Default dimensions of mass
     */
    public static final Dimension DEFAULT_SIZE = new Dimension(16, 16);
    /**
     * Default image for mass
     */
    public static final Pixmap DEFUALT_IMAGE = new Pixmap("mass.gif");

    private Vector myTotalForce;

    /**
     * Constructs the Mass given its position and mass.
     * @param x coordinate of mass
     * @param y coordinate of mass
     * @param mass weight
     */
    public Mass (double x, double y, double mass) {
        super(DEFUALT_IMAGE, new Location(x, y), DEFAULT_SIZE);
        myTotalForce = new Vector();
    }

    /**
     * Updates the mass to move according to forces and be redrawn.
     * @param elapsedTime since last update
     * @param bounds of model
     */
    @Override
    public void update (double elapsedTime, Dimension bounds) {
        applyForce(getBounce(bounds));
        // convert force into acceleration
        // myTotalForce.scale(1/myMass);
        // and then Mover's velocity
        getVelocity().sum(myTotalForce);
        myTotalForce.reset();
        // move mass by velocity
        super.update(elapsedTime, bounds);
    }

    /**
     * Redraws mass.
     * @param pen to draw mass on canvas
     */
    @Override
    public void paint (Graphics2D pen) {
        pen.setColor(Color.BLACK);
        pen.fillOval((int) getLeft(), (int) getTop(), (int) getWidth(), (int) getHeight());
    }

    /**
     * Use the given force to change this mass's acceleration.
     * @param force to be applied
     */
    public void applyForce (Vector force) {
        myTotalForce.sum(force);
    }

    /**
     * Convenience method.
     * @param other mass to calculate distance to
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
