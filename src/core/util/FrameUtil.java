package core.util;

import javax.swing.JFrame;

/**
 * Util / convenience for frames.
 */
public class FrameUtil {

    /**
     * @param frame the frame
     */
    public static void center(JFrame frame) {
        frame.setLocationRelativeTo(null);
    }

    /**
     * @param frame the frame
     */
    public static void maximize(JFrame frame) {
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    /**
     * @param frame the frame
     */
    public static void undecorate(JFrame frame) {
        frame.setUndecorated(true);
    }

    /**
     * @param frame the frame
     */
    public static void setFullscreen(JFrame frame) {
        maximize(frame);
        undecorate(frame);
    }

}