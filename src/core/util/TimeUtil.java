package core.util;

/**
 * Utility and convenience methods.
 */
public class TimeUtil {

    /**
     * @param r
     * 
     * @return the time it took to execute in nanoseconds
     */
    public static double measureTime(Runnable r) {

        double t = System.nanoTime();

        r.run();

        return System.nanoTime() - t;
    }

    /**
     * Convert to a more readable format as milliseconds.
     * 
     * @param time the time in nanoseconds
     * 
     * @return the string
     */
    public static String formatNanoTime(double time) {
        return time / Math.pow(10, 6) + "ms";
    }
    
}