package simulation.factory;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import simulation.Assembly;
import simulation.Model;
import simulation.masses.FixedMass;
import simulation.masses.Mass;
import simulation.springs.Muscle;
import simulation.springs.Spring;


/**
 * The AssemblyFactory class is responsible for interpreting the input files and constructing an
 * assembly from the file.
 * 
 * @author Robert C. Duvall
 * @author David Winegar
 * @author David Le
 */
public class AssemblyFactory extends Factory {
    // data file keywords
    private static final String MASS_KEYWORD = "mass";
    private static final String SPRING_KEYWORD = "spring";
    private static final String MUSCLE_KEYWORD = "muscle";

    // mass IDs
    private Map<Integer, Mass> myMasses = new HashMap<Integer, Mass>();

    private Assembly myAssembly = new Assembly();

    /**
     * Passes model to superconstructor.
     * 
     * @param model creates assembly for this model
     */
    public AssemblyFactory (Model model) {
        super(model);
    }

    /**
     * If assembly has no masses, does not add it to the model. If assembly has masses, it gets
     * added to model.
     */
    @Override
    protected void loadItemsIntoModel () {
        if (myAssembly.hasMasses()) {
            getModel().add(myAssembly);
        }
    }

    /**
     * Reads input and calls helper methods to add objects to assembly.
     * 
     * @param line scanner of next information
     * @param type current line
     */
    @Override
    protected void processInput (Scanner line, String type) {
        if (MASS_KEYWORD.equals(type)) {
            addMass(line);
        }
        else if (SPRING_KEYWORD.equals(type)) {
            addSpring(line);
        }
        else if (MUSCLE_KEYWORD.equals(type)) {
            addMuscle(line);
        }
    }

    /**
     * Create mass from formatted data
     * @param line information to be read
     */
    private void addMass (Scanner line) {
        double x = line.nextDouble();
        double y = line.nextDouble();
        double mass = line.nextDouble();
        int id = myMasses.size() + 1;
        Mass result;
        if (mass > 0) {
            result = new Mass(x, y, mass);
        }
        else {
            result = new FixedMass(x, y, mass);
        }
        myMasses.put(id, result);

        myAssembly.add(result);
    }

    /**
     * Create spring from formatted data
     * @param line information to be read
     */
    private void addSpring (Scanner line) {
        Mass m1 = myMasses.get(line.nextInt());
        Mass m2 = myMasses.get(line.nextInt());
        double restLength = line.nextDouble();
        double ks = line.nextDouble();
        myAssembly.add(new Spring(m1, m2, restLength, ks));
    }

    /**
     * Create muscle from formatted data
     * @param line information to be read
     */
    private void addMuscle (Scanner line) {
        Mass m1 = myMasses.get(line.nextInt());
        Mass m2 = myMasses.get(line.nextInt());
        double restLength = line.nextDouble();
        double ks = line.nextDouble();
        double amplitude = line.nextDouble();
        myAssembly.add(new Muscle(m1, m2, restLength, ks, amplitude));
    }

}
