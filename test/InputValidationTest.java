import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class InputValidationTest {

    // Coordinate Validation
    @Test
    public void testWholeNumberInputCanConvertToCoordinate() {
        String input = "50";
        assertTrue(InputValidation.isValidDouble(input));
    }

    @Test
    public void testDecimalNumberInputCanConvertToCoordinate() {
        String input = "50.99";
        assertTrue(InputValidation.isValidDouble(input));
    }

    @Test
    public void testNegativeNumberInputCanConvertToCoordinate() {
        String input = "-10.0";
        assertTrue(InputValidation.isValidDouble(input));
    }

    @Test
    public void testWordsCannotConvertToCoordinate() {
        String input = "Hello World";
        assertFalse(InputValidation.isValidDouble(input));
    }

    @Test
    public void testSpecialCharactersCannotConvertToCoordinate() {
        String input = "3!@#$%^&*()[]{},.<>-_=+`~/?'\"\\";
        assertFalse(InputValidation.isValidDouble(input));
    }

    // Integer Validation
    @Test
    public void testPositiveWholeNumberIsValidInteger() {
        String input = "50", input2 = "-50";
        assertTrue(InputValidation.isValidInteger(input));
        assertTrue(InputValidation.isValidInteger(input2));
    }

    @Test
    public void testDecimalNumberIsInvalidInteger() {
        String input = "50.5";
        assertFalse(InputValidation.isValidInteger(input));
    }

    @Test
    public void testStringsAreInvalidIntegers() {
        String input = "3!@#$%^&*()[]{},.<>-_=+`~/?'\"\\";
        assertFalse(InputValidation.isValidInteger(input));
    }

    // Age Validation
    @Test
    public void testTwentyTwoIsAValidAge() {
        String input = "22";
        assertTrue(InputValidation.isValidAge(input));
    }

    @Test
    public void testTwentyOneIsAnInvalidAge() {
        String input = "21";
        assertFalse(InputValidation.isValidAge(input));
    }

    @Test
    public void testNinetyEightIsAValidAge() {
        String input = "98";
        assertTrue(InputValidation.isValidAge(input));
    }

    @Test
    public void testNinetyNineIsAnInvalidAge() {
        String input = "99";
        assertFalse(InputValidation.isValidAge(input));
    }

}