package org.combinations.queens;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * Solver for the nqueens problem see: https://en.wikipedia.org/wiki/Eight_queens_puzzle
 */
public class Solver {

    /**
     * Count the number of solutions for the nqueens problem on a NxN board.
     *
     * @param n          The number of queens, rows and columns on a the board.
     * @param predicates Each solution is validated by an optional predicate chain
     * @return The number of solutions
     */
    @SafeVarargs
    public static int countSolutions(int n, Predicate<int[]>... predicates) {
        AtomicInteger counter = new AtomicInteger();

        Consumer<int[]> counterConsumer = arr -> counter.incrementAndGet();

        Solver.solve(n, counterConsumer, predicates);

        return counter.get();
    }

    /**
     * Call consumer with the coordinates of each solution found for the nqueens problem on a NxN board.
     *
     * @param n          The number of queens, rows and columns on a the board.
     * @param consumer   is called with the coordinates e.g <br/>
     *                   Coord(row=2, col=1) => int[2] = 1
     * @param predicates Each solution is validated by an optional predicate chain
     */
    @SafeVarargs
    public static void solve(int n, Consumer<int[]> consumer, Predicate<int[]>... predicates) {
        int[] queens = new int[n];
        combinations(Predicates.consumer(consumer, predicates), queens, 0);
    }

    /**
     * Checks if each point in the board {@code queens} we check
     *
     * @param queens each queen is marked as a point in queens[row] == column
     *               where column is the value of the column the queen can be found at.
     * @param row    the current row being checked
     * @return true if the queen marked at int[row]==column is not in any attack path
     */
    protected static boolean isOnAttackPath(int[] queens, int row) {

        for (int i = 0; i < row; i++) {
            if (queens[i] == queens[row]) {
                return true;
            }
            //check diagonals
            // two points are diagonally aligned when |y2-y1| == |x2-x1|
            // it does not matter how far apart those two points are
            if (Math.abs(i - row) == Math.abs(queens[i] - queens[row])) {
                return true;
            }
        }

        return false;
    }

    private static void combinations(Consumer<int[]> consumer, int[] queens, int row) {
        int n = queens.length;

        if (n == row) {
            consumer.accept(queens);
        } else {
            for (int col = 0; col < n; col++) {
                queens[row] = col; // set the queen position
                if (!isOnAttackPath(queens, row)) {
                    combinations(consumer, queens, row + 1);
                }
            }
        }
    }
}
