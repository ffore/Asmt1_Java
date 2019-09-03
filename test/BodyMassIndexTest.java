import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BodyMassIndexTest {

    @Test
    public void testCheckHeightInputReturnsBoolean() {
        assertTrue(BodyMassIndex.isValidHeight("5'6\""));
    }

    @Test
    public void testCheckHeightInputHasSingleAndDoubleQuote() {
//        using words
        assertFalse(BodyMassIndex.isValidHeight("6ft 3in"));
//        no measurement units
        assertFalse(BodyMassIndex.isValidHeight("63"));
//        using 2 single quotes instead of 1 double quote
        assertFalse(BodyMassIndex.isValidHeight("6\'3\'\'"));
//        using 2 double quotes
        assertFalse(BodyMassIndex.isValidHeight("6\"3\""));
//        correct use
        assertTrue(BodyMassIndex.isValidHeight("4\'11\""));
    }

    @Test
    public void testCheckHeightInputQuotesAreInOrder() {
//        correct order of quotes
        assertTrue(BodyMassIndex.isValidHeight("5\'3\""));
//        incorrect order of quotes
        assertFalse(BodyMassIndex.isValidHeight("5\"3\'"));
    }

    @Test
    public void testCheckHeightInputHasCorrectNumberOfQuotes() {
        assertFalse(BodyMassIndex.isValidHeight("\'4\'11\"\""));
        assertFalse(BodyMassIndex.isValidHeight("6\'\'9\""));
        assertTrue(BodyMassIndex.isValidHeight("7\'4\""));
    }


//    check input only has numbers and ' and "
    @Test
    public void testCheckHeightInputHasOnlyQuotesAndNumbers() {
        assertFalse(BodyMassIndex.isValidHeight("hello\'goodbye\""));
        assertFalse(BodyMassIndex.isValidHeight("5h\'1\""));
//        spaces are not allowed
        assertFalse(BodyMassIndex.isValidHeight("6\' 5\""));
        assertFalse(BodyMassIndex.isValidHeight("5\'11\"!"));
        assertTrue(BodyMassIndex.isValidHeight("5\'11\""));
    }

//    check input has quotes after numbers not before (not '5"3)
    @Test
    public void testCheckHeightInputQuotesAreAfterNumbers() {
        assertFalse(BodyMassIndex.isValidHeight("\'5\"9")); // x'5"9  x'59" x5'"9 ->5'9"
        assertFalse(BodyMassIndex.isValidHeight("\'59\""));
        assertFalse(BodyMassIndex.isValidHeight("5\'\"9"));
        assertTrue(BodyMassIndex.isValidHeight("5\'9\""));
    }

    @Test
    public void testCheckHeightInputHasValidNumberMeasurements() {
        assertFalse(BodyMassIndex.isValidHeight("5\'12\""));
        assertFalse(BodyMassIndex.isValidHeight("3\'22\""));
        assertFalse(BodyMassIndex.isValidHeight("0\'0\""));
    }

    @Test
    public void testCheckIfHeightIsZeroPreceding() {
        assertFalse(BodyMassIndex.isValidHeight("5\'09\""));
        assertFalse(BodyMassIndex.isValidHeight("04\'10\""));
        assertFalse(BodyMassIndex.isValidHeight("0\'05\""));
        assertFalse(BodyMassIndex.isValidHeight("06\'07\""));
        assertFalse(BodyMassIndex.isValidHeight("005\'0009\""));
        assertTrue(BodyMassIndex.isValidHeight("10\'10\""));
        assertTrue(BodyMassIndex.isValidHeight("0\'10\""));
    }


    @Test
    public void testCheckWeightInputReturnsBoolean(){
        assertTrue(BodyMassIndex.isValidWeight(150));
        assertFalse(BodyMassIndex.isValidWeight(-120));
        assertFalse(BodyMassIndex.isValidWeight(0));
    }

}
