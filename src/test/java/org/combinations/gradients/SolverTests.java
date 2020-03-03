package org.combinations.gradients;

import org.combinations.queens.Consumers;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SolverTests {

    private static final CoordData[] coordData = new CoordData[]{
            /**
             * - - - - - - Q -
             * - - - - Q - - -
             * Q - - - - - - -
             * - - Q - - - - -
             * - - - - Q - - -
             * - Q - - - - - -
             * - - - - - - Q -
             * - - - - Q - - -
            */
            new CoordData(
                    new int[]{6, 4, 0, 2, 4, 1, 5, 4},
                    true
            ),

            /**
             * - - - - - - Q -
             * - - - Q - - - -
             * Q - - - - - - -
             * - - Q - - - - -
             * - - - - - Q - -
             * - Q - - - - - -
             * - - - - - - Q -
             * - - - - Q - - -
             **/
            new CoordData(
                    new int[]{6, 3, 0, 2, 5, 1, 6, 4},
                    true
            ),

            /**
             * - - - - - - - Q
             * - - - Q - - - -
             * Q - - - - - - -
             * - - Q - - - - -
             * - - - - - Q - -
             * - Q - - - - - -
             * - - - - - - Q -
             * - - - - Q - - -
            */
            new CoordData(
                    new int[]{7, 3, 0, 2, 5, 1, 6, 4},
                    false
            ),
    };

    @Test
    public void testGradientSolver() {

        for (CoordData data : coordData) {
            new Consumers.Printer().accept(data.coords);
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
