package com.abnamro.assessment.euler.project15;

/**
 * Recursive brute force approach trying all possible options of stepping down/left ending in
 * the bottom right corner and counting the valid paths.
 */
public class BruteForce {
    private final boolean[][] paths; // does not define the grid but the steps/corner
                                     // points of the squares in the grid
    private final int height;
    private final int width;

    public BruteForce(int v, int h) {
        this.height = v + 1;
        this.width = h + 1;

        this.paths = new boolean[this.height][this.width];
    }

    public long calculate() {
        return step(0, 0, 0);
    }

    long step(long count, int v, int h) {
        paths[v][h] = true; // mark this step as taken

        //print();

        // check if we haven't taken the next step to the right yet
        // if not... take it
        if(h < this.width-1 && !paths[v][h + 1]) {
            count = step(count, v, h + 1);
        }

        // check if we haven't taken the next step down yet
        // if not... take it
        if(v < this.height-1 && !paths[v + 1][h]){
            count = step(count, v + 1, h);
        }

        // if we have reached the bottom right of the grid... we are done
        // increase the possible path counter
        if(v==this.height-1 && h == this.width-1) { // right bottom corner
            count = count + 1;
        }

        // we are going to step back so unmark this part of the path as we
        // may reach it another time having taken a step down earlier
        paths[v][h] = false;

        return count;
    }

    @SuppressWarnings("unused")
    void print() {
        System.out.println();
        for(int v = 0; v<this.height; v++){
            for(int h = 0; h<this.width; h++){
                System.out.print(paths[v][h]?"X":"O");
            }
            System.out.println();
        }
        System.out.println();
    }
}

