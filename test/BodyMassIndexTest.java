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
        assertTrue(BodyMassIndex.isValidWeight("150"));
//        assertFalse(BodyMassIndex.isValidWeight("0"));
    }

    @Test
    public void testCheckIfWeightIsOnlyNumbers(){
        assertFalse(BodyMassIndex.isValidWeight("120 lbs"));
        assertFalse(BodyMassIndex.isValidWeight("lbs 120"));
        assertFalse(BodyMassIndex.isValidWeight("120lbs"));
        assertFalse(BodyMassIndex.isValidWeight("120 pounds"));
        assertFalse(BodyMassIndex.isValidWeight("120 lbs"));
        assertFalse(BodyMassIndex.isValidWeight("-120"));
        assertFalse(BodyMassIndex.isValidWeight("one hundred and twenty pounds"));
    }

    @Test
    public void testCheckWeightIsNotZeroPreceding(){
        assertFalse(BodyMassIndex.isValidWeight("090"));
        assertFalse(BodyMassIndex.isValidWeight("0"));
        assertTrue(BodyMassIndex.isValidWeight("100"));
    }

    @Test
    public void testUserInputObject(){
        userInput userInput = new userInput("5\'11\"", 120);
        assertEquals(120, userInput.getWeight());
        assertEquals("5\'11\"", userInput.getHeight());
        assertEquals(11, userInput.getHeightIn());
        assertEquals(5, userInput.getHeightFt());
    }

    @Test
    public void testHeightInInchesConversion(){
        assertEquals(63,BodyMassIndex.getHeightInInches(5, 3));
        assertEquals(10,BodyMassIndex.getHeightInInches(0, 10));
        assertEquals(72,BodyMassIndex.getHeightInInches(6, 0));
    }

//    3 people to test:
//      1.) 5'3" 125lbs
//      2.) 6'0" 166lbs
//      3.) 1'5" 66lbs
    @Test
    public void testHeightInMetersConversion(){
        assertEquals(1.575, BodyMassIndex.getHeightInMeters(5, 3));
        assertEquals(1.8, BodyMassIndex.getHeightInMeters(6, 0));
        assertEquals(0.425, BodyMassIndex.getHeightInMeters(1, 5));
    }

    @Test
    public void testWeightToKiloConversion(){
        assertEquals(56.25, BodyMassIndex.getWeightInKilos(125));
        assertEquals(74.7, BodyMassIndex.getWeightInKilos(166));
        assertEquals(29.7, BodyMassIndex.getWeightInKilos(66));
    }

    @Test
    public void testRoundingHelper(){
        assertEquals(22.5, BodyMassIndex.round(22.4999, 1));
        assertEquals(29.1, BodyMassIndex.round(29.1222, 1));
        assertEquals(0.112, BodyMassIndex.round(0.11223, 3));
        assertEquals(3.789, BodyMassIndex.round(3.78877, 3));
        assertEquals(27.0, BodyMassIndex.round(27.0, 1));
    }

    @Test
    public void testGetBMI(){
        assertEquals(22.7, BodyMassIndex.getBMI(new userInput("5\'3\"", 125)));
        assertEquals(23.1, BodyMassIndex.getBMI(new userInput("6\'0\"", 166)));
        assertEquals(164.4, BodyMassIndex.getBMI(new userInput("1\'5\"", 66)));
    }

    @Test
    public void testGetCategory(){
//        assertThrows()
        assertEquals("Underweight", BodyMassIndex.getBMICategory(17.2));
        assertNotEquals("Underweight", BodyMassIndex.getBMICategory(18.5));
        assertEquals("Normal weight", BodyMassIndex.getBMICategory(18.5));
        assertEquals("Normal weight", BodyMassIndex.getBMICategory(24.9));
        assertNotEquals("Normal weight", BodyMassIndex.getBMICategory(25.0));
        assertEquals("Overweight", BodyMassIndex.getBMICategory(25.0));
        assertEquals("Overweight", BodyMassIndex.getBMICategory(29.9));
        assertNotEquals("Overweight", BodyMassIndex.getBMICategory(30.0));
        assertEquals("Obese", BodyMassIndex.getBMICategory(30.0));
        assertEquals("Obese", BodyMassIndex.getBMICategory(38.1));
    }
}
