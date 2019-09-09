import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class InputValidationTest {

    // Menu Option
    @Test
    public void testValidNumberIsValidMenuOption() {
        assertTrue(InputValidation.isValidMenuOption("5"));
    }

    @Test
    public void testInvalidInputIsInvalidMenuOption() {
        assertFalse(InputValidation.isValidMenuOption("Hello World"));
    }

    @Test
    public void testIntegerGetsOption() {
        assertEquals(InputValidation.convertToInteger("1"), 1);
    }

    @Test
    public void testStringFailsToGetOption() {
        assertEquals(InputValidation.convertToInteger("Hello World"), 0);
    }

    @Test
    public void testValidOptionFallsInRange() {
        assertTrue(InputValidation.fallsInOptionRange(2));
    }

    @Test
    public void testInvalidOptionDoesNotFallInRange() {
        assertFalse(InputValidation.fallsInOptionRange(0));
    }

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
    public void testValidNumberIsValidAge() {
        String input = "22";
        assertTrue(InputValidation.isValidAge(input));
    }

    @Test
    public void testWordIsInvalidAge() {
        String input = "Hello World";
        assertFalse(InputValidation.isValidAge(input));
    }

    @Test
    public void testTwentyTwoIsAValidAge() {
        int input = 22;
        assertTrue(InputValidation.fallsInAgeRange(input));
    }

    @Test
    public void testTwentyOneIsAnInvalidAge() {
        int input = 21;
        assertFalse(InputValidation.fallsInAgeRange(input));
    }

    @Test
    public void testNinetyEightIsAValidAge() {
        int input = 98;
        assertTrue(InputValidation.fallsInAgeRange(input));
    }

    @Test
    public void testNinetyNineIsAnInvalidAge() {
        int input = 99;
        assertFalse(InputValidation.fallsInAgeRange(input));
    }

    // Amount Validation
    @Test
    public void testStrippingInputWithDollarSignOnly() {
        String input = "$10";
        assertEquals(InputValidation.stripInput(input), "10");
    }

    @Test
    public void testStrippingInputWithCommasOnly() {
        String input = "1,,,,1,";
        assertEquals(InputValidation.stripInput(input), "11");
    }

    @Test
    public void testStrippingInputWithCommaAndDollarSign() {
        String input = "21,3$,";
        assertEquals(InputValidation.stripInput(input), "213");
    }

    @Test
    public void testStrippingInputWithOtherCharacters() {
        String input = "123%^&*";
        assertEquals(InputValidation.stripInput(input), "123%^&*");
    }
}
