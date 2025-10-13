import com.example.FactorialCalculator;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class FactorialCalculatorTest {

    @Test
    public void testFactorialWithZero() {
        assertEquals(FactorialCalculator.calculateFactorial(0), 1L);
    }

    @Test
    public void testFactorialWithOne() {
        assertEquals(FactorialCalculator.calculateFactorial(1), 1L);
    }

    @Test
    public void testFactorialWithPositiveNumbers() {
        assertEquals(FactorialCalculator.calculateFactorial(5), 120L);
        assertEquals(FactorialCalculator.calculateFactorial(7), 5040L);
        assertEquals(FactorialCalculator.calculateFactorial(10), 3628800L);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testFactorialWithNegativeNumber() {
        FactorialCalculator.calculateFactorial(-5);
    }
}