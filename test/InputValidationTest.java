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
    public void testIntegerConvertsToInteger() {
        assertEquals(InputValidation.convertToInteger("1"), 1);
    }

    @Test
    public void testStringFailsToConvertToInteger() {
        assertEquals(InputValidation.convertToInteger("Hello World"), -1);
    }

    @Test
    public void testValidOptionFallsInRange() {
        assertTrue(InputValidation.fallsInOptionRange(2));
    }

    @Test
    public void testInvalidOptionDoesNotFallInRange() {
        assertFalse(InputValidation.fallsInOptionRange(0));
    }

    // Double Validation
    @Test
    public void testWholeNumberInputCanConvertToDouble() {
        String input = "50";
        assertTrue(InputValidation.isValidDouble(input));
    }

    @Test
    public void testDecimalNumberInputCanConvertToDouble() {
        String input = "50.99";
        assertTrue(InputValidation.isValidDouble(input));
    }

    @Test
    public void testNegativeNumberInputCanConvertToDouble() {
        String input = "-10.0";
        assertTrue(InputValidation.isValidDouble(input));
    }

    @Test
    public void testWordsCannotConvertToDouble() {
        String input = "Hello World";
        assertFalse(InputValidation.isValidDouble(input));
    }

    @Test
    public void testSpecialCharactersCannotConvertToDouble() {
        String input = "3!@#$%^&*()[]{},.<>-_=+`~/?'\"\\";
        assertFalse(InputValidation.isValidDouble(input));
    }

    // Integer Validation
    @Test
    public void testPositiveWholeNumberIsValidInteger() {
        String input = "50", input2 = "-50";
        assertTrue(InputValidation.isValidInteger(input));
        assertFalse(InputValidation.isValidInteger(input2));
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
    public void testTwentyTwoFallsInAgeRange() {
        int input = 22;
        assertTrue(InputValidation.fallsInAgeRange(input));
    }

    @Test
    public void testTwentyOneDoesNotFallInAgeRange() {
        int input = 21;
        assertFalse(InputValidation.fallsInAgeRange(input));
    }

    @Test
    public void testNinetyEightFallsInAgeRange() {
        int input = 98;
        assertTrue(InputValidation.fallsInAgeRange(input));
    }

    @Test
    public void testNinetyNineDoesNotFallInAgeRange() {
        int input = 99;
        assertFalse(InputValidation.fallsInAgeRange(input));
    }

    // Amount Validation
    @Test
    public void testExtraCharactersAreInvalidAmount() {
        String input = "$1.000,000";
        assertFalse(InputValidation.isValidAmount(input));
    }

    @Test
    public void testWordsAreInvalidAmount() {
        String input = "Hello World";
        assertFalse(InputValidation.isValidAmount(input));
    }

    @Test
    public void testValidSalaryIsValidAmount() {
        String input = "1000000";
        assertTrue(InputValidation.isValidAmount(input));
    }

    @Test
    public void testNegativeValuesAreInvalid() {
        String input = "-1";
        assertFalse(InputValidation.isValidAmount(input));
    }

    @Test
    public void testCanConvertSalaryAndRoundToAmount() {
        String input = "1000.005";
        assertEquals(InputValidation.convertToAmount(input), 1000.01);
    }

    @Test
    public void testCannotConvertWordsToAmount() {
        String input = "Hello World";
        assertEquals(InputValidation.convertToAmount(input), -1);
    }

    // Percentage Validation
    @Test
    public void testSimpleInputIsValidPercentage() {
        assertTrue(InputValidation.isValidPercentage("1"));
    }

    @Test
    public void testWordsAreInvalidPercentage() {
        assertFalse(InputValidation.isValidPercentage("Hello World"));
    }

    @Test
    public void testZeroIsValidAge() {
        assertTrue(InputValidation.fallsInPercentageRange(0));
    }

    @Test
    public void testPositiveNumbersFallInPercentageRange() {
        assertTrue(InputValidation.fallsInPercentageRange(1));
        assertTrue(InputValidation.fallsInPercentageRange(100));
    }

    @Test
    public void testNegativeAndLargeNumbersDoNotFallInPercentageRange() {
        assertFalse(InputValidation.fallsInPercentageRange(-1));
        assertFalse(InputValidation.fallsInPercentageRange(101));
    }

    // Input Checking
    @Test
    public void testInputContainsAllExtraSymbols() {
        String input = "$1,000.000.00";
        assertTrue(InputValidation.containsExtraSymbols(input));
    }

    @Test
    public void testInputContainsNoExtraSymbols() {
        String input = "100.00";
        assertFalse(InputValidation.containsExtraSymbols(input));
    }

    @Test
    public void testInputContainsMultipleDollarSigns() {
        String input = "$$$$";
        assertTrue(InputValidation.containsDollarSign(input));
    }

    @Test
    public void testInputContainsNoDollarSigns() {
        String input = "100";
        assertFalse(InputValidation.containsDollarSign(input));
    }

    @Test
    public void testInputContainsDollarSign() {
        String input = "$100";
        assertTrue(InputValidation.containsDollarSign(input));
    }

    @Test
    public void testInputContainsMultipleCommas() {
        String input = "1,000,000";
        assertTrue(InputValidation.containsCommas(input));
    }

    @Test
    public void testInputContainsNoCommas() {
        String input = "1000";
        assertFalse(InputValidation.containsCommas(input));
    }

    @Test
    public void testInputContainsOneComma() {
        String input = "1,000";
        assertTrue(InputValidation.containsCommas(input));
    }

    @Test
    public void testInputContainsMultipleDecimals() {
        String input = "1.10.0";
        assertTrue(InputValidation.containsMultipleDecimals(input));
    }

    @Test
    public void testInputContainsNoDecimals() {
        String input2 = "100";
        assertFalse(InputValidation.containsMultipleDecimals(input2));
    }

    @Test
    public void testInputContainsOneDecimal() {
        String input3 = "10.00";
        assertFalse(InputValidation.containsMultipleDecimals(input3));
    }

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
        assertTrue(InputValidation.notZero(".15"));
    }

    @Test
    public void testNumOfPeopleIsPositiveInt(){
        assertFalse(InputValidation.isOnlyNumbers("hello"));
        assertFalse(InputValidation.isOnlyNumbers("99.99"));
        assertTrue(InputValidation.isOnlyNumbers("44"));
        assertFalse(InputValidation.isOnlyNumbers("-3"));
        assertTrue(InputValidation.isOnlyNumbers("0"));
    }

    @Test
    public void testNumOfPeopleNotZero(){
        assertFalse(InputValidation.notZeroPeople("0"));
        assertTrue(InputValidation.notZeroPeople("4"));
        assertTrue(InputValidation.notZeroPeople("-2"));
    }

}
