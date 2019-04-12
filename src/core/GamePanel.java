package core;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import core.swing.SwingKeyInput;
import core.swing.SwingMouseInput;

/**
 * GamePanel
 */
public class GamePanel extends JPanel {

    // serial ID
    private static final long serialVersionUID = -2117966474222862734L;

    // Modules
    SwingMouseInput mouseInput;
    SwingKeyInput keyInput;
    
    SwingWindow window;

    protected GamePanel() {}

    public GamePanel(SwingWindow window) {
        setWindow(window);
    }

    public void initialize(Game game) {
        mouseInput = new SwingMouseInput(game, getWindow());
        keyInput = new SwingKeyInput(game, getWindow());
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        getWindow().paint((Graphics2D) g);
    }

    /**
     * @return the mouse
     */
    public SwingMouseInput getMouseInput() {
        return mouseInput;
    }

    /**
     * @return the keyInput
     */
    public SwingKeyInput getKeyInput() {
        return keyInput;
    }

    /**
     * @param window the window to set
     */
    public void setWindow(SwingWindow window) {
        this.window = window;
    }

    /**
     * @return the window
     */
    public SwingWindow getWindow() {
        return window;
    }

}