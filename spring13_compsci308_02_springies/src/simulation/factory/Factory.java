package simulation.factory;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import simulation.Model;


/**
 * The factory abstract class loads a text configuration file and loads various information about it
 * into the model.
 * 
 * @author Robert C. Duvall
 * @author David Winegar
 * @author David Le
 * 
 */
public abstract class Factory {

    private Model myModel;

    /**
     * Stores model to later pass objects to.
     * 
     * @param model passes new objects to this model
     */
    public Factory (Model model) {
        myModel = model;
    }

    /**
     * Getter for subclasses for model.
     * 
     * @return model for this factory.
     */
    protected Model getModel () {
        return myModel;
    }

    /**
     * Scans information in file and calls a processInput, calls loadItemsIntoModel to load
     * everything into the model after it is finished.
     * 
     * @param modelFile the file to be processed
     */
    public void loadFile (File modelFile) {

        try {
            Scanner input = new Scanner(modelFile);
            while (input.hasNext()) {
                Scanner line = new Scanner(input.nextLine());
                if (line.hasNext()) {
                    String type = line.next();
                    processInput(line, type);
                }
            }
            input.close();
            // loads items
            loadItemsIntoModel();
        }
        catch (FileNotFoundException e) {
            // should not happen because File came from user selection
            e.printStackTrace();
        }
    }

    /**
     * Processes each line of information.
     * 
     * @param line scanner of next information
     * @param type current line
     */
    protected abstract void processInput (Scanner line, String type);

    /**
     * Loads items that have been read into model.
     */
    protected abstract void loadItemsIntoModel ();
}
