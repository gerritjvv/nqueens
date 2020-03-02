package org.combinations.queens;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

public class Consumers {

    public static <T> Consumer<T> compose(Consumer<T>... consumers) {
        if (consumers == null || consumers.length == 0)
            return null;

        return Arrays.stream(consumers).reduce((c1, c2) -> c1.andThen(c2)).orElse(consumers[0]);
    }

    public static class Printer implements Consumer<int[]> {
        public static final Printer INSTANCE = new Printer();

        @Override
        public void accept(int[] queens) {
            if (queens != null) {
                synchronized (this) {

                    for (int row = 0; row < queens.length; row++) {
                        for (int col = 0; col < queens.length; col++) {
                            System.out.print(queens[row] == col ? "Q " : "- ");
                        }
                        System.out.println();
                    }
                    System.out.println();

                }
            }
        }
    }

    public static class Counter implements Consumer<int[]> {
        public static final Counter INSTANCE = new Counter();

        private final AtomicInteger counter = new AtomicInteger();

        @Override
        public void accept(int[] queens) {
            if (queens == null) {
                System.out.println("Solutions: " + counter.get());
            } else {
                counter.getAndIncrement();
            }
        }
    }


}
