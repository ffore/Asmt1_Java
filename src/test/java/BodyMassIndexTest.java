import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

public class BodyMassIndexTest {

//    @Test
//    public void testHeightPrompt() {
//        assertEquals("Height Measurement\nUse single quote (\') for feet & double quote (\") for inches\nThis is valid input --> 5\'8\" (no spaces!)\nEnter height: ", BodyMassIndex.getHeightPrompt());
//    }
//
//    @Test
//    public void testHeightRetryPrompt() {
//        assertEquals("Something went wrong, please enter height again: ", BodyMassIndex.getHeightRetryPrompt());
//    }

    @Test
    public void testInput() {
        String input = "5\'11\"\r150\r";
//        following code inspired by: https://stackoverflow.com/a/31635737
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        BodyMassIndex bodyMassIndex = new BodyMassIndex();
        userInput x = bodyMassIndex.acceptInput();
        assertEquals(150, x.getWeight());
        assertEquals("5\'11\"", x.getHeight());
    }

    @Test
    public void testCheckHeightInputReturnsBoolean() {
        BodyMassIndex bodyMassIndex = new BodyMassIndex();
        assertTrue(bodyMassIndex.isValidHeight("5\'6\""));
    }

    @Test
    public void testWordsAreInvalidHeight() {
        BodyMassIndex bodyMassIndex = new BodyMassIndex();
        assertFalse(bodyMassIndex.isValidHeight("6ft 3in"));
    }

    @Test
    public void testNoMeasurementUnitsAreInvalidHeights() {
        BodyMassIndex bodyMassIndex = new BodyMassIndex();
        assertFalse(bodyMassIndex.isValidHeight("63"));
    }

    @Test
    public void testTwoSingleQuotesAreInvalidHeights() {
        BodyMassIndex bodyMassIndex = new BodyMassIndex();
        assertFalse(bodyMassIndex.isValidHeight("6\'3\'\'"));
    }

    @Test
    public void testTwoDoubleQuotesAreInvalidHeights() {
        BodyMassIndex bodyMassIndex = new BodyMassIndex();
        assertFalse(bodyMassIndex.isValidHeight("6\"3\""));
    }

    @Test
    public void testCheckHeightInputHasSingleAndDoubleQuote() {
        BodyMassIndex bodyMassIndex = new BodyMassIndex();
        assertTrue(bodyMassIndex.isValidHeight("4\'11\""));
    }

    @Test
    public void testCheckHeightInputQuotesAreInOrder() {
        BodyMassIndex bodyMassIndex = new BodyMassIndex();
        assertTrue(bodyMassIndex.isValidHeight("5\'3\""));
    }

    @Test
    public void testInputQuotesAreInWrongOrder() {
        BodyMassIndex bodyMassIndex = new BodyMassIndex();
        assertFalse(bodyMassIndex.isValidHeight("5\"3\'"));
    }

    @Test
    public void testCheckHeightInputHasCorrectNumberOfQuotes() {
        BodyMassIndex bodyMassIndex = new BodyMassIndex();
        assertFalse(bodyMassIndex.isValidHeight("\'4\'11\"\""));
        assertFalse(bodyMassIndex.isValidHeight("6\'\'9\""));
        assertTrue(bodyMassIndex.isValidHeight("7\'4\""));
    }

//    check input only has numbers && ' and "
    @Test
    public void testCheckHeightInputHasOnlyQuotesAndNumbers() {
        BodyMassIndex bodyMassIndex = new BodyMassIndex();
        assertTrue(bodyMassIndex.isValidHeight("5\'11\""));
    }

    @Test
    public void testHeightInputFailsOnWords() {
        BodyMassIndex bodyMassIndex = new BodyMassIndex();
        assertFalse(bodyMassIndex.isValidHeight("hello\'goodbye\""));
        assertFalse(bodyMassIndex.isValidHeight("5h\'1\""));
    }

    @Test
    public void testHeightInputFailsOnExtraCharacters() {
        BodyMassIndex bodyMassIndex = new BodyMassIndex();
        assertFalse(bodyMassIndex.isValidHeight("6\' 5\""));
        assertFalse(bodyMassIndex.isValidHeight("5\'11\"!"));
    }

//    check input has quotes after numbers not before (not '5"3)
    @Test
    public void testCheckHeightInputQuotesAreAfterNumbers() {
//       ->5'9"
        BodyMassIndex bodyMassIndex = new BodyMassIndex();
        assertTrue(bodyMassIndex.isValidHeight("5\'9\""));
    }

    @Test
    public void testHeightInputCatchesWrongQuoteOrder() {
//        x'5"9  x'59" x5'"9
        BodyMassIndex bodyMassIndex = new BodyMassIndex();
        assertFalse(bodyMassIndex.isValidHeight("\'5\"9"));
        assertFalse(bodyMassIndex.isValidHeight("\'59\""));
        assertFalse(bodyMassIndex.isValidHeight("5\'\"9"));
    }

    @Test
    public void testCheckHeightInputHasValidNumberMeasurements() {
        BodyMassIndex bodyMassIndex = new BodyMassIndex();
        assertTrue(bodyMassIndex.isValidHeight("4\'0\""));
    }

    @Test
    public void testCheckHeightInputStopsInvalidNumberMeasurements() {
        BodyMassIndex bodyMassIndex = new BodyMassIndex();
        assertFalse(bodyMassIndex.isValidHeight("5\'12\""));
        assertFalse(bodyMassIndex.isValidHeight("3\'22\""));
    }

    @Test
    public void testCheckHeightInputIsNotOnlyZero() {
        BodyMassIndex bodyMassIndex = new BodyMassIndex();
        assertFalse(bodyMassIndex.isValidHeight("0\'0\""));
    }

    @Test
    public void testCheckIfHeightIsZeroPreceding() {
        BodyMassIndex bodyMassIndex = new BodyMassIndex();
        assertTrue(bodyMassIndex.isValidHeight("10\'10\""));
        assertTrue(bodyMassIndex.isValidHeight("0\'10\""));
    }

    @Test
    public void testFeetAndInchesDoNotStartWithZero() {
        BodyMassIndex bodyMassIndex = new BodyMassIndex();
        assertFalse(bodyMassIndex.isValidHeight("06\'07\""));
        assertFalse(bodyMassIndex.isValidHeight("005\'0009\""));
    }

    @Test
    public void testFeetDoesNotStartWithZero() {
        BodyMassIndex bodyMassIndex = new BodyMassIndex();
        assertFalse(bodyMassIndex.isValidHeight("04\'10\""));
    }

    @Test
    public void testInchesDoesNotStartWithZero() {
        BodyMassIndex bodyMassIndex = new BodyMassIndex();
        assertFalse(bodyMassIndex.isValidHeight("5\'09\""));
        assertFalse(bodyMassIndex.isValidHeight("0\'05\""));
    }


    @Test
    public void testCheckWeightInputReturnsBoolean(){
        BodyMassIndex bodyMassIndex = new BodyMassIndex();
        assertTrue(bodyMassIndex.isValidWeight("150"));
        assertFalse(bodyMassIndex.isValidWeight("0"));
    }

    @Test
    public void testCheckIfWeightContainsAbbreviations(){
        BodyMassIndex bodyMassIndex = new BodyMassIndex();
        assertFalse(bodyMassIndex.isValidWeight("lbs 120"));
        assertFalse(bodyMassIndex.isValidWeight("120 lbs"));
    }

    @Test
    public void testCheckIfWeightIsNegative() {
        BodyMassIndex bodyMassIndex = new BodyMassIndex();
        assertFalse(bodyMassIndex.isValidWeight("-120"));
    }

    @Test
    public void testIfWeightIsWords() {
        BodyMassIndex bodyMassIndex = new BodyMassIndex();
        assertFalse(bodyMassIndex.isValidWeight("one hundred and twenty pounds"));
    }

    @Test
    public void testCheckWeightIsNotZeroPreceding(){
        BodyMassIndex bodyMassIndex = new BodyMassIndex();
        assertFalse(bodyMassIndex.isValidWeight("090"));
    }

    @Test
    public void testCheckWeightIsNotZero() {
        BodyMassIndex bodyMassIndex = new BodyMassIndex();
        assertFalse(bodyMassIndex.isValidWeight("0"));
    }

    @Test
    public void testCheckWeightHasValidZeros() {
        BodyMassIndex bodyMassIndex = new BodyMassIndex();
        assertTrue(bodyMassIndex.isValidWeight("100"));
    }

    @Test
    public void testUserInputObject(){
//        assuming only valid height and weight will be fed into userInput object
//        all bad inputs would have been prevented by acceptUserInput function
        userInput userInput = new userInput("5\'11\"", 120);
        assertEquals(120, userInput.getWeight());
        assertEquals("5\'11\"", userInput.getHeight());
    }

    @Test
    public void testHeightInInchesConversion(){
        BodyMassIndex bodyMassIndex = new BodyMassIndex();
        assertEquals(63,bodyMassIndex.getHeightInInches(5, 3));
        assertEquals(10,bodyMassIndex.getHeightInInches(0, 10));
        assertEquals(72,bodyMassIndex.getHeightInInches(6, 0));
    }

//    3 people to test:
//      1.) 5'3" 125lbs
//      2.) 6'0" 166lbs
//      3.) 1'5" 66lbs
    @Test
    public void testHeightInMetersConversion(){
        BodyMassIndex bodyMassIndex = new BodyMassIndex();
        assertEquals(1.575, bodyMassIndex.getHeightInMeters(5, 3));
        assertEquals(1.8, bodyMassIndex.getHeightInMeters(6, 0));
        assertEquals(0.425, bodyMassIndex.getHeightInMeters(1, 5));
    }

    @Test
    public void testWeightToKiloConversion(){
        BodyMassIndex bodyMassIndex = new BodyMassIndex();
        assertEquals(56.25, bodyMassIndex.getWeightInKilos(125));
        assertEquals(74.7, bodyMassIndex.getWeightInKilos(166));
        assertEquals(29.7, bodyMassIndex.getWeightInKilos(66));
    }

    @Test
    public void testRoundingToOnePlace(){
        BodyMassIndex bodyMassIndex = new BodyMassIndex();
        assertEquals(22.5, bodyMassIndex.round(22.4999, 1));
        assertEquals(29.1, bodyMassIndex.round(29.1222, 1));
    }

    @Test
    public void testRoundingToTwoPlaces() {
        BodyMassIndex bodyMassIndex = new BodyMassIndex();
        assertEquals(27.22, bodyMassIndex.round(27.2167, 2));
        assertEquals(0.22, bodyMassIndex.round(0.2222222, 2));
    }

    @Test
    public void testRoundingToThreePlaces() {
        BodyMassIndex bodyMassIndex = new BodyMassIndex();
        assertEquals(0.112, bodyMassIndex.round(0.11223, 3));
        assertEquals(3.789, bodyMassIndex.round(3.78877, 3));
    }

    @Test
    public void testGetBMI(){
        BodyMassIndex bodyMassIndex = new BodyMassIndex();
        assertEquals(22.7, bodyMassIndex.getBMI("5\'3\"", 125));
        assertEquals(23.1, bodyMassIndex.getBMI("6\'0\"", 166));
        assertEquals(164.4, bodyMassIndex.getBMI("1\'5\"", 66));
    }

    @Test
    public void testGetUnderweight(){
//        assertThrows()
        BodyMassIndex bodyMassIndex = new BodyMassIndex();
        assertEquals("Underweight", bodyMassIndex.getBMICategory(17.2));
        assertNotEquals("Underweight", bodyMassIndex.getBMICategory(18.5));
    }

    @Test
    public void testGetNormalWeight() {
        BodyMassIndex bodyMassIndex = new BodyMassIndex();
        assertEquals("Normal weight", bodyMassIndex.getBMICategory(18.5));
        assertEquals("Normal weight", bodyMassIndex.getBMICategory(24.9));
        assertNotEquals("Normal weight", bodyMassIndex.getBMICategory(25.0));
    }

    @Test
    public void testGetOverweight() {
        BodyMassIndex bodyMassIndex = new BodyMassIndex();
        assertEquals("Overweight", bodyMassIndex.getBMICategory(25.0));
        assertEquals("Overweight", bodyMassIndex.getBMICategory(29.9));
        assertNotEquals("Overweight", bodyMassIndex.getBMICategory(30.0));
    }

    @Test
    public void testGetObese() {
        BodyMassIndex bodyMassIndex = new BodyMassIndex();
        assertEquals("Obese", bodyMassIndex.getBMICategory(30.0));
        assertEquals("Obese", bodyMassIndex.getBMICategory(38.1));
    }

}
