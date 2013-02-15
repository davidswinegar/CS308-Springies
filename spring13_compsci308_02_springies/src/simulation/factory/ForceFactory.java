package simulation.factory;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import simulation.Model;
import simulation.globalforces.CenterOfMassForce;
import simulation.globalforces.GlobalForce;
import simulation.globalforces.Gravity;
import simulation.globalforces.ViscosityForce;
import simulation.globalforces.wallrepulsionforces.BottomWallRepulsionForce;
import simulation.globalforces.wallrepulsionforces.LeftWallRepulsionForce;
import simulation.globalforces.wallrepulsionforces.RightWallRepulsionForce;
import simulation.globalforces.wallrepulsionforces.TopWallRepulsionForce;
import simulation.globalforces.wallrepulsionforces.WallRepulsionForce;
import simulation.listeners.GlobalForceListener;


/**
 * The ForceFactory class is responsible for interpreting the input files and constructing forces.
 * It creates one set of default forces and replaces them if new ones are read from the data file.
 * 
 * @author Robert C. Duvall
 * @author David Winegar
 * @author David Le
 */
public class ForceFactory extends Factory {
    // data file keywords
    private static final String GRAVITY_KEYWORD = "gravity";
    private static final String VISCOSITY_KEYWORD = "viscosity";
    private static final String CENTERMASS_KEYWORD = "centermass";
    private static final String WALL_KEYWORD = "wall";
    private static final int TOP_WALL_NUMBER = 1;
    private static final int RIGHT_WALL_NUMBER = 2;
    private static final int BOTTOM_WALL_NUMBER = 3;
    private static final int LEFT_WALL_NUMBER = 4;

    private Map<Integer, GlobalForce> myGlobalForces = new HashMap<Integer, GlobalForce>();

    /**
     * Passes model to superconstructor.
     * 
     * @param model creates forces for this model
     */
    public ForceFactory (Model model) {
        super(model);
        initializeGlobalForces();
    }

    /**
     * Reads input and calls helper methods to add forces to model.
     * 
     * @param line scanner of next information
     * @param type current line
     */
    @Override
    protected void processInput (Scanner line, String type) {

        if (GRAVITY_KEYWORD.equals(type)) {
            addGravity(line);
        }
        else if (VISCOSITY_KEYWORD.equals(type)) {
            addViscosity(line);
        }
        else if (CENTERMASS_KEYWORD.equals(type)) {
            addCenterMass(line);
        }
        else if (WALL_KEYWORD.equals(type)) {
            addWall(line);
        }
    }

    /**
     * Loads forces into model and adds listeners to Model.
     */
    @Override
    protected void loadItemsIntoModel () {
        Model model = getModel();

        for (Integer i : myGlobalForces.keySet()) {
            model.add(i, new GlobalForceListener(myGlobalForces.get(i)));
        }

        List<GlobalForce> forceList = new ArrayList<GlobalForce>(myGlobalForces.values());
        model.addGlobalForces(forceList);
    }

    /**
     * Initializes all global forces and puts them in a map.
     */
    public void initializeGlobalForces () {

        CenterOfMassForce centerOfMass = new CenterOfMassForce();
        myGlobalForces.put(KeyEvent.VK_M, centerOfMass);
        Gravity gravity = new Gravity();
        myGlobalForces.put(KeyEvent.VK_G, gravity);
        WallRepulsionForce topWallForce = new TopWallRepulsionForce();
        myGlobalForces.put(KeyEvent.VK_1, topWallForce);
        WallRepulsionForce rightWallForce = new RightWallRepulsionForce();
        myGlobalForces.put(KeyEvent.VK_2, rightWallForce);
        WallRepulsionForce bottomWallForce = new BottomWallRepulsionForce();
        myGlobalForces.put(KeyEvent.VK_3, bottomWallForce);
        WallRepulsionForce leftWallForce = new LeftWallRepulsionForce();
        myGlobalForces.put(KeyEvent.VK_4, leftWallForce);
        ViscosityForce viscosity = new ViscosityForce();
        myGlobalForces.put(KeyEvent.VK_V, viscosity);
    }

    /**
     * Replaces default gravity with data read from file
     * 
     * @param line information to be read
     */
    private void addGravity (Scanner line) {
        double angle = line.nextDouble();
        double magnitude = line.nextDouble();
        Gravity gravity = new Gravity(angle, magnitude);
        myGlobalForces.put(KeyEvent.VK_G, gravity);
    }

    /**
     * Replaces default viscosity with data read from file
     * 
     * @param line information to be read
     */
    private void addViscosity (Scanner line) {
        double scale = line.nextDouble();
        ViscosityForce viscosity = new ViscosityForce(scale);
        myGlobalForces.put(KeyEvent.VK_V, viscosity);
    }

    /**
     * Replaces default center of mass with data read from file
     * 
     * @param line information to be read
     */
    private void addCenterMass (Scanner line) {
        double magnitude = line.nextDouble();
        double exponent = line.nextDouble();
        CenterOfMassForce centerOfMass = new CenterOfMassForce(magnitude, exponent);
        myGlobalForces.put(KeyEvent.VK_M, centerOfMass);
    }

    /**
     * Replaces default wall repulsion force with data read from file
     * 
     * @param line information to be read
     */
    private void addWall (Scanner line) {
        int id = line.nextInt();
        double magnitude = line.nextDouble();
        double exponent = line.nextDouble();
        WallRepulsionForce wallForce = null;
        int key = 0;
        if (id == TOP_WALL_NUMBER) {
            wallForce = new TopWallRepulsionForce(magnitude, exponent);
            key = KeyEvent.VK_1;
        }
        else if (id == RIGHT_WALL_NUMBER) {
            wallForce = new RightWallRepulsionForce(magnitude, exponent);
            key = KeyEvent.VK_2;
        }
        else if (id == BOTTOM_WALL_NUMBER) {
            wallForce = new BottomWallRepulsionForce(magnitude, exponent);
            key = KeyEvent.VK_3;
        }
        else if (id == LEFT_WALL_NUMBER) {
            wallForce = new LeftWallRepulsionForce(magnitude, exponent);
            key = KeyEvent.VK_4;
        }
        myGlobalForces.put(key, wallForce);
    }

}
