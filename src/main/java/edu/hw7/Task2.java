package edu.hw7;

import java.math.BigInteger;
import java.util.stream.LongStream;

public class Task2 {

    public static BigInteger factorial(int n) {
        return LongStream.rangeClosed(1, n)
            .parallel()
            .mapToObj(BigInteger::valueOf)
            .reduce(BigInteger.ONE, BigInteger::multiply);
    }
}
