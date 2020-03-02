package org.combinations.queens;

import org.combinations.gradients.Solver;

import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * Extra predicates applied to each nqueens solution.
 * <p/>
 * Example:<br/>
 * If a solution is found for:
 * <pre>
 *     | x | Q | x | x |
 *     | x | x | x | Q |
 *     | Q | x | x | x |
 *     | x | x | Q | x |
 * </pre>
 * <p>
 * The predicates will be passed in the coordinates of the queens as:
 * <pre>
 *     [0] = 1
 *     [1] = 3
 *     [2] = 2
 *     [3] = 2
 * </pre>
 */
public class Predicates {

    /**
     * Returns false if any 3 points are on the same line.<br/>
     * Note: any 3 points are on the same line if their gradients are the same.
     */
    public static class No3PointsOnStraightLine implements Predicate<int[]> {
        public static final No3PointsOnStraightLine INSTANCE = new No3PointsOnStraightLine();

        @Override
        public boolean test(int[] queens) {
            return !Solver.has3Points(queens);
        }
    }

    @SafeVarargs
    public static Consumer<int[]> consumer(Consumer<int[]> consumer, Predicate<int[]>... predicates) {
        if (predicates == null || predicates.length == 0 || predicates[0] == null) {
            return consumer;
        }

        final Predicate<int[]> predicateChain = chain(predicates);

        return arr -> {
            if (predicateChain.test(arr)) {
                consumer.accept(arr);
            }
        };
    }

    @SafeVarargs
    private static <T> Predicate<T> chain(Predicate<T>... predicates) {
        Predicate<T> pChain = predicates[0];

        for (int i = 1; i < predicates.length; i++) {
            pChain = pChain.and(predicates[i]);
        }

        return pChain;
    }


}
