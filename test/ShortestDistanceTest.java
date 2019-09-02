import org.junit.jupiter.api.Test;
import java.io.StringBufferInputStream;
import static org.junit.jupiter.api.Assertions.*;

public class ShortestDistanceTest {

    private ShortestDistance shortestDistance = new ShortestDistance();

    // Input Validation
    @Test
    public void testWholeNumberInputCanConvertToNumber() {
        String input = "50";
        assertTrue(ShortestDistance.isValidNumber(input));
    }

    @Test
    public void testDecimalNumberInputCanConvertToNumber() {
        String input = "50.99";
        assertTrue(ShortestDistance.isValidNumber(input));
    }

    @Test
    public void testNegativeNumberInputCanConvertToNumber() {
        String input = "-10.0";
        assertTrue(ShortestDistance.isValidNumber(input));
    }

    @Test
    public void testWordsCannotConvertToNumber() {
        String input = "Hello World";
        assertFalse(ShortestDistance.isValidNumber(input));
    }

    @Test
    public void testSpecialCharactersCannotConvertToNumber() {
        String input = "3!@#$%^&*()[]{},.<>-_=+`~/?'\"\\";
        assertFalse(ShortestDistance.isValidNumber(input));
    }

    // User Input
    @Test
    public void testValidInputReturnsCoordinate() {
        StringBufferInputStream inputStream = new StringBufferInputStream("20");
        System.setIn(inputStream);
        assertEquals(shortestDistance.acceptCoordinate("x1"), 20);
    }

    // Subtraction
    @Test
    public void testSubtractionWithPositiveNumbers() {
        assertEquals(shortestDistance.subtract(5.0, 3.0), 2.0);
    }

    @Test
    public void testSubtractionWithNegativeNumbers() {
        assertEquals(shortestDistance.subtract(-3, -2), -1);
    }

    @Test
    public void testSubtractionWithOppositeSignedNumbers() {
        assertEquals(shortestDistance.subtract(-3, 3), -6);
        assertEquals(shortestDistance.subtract(3, -3), 6);
    }

    @Test
    public void testSubtractionWithDecimalNumbers() {
        assertEquals(shortestDistance.subtract(-2.5, -2.0), -0.5);
    }

//    @Test
//    public void testSubtractionWithSixDigitDecimalNumbers() {
//        assertEquals(shortestDistance.subtract(5.666666, 4.333333), 1.333333);
//        System.out.println(shortestDistance.subtract(4.4, 4.2));
//    }

    // Addition
    @Test
    public void testAdditionWithPositiveNumbers() {
        assertEquals(shortestDistance.add(1.0, 1.0), 2.0);
    }

    @Test
    public void testAdditionwithNegativeNumbers() {
        assertEquals(shortestDistance.add(-1, -11), -12);
    }

    @Test
    public void testAdditionWithOppositeSignedNumbers() {
        assertEquals(shortestDistance.add(1, -1), 0);
    }

    @Test
    public void testAdditionWithDecimalNumbers() {
        assertEquals(shortestDistance.add(1.5, 3.5), 5);
    }

    @Test
    public void testAdditionWithSixDigitDecimalNumbers() {
        assertEquals(shortestDistance.add(1.111111, 3.888888), 4.999999);
    }

    // Square
    @Test
    public void testSquareWithPositiveNumber() {
        assertEquals(shortestDistance.square(5.0), 25);
    }

    @Test
    public void testSquareWithNegativeNumber() {
        assertEquals(shortestDistance.square(-5), 25);
    }

    @Test
    public void testSquareWithDecimalNumber() {
        assertEquals(shortestDistance.square(2.5), 6.25);
    }

//    @Test
//    public void testSquareWithSixDigitDecimalNumber() {
//        assertEquals(shortestDistance.square(2.112233), 4.46152825);
//    }

    // Private Variable Interactions
    @Test
    public void testGetDistance() {
        ShortestDistance emptyDistance = new ShortestDistance();
        assertEquals(emptyDistance.getDistance(), 0);
    }

    @Test
    public void testSetDistance() {
        ShortestDistance emptyDistance = new ShortestDistance();
        emptyDistance.setDistance(2);
        assertEquals(emptyDistance.getDistance(), 2);

    }

    @Test
    public void testGetCoordinate() {
        ShortestDistance emptyCoordinate = new ShortestDistance();
        double[] arr = emptyCoordinate.getCoordinates();
        assertEquals(arr.length, 4);
    }

    @Test
    public void testSetCoordinate() {
        ShortestDistance emptyCoordinate = new ShortestDistance();
        double[] arr = {1, 2, 3, 4};
        emptyCoordinate.setCoordinates(arr);
        assertEquals(emptyCoordinate.getCoordinates(), arr);
    }

    // Distance
    @Test
    public void testDistanceCalculationWithWholePositiveNumbers() {
        double[] values = {1, 1, 2, 2};
        ShortestDistance shortestDistance = new ShortestDistance(values);

        shortestDistance.calculateDistance();
        assertEquals(shortestDistance.getDistance(), Math.sqrt(2));
    }

    @Test
    public void testDistanceCalculationWithMixedNumbers() {
        double[] values = {-1, -1, -2, -2};
        ShortestDistance shortestDistance = new ShortestDistance(values);

        shortestDistance.calculateDistance();
        assertEquals(shortestDistance.getDistance(), Math.sqrt(2));
    }

    @Test
    public void testDistanceCalculationWithDecimalNumbers() {
        double[] values = {1.5, 2, 6, 7.5};
        ShortestDistance shortestDistance = new ShortestDistance(values);

        shortestDistance.calculateDistance();
        assertEquals(shortestDistance.getDistance(), Math.sqrt(50.5));
    }

}
