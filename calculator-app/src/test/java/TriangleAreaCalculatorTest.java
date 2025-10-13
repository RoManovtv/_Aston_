package com.example.calculators;

import com.example.TriangleAreaCalculator;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TriangleAreaCalculatorTest {

    @Test
    void testAreaCalculation() {
        assertEquals(10.0, TriangleAreaCalculator.calculateArea(5, 4));
        assertEquals(7.5, TriangleAreaCalculator.calculateArea(3, 5));
    }

    @Test
    void testInvalidInput() {
        assertThrows(IllegalArgumentException.class, () -> {
            TriangleAreaCalculator.calculateArea(-1, 5);
        });
    }
}