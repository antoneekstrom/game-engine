package core;

import java.awt.event.MouseEvent;

/**
 * MouseInputHelper
 */
public class MouseInputHelper {

    public static enum Button {
        LEFT, RIGHT, MIDDLE, ANY
    }

    public static boolean isReleased(MouseEvent e) {
        return e.getID() == MouseEvent.MOUSE_RELEASED;
    }

    public static boolean isLeftClick(MouseEvent e) {
        return e.getButton() == MouseEvent.BUTTON1;
    }

    public static boolean isRightClick(MouseEvent e) {
        return e.getButton() == MouseEvent.BUTTON2;
    }

    public static Button getButton(MouseEvent e) {
        switch (e.getButton()) {
            case MouseEvent.BUTTON1: return Button.LEFT;
            case MouseEvent.BUTTON2: return Button.RIGHT;
            case MouseEvent.BUTTON3: return Button.MIDDLE;
            default: return Button.ANY;
        }
    }
    
}