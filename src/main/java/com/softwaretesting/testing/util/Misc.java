package com.softwaretesting.testing.util;

import org.springframework.util.Assert;


public class Misc {
    private Misc() {}
    enum Color {
        RED,
        YELLOW,
        GREEN, // adding this color just for test coverage
        BLUE
    }

    public static int sum(int arg1, int arg2) {
        return arg1 + arg2;
    }

    public static double divide(int divide, int divideBy) {
        if (divideBy == 0) {
            throw new RuntimeException("This operation would result in division by zero error.");
        }

        return (double) divide / divideBy;
    }

    public static boolean isColorSupported(Color color) {
        Assert.isTrue(color != null, "color cannot be null");

        switch (color) {
            case RED:
                /* fallthrough */
            case YELLOW:
            case BLUE: return true;
        }

        return false;
    }

    public static long calculateFactorial(int num) {
        if (num < 0) {
            throw new RuntimeException("This operation would result in calculation factorial by zero error.");
        } else if (num == 0) {
            return 1;
        }
        else {
            return num * calculateFactorial(num - 1);
        }
    }

    public static boolean isPrime(int n, int i) {
        if (n <= 0) {
           return false;
        }
        if (n <= 2) {
            return n == 2;
        }
        if (n % i == 0) {
            return false;
        }
        if (i * i > n) {
            return true;
        }

        return isPrime(n, i + 1);
    }

    public static boolean isEven(int n) {
        return n % 2 == 0;
    }
}
