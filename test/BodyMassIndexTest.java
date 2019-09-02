import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BodyMassIndexTest {

    @Test
    public void testCheckHeightInputReturnsBoolean() {
        BodyMassIndex bmi = new BodyMassIndex();
        assertTrue(bmi.isValidHeight("5'6\""));
    }

    @Test
    public void testCheckHeightInputHasSingleAndDoubleQuote() {
        BodyMassIndex bmi = new BodyMassIndex();
//        using words
        assertFalse(bmi.isValidHeight("6ft 3in"));
//        no measurement units
        assertFalse(bmi.isValidHeight("63"));
//        using 2 single quotes instead of 1 double quote
        assertFalse(bmi.isValidHeight("6\'3\'\'"));
//        using 2 double quotes
        assertFalse(bmi.isValidHeight("6\"3\""));
//        correct use
        assertTrue(bmi.isValidHeight("4\'11\""));
    }

    @Test
    public void testCheckHeightInputQuotesAreInOrder() {
        BodyMassIndex bmi = new BodyMassIndex();
//        correct order of quotes
        assertTrue(bmi.isValidHeight("5\'3\""));
//        incorrect order of quotes
        assertFalse(bmi.isValidHeight("5\"3\'"));
    }

    @Test
    public void testCheckHeightInputHasCorrectNumberOfQuotes() {
        BodyMassIndex bmi = new BodyMassIndex();
        assertFalse(bmi.isValidHeight("\'4\'11\"\""));
        assertFalse(bmi.isValidHeight("6\'\'9\""));
        assertTrue(bmi.isValidHeight("7\'4\""));
    }


//    check input only has numbers and ' and "
    @Test
    public void testCheckHeightInputHasOnlyQuotesAndNumbers() {
        BodyMassIndex bmi = new BodyMassIndex();
        assertFalse(bmi.isValidHeight("hello\'goodbye\""));
        assertFalse(bmi.isValidHeight("5h\'1\""));
//        spaces are not allowed
        assertFalse(bmi.isValidHeight("6\' 5\""));
        assertFalse(bmi.isValidHeight("5\'11\"!"));
        assertTrue(bmi.isValidHeight("5\'11\""));
    }

//    check input has quotes after numbers not before (not '5"3)
    @Test
    public void testCheckHeightInputQuotesAreAfterNumbers() {
        BodyMassIndex bmi = new BodyMassIndex();
        assertFalse(bmi.isValidHeight("\'5\"9")); // x'5"9  x'59" x5'"9 ->5'9"
        assertFalse(bmi.isValidHeight("\'59\""));
        assertFalse(bmi.isValidHeight("5\'\"9"));
        assertTrue(bmi.isValidHeight("5\'9\""));
    }

    @Test
    public void testCheckHeightInputHasValidNumberMeasurements() {
        BodyMassIndex bmi = new BodyMassIndex();
        assertFalse(bmi.isValidHeight("5\'12\""));
        assertFalse(bmi.isValidHeight("3\'22\""));
        assertFalse(bmi.isValidHeight("0\'0\""));
    }


}
