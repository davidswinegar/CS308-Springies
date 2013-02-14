package simulation;

import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.List;
import simulation.globalforces.GlobalForce;
import simulation.globalforces.CenterOfMassForce;
import simulation.globalforces.Gravity;
import simulation.globalforces.ViscosityForce;
import simulation.globalforces.wallrepulsionforces.BottomWallRepulsionForce;
import simulation.globalforces.wallrepulsionforces.LeftWallRepulsionForce;
import simulation.globalforces.wallrepulsionforces.RightWallRepulsionForce;
import simulation.globalforces.wallrepulsionforces.TopWallRepulsionForce;
import simulation.globalforces.wallrepulsionforces.WallRepulsionForce;
import simulation.listeners.GlobalForceListener;
import simulation.masses.FixedMass;
import simulation.masses.Mass;
import simulation.springs.Muscle;
import simulation.springs.Spring;


/**
 * The Factory class is responsible for interpreting the input files and constructing the
 * appropriate objects.
 * 
 * @author Robert C. Duvall
 * @author David Winegar
 * @author David Le
 */
public class Factory {
    // data file keywords
    private static final String MASS_KEYWORD = "mass";
    private static final String SPRING_KEYWORD = "spring";
    private static final String MUSCLE_KEYWORD = "muscle";
    private static final String GRAVITY_KEYWORD = "gravity";
    private static final String VISCOSITY_KEYWORD = "viscosity";
    private static final String CENTERMASS_KEYWORD = "centermass";
    private static final String WALL_KEYWORD = "wall";

    // mass IDs
    private Map<Integer, Mass> myMasses = new HashMap<Integer, Mass>();

    private Model mySimulation;
    /**
     * Creates an assembly based on file input and passes it to the model.
     */
    
    public Factory (Model model) {
        mySimulation = model;
    }
    
    public void loadAssembly (File modelFile) {
        Assembly currentAssembly = new Assembly();
        try {
            Scanner input = new Scanner(modelFile);
            while (input.hasNext()) {
                Scanner line = new Scanner(input.nextLine());
                if (line.hasNext()) {
                    String type = line.next();
                    if (MASS_KEYWORD.equals(type)) {
                        currentAssembly.add(addMass(line));
                    }
                    else if (SPRING_KEYWORD.equals(type)) {
                        currentAssembly.add(addSpring(line));
                    }
                    else if (MUSCLE_KEYWORD.equals(type)) {
                        currentAssembly.add(addMuscle(line));
                    }
                }
            }
            input.close();
        }
        catch (FileNotFoundException e) {
            // should not happen because File came from user selection
            e.printStackTrace();
        }
        mySimulation.add(currentAssembly);
    }

    /**
     * Loads environment based on the file input (environment.xsp).
     * 
     * @param model
     * @param modelFile
     */
    public void loadEnvironment (File modelFile) {
        try {
            Scanner input = new Scanner(modelFile);
            while (input.hasNext()) {
                Scanner line = new Scanner(input.nextLine());
                if (line.hasNext()) {
                    String type = line.next();
                    if (GRAVITY_KEYWORD.equals(type)) {
                        mySimulation.add(addGravity(line));
                    }
                    else if (VISCOSITY_KEYWORD.equals(type)) {
                        mySimulation.add(addViscosity(line));
                    }
                    else if (CENTERMASS_KEYWORD.equals(type)) {
                        mySimulation.add(addCenterMass(line));
                    }
                    else if (WALL_KEYWORD.equals(type)) {
                        mySimulation.add(addWall(line));
                    }
                }
            }
            input.close();
        }
        catch (FileNotFoundException e) {
            // should not happen because File came from user selection
            e.printStackTrace();
        }
        
    }
    
    public List<GlobalForce> initializeGlobalForces () {
        List<GlobalForce> forces = new ArrayList<GlobalForce>();
        CenterOfMassForce centerOfMass = new CenterOfMassForce();
        mySimulation.add(KeyEvent.VK_M, new GlobalForceListener(centerOfMass));
        Gravity gravity = new Gravity();
        mySimulation.add(KeyEvent.VK_G, new GlobalForceListener(gravity));
        WallRepulsionForce topWallForce = new TopWallRepulsionForce();
        mySimulation.add(KeyEvent.VK_1, new GlobalForceListener(topWallForce));
        WallRepulsionForce rightWallForce = new RightWallRepulsionForce();
        mySimulation.add(KeyEvent.VK_2, new GlobalForceListener(rightWallForce));
        WallRepulsionForce bottomWallForce = new BottomWallRepulsionForce();
        mySimulation.add(KeyEvent.VK_3, new GlobalForceListener(bottomWallForce));
        WallRepulsionForce leftWallForce = new LeftWallRepulsionForce();
        mySimulation.add(KeyEvent.VK_4, new GlobalForceListener(leftWallForce));
        ViscosityForce viscosity = new ViscosityForce();
        mySimulation.add(KeyEvent.VK_V, new GlobalForceListener(viscosity));
        return forces;
    }

    // create mass from formatted data
    private Mass addMass (Scanner line) {
        int id = line.nextInt();
        double x = line.nextDouble();
        double y = line.nextDouble();
        double mass = line.nextDouble();
        Mass result;
        if (mass > 0) {
            result = new Mass(x, y, mass);
        }
        else {
            result = new FixedMass(x, y, mass);
        }
        myMasses.put(id, result);
        return result;
    }

    // create spring from formatted data
    private Spring addSpring (Scanner line) {
        Mass m1 = myMasses.get(line.nextInt());
        Mass m2 = myMasses.get(line.nextInt());
        double restLength = line.nextDouble();
        double ks = line.nextDouble();
        return new Spring(m1, m2, restLength, ks);
    }

    // create muscle from formatted data
    private Muscle addMuscle (Scanner line) {
        Mass m1 = myMasses.get(line.nextInt());
        Mass m2 = myMasses.get(line.nextInt());
        double restLength = line.nextDouble();
        double ks = line.nextDouble();
        double amplitude = line.nextDouble();
        return new Muscle(m1, m2, restLength, ks, amplitude);
    }

    // create gravity from formatted data
    private Gravity addGravity (Scanner line) {
        double angle = line.nextDouble();
        double magnitude = line.nextDouble();
        Gravity gravity = new Gravity(angle, magnitude);
        mySimulation.add(KeyEvent.VK_G, new GlobalForceListener(gravity));
        return gravity;
    }

    // create gravity from formatted data
    private ViscosityForce addViscosity (Scanner line) {
        double scale = line.nextDouble();
        ViscosityForce viscosity = new ViscosityForce(scale);
        mySimulation.add(KeyEvent.VK_V, new GlobalForceListener(viscosity));
        return viscosity;
    }

    // create gravity from formatted data
    private CenterOfMassForce addCenterMass (Scanner line) {
        double magnitude = line.nextDouble();
        double exponent = line.nextDouble();
        CenterOfMassForce centerOfMass = new CenterOfMassForce(magnitude, exponent);
        mySimulation.add(KeyEvent.VK_M, new GlobalForceListener(centerOfMass));
        return centerOfMass;
    }

    // create gravity from formatted data
    private WallRepulsionForce addWall (Scanner line) {
        int id = line.nextInt();
        double magnitude = line.nextDouble();
        double exponent = line.nextDouble();
        WallRepulsionForce[] forces = { new TopWallRepulsionForce(magnitude, exponent),
                                       new RightWallRepulsionForce(magnitude, exponent),
                                       new BottomWallRepulsionForce(magnitude, exponent),
                                       new LeftWallRepulsionForce(magnitude, exponent)
        };
        int[] keys = { KeyEvent.VK_UP, KeyEvent.VK_RIGHT, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT };
        WallRepulsionForce wallForce = forces[id - 1];
        int key = keys[id - 1];
        mySimulation.add(key, new GlobalForceListener(wallForce));
        return wallForce;
    }
}
