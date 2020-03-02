package combinations.queens;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Test our solver without predicates against known solutions.
 */
public class SolverTests {

    /**
     * Taken from: https://en.wikipedia.org/wiki/Eight_queens_puzzle
     */
    private static final SolveTestData[] solveTestData = new SolveTestData[]{
            new SolveTestData(1, 1),
            new SolveTestData(2, 0),
            new SolveTestData(3, 0),
            new SolveTestData(4, 2),
            new SolveTestData(5, 10),
            new SolveTestData(6, 4),
            new SolveTestData(7, 40),
            new SolveTestData(8, 92),
            new SolveTestData(9, 352),
            new SolveTestData(10, 724),
            new SolveTestData(11, 2680)
    };

    private static final SolveTestData[] solveNo3PointsTestData = new SolveTestData[]{
            new SolveTestData(1, 1),
            new SolveTestData(2, 0),
            new SolveTestData(3, 0),
            new SolveTestData(4, 2),
            new SolveTestData(5, 6),
            new SolveTestData(6, 0),
            new SolveTestData(7, 12),
            new SolveTestData(8, 66),
            new SolveTestData(9, 194),
            new SolveTestData(10, 300),
            new SolveTestData(11, 1250),
    };

    /**
     * Solve for different values of N and compare to known solutions
     */
    @Test
    public void testSolve() {
        for (SolveTestData testData : solveTestData) {
            int solutions = Solver.countSolutions(testData.n);
            assertEquals(testData.solutions, solutions);
        }
    }

    /**
     * Solve for different values of N and filter so that no 3 points are on a straight line.<br/>
     * Compare to known solutions.
     * @TODO Generate known solutions from alternative solver
     */
    @Test
    public void testSolveNo3PointsInStraightLine() {

        for (SolveTestData testData : solveNo3PointsTestData) {
            int solutions = Solver.countSolutions(testData.n, Predicates.No3PointsOnStraightLine.INSTANCE);
            assertEquals(testData.solutions, solutions);
        }
    }

    private static class SolveTestData {
        final int n;
        final int solutions;

        public SolveTestData(int n, int solutions) {
            this.n = n;
            this.solutions = solutions;
        }

    }
}
