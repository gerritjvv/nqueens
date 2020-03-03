package org.combinations.gradients;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SolverTests {


    /**
     * @TODO add more test cases
     */
    private static final CoordData[] coordData = new CoordData[]{
            new CoordData(
                    new int[]{6, 3, 0, 2, 5, 1, 6, 4},
                    true
            ),
            new CoordData(
                    new int[]{7, 3, 0, 2, 5, 1, 6, 4},
                    false
            ),
    };

    @Test
    public void testGradientSolver() {

        for(CoordData data : coordData) {
            assertEquals(data.test, Solver.has3Points(data.coords));
        }

    }

    private static class CoordData {
        final int[] coords;
        final boolean test;

        public CoordData(int[] coords, boolean test) {
            this.coords = coords;
            this.test = test;
        }
    }

}
