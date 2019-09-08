import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TipCalculatorTest {

    @Test
    public void testOnlyNumbersAndOnePeriod(){
        assertFalse(InputValidation.hasOnlyNumbersAndOnePeriod("$100.00"));
        assertFalse(InputValidation.hasOnlyNumbersAndOnePeriod("$100"));
        assertFalse(InputValidation.hasOnlyNumbersAndOnePeriod("$100.00."));
        assertFalse(InputValidation.hasOnlyNumbersAndOnePeriod("hello"));
        assertTrue(InputValidation.hasOnlyNumbersAndOnePeriod("100.00"));
        assertTrue(InputValidation.hasOnlyNumbersAndOnePeriod("100.900"));
    }

    @Test
    public void testOnlyTwoDecimalPlaces(){
        assertFalse(InputValidation.hasOnlyTwoDecimalPlaces("100.900"));
        assertTrue(InputValidation.hasOnlyTwoDecimalPlaces("45.75"));
        assertTrue(InputValidation.hasOnlyTwoDecimalPlaces("hell o45.75"));
        assertFalse(InputValidation.hasOnlyTwoDecimalPlaces("9.9"));
    }

    @Test
    public void testNotZero(){
        assertTrue(InputValidation.notZero("10.00"));
        assertFalse(InputValidation.notZero("0.00"));
        assertFalse(InputValidation.notZero("000.00"));
        assertFalse(InputValidation.notZero(".00"));
    }

    @Test
    public void testValidPrice(){
        assertFalse(TipCalculator.isValidPrice("hell o45.75"));
        assertFalse(TipCalculator.isValidPrice("100.900"));
        assertFalse(TipCalculator.isValidPrice("95.111"));
        assertTrue(TipCalculator.isValidPrice("95.11"));
        assertFalse(TipCalculator.isValidPrice("0.0"));
        assertFalse(TipCalculator.isValidPrice("0.00"));
    }
}
