package com.abnamro.assessment.euler.project15;

import java.math.BigInteger;


public class BinomialCoeffient {
    private final int height;

    /**
     * We only support squares so height and with of the grid is the same.
     */
    public BinomialCoeffient(int height) {
        this.height = height;
    }

    /**
     * N-choose-k combinatorics: (n! / (k! * (n-k)!)
     * Where:
     *  		n is the number of moves,
     *  		k is the number of down and right moves required
     *
     * So for a 20x20 grid we have a total nr of moves of 40, 20 right and 20 down.
     */
    public long calculate() {
        int n = this.height*2;
        int k = this.height;

        return factorial(n)
                .divide(
                        factorial(k).multiply(factorial(n - k))
                ).longValue();
    }

    public static BigInteger factorial(int value ) {
        BigInteger factorial = BigInteger.ONE;

        for (int factor = 1; factor <= value; factor++) {
            factorial = factorial.multiply(BigInteger.valueOf(factor));
        }

        return factorial;
    }
}

