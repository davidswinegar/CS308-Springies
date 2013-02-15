package simulation.factory;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import simulation.Model;


public abstract class Factory {

    private Model myModel;

    public Factory (Model model) {
        myModel = model;
    }

    protected Model getModel () {
        return myModel;
    }

    public void loadFile (File modelFile) {
        initializeReadFile();

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
            loadItemsIntoModel();
        }
        catch (FileNotFoundException e) {
            // should not happen because File came from user selection
            e.printStackTrace();
        }
    }

    protected abstract void processInput (Scanner line, String type);

    protected void initializeReadFile () {
    }

    protected abstract void loadItemsIntoModel ();
}
