package core.swing;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

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

    public SwingKeyInput(Game game, SwingWindow window) {

        this.window = window;
        this.game = game;

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

        for (IGameObject<?> obj : game.getLogic().getObjects()) {
            obj.keyPressed(e);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        super.keyReleased(e);

        for (IGameObject<?> obj : game.getLogic().getObjects()) {
            obj.keyReleased(e);
        }
    }

    /**
     * @return the window
     */
    public SwingWindow getWindow() {
        return window;
    }
    
}