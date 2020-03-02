package math;

import it.unimi.dsi.fastutil.objects.Object2IntArrayMap;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import org.apache.commons.math3.fraction.Fraction;

/**
 * Simple math utils for coordinates
 */
public class MathUtils {

    /**
     * Return the gradient as a fraction between two points
     */
    public static Fraction gradient(int x1, int y1, int x2, int y2) {
        return new Fraction(
                y2 - y1,
                x2 - x1
        );
    }

    /**
     * Return the frequencies of all the gradients in a list of coordinates.
     * @param coords an array of coordinates where Coord(row=1, col=2) is<br/>
     *               coords[1] = 2
     * @return A map of key=Fraction, value=frequency
     */
    public static Object2IntMap<Fraction> gradientFrequencies(int[] coords) {
        Object2IntMap<Fraction> map = new Object2IntArrayMap<>();

        for (int i = 1; i < coords.length; i++) {
            Fraction gradient = MathUtils.gradient(coords[i - 1], i - 1, coords[i], i);

            int freq = map.getOrDefault(gradient, 0);
            map.put(gradient, freq + 1);
        }

        return map;
    }

}
