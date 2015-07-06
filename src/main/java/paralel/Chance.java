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
    static final String alphabet_upper_chars_only = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    static final String alphabet_lower_chars_only = "abcdefghijklmnopqrstuvwxyz";
    static final String digits = "0123456789";
    static final String symbols = "!@#$%^&*()";
    static final char blank = ' ';

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

    /**
     * Return a semi-pronounceable random (nonsense) word.
     * ~3-5 chars which seems about right.
     */
    public static String word() {
        return word(Option.Strings.word);
    }

    public static String word(Option.Strings options) {
        return string(options);
    }


    /**
     * Return a random sentence populated by semi-pronounceable random (nonsense) words.
     * The sentence starts with a capital letter, and ends with a period.
     * Default is a sentence with a random number of words from 12 to 18.
     */
    public static String sentence() {
        return sentence(integer(12, 18), Chance.alphabet_chars_only);
    }

    public static String sentence(String pool) {
        return sentence(integer(12, 18), pool);
    }

    public static String sentence(int length, String pool) {
        if (length < 1) {
            throw new IllegalArgumentException("Options max value should be bigger than min");
        }
        StringBuffer sentence = new StringBuffer();
        int index = 1;
        sentence.append(character(Chance.alphabet_upper_chars_only)).append(word());
        while (index++ < length) {
            sentence.append(blank).append(word());
        }
        sentence.append('.');
        return sentence.toString();
    }

    /**
     * Return a random paragraph generated from sentences populated by semi-pronounceable random (nonsense) words.
     * Default is a paragraph with a random number of sentences from 3 to 7.
     */
    public static String paragraph() {
        return paragraph(integer(3, 7));
    }

    public static String paragraph(int length, String pool) {
        int index = 1; // not to clear space at the end of the paragraph
        StringBuffer paragraph = new StringBuffer();
        paragraph.append(sentence(pool));
        while (index++ < length) {
            paragraph.append(Chance.blank).append(sentence(pool));
        }
        return paragraph.toString();
    }

    public static String paragraph(int length) {
        return paragraph(length, Chance.alphabet_chars_only);
    }
}
