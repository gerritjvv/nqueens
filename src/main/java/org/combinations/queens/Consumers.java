package org.combinations.queens;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

/**
 * Here we group some consumer helper functions and pre-built consumers for our queens {@link Solver}.
 * <p/>
 * <p>
 * Print out each solutions: <br/>
 * <pre>
 *     Solver.solve( Consumers.Printer.INSTANCE );
 * </pre>
 * <p/>
 * Counter solutions:<br/>
 * <pre>
 *     Consumers.Counter counter = Consumers.Counter.create();
 *     Solver.solve(counter);
 *
 *     //get the counter value
 *     System.out.println("Count: " + counter.getCount());
 *
 *     // or flush, which will print the count to stdout;
 *     counter.accept(null);
 * </pre>
 */
public class Consumers {

    public static class Printer implements Consumer<int[]> {
        public static final Printer INSTANCE = new Printer();

        @Override
        public void accept(int[] queens) {
            if (queens != null) {
                synchronized (this) {

                    // print each row
                    for (int row = 0; row < queens.length; row++) {
                        // print columns
                        for (int col = 0; col < queens.length; col++)
                            System.out.print(queens[row] == col ? "Q " : "- ");

                        // end of row
                        System.out.println();
                    }

                    // end or solution
                    System.out.println();

                }
            }
        }
    }

    public static class Counter implements Consumer<int[]> {

        private final AtomicInteger counter = new AtomicInteger();

        public static Counter create() {
            return new Counter();
        }

        @Override
        public void accept(int[] queens) {
            if (queens == null) {
                System.out.println("Solutions: " + counter.get());
            } else {
                counter.getAndIncrement();
            }
        }

        public int getCount() {
            return counter.get();
        }
    }


}
