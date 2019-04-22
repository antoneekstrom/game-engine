package core.util;

import core.math.Box;
import core.math.Vector2D;

/**
 * Helper methods for positioning things.
 */
public class LayoutHelper {

    public static Vector2D centerInside(Box container, Box element) {
        return element.getPosition().copy().set(container.getCenter().sub(element.getSize().copy().div(2)));
    }
    
}