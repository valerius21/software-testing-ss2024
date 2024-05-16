package com.softwaretesting.testing.util;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static com.softwaretesting.testing.util.Misc.isPrime;
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
    void testForEqualSquare() {
        assertFalse(isPrime(4, 2));
    }

    @ParameterizedTest
    @CsvSource({
            "2.5,5,2",
            "-3.0,-15,5",
            "0.25,1,4",
    })
    void testDivision(double expected, int divide, int divideBy) {
        assertEquals(expected, Misc.divide(divide, divideBy), 0.0001);
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

    @ParameterizedTest
    @CsvSource({
            "1,1",
            "2,2",
            "3,6",
            "4,24",
            "5,120",
            "6,720",
            "7,5040",
            "8,40320",
            "9,362880",
            "10,3628800",
    })
    void testCalculateFactorial(int number, int expected) {
        assertEquals(expected, Misc.calculateFactorial(number));
    }

    @Test()
    void testCalculateFactorial_NegativeInteger() {
        assertThrows(RuntimeException.class, () -> Misc.calculateFactorial(-1));
    }

    @Test
    void testIsPrime_Zero() {
        assertFalse(isPrime(0, 2));
    }

    @Test
    void testIsPrime_One() {
        assertFalse(isPrime(1, 2));
    }

    @Test
    void testIsPrime_Two() {
        assertTrue(isPrime(2, 2));
    }


    @ParameterizedTest
    @ValueSource(ints = {3, 5, 7, 11})
    void testIsPrime_PrimeNumbers(int number) {
        assertTrue(isPrime(number, 2));
    }

    @ParameterizedTest
    @ValueSource(ints = {4, 9, 16, 25})
    void testIsPrime_NonPrimePerfectSquares(int number) {
        assertFalse(isPrime(number, 2));
    }

    @Test()
    void testIsPrime_NegativeNumber() {
        assertFalse(isPrime(-1, 2));
    }

    @Test
    void testIsEven_Zero() {
        assertTrue(Misc.isEven(0));
    }

    @ParameterizedTest
    @ValueSource(ints = {2, 4, 6, 8})
    void testIsEven_PositiveEvenNumbers(int number) {
        assertTrue(Misc.isEven(number));
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 3, 5, 7})
    void testIsEven_PositiveOddNumbers(int number) {
        assertFalse(Misc.isEven(number));
    }

    @ParameterizedTest
    @ValueSource(ints = {-2, -4, -6, -8})
    void testIsEven_NegativeEvenNumbers(int number) {
        assertTrue(Misc.isEven(number));
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, -3, -5, -7})
    void testIsEven_NegativeOddNumbers(int number) {
        assertFalse(Misc.isEven(number));
    }

    @ParameterizedTest
    @ValueSource(ints ={4, 9, 16, 25})
    void testNonPrimePerfectSquares(int number) {
        assertFalse(isPrime(number, 2));
    }

    @ParameterizedTest
    @ValueSource(ints = {6, 15})
    void testNonPrimeNumbers(int number) {
        assertFalse(isPrime(number, 2));
    }

    @ParameterizedTest
    @ValueSource(ints = {2, 3, 5, 7, 11, 13})
    void testPrimeNumbers(int number) {
        assertTrue(isPrime(number, 2));
    }
}