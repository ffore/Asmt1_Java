import org.junit.jupiter.api.Test;

import java.io.DataInput;
import java.io.DataInputStream;
import java.io.InputStream;
import java.io.StringBufferInputStream;
import java.util.Scanner;

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

}
