package com.softwaretesting.testing.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MiscTest {

    @Test
    public void testPositiveNumbers() {
        assertEquals(8, Misc.sum(5, 3));
    }

    @Test
    public void testNegativeNumbers() {
        assertEquals(-8, Misc.sum(-5, -3));
    }

    @Test
    public void testZeroAndPositiveNumber() {
        assertEquals(5, Misc.sum(0, 5));
    }

    @Test
    public void testZeroAndNegativeNumber() {
        assertEquals(-5, Misc.sum(0, -5));
    }

    @Test
    public void testZeroAndZero() {
        assertEquals(0, Misc.sum(0, 0));
    }


    @Test
    public void testDivision() {
        assertEquals(2.5, Misc.divide(5, 2), 0.0001);
        assertEquals(-3.0, Misc.divide(-15, 5), 0.0001);
        assertEquals(0.25, Misc.divide(1, 4), 0.0001);
    }

    @Test()
    public void testDivisionByZero() {
        assertThrows(RuntimeException.class, () -> Misc.divide(10, 0));
    }

    @Test
    public void testIsColorSupported_Red() {
        assertTrue(Misc.isColorSupported(Misc.Color.RED));
    }

    @Test
    public void testIsColorSupported_Yellow() {
        assertTrue(Misc.isColorSupported(Misc.Color.YELLOW));
    }

    @Test
    public void testIsColorSupported_Blue() {
        assertTrue(Misc.isColorSupported(Misc.Color.BLUE));
    }

    @Test
    public void testIsColorSupported_Null() {
        assertThrows(IllegalArgumentException.class, () -> Misc.isColorSupported(null));
    }


    @Test
    void calculateFactorial() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Test
    void isPrime() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Test
    void isEven() {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}