import main.java.userInput;
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

        userInput x = BodyMassIndex.acceptInput();
        assertEquals(150, x.getWeight());
        assertEquals("5\'11\"", x.getHeight());
    }

    @Test
    public void testCheckHeightInputReturnsBoolean() {
        assertTrue(BodyMassIndex.isValidHeight("5\'6\""));
    }

    @Test
    public void testWordsAreInvalidHeight() {
        assertFalse(BodyMassIndex.isValidHeight("6ft 3in"));
    }

    @Test
    public void testNoMeasurementUnitsAreInvalidHeights() {
        assertFalse(BodyMassIndex.isValidHeight("63"));
    }

    @Test
    public void testTwoSingleQuotesAreInvalidHeights() {
        assertFalse(BodyMassIndex.isValidHeight("6\'3\'\'"));
    }

    @Test
    public void testTwoDoubleQuotesAreInvalidHeights() {
        assertFalse(BodyMassIndex.isValidHeight("6\"3\""));
    }

    @Test
    public void testCheckHeightInputHasSingleAndDoubleQuote() {
        assertTrue(BodyMassIndex.isValidHeight("4\'11\""));
    }

    @Test
    public void testCheckHeightInputQuotesAreInOrder() {
        assertTrue(BodyMassIndex.isValidHeight("5\'3\""));
    }

    @Test
    public void testInputQuotesAreInWrongOrder() {
        assertFalse(BodyMassIndex.isValidHeight("5\"3\'"));
    }

    @Test
    public void testCheckHeightInputHasCorrectNumberOfQuotes() {
        assertFalse(BodyMassIndex.isValidHeight("\'4\'11\"\""));
        assertFalse(BodyMassIndex.isValidHeight("6\'\'9\""));
        assertTrue(BodyMassIndex.isValidHeight("7\'4\""));
    }

//    check input only has numbers && ' and "
    @Test
    public void testCheckHeightInputHasOnlyQuotesAndNumbers() {
        assertTrue(BodyMassIndex.isValidHeight("5\'11\""));
    }

    @Test
    public void testHeightInputFailsOnWords() {
        assertFalse(BodyMassIndex.isValidHeight("hello\'goodbye\""));
        assertFalse(BodyMassIndex.isValidHeight("5h\'1\""));
    }

    @Test
    public void testHeightInputFailsOnExtraCharacters() {
        assertFalse(BodyMassIndex.isValidHeight("6\' 5\""));
        assertFalse(BodyMassIndex.isValidHeight("5\'11\"!"));
    }

//    check input has quotes after numbers not before (not '5"3)
    @Test
    public void testCheckHeightInputQuotesAreAfterNumbers() {
//       ->5'9"
        assertTrue(BodyMassIndex.isValidHeight("5\'9\""));
    }

    @Test
    public void testHeightInputCatchesWrongQuoteOrder() {
//        x'5"9  x'59" x5'"9
        assertFalse(BodyMassIndex.isValidHeight("\'5\"9"));
        assertFalse(BodyMassIndex.isValidHeight("\'59\""));
        assertFalse(BodyMassIndex.isValidHeight("5\'\"9"));
    }

    @Test
    public void testCheckHeightInputHasValidNumberMeasurements() {
        assertTrue(BodyMassIndex.isValidHeight("4\'0\""));
    }

    @Test
    public void testCheckHeightInputStopsInvalidNumberMeasurements() {
        assertFalse(BodyMassIndex.isValidHeight("5\'12\""));
        assertFalse(BodyMassIndex.isValidHeight("3\'22\""));
    }

    @Test
    public void testCheckHeightInputIsNotOnlyZero() {
        assertFalse(BodyMassIndex.isValidHeight("0\'0\""));
    }

    @Test
    public void testCheckIfHeightIsZeroPreceding() {
        assertTrue(BodyMassIndex.isValidHeight("10\'10\""));
        assertTrue(BodyMassIndex.isValidHeight("0\'10\""));
    }

    @Test
    public void testFeetAndInchesDoNotStartWithZero() {
        assertFalse(BodyMassIndex.isValidHeight("06\'07\""));
        assertFalse(BodyMassIndex.isValidHeight("005\'0009\""));
    }

    @Test
    public void testFeetDoesNotStartWithZero() {
        assertFalse(BodyMassIndex.isValidHeight("04\'10\""));
    }

    @Test
    public void testInchesDoesNotStartWithZero() {
        assertFalse(BodyMassIndex.isValidHeight("5\'09\""));
        assertFalse(BodyMassIndex.isValidHeight("0\'05\""));
    }


    @Test
    public void testCheckWeightInputReturnsBoolean(){
        assertTrue(BodyMassIndex.isValidWeight("150"));
        assertFalse(BodyMassIndex.isValidWeight("0"));
    }

    @Test
    public void testCheckIfWeightContainsAbbreviations(){
        assertFalse(BodyMassIndex.isValidWeight("lbs 120"));
        assertFalse(BodyMassIndex.isValidWeight("120 lbs"));
    }

    @Test
    public void testCheckIfWeightIsNegative() {
        assertFalse(BodyMassIndex.isValidWeight("-120"));
    }

    @Test
    public void testIfWeightIsWords() {
        assertFalse(BodyMassIndex.isValidWeight("one hundred and twenty pounds"));
    }

    @Test
    public void testCheckWeightIsNotZeroPreceding(){
        assertFalse(BodyMassIndex.isValidWeight("090"));
    }

    @Test
    public void testCheckWeightIsNotZero() {
        assertFalse(BodyMassIndex.isValidWeight("0"));
    }

    @Test
    public void testCheckWeightHasValidZeros() {
        assertTrue(BodyMassIndex.isValidWeight("100"));
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
    public void testRoundingToOnePlace(){
        assertEquals(22.5, BodyMassIndex.round(22.4999, 1));
        assertEquals(29.1, BodyMassIndex.round(29.1222, 1));
    }

    @Test
    public void testRoundingToTwoPlaces() {
        assertEquals(27.22, BodyMassIndex.round(27.2167, 2));
        assertEquals(0.22, BodyMassIndex.round(0.2222222, 2));
    }

    @Test
    public void testRoundingToThreePlaces() {
        assertEquals(0.112, BodyMassIndex.round(0.11223, 3));
        assertEquals(3.789, BodyMassIndex.round(3.78877, 3));
    }

    @Test
    public void testGetBMI(){
        assertEquals(22.7, BodyMassIndex.getBMI("5\'3\"", 125));
        assertEquals(23.1, BodyMassIndex.getBMI("6\'0\"", 166));
        assertEquals(164.4, BodyMassIndex.getBMI("1\'5\"", 66));
    }

    @Test
    public void testGetUnderweight(){
//        assertThrows()
        assertEquals("Underweight", BodyMassIndex.getBMICategory(17.2));
        assertNotEquals("Underweight", BodyMassIndex.getBMICategory(18.5));
    }

    @Test
    public void testGetNormalWeight() {
        assertEquals("Normal weight", BodyMassIndex.getBMICategory(18.5));
        assertEquals("Normal weight", BodyMassIndex.getBMICategory(24.9));
        assertNotEquals("Normal weight", BodyMassIndex.getBMICategory(25.0));
    }

    @Test
    public void testGetOverweight() {
        assertEquals("Overweight", BodyMassIndex.getBMICategory(25.0));
        assertEquals("Overweight", BodyMassIndex.getBMICategory(29.9));
        assertNotEquals("Overweight", BodyMassIndex.getBMICategory(30.0));
    }

    @Test
    public void testGetObese() {
        assertEquals("Obese", BodyMassIndex.getBMICategory(30.0));
        assertEquals("Obese", BodyMassIndex.getBMICategory(38.1));
    }

}
