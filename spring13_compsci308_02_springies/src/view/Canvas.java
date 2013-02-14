package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.Timer;
import simulation.Factory;
import simulation.Model;


/**
 * Creates an area of the screen in which the game will be drawn that supports:
 * <UL>
 * <LI>animation via the Timer
 * <LI>mouse input via the MouseListener and MouseMotionListener
 * <LI>keyboard input via the KeyListener
 * </UL>
 * 
 * @author Robert C Duvall
 * @author David Winegar
 * @author David Le
 */
public class Canvas extends JComponent {
    // default serialization ID
    private static final long serialVersionUID = 1L;
    // animate 25 times per second if possible
    public static final int FRAMES_PER_SECOND = 25;
    // better way to think about timed events (in milliseconds)
    public static final int ONE_SECOND = 1000;
    public static final int DEFAULT_DELAY = ONE_SECOND / FRAMES_PER_SECOND;
    // only one so that it maintains user's preferences
    public static final JFileChooser INPUT_CHOOSER =
            new JFileChooser(System.getProperties().getProperty("user.dir"));
    // input state
    public static final int NO_KEY_PRESSED = -1;
    public static final Point NO_MOUSE_PRESSED = null;
    public static final String ENVIRONMENT_NAME = "environment.xsp";

    // drives the animation
    private Timer myTimer;
    // game to be animated
    private Model mySimulation;
    // input state
    private int myLastKeyPressed;
    private Point myLastMousePosition;
    private boolean myMouseClick;
    private Set<Integer> myKeys;
    private Factory myFactory;

    /**
     * Create a panel so that it knows its size
     */
    public Canvas (Dimension size) {
        // set size (a bit of a pain)
        setPreferredSize(size);
        setSize(size);
        // prepare to receive input
        setFocusable(true);
        requestFocus();
        setInputListeners();
    }

    /**
     * Paint the contents of the canvas.
     * 
     * Never called by you directly, instead called by Java runtime
     * when area of screen covered by this container needs to be
     * displayed (i.e., creation, uncovering, change in status)
     * 
     * @param pen used to paint shape on the screen
     */
    @Override
    public void paintComponent (Graphics pen) {
        pen.setColor(Color.WHITE);
        pen.fillRect(0, 0, getSize().width, getSize().height);
        // first time needs to be special cased :(
        if (mySimulation != null) {
            mySimulation.paint((Graphics2D) pen);
        }
    }

    /**
     * Returns last key pressed by the user or -1 if nothing is pressed.
     */
    public int getLastKeyPressed () {
        int keyPressed = myLastKeyPressed;
        myLastKeyPressed = NO_KEY_PRESSED;
        return keyPressed;
    }

    /**
     * Returns all keys currently pressed by the user.
     */
    public Collection<Integer> getKeysPressed () {
        return Collections.unmodifiableSet(myKeys);
    }

    /**
     * Returns last position of the mouse in the canvas.
     */
    public Point getLastMousePosition () {
        return myLastMousePosition;
    }
    
    /**
     * Returns true if mouse is currently held down.
     * 
     * @return
     */
    public boolean getMouseClick () {
        return myMouseClick;
    }

    /**
     * Start the animation.
     */
    public void start () {
        // create a timer to animate the canvas
        myTimer = new Timer(DEFAULT_DELAY,
                            new ActionListener() {
                                @Override
                                public void actionPerformed (ActionEvent e) {
                                    step();
                                }
                            });
        // start animation
        mySimulation = new Model(this);
        loadModel();
        loadEnvironment();
        myTimer.start();
    }

    /**
     * Stop the animation.
     */
    public void stop () {
        myTimer.stop();
    }

    /**
     * Take one step in the animation.
     */
    public void step () {
        mySimulation.update((double) FRAMES_PER_SECOND / ONE_SECOND);
        // indirectly causes paint to be called
        repaint();
    }

    /**
     * Create listeners that will update state based on user input.
     */
    private void setInputListeners () {
        // initialize input state
        myLastKeyPressed = NO_KEY_PRESSED;
        myKeys = new TreeSet<Integer>();
        addKeyListener(new KeyAdapter() {
            @Override
            //testing a key release method. this one is slightly bugged for me but works slightly better. -David Le
            public void keyPressed (KeyEvent e) {
                // resets key after being used once. Does not affect getKeysPressed.
           /*     if(myLastKeyPressed == e.getKeyCode()){
                    myLastKeyPressed = NO_KEY_PRESSED;
                } else {
                    myLastKeyPressed = e.getKeyCode();
                }
                myKeys.add(e.getKeyCode());*/
            }

            @Override
            public void keyReleased (KeyEvent e) {
                //myKeys.remove(e.getKeyCode());
                myLastKeyPressed = e.getKeyCode();
            }
        });
        myLastMousePosition = NO_MOUSE_PRESSED;
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged (MouseEvent e) {
                myLastMousePosition = e.getPoint();
            }
        });
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed (MouseEvent e) {
                myLastMousePosition = e.getPoint();
                myMouseClick = true;
            }

            @Override
            public void mouseReleased (MouseEvent e) {
                myLastMousePosition = NO_MOUSE_PRESSED;
                myMouseClick = false;
            }
        });
    }

    // load model from file chosen by user
    private void loadModel () {
        myFactory = new Factory(mySimulation);
        int response = INPUT_CHOOSER.showDialog(null, "Assembly file");
        if (response == JFileChooser.APPROVE_OPTION) {
            myFactory.loadAssembly(INPUT_CHOOSER.getSelectedFile());
        }
    }

    // loads environment file chosen by user and checks for environment name match
    private void loadEnvironment () {
        int response = INPUT_CHOOSER.showDialog(null, "Environment file");
        String responseName = INPUT_CHOOSER.getSelectedFile().getName();
        if (response == JFileChooser.APPROVE_OPTION && responseName.equals(ENVIRONMENT_NAME)) {
            myFactory.loadEnvironment(INPUT_CHOOSER.getSelectedFile());
        }
    }
}
