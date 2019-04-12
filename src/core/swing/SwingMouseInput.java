package core.swing;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;

import core.Game;
import core.GamePanel;
import core.IMouseInput;
import core.SwingWindow;
import core.math.Vector2D;
import core.obj.IGameObject;

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
        
        panel.setFocusable(true);
        panel.requestFocus();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        super.mouseReleased(e);

        for (IGameObject<?> obj : game.getLogic().getObjects()) {
            obj.mouseReleased(e);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        super.mousePressed(e);

        for (IGameObject<?> obj : game.getLogic().getObjects()) {
            obj.mousePressed(e);
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        super.mouseDragged(e);

        updateMousePosition(e);

        for (IGameObject<?> obj : game.getLogic().getObjects()) {
            obj.mouseDragged(e);
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        super.mouseMoved(e);

        updateMousePosition(e);

        for (IGameObject<?> obj : game.getLogic().getObjects()) {
            obj.mouseMoved(e);
        }
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