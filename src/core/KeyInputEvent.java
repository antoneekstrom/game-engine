package core;

/**
 * KeyInputEvent
 */
public class KeyInputEvent {

    public static enum Type {
        RELEASED, PRESSED
    }

    public static enum Modifier {
        RELEASED, PRESSED
    }

    Key key;
    Type type;

    /**
     * @param k
     * @param type
     * @param modifiers
     */
    public KeyInputEvent(Key k, Type type, Modifier... modifiers) {
        this.key = k;
        this.type = type;
    }

    /**
     * @return
     */
    public boolean isReleased() { return type == Type.RELEASED; }

    /**
     * @return
     */
    public boolean isPressed() { return type == Type.PRESSED; }

    /**
     * @return the type
     */
    public Type getType() {
        return type;
    }

    /**
     * @return the keycode
     */
    public int getKeyCode() {
        return getKey().getKeyCode();
    }

    /**
     * @return the key
     */
    public Key getKey() {
        return key;
    }
    
}
