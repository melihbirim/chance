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
        logger.debug("String is [" + randomString + "]");
    }
}
