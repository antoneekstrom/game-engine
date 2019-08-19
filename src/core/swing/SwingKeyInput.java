package core.swing;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.function.Consumer;

import javax.swing.JFrame;

import core.Game;
import core.GamePanel;
import core.IKeyInput;
import core.Key;
import core.KeyInputEvent;
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

    private ArrayList<Consumer<KeyInputEvent>> listeners;

    /**
     * Keys that are currently being held down.
     */
    private ArrayList<Integer> keysDown;

    /**
     * @param game
     * @param window
     */
    public SwingKeyInput(Game game, SwingWindow window) {

        this.window = window;
        this.game = game;

        listeners = new ArrayList<>();
        keysDown = new ArrayList<>();

        frame = window.getFrame();
        panel = window.getPanel();

        frame.addKeyListener(this);
        panel.addKeyListener(this);

        panel.setFocusable(true);
        panel.requestFocus();
    }

    @Override
    public boolean keyIsDown(Key key) {
        for (int k : keysDown) {
            if (k == key.getKeyCode()) return true;
        }
        return false;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        super.keyPressed(e);

        KeyInputEvent ke = new KeyInputEvent(new Key(e.getKeyCode()), e.getID() == KeyEvent.KEY_RELEASED ? KeyInputEvent.Type.RELEASED : KeyInputEvent.Type.PRESSED);
        keysDown.add(ke.getKeyCode());

        getListeners().forEach(c -> c.accept(ke));
    }

    @Override
    public void keyReleased(KeyEvent e) {
        super.keyReleased(e);

        KeyInputEvent ke = new KeyInputEvent(new Key(e.getKeyCode()), e.getID() == KeyEvent.KEY_RELEASED ? KeyInputEvent.Type.RELEASED : KeyInputEvent.Type.PRESSED);
        keysDown.remove((Object) ke.getKeyCode());

        getListeners().forEach(c -> c.accept(ke));
    }

    @Override
    public void addKeyListener(Consumer<KeyInputEvent> listener) {
        listeners.add(listener);
    }

    @Override
    public void removeKeyListener(Consumer<KeyInputEvent> listener) {
        listeners.remove(listener);
    }

    /**
     * @return the listeners
     */
    public ArrayList<Consumer<KeyInputEvent>> getListeners() {
        return listeners;
    }
    
    /**
     * @return the window
     */
    public SwingWindow getWindow() {
        return window;
    }
}