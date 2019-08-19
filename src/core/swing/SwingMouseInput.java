package core.swing;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Consumer;

import javax.swing.JFrame;

import core.Game;
import core.GamePanel;
import core.IMouseInput;
import core.SwingWindow;
import core.math.Vector2D;

/**
 * Listens to input from the mouse.
 * 
 * @see SwingWindow
 */
public class SwingMouseInput extends MouseAdapter implements IMouseInput {

    private SwingWindow window;
    private Game game;

    private JFrame frame;
    private GamePanel panel;

    private Vector2D mousePosition;
    private Vector2D mouseDelta;

    private HashMap<Long, Consumer<MouseEvent>> listeners;

    /**
     * 
     * @param game
     * @param window
     */
    public SwingMouseInput(Game game, SwingWindow window) {

        mousePosition = Vector2D.create();
        mouseDelta = Vector2D.create();
        
        this.window = window;
        this.game = game;

        frame = window.getFrame();
        panel = window.getPanel();

        frame.addMouseListener(this);
        frame.addMouseMotionListener(this);
        
        panel.addMouseListener(this);
        panel.addMouseMotionListener(this);
        
        // was true previously
        panel.setFocusable(false);
        panel.requestFocus();

        listeners = new HashMap<>();
    }

    @Override
    public long connect(Consumer<MouseEvent> listener) {
        long id = ThreadLocalRandom.current().nextLong();
        listeners.put(id, listener);
        return id;
    }

    @Override
    public void disconnect(long id) {
        listeners.remove(id);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        for (Consumer<MouseEvent> l : listeners.values()) {
            l.accept(e);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        for (Consumer<MouseEvent> l : listeners.values()) {
            l.accept(e);
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        for (Consumer<MouseEvent> l : listeners.values()) {
            l.accept(e);
        }
        updateMousePosition(e);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        for (Consumer<MouseEvent> l : listeners.values()) {
            l.accept(e);
        }
        updateMousePosition(e);
    }

    /**
     * 
     * @param e
     */
    protected void updateMousePosition(MouseEvent e) {
        mouseDelta.set(e.getX() - mousePosition.getX(), e.getY() - mousePosition.getY());
        mousePosition.set(e.getPoint());
    }

    /**
     * @return the mouseDelta
     */
    public Vector2D getDelta() {
        return mouseDelta;
    }

    /**
     * @return the mousePosition
     */
    public Vector2D getPosition() {
        return mousePosition;
    }

    /**
     * @return the window
     */
    public SwingWindow getWindow() {
        return window;
    }

}