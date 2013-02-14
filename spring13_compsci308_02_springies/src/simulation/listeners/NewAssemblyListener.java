package simulation.listeners;

import javax.swing.JFileChooser;
import simulation.Model;
import simulation.factory.AssemblyFactory;
import simulation.factory.Factory;
import view.Canvas;


public class NewAssemblyListener implements Listener {

    private Model myModel;

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
