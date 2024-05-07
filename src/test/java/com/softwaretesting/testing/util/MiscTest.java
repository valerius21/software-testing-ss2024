package com.softwaretesting.testing.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MiscTest {

    @Test
    void testPositiveNumbers() {
        assertEquals(8, Misc.sum(5, 3));
    }

    @Test
    void testNegativeNumbers() {
        assertEquals(-8, Misc.sum(-5, -3));
    }

    @Test
    void testZeroAndPositiveNumber() {
        assertEquals(5, Misc.sum(0, 5));
    }

    @Test
    void testZeroAndNegativeNumber() {
        assertEquals(-5, Misc.sum(0, -5));
    }

    @Test
    void testZeroAndZero() {
        assertEquals(0, Misc.sum(0, 0));
    }


    @Test
    void testDivision() {
        assertEquals(2.5, Misc.divide(5, 2), 0.0001);
        assertEquals(-3.0, Misc.divide(-15, 5), 0.0001);
        assertEquals(0.25, Misc.divide(1, 4), 0.0001);
    }

    @Test()
    void testDivisionByZero() {
        assertThrows(RuntimeException.class, () -> Misc.divide(10, 0));
    }

    @Test
    void testIsColorSupported_Red() {
        assertTrue(Misc.isColorSupported(Misc.Color.RED));
    }

    @Test
    void testIsColorSupported_Yellow() {
        assertTrue(Misc.isColorSupported(Misc.Color.YELLOW));
    }

    @Test
    void testIsColorSupported_Blue() {
        assertTrue(Misc.isColorSupported(Misc.Color.BLUE));
    }

    @Test
    void testIsColorSupported_False() {
        assertFalse(Misc.isColorSupported(Misc.Color.GREEN));
    }

    @Test
    void testIsColorSupported_Null() {
        assertThrows(IllegalArgumentException.class, () -> Misc.isColorSupported(null));
    }


    @Test
    void testCalculateFactorial_Zero() {
        assertEquals(1, Misc.calculateFactorial(0));
    }

    @Test
    void testCalculateFactorial_PositiveInteger() {
        assertEquals(1, Misc.calculateFactorial(1));
        assertEquals(2, Misc.calculateFactorial(2));
        assertEquals(6, Misc.calculateFactorial(3));
        assertEquals(24, Misc.calculateFactorial(4));
        assertEquals(120, Misc.calculateFactorial(5));
    }

    @Test()
    void testCalculateFactorial_NegativeInteger() {
        assertThrows(RuntimeException.class, () -> Misc.calculateFactorial(-1));
    }

    @Test
    void testIsPrime_Zero() {
        assertFalse(Misc.isPrime(0, 2));
    }

    @Test
    void testIsPrime_One() {
        assertFalse(Misc.isPrime(1, 2));
    }

    @Test
    void testIsPrime_Two() {
        assertTrue(Misc.isPrime(2, 2));
    }

    @Test
    void testIsPrime_PrimeNumbers() {
        assertTrue(Misc.isPrime(3, 2));
        assertTrue(Misc.isPrime(5, 2));
        assertTrue(Misc.isPrime(7, 2));
        assertTrue(Misc.isPrime(11, 2));
    }

    @Test
    void testIsPrime_NonPrimeNumbers() {
        assertFalse(Misc.isPrime(4, 2));
        assertFalse(Misc.isPrime(6, 2));
        assertFalse(Misc.isPrime(8, 2));
        assertFalse(Misc.isPrime(9, 2));
        assertFalse(Misc.isPrime(10, 2));
    }

    @Test()
    void testIsPrime_NegativeNumber() {
        assertFalse(Misc.isPrime(-1, 2));
    }

    @Test
    void testIsEven_Zero() {
        assertTrue(Misc.isEven(0));
    }

    @Test
    void testIsEven_PositiveEvenNumbers() {
        assertTrue(Misc.isEven(2));
        assertTrue(Misc.isEven(4));
        assertTrue(Misc.isEven(6));
        assertTrue(Misc.isEven(8));
    }

    @Test
    void testIsEven_PositiveOddNumbers() {
        assertFalse(Misc.isEven(1));
        assertFalse(Misc.isEven(3));
        assertFalse(Misc.isEven(5));
        assertFalse(Misc.isEven(7));
    }

    @Test
    void testIsEven_NegativeEvenNumbers() {
        assertTrue(Misc.isEven(-2));
        assertTrue(Misc.isEven(-4));
        assertTrue(Misc.isEven(-6));
        assertTrue(Misc.isEven(-8));
    }

    @Test
    void testIsEven_NegativeOddNumbers() {
        assertFalse(Misc.isEven(-1));
        assertFalse(Misc.isEven(-3));
        assertFalse(Misc.isEven(-5));
        assertFalse(Misc.isEven(-7));
    }
}