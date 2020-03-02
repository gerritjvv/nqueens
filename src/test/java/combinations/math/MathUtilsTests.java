package combinations.math;

import it.unimi.dsi.fastutil.objects.Object2IntMap;
import math.MathUtils;
import org.apache.commons.math3.fraction.Fraction;
import org.junit.Test;

import java.util.concurrent.ThreadLocalRandom;

import static org.junit.Assert.assertEquals;

public class MathUtilsTests {

    @Test
    public void testGradientFrequencies() {

        int[] coords = new int[15];

        // add 9 points on the same line
        for (int i = 0; i < 10; i++) {
            coords[i] = i;
        }


        coords[10] = 0;
        coords[11] = 100;
        coords[12] = 0;
        coords[13] = 100;
        coords[14] = 0;

        // expect:
        // result 9 at gradient 1
        //        1 at gradient -1/9
        //        2 at gradient 1/100
        //        2 at gradient -1/100

        Object2IntMap<Fraction> frequencies = MathUtils.gradientFrequencies(coords);

        assertEquals(4, frequencies.size());
        assertEquals(frequencies.getInt(new Fraction(1, 1)), 9);
        assertEquals(frequencies.getInt(new Fraction(-1, 9)), 1);
        assertEquals(frequencies.getInt(new Fraction(1, 100)), 2);
        assertEquals(frequencies.getInt(new Fraction(-1, 100)), 2);

    }

    @Test
    public void testGradient() {
        int x1 = randInt();
        int y1 = randInt();
        int x2 = randInt();
        int y2 = randInt();

        assertEquals(
                new Fraction(y2 - y1, x2 - x1),
                MathUtils.gradient(x1, y1, x2, y2));
    }


    private static int randInt() {
        return ThreadLocalRandom.current().nextInt();
    }
}
