package com.mitskevich.course_7sem.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.Random;

@Component
public class BigIntegerGenerator {

    private static final String MAX_VALUE = "9223372036854775808";
    private static final String MIN_VALUE = "9123372036854775808";
    private static final int NUM_BITS = 64;

    public BigInteger generateBigInt() {
        BigInteger number = new BigInteger(NUM_BITS, new Random());
        number = number.setBit(0);
        return number;
    }
}
