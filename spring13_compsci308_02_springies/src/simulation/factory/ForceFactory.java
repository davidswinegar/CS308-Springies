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
    
    @Override
    protected void loadItemsIntoModel () {
        Model model = getModel();
        
        for(Integer i : myGlobalForces.keySet()) {
            model.add(i, new GlobalForceListener(myGlobalForces.get(i)));
        }
        
        List<GlobalForce> forceList = new ArrayList<GlobalForce>(myGlobalForces.values());
        model.addGlobalForces(forceList);        
    }
    
    @Override
    protected void initializeReadFile () {
        initializeGlobalForces();
    }

    public void initializeGlobalForces () {
        
        CenterOfMassForce centerOfMass = new CenterOfMassForce();
        myGlobalForces.put(KeyEvent.VK_M, centerOfMass);
        Gravity gravity = new Gravity();
        myGlobalForces.put(KeyEvent.VK_G, gravity);
        WallRepulsionForce topWallForce = new TopWallRepulsionForce();
        myGlobalForces.put(KeyEvent.VK_1,topWallForce);
        WallRepulsionForce rightWallForce = new RightWallRepulsionForce();
        myGlobalForces.put(KeyEvent.VK_2, rightWallForce);
        WallRepulsionForce bottomWallForce = new BottomWallRepulsionForce();
        myGlobalForces.put(KeyEvent.VK_3, bottomWallForce);
        WallRepulsionForce leftWallForce = new LeftWallRepulsionForce();
        myGlobalForces.put(KeyEvent.VK_4, leftWallForce);
        ViscosityForce viscosity = new ViscosityForce();
        myGlobalForces.put(KeyEvent.VK_V, viscosity);
    }

    // create gravity from formatted data
    private void addGravity (Scanner line) {
        double angle = line.nextDouble();
        double magnitude = line.nextDouble();
        Gravity gravity = new Gravity(angle, magnitude);
        myGlobalForces.put(KeyEvent.VK_G, gravity);
    }

    // create gravity from formatted data
    private void addViscosity (Scanner line) {
        double scale = line.nextDouble();
        ViscosityForce viscosity = new ViscosityForce(scale);
        myGlobalForces.put(KeyEvent.VK_V, viscosity);
    }

    // create gravity from formatted data
    private void addCenterMass (Scanner line) {
        double magnitude = line.nextDouble();
        double exponent = line.nextDouble();
        CenterOfMassForce centerOfMass = new CenterOfMassForce(magnitude, exponent);
        myGlobalForces.put(KeyEvent.VK_M, centerOfMass);
    }

    // create gravity from formatted data
    private void addWall (Scanner line) {
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
        myGlobalForces.put(key, wallForce);
    }

    

}
