import com.example.NumberComparator;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class NumberComparatorTest {

    @Test
    public void testFirstNumberGreater() {
        String result = NumberComparator.compare(10, 5);
        assertEquals(result, "10 > 5");
    }

    @Test
    public void testSecondNumberGreater() {
        String result = NumberComparator.compare(3, 8);
        assertEquals(result, "3 < 8");
    }

    @Test
    public void testNumbersEqual() {
        String result = NumberComparator.compare(7, 7);
        assertEquals(result, "7 = 7");
    }

    @Test
    public void testNegativeNumbers() {
        String result = NumberComparator.compare(-2, 1);
        assertEquals(result, "-2 < 1");
    }

    @Test
    public void testBothNegativeFirstGreater() {
        String result = NumberComparator.compare(-1, -5);
        assertEquals(result, "-1 > -5");
    }

    @Test
    public void testBothNegativeEqual() {
        String result = NumberComparator.compare(-3, -3);
        assertEquals(result, "-3 = -3");
    }

    @Test
    public void testWithZero() {
        String result = NumberComparator.compare(0, 0);
        assertEquals(result, "0 = 0");
    }
}