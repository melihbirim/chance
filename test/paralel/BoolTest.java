package paralel;


import org.junit.Assert;
import org.junit.Test;

public class BoolTest {
    @Test
    public void test_bool() {
        double t = 0;
        double f = 0;
        double total = 1000;
        for (int i = 0; i < total; i++) {
            boolean bool = Chance.bool();
            if (bool)
                t++;
            else
                f++;
        }
        double tp = t / total;
        double fp = f / total;

        System.out.println("Total TRUE:" + t + " and PERCENTAGE:" + tp + " FALSE: " + f + " and PERCENTAGE: " + fp
                + " over :" + t / f);

        if (tp > 51) {
            Assert.fail("percentage for total boolean should not be bigger than 51 percent");
        }

        if (fp > 51) {
            Assert.fail("percentage for total boolean should not be bigger than 51 percent");
        }
    }

    @Test
    public void test_bool_likelihood() {
        double t = 0;
        double f = 0;
        double total = 1000;
        for (int i = 0; i < total; i++) {
            boolean bool = Chance.bool(10);
            if (bool)
                t++;
            else
                f++;
        }

        double tp = t / total;
        double fp = f / total;

        System.out.println("Total TRUE:" + t + " and PERCENTAGE:" + tp + " FALSE: " + f + " and PERCENTAGE: " + fp
                + " over :" + t / f);

        if (tp > 11) {
            Assert.fail("percentage for total boolean should not be bigger than 11 percent");
        }

        if (fp > 91) {
            Assert.fail("percentage for total boolean should not be bigger than 11 percent");
        }

    }

}
