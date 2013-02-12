package simulation.listeners;

import javax.swing.JFileChooser;
import simulation.Factory;
import simulation.Model;
import view.Canvas;

public class NewAssemblyListener implements Listener {

    private Model myModel;
    
    public NewAssemblyListener(Model model) {
        myModel = model;
    }
    // TODO 
    @Override
    public void takeAction () {
        Factory factory = new Factory();
        int response = Canvas.INPUT_CHOOSER.showOpenDialog(null);
        if (response == JFileChooser.APPROVE_OPTION) {
            factory.loadModel(myModel, Canvas.INPUT_CHOOSER.getSelectedFile());
        }
    }

}
