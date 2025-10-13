import com.example.ArithmeticOperations;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ArithmeticOperationsTest {

    @Test
    public void testAdd() {
        int result = ArithmeticOperations.add(5, 3);
        assertEquals(result, 8);
    }

    @Test
    public void testSubtract() {
        int result = ArithmeticOperations.subtract(10, 4);
        assertEquals(result, 6);
    }

    @Test
    public void testMultiply() {
        int result = ArithmeticOperations.multiply(5, 4);
        assertEquals(result, 20);
    }

    @Test
    public void testDivide() {
        double result = ArithmeticOperations.divide(10, 2);
        assertEquals(result, 5.0);
    }

    @Test
    public void testDivideWithDecimal() {
        double result = ArithmeticOperations.divide(7, 2);
        assertEquals(result, 3.5);
    }

    @Test(expectedExceptions = ArithmeticException.class)
    public void testDivideByZero() {
        ArithmeticOperations.divide(10, 0);
    }

    @Test
    public void testAddNegative() {
        int result = ArithmeticOperations.add(-5, 3);
        assertEquals(result, -2);
    }

    @Test
    public void testSubtractNegative() {
        int result = ArithmeticOperations.subtract(5, 10);
        assertEquals(result, -5);
    }
}