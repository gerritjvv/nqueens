package org.combinations.gradients;

public class Solver {

    /**
     * Return true if any 3 points have the same gradient i.e are on the same line
     * s
     */
    public static boolean has3Points(int[] coords) {

        int pointsGoal = 3;
        int n = coords.length;
        if (n < pointsGoal)
            return false;

        for (int i = 0; i < n - pointsGoal; i++) {
            if (same3Gradients(coords, i)) {
                return true;
            }
        }

        return false;
    }
    private static boolean same3Gradients(int[] coords, int r) {
        double n1 = (r + 1) - r;
        double n2 = (r + 2) - (r + 1);

        double d1 = coords[r + 1] - coords[r];
        double d2 = coords[r + 2] - coords[r + 1];

        return Math.abs(n1 / d1 - n2 / d2) < 1e-99;
    }
}
