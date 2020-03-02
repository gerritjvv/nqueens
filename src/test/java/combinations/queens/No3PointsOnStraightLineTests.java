package combinations.queens;

import org.junit.Test;

import static org.junit.Assert.*;

public class No3PointsOnStraightLineTests {

    /**
     * Test the case where we have 3 or more
     */
    @Test
    public void test3PointsOnStraightLine() {

        int[] coords = new int[4];
        coords[0] = 0;
        coords[1] = 1;
        coords[2] = 2;
        coords[3] = 3;

        boolean test = Predicates.No3PointsOnStraightLine.INSTANCE.test(coords);

        assertFalse(test);
    }

    /**
     * Test the case where no 3 points are on the same straight line
     */
    @Test
    public void testNo3PointsOnStraightLine() {
        int[] coords = new int[4];
        coords[0] = 10;
        coords[1] = 9;
        coords[2] = 3;
        coords[3] = 1;

        boolean test = Predicates.No3PointsOnStraightLine.INSTANCE.test(coords);

        assertTrue(test);
    }
}
