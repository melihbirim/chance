package paralel;

import java.math.BigDecimal;
import java.util.Random;

/**
 * Java clone of http://chancejs.com/
 * <p/>
 * Chance is a minimalist generator of random strings, numbers, etc.
 * to help reduce some monotony particularly while writing automated tests or anywhere else you need anything random.
 */
public final class Chance {
    static Random random = new Random();
    static final String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!@#$%^&*()";
    static final String alphabet_chars_only = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    static final String digits = "0123456789";
    static final String symbols = "!@#$%^&*()";

    /**
     * Updates the random instance
     */
    public static void update() {
        random = new Random();
    }

    /**
     * returns random boolean
     */
    public static boolean bool() {
        return random.nextBoolean();
    }

    /**
     * The default likelihood of success (returning true) is 50%.
     * Can optionally specify the likelihood in percent: (almost!!!) not exactly same number of possibilities
     *
     * @param likelihood, a value between 1 and 100
     */
    public static boolean bool(int likelihood) {
        return random.nextInt(100) <= likelihood;
    }

    /**
     * @return a random char within static alphabet set
     */
    public static char character() {
        return alphabet.charAt(random.nextInt(alphabet.length()));
    }

    /**
     * @return a random char within given alphabet pool
     */
    public static char character(String pool) {
        return pool.charAt(random.nextInt(pool.length()));
    }

    /**
     * @return a new random double value
     */
    public static double floating() {
        return random.nextDouble();
    }

    /**
     * Returns a new random double value with given options.
     * It can be defined max, min, and round options
     */
    public static double floating(Option.Floatings options) {
        if (options.max < options.min) {
            throw new IllegalArgumentException("Options max value should be bigger than min");
        }
        Double value = (options.max - options.min);
        if (value.isInfinite())
            return random.nextDouble();

        value = options.min + (value * random.nextDouble());

        return new BigDecimal(value).setScale(options.fixed, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * random integer
     */
    public static int integer() {
        return random.nextInt();
    }

    /**
     * random integer at max!
     */
    public static int integer(int max) {
        return random.nextInt(max);
    }

    public static int integer(int min, int max) {
        return random.nextInt((max - min) + 1) + min;
    }

    /**
     * Returns a new random double value with given options.
     * It can be defined max, min, and round options
     */
    public static int integer(Option.Ints options) {
        if (options.max < options.min) {
            throw new IllegalArgumentException("Options max value should be bigger than min");
        }

        if (options.unsigned && options.min < 0) {
            throw new IllegalArgumentException("Options min value should be bigger or equal to 0");
        }

        return integer(options.min, options.max);
    }

    /**
     * returns an integer with given options, be sure it is unsigned always!
     */
    public static int natural(Option.Ints options) {
        options.unsigned = true;

        if (options.min < 0) {
            options.min = 0;
        }

        return integer(options);
    }

    /**
     * Generates random length random string value
     */
    public static String string() {
        return string(alphabet);
    }

    /**
     * Generates random length random string value within given pool string
     * min 1, max 30 characters
     */
    public static String string(String pool) {
        return string(new Option.Strings().pool(pool).min(1).max(30));
    }


    /**
     * Generates random length random string value within given options
     * options may have max, min, and pools
     */
    public static String string(Option.Strings options) {
        int length = integer(options.min, options.max);

        if (options.pool == null)
            options.pool = alphabet;

        char[] arr = new char[length];
        for (int i = 0; i < length; i++) {
            arr[i] = character(options.pool);
        }
        return new String(arr);
    }
}
