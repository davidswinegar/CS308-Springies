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
import simulation.masses.Mass;


/**
 * The Factory class is responsible for interpreting the input files and constructing the
 * appropriate objects.
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
    
    private Map<Integer, GlobalForce> myGlobalForces = new HashMap<Integer, GlobalForce>();

    /**
     * Creates an assembly based on file input and passes it to the model.
     */
    public ForceFactory (Model model) {
        super(model);
    }

    @Override
    protected void processInput (Scanner line, String type) {
        if (GRAVITY_KEYWORD.equals(type)) {
            getModel().add(addGravity(line));
        }
        else if (VISCOSITY_KEYWORD.equals(type)) {
            getModel().add(addViscosity(line));
        }
        else if (CENTERMASS_KEYWORD.equals(type)) {
            getModel().add(addCenterMass(line));
        }
        else if (WALL_KEYWORD.equals(type)) {
            getModel().add(addWall(line));
        }
    }
    
    @Override
    protected void initializeReadFile () {
        getModel().clearAllAssemblies();
        initializeGlobalForces();
    }

    public void initializeGlobalForces () {
        Model model = getModel();
        List<GlobalForce> forces = new ArrayList<GlobalForce>();
        CenterOfMassForce centerOfMass = new CenterOfMassForce();
        model.add(KeyEvent.VK_M, new GlobalForceListener(centerOfMass));
        Gravity gravity = new Gravity();
        model.add(KeyEvent.VK_G, new GlobalForceListener(gravity));
        WallRepulsionForce topWallForce = new TopWallRepulsionForce();
        model.add(KeyEvent.VK_1, new GlobalForceListener(topWallForce));
        WallRepulsionForce rightWallForce = new RightWallRepulsionForce();
        model.add(KeyEvent.VK_2, new GlobalForceListener(rightWallForce));
        WallRepulsionForce bottomWallForce = new BottomWallRepulsionForce();
        model.add(KeyEvent.VK_3, new GlobalForceListener(bottomWallForce));
        WallRepulsionForce leftWallForce = new LeftWallRepulsionForce();
        model.add(KeyEvent.VK_4, new GlobalForceListener(leftWallForce));
        ViscosityForce viscosity = new ViscosityForce();
        model.add(KeyEvent.VK_V, new GlobalForceListener(viscosity));
    }

    // create gravity from formatted data
    private Gravity addGravity (Scanner line) {
        double angle = line.nextDouble();
        double magnitude = line.nextDouble();
        Gravity gravity = new Gravity(angle, magnitude);
        getModel().add(KeyEvent.VK_G, new GlobalForceListener(gravity));
        return gravity;
    }

    // create gravity from formatted data
    private ViscosityForce addViscosity (Scanner line) {
        double scale = line.nextDouble();
        ViscosityForce viscosity = new ViscosityForce(scale);
        getModel().add(KeyEvent.VK_V, new GlobalForceListener(viscosity));
        return viscosity;
    }

    // create gravity from formatted data
    private CenterOfMassForce addCenterMass (Scanner line) {
        double magnitude = line.nextDouble();
        double exponent = line.nextDouble();
        CenterOfMassForce centerOfMass = new CenterOfMassForce(magnitude, exponent);
        getModel().add(KeyEvent.VK_M, new GlobalForceListener(centerOfMass));
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
        try{
            
        } catch(IndexOutOfBoundsException e){
            // TODO make catch nicer
            e.printStackTrace();
            System.out.println("malformed data file");
        }
        int[] keys = { KeyEvent.VK_UP, KeyEvent.VK_RIGHT, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT };
        WallRepulsionForce wallForce = forces[id - 1];
        int key = keys[id - 1];
        getModel().add(key, new GlobalForceListener(wallForce));
        return wallForce;
    }

    

}
