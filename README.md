
## Overview

This is my take on making a simple NQueens Solver.

## Requirements

  * [Gradle](https://gradle.org)

## Getting started

The solution is divided into:  
  * queens Solver
  * gradients Solver
  * Consumers.Printer, Consumers.Counter
  * Predicates.No3PointsOnStraightLine

```java
// find number of solutions
int solutions = Solver.countSolutions(4);

// print out each solution
Solver.solve(Consumers.Printer.INSTANCE);
```

### Build the cli application
```bash

gradle shadowJar

## run as an application
java -jar ./build/libs/nqueens-all.jar --help

#>> Usage: <main class> [-3p] [-n=<n>]
#>> -3        No 3 queens can be in a straight line at any angle
#>> -n=<n>    The value of n for a NxN board and n queens, default 4
#>> -p        Print out each solution


## solve for N=8
java -jar ./build/libs/nqueens-all.jar -n 8

## solve for N=8 and print out solutions
java -jar ./build/libs/nqueens-all.jar -n 8 -p

## solve for N=8 and only consider solutions with no 3 queens in a straight line
java -jar ./build/libs/nqueens-all.jar -n 8 -p -3

```

## Performance

The solver uses simple constraint propagation and backtracking.  
Its written in a recursive devide and conquer style which is fairly efficient vs brute force methods.    
The time complexity for this is `O(2^n)` which `n` is the size of `nxn` board.  

Memory usage is fairly low and stable. This is due to the simple array used to keep track of existing queens.  
It works as follows:  

For a `NxN` board we use a `int[N]` array called `queens`.  
For a queen on row `r` we store the column at `queens[r]`, this means if we wanted to see if there is a queen at point `row=2, col=4`.  
We would need to check if `queens[2] == 4`.

How do we store multiple columns? We don't need to. One of the NQueens problem's constraints states that only a single queen can  
be on a single row at any time.  

### Enhancements

Some more enhancements can be done here e.g keep track of the diagonals on the attack path etc.

### Other options

  * [Google OR-Tools](https://developers.google.com/optimization/cp/queens)
  * https://www.lth.se/jacop/
  * https://www.optaplanner.org/

