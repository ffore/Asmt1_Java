import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TipCalculatorTest {
    @Test
    public void testIsValidPrice(){
        assertTrue(TipCalculator.isValidPrice("100"));
    }
}
