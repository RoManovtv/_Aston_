import com.example.TriangleAreaCalculator;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class TriangleAreaCalculatorTest {

    @Test
    public void testAreaCalculation() {
        double result = TriangleAreaCalculator.calculateArea(10, 5);
        assertEquals(result, 25.0);
    }

    @Test
    public void testAreaWithDecimalNumbers() {
        double result = TriangleAreaCalculator.calculateArea(7.5, 4);
        assertEquals(result, 15.0);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testZeroBase() {
        TriangleAreaCalculator.calculateArea(0, 5);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testZeroHeight() {
        TriangleAreaCalculator.calculateArea(10, 0);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testNegativeBase() {
        TriangleAreaCalculator.calculateArea(-5, 10);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testNegativeHeight() {
        TriangleAreaCalculator.calculateArea(10, -5);
    }
}