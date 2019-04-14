package core;

import java.awt.event.KeyEvent;
import java.util.function.Consumer;

/**
 * KeyInput handles the input from the keyboard.
 */
public interface IKeyInput {

    /**
     * Invoked when the key is pressed.
     * 
     * @param e the event
     */
    public void keyPressed(KeyEvent e);

    /**
     * Invoked when the key is released.
     * 
     * @param e the event
     */
    public void keyReleased(KeyEvent e);

    /**
     * Add a consumer that receives key inputs.
     * 
     * @param listener the listener
     */
    public void addKeyListener(Consumer<KeyEvent> listener);

    /**
     * Remove a consumer that receives key inputs.
     * 
     * @param listener the listener
     */
    public void removeKeyListener(Consumer<KeyEvent> listener);
    
}