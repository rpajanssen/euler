package com.abnamro.assessment.euler.project15;

/**
 * If you look at the grid and calculate how many paths are possible to traverse
 * to each point in that grid, then a pattern becomes obvious.
 *
 *    0     1     1     1     1     1
 *    1     2     3     4     5     6
 *    1     3     6     10    15    21
 *    1     4     10    20    35    56
 *    1     5     15    35    70    126
 *    1     6     21    56    126   252
 *
 *  So with grid size two we see we have 6 possible paths to the bottom right
 *  grid point. With grid size five we see we have 252 possible paths to the bottom right
 *  grid point.
 *
 *  This pattern is Pascals Triangle. The number of possible paths to each grid point is the
 *  sum of the number of the grid point at the top and to the left.
 *
 *  The points at the edges (top, left) all have only one possible path. So if we setup a grid
 *  and initiate the these points with value 1 then we can calculate the other points
 *  by using the sum of the value of the points above and left. This way we can construct a grid
 *  with each point having the number of possible path and then we can navigate to that
 *  grid point to find out how many paths to that point are possible.
 */
public class PascalsTriangle {
    private final long[][] grid;
    private final int height;

    /**
     * We only support squares so height and with of the grid is the same.
     */
    public PascalsTriangle(int height) {
        this.height = height + 1;

        this.grid = new long[this.height][this.height];
    }

    public long calculate() {
        // initiate the outer sides of Pascals Triangle
        grid[0][0] = 0;
        for (int index=1; index <= this.height - 1; index++){
            grid[index][0] = 1;
            grid[0][index] = 1;
        }

        // calculate values of all other points in Pascals Triangle
        // these loops fill in the next reverse L of the grid like:
        // Starting point:
        //    0   1   1   1   1
        //    1
        //    1
        //    1
        //    1
        //
        // Step 1:
        //    0   1   1   1   1
        //    1   2
        //    1
        //    1
        //    1
        //
        // Step 2:
        //    0   1   1   1   1
        //    1   2   3
        //    1   3   6
        //    1
        //    1
        //
        // Step 3:
        //    0   1   1   1   1
        //    1   2   3   4
        //    1   3   6   10
        //    1   4   10  20
        //    1
        for (int i=1; i <= this.height - 1; i++){
            for (int j=1; j <=i; j++){
                grid[i][j] = grid[j][i] = grid[j-1][i] + grid[j][i-1];
            }
        }

        //print();

        return grid[this.height - 1][this.height -1];
    }

    @SuppressWarnings("unused")
    void print() {
        System.out.println();
        for(int v = 0; v<this.height; v++){
            for(int h = 0; h<this.height; h++){
                System.out.print((grid[v][h] + "          ").substring(0, 6));
            }
            System.out.println();
        }
        System.out.println();
    }
}

