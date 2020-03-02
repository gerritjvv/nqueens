package org.combinations;

import org.combinations.queens.Consumers;
import org.combinations.queens.Predicates;
import org.combinations.queens.Solver;
import picocli.CommandLine;

import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * Run the solver as an app
 */
@CommandLine.Command
public class Main implements Runnable {

    @CommandLine.Option(names = "-n", description = "The value of n for a NxN board and n queens, default 4")
    int n = 4;

    @CommandLine.Option(names = "-p", description = "Print out each solution")
    boolean printBoards = false;

    @CommandLine.Option(names = "-3", description = "No 3 queens can be in a straight line at any angle")
    boolean no3QueensInLine = false;

    public static final void main(String[] args) {
        new CommandLine(new Main()).execute(args);
    }


    @Override
    public void run() {

        Predicate<int[]> p = null;

        if(no3QueensInLine) {
            p = Predicates.No3PointsOnStraightLine.INSTANCE;
        }

        Consumer<int[]> consumer;

        if(printBoards) {
            consumer = Consumers.Printer.INSTANCE.andThen(Consumers.Counter.INSTANCE);
        } else{
            consumer = Consumers.Counter.INSTANCE;
        }

        Solver.solve(n, consumer, p);

        // flush consumers
        consumer.accept(null);
    }
}
