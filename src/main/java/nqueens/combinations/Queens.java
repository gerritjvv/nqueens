package nqueens.combinations;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

public class Queens {

    private static final boolean isOnAttackPath(int[] queens, int row) {

        for (int i = 0; i < row; i++) {
            if (queens[i] == queens[row]) return true;
            //check diagonals
            // two points are diagonally aligned when |y2-y1| == |x2-x1|
            // it does not matter how far apart those two points are
            if (Math.abs(i - row) == Math.abs(queens[i] - queens[row])) return true;
        }

        return false;
    }

    public static final void solve(int n, Consumer<int[]> consumer) {


        /**
         * the index is the rows 0 -> (n-1)
         * the value at each index is the column of the queen set can be any 0 -> (n-1)
         */
        int[] queens = new int[n];
        combinations(consumer, queens);
    }

    private static final void combinations(Consumer<int[]> consumer, int[] queens) {
        int n = queens.length;

        int[][] stack = new int[n*10][2];
        int pointer = 1;

        STACKLOOP:
        for(; pointer > 0;) {

            int row = stack[pointer][0];
            int colStart = stack[pointer][1];

            if (n == row) {
                consumer.accept(copyArray(queens));
            } else {
                for (int col = colStart; col < n; col++) {
                    queens[row] = col; // set the queen position
                    if (!isOnAttackPath(queens, row)) {
                        // push to stack
                        stack[pointer][1] = col + 1;
                        pointer++;
                        stack[pointer][0] = row+1;
                        stack[pointer][1] = 0;
                        continue STACKLOOP;
                    }
                }
            }
            pointer--;

        }
    }

    private static final int[] copyArray(int[] queens) {
        int arr[] = new int[queens.length];
        System.arraycopy(queens, 0, arr, 0, queens.length);
        return arr;
    }

    public static final void main(String[] args) {
        AtomicInteger counter = new AtomicInteger();
        long ms = System.currentTimeMillis();

        Queens.solve(15, arr -> {
            counter.incrementAndGet();
        });

        System.out.println(counter.get());
    }
}
