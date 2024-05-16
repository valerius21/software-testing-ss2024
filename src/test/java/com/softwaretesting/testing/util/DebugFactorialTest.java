package com.softwaretesting.testing.util;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class DebugFactorialTest {

    @Test
    void testConstructor() {
        DebugFactorial debugFactorial = new DebugFactorial();
        assertNotNull(debugFactorial);
    }

    @Test
    void factorial() {
        assertEquals(1, DebugFactorial.factorial(0));
        assertEquals(1, DebugFactorial.factorial(1));
        assertEquals(2, DebugFactorial.factorial(2));
        assertEquals(6, DebugFactorial.factorial(3));
        assertEquals(24, DebugFactorial.factorial(4));
        assertEquals(120, DebugFactorial.factorial(5));
    }

    @Test
     void testMain() {
        // Capture output
        PrintStream originalOut = System.out;

        // Create a new ByteArrayOutputStream to capture the output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream testOut = new PrintStream(outputStream);
        System.setOut(testOut);

        String[] args = {};
        DebugFactorial.main(args);

        // Reset System.out to the original PrintStream
        System.setOut(originalOut);

        String output = outputStream.toString().trim();
        assertEquals("Factorial of 5 is 120", output);
    }
}