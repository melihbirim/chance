package paralel;

import java.math.BigDecimal;
import java.util.Random;

public class Chance {
    private static final Random random = new Random();
    private static final String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!@#$%^&*()";

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
        if (options.min <= options.max) {
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

    /**
     * Returns a new random double value with given options.
     * It can be defined max, min, and round options
     */
    public static int integer(Option.Ints options) {
        if (options.min <= options.max) {
            throw new IllegalArgumentException("Options max value should be bigger than min");
        }

        if (options.unsigned && options.min < 0) {
            throw new IllegalArgumentException("Options min value should be bigger or equal to 0");
        }

        return random.nextInt((options.max - options.min) + 1) + options.min;
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
     */
    public static String string(String pool) {
        return string(new Option.Strings().pool(pool));
    }


    /**
     * Generates random length random string value within given options
     * options may have max, min, and pools
     */
    public static String string(Option.Strings options) {
        int length = integer(options);

        if (options.pool == null)
            options.pool = alphabet;

        char[] arr = new char[length];
        for (int i = 0; i < length; i++) {
            arr[i] = character(options.pool);
        }
        return new String(arr);
    }
}