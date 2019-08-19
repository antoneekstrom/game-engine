package core;

import java.util.function.Consumer;

/**
 * KeyInput handles the input from the keyboard.
 */
public interface IKeyInput {

    /**
     * 
     */
    public boolean keyIsDown(Key key);

    /**
     * Add a consumer that receives key inputs.
     * 
     * @param listener the listener
     */
    public void addKeyListener(Consumer<KeyInputEvent> listener);

    /**
     * Remove a consumer that receives key inputs.
     * 
     * @param listener the listener
     */
    public void removeKeyListener(Consumer<KeyInputEvent> listener);
    
}