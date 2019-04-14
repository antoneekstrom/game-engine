package core.swing;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.function.Consumer;

import javax.swing.JFrame;

import core.Game;
import core.GamePanel;
import core.IKeyInput;
import core.SwingWindow;
import core.obj.IGameObject;

/**
 * KeyInput
 */
public class SwingKeyInput extends KeyAdapter implements IKeyInput {

    private SwingWindow window;
    private Game game;

    private JFrame frame;
    private GamePanel panel;

    private ArrayList<Consumer<KeyEvent>> listeners;

    /**
     * @param game
     * @param window
     */
    public SwingKeyInput(Game game, SwingWindow window) {

        this.window = window;
        this.game = game;

        listeners = new ArrayList<>();

        frame = window.getFrame();
        panel = window.getPanel();

        frame.addKeyListener(this);
        panel.addKeyListener(this);

        panel.setFocusable(true);
        panel.requestFocus();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        super.keyPressed(e);

        getListeners().forEach(c -> c.accept(e));

        for (IGameObject<?> obj : game.getLogic().getObjects()) {
            obj.keyPressed(e);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        super.keyReleased(e);

        getListeners().forEach(c -> c.accept(e));

        for (IGameObject<?> obj : game.getLogic().getObjects()) {
            obj.keyReleased(e);
        }
    }

    @Override
    public void addKeyListener(Consumer<KeyEvent> listener) {
        listeners.add(listener);
    }

    @Override
    public void removeKeyListener(Consumer<KeyEvent> listener) {
        listeners.remove(listener);
    }

    /**
     * @return the listeners
     */
    public ArrayList<Consumer<KeyEvent>> getListeners() {
        return listeners;
    }
    
    /**
     * @return the window
     */
    public SwingWindow getWindow() {
        return window;
    }
}