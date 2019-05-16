package core.util;

import java.util.Random;

/**
 * MathUtil
 */
public class MathUtil {

    public static int randomBetween(Random random, int min, int max) {
        return random.nextInt(max - min + 1) + min;
    }
    
}