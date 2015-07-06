package paralel;

import org.junit.Assert;
import org.junit.Test;
import paralel.Option.Ints;

public class OptionsTest {

    int total_run = 10000;

    @Test
    public void test_intOptions_min_and_max() {
        Ints intOptions = new Ints().max(3).min(2);

        int randomInteger = Chance.integer(intOptions);

        int i = 0;
        while (i++ < total_run) {
            if (randomInteger > 300 && randomInteger < 2) {
                Assert.fail("Integer should be max 3 or min 2 but has [" + randomInteger + "]");
            }
        }
    }

    @Test
    public void test_intOptions_max() {
        Ints intOptions = new Ints();
        intOptions.max(300);

        int randomInteger = Chance.integer(intOptions);

        int i = 0;
        while (i++ < total_run) {
            if (randomInteger > 300 && randomInteger < 1) {
                Assert.fail("Integer should be max 300 or min 2 but has [" + randomInteger + "]");
            }
        }
    }
}
