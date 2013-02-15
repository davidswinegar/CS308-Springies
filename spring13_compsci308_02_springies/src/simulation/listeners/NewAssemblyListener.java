package simulation.listeners;

import javax.swing.JFileChooser;
import simulation.Model;
import simulation.factory.AssemblyFactory;
import simulation.factory.Factory;
import view.Canvas;


/**
 * Listener which listens for the creation of a new assembly.
 * 
 * @author David Le
 * @author David Winegar
 */
public class NewAssemblyListener implements Listener {

    private Model myModel;

    /**
     * Constructs new "new assembly listener"
     * 
     * @param model to add the listener to
     */
    public NewAssemblyListener (Model model) {
        myModel = model;
    }

    @Override
    public void takeAction () {
        Factory factory = new AssemblyFactory(myModel);
        int response = Canvas.INPUT_CHOOSER.showDialog(null, "Assembly file");
        if (response == JFileChooser.APPROVE_OPTION) {
            factory.loadFile(Canvas.INPUT_CHOOSER.getSelectedFile());
        }
    }

}
