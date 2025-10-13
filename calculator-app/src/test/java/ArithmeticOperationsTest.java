package com.example.calculators;

import com.example.ArithmeticOperations;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ArithmeticOperationsTest {

    @Test
    void testAllOperations() {
        assertEquals(15, ArithmeticOperations.add(10, 5));
        assertEquals(5, ArithmeticOperations.subtract(10, 5));
        assertEquals(50, ArithmeticOperations.multiply(10, 5));
        assertEquals(2.0, ArithmeticOperations.divide(10, 5));
    }

    @Test
    void testDivisionByZero() {
        assertThrows(ArithmeticException.class, () -> {
            ArithmeticOperations.divide(10, 0);
        });
    }
}