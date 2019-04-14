package core.util;

import core.math.Box;

/**
 * Helper methods for positioning things.
 */
public class LayoutHelper {

    public static Box centerInside(Box container, Box element) {
        return element.setPosition(container.getCenter().sub(element.getSize().copy().div(2)));
    }
    
}