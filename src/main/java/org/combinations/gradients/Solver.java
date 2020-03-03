package org.combinations.gradients;

/**
 * Check if 3 points are on the same line at any angle.<br/>
 * The input to this solver comes from the output of {@link org.combinations.queens.Solver}.<br/>
 * <p/>
 * See: {@link org.combinations.queens.Predicates.No3PointsOnStraightLine}
 */
public class Solver {

    /**
     * Return true if any 3 points have the same gradient i.e are on the same line
     */
    public static boolean has3Points(int[] coords) {

        int pointsGoal = 3;
        int n = coords.length;
        if (n < pointsGoal)
            return false;

        // simple shift forward by by check 3 at a time loop.
        // for coords = [1, 2, 3, 4, 5, 6, 7, 8] , n 8
        // loop 1: check (1,2,3)
        // loop 2: check (2,3,4)
        // loop 3: check (3,4,5)
        // loop 4: check (4,5,6)
        // loop 5: check (5,6,7)
        // loop 6: check (6,7,8)   n - 3 == 6
        for (int i = 0; i < n - pointsGoal; i++) {
            if (same3Gradients(coords, i)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Check the gradients of three points (one on each row in coords), starting from r.<br/>
     * <pre>
     * Point1 = (coords[r], r)
     * Point2 = (coords[r+1], r+1)
     * Point3 = (coords[r+2], r+2)
     * </pre>
     * <br/>
     * If the gradients are the same we return true.
     */
    private static boolean same3Gradients(int[] coords, int r) {
        double n1 = (r + 1) - r;
        double n2 = (r + 2) - (r + 1);

        double d1 = coords[r + 1] - coords[r];
        double d2 = coords[r + 2] - coords[r + 1];

        // These cases are when we have Q on the same line or column.
        // This is something that will never happen, because of the nqueens Solver's constraints.
        // if (d1 == 0 && d2 == 0) {
        //    return n1 == n2;
        // } else if (n1 == 0) {
        //  if (n2 == 0)
        //    return d1 == d2
        //  return false;
        // }

        return Math.abs(n1 / d1 - n2 / d2) < 1e-99;
    }
}
