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
 * The Factory class is responsible for interpreting the input files and constructing the
 * appropriate objects.
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

    Assembly myAssembly = new Assembly();

    /**
     * Creates an assembly based on file input and passes it to the model.
     */
    public AssemblyFactory (Model model) {
        super(model);
    }
    
    @Override
    protected void loadItemsIntoModel () {
        if(myAssembly.hasMasses()){
            getModel().add(myAssembly);
        }
    }

    @Override
    protected void processInput (Scanner line, String type) {
        if (MASS_KEYWORD.equals(type)) {
            myAssembly.add(addMass(line));
        }
        else if (SPRING_KEYWORD.equals(type)) {
            myAssembly.add(addSpring(line));
        }
        else if (MUSCLE_KEYWORD.equals(type)) {
            myAssembly.add(addMuscle(line));
        }
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

}
