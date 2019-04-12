package core.io.resources;

import java.awt.Font;

/**
 * Global resources/variables.
 */
public class Resources {

    /**
     * A font.
     */
    private static Font font;

    /**
     * Set the font.
     * 
     * @param font the font
     */
    public static void setFont(Font font) {
        Resources.font = font;
    }

    /**
     * Get the font.
     * 
     * @return the font
     */
    public static Font getFont() {
        return font;
    }
    
}