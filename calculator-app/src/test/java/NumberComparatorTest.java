package com.example.calculators;

import com.example.NumberComparator;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class NumberComparatorTest {

    @Test
    void testComparisons() {
        assertEquals("5 > 3", NumberComparator.compare(5, 3));
        assertEquals("3 < 5", NumberComparator.compare(3, 5));
        assertEquals("4 = 4", NumberComparator.compare(4, 4));
    }
}