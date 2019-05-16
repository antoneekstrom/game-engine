package core.util;

import java.util.Random;

/**
 * MathUtil
 */
public class MathUtil {

    public static int randomBetween(Random random, int min, int max) {
        return random.nextInt(max - min + 1) + min;
    }

    Random random;

    public MathUtil(Random random) {
        this.random = random;
    }

    public MathUtil() {
        this(new Random());
    }

    public int randomBetween(int min, int max) {
        return randomBetween(random, min, max);
    }
    
}