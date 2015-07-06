package paralel;

import org.apache.log4j.Logger;
import org.databene.contiperf.PerfTest;
import org.databene.contiperf.Required;
import org.databene.contiperf.junit.ContiPerfRule;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;

public class StringTest {

    Logger logger = Logger.getLogger(StringTest.class);

    @Rule
    public ContiPerfRule i = new ContiPerfRule();


    @Test
    @PerfTest(invocations = 1000, threads = 20)
    @Required(max = 1200, average = 250)
    public void test_string() {
        String randomString = Chance.string();
        Assert.assertTrue(randomString.length() > 0);
        logger.debug("String is [" + randomString + "]");
    }

    @Test
    @PerfTest(invocations = 100, threads = 1)
    @Required(max = 15, average = 1) // average 1ms and max 12ms for 1 cal, add ramp up calls to measure correctly
    public void test_string_2chars() {
        String randomString = Chance.string(new Option.Strings().min(1).max(2));
        int length = randomString.length();
        Assert.assertTrue(length < 3 && length > 0);
        logger.debug("String is [" + randomString + "]"); // remove it for performance related measurements
    }

    @Test
    @PerfTest(invocations = 1000, threads = 10)
    //@Required(max = 12, average = 1)
    public void test_string_2chars_multi_thread() {
        String randomString = Chance.string(new Option.Strings().min(1).max(2));
        int length = randomString.length();
        Assert.assertTrue(length < 3 && length > 0);
    }

    @Test
    @PerfTest(invocations = 1000, threads = 1)
    public void test_string_symbols_only() {
        String randomString = Chance.string(new Option.Strings().min(1).max(1).symbols());
        int length = randomString.length();
        Assert.assertTrue(length == 1);
        Assert.assertTrue(Chance.symbols.contains(randomString));
    }

    @Test
    @PerfTest(invocations = 1000, threads = 1)
    public void test_string_digits_only() {
        String randomString = Chance.string(new Option.Strings().min(1).max(1).digit());
        int length = randomString.length();
        Assert.assertTrue(length == 1);
        Assert.assertTrue(Chance.digits.contains(randomString));
    }

    @Test
    @PerfTest(invocations = 1000, threads = 1)
    public void test_string_chars_only() {
        String randomString = Chance.string(new Option.Strings().min(1).max(1));
        int length = randomString.length();
        Assert.assertTrue(length == 1);
        Assert.assertTrue(Chance.alphabet_chars_only.contains(randomString));
    }

    @Test
    @PerfTest(invocations = 100, threads = 1)
    public void test_word() {
        String randomWord = Chance.word();
        int length = randomWord.length();
        Assert.assertTrue(length >= 3 && length <= 5);
        logger.debug("Random word is [" + randomWord + "]");
    }

    @Test
    @PerfTest(invocations = 100, threads = 1)
    public void test_word_length() {
        Option.Strings options = Option.Strings.word;
        options.max(20);
        String randomWord = Chance.word(options);
        int length = randomWord.length();
        Assert.assertTrue(length >= 3 && length <= 20);
        logger.debug("Random word is [" + randomWord + "]");
    }

    @Test
    @PerfTest(invocations = 100, threads = 1)
    public void test_sentence() {
        String randomWord = Chance.sentence();
        int length = randomWord.split(" ").length;
        logger.debug("total number of words is [" + length + "]");
        logger.debug("Random sentence is [" + randomWord + "]");
        Assert.assertTrue(length >= 12 && length <= 18);
    }


    @Test
    @PerfTest(invocations = 10, threads = 1)
    public void test_paragraph() {
        String randomWord = Chance.paragraph();
        int index = 0;
        int length = 0;
        while (true) {
            char c = randomWord.charAt(index);
            if (c == '.') length++;
            if (++index == randomWord.length()) break;
        }
        logger.debug("total number of sentences is [" + length + "]");
        logger.debug("Random paragraph is [" + randomWord + "]");
        Assert.assertTrue(length >= 3 && length <= 7);
    }

    @Test
    @PerfTest(invocations = 10, threads = 1)
    public void test_paragraph_length() {
        String randomWord = Chance.paragraph(2);
        int index = 0;
        int length = 0;
        while (true) {
            char c = randomWord.charAt(index);
            if (c == '.') length++;
            if (++index == randomWord.length()) break;
        }
        logger.debug("total number of sentences is [" + length + "]");
        logger.debug("Random paragraph is [" + randomWord + "]");
        Assert.assertTrue(length <= 2);
    }
}
