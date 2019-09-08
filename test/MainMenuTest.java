import com.sun.tools.javac.Main;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

public class MainMenuTest {

    // User Input
    // References: https://stackoverflow.com/a/31635737
    @Test
    public void testValidInputReturnsOption() {
        MainMenu menu = new MainMenu();
        String input = "4\r";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        assertEquals(menu.acceptInput(), 4);
    }

    @Test
    public void testInvalidInputLoopsUntilValid() {
        MainMenu menu = new MainMenu();
        String input = "Hello World\r4";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        assertEquals(menu.acceptInput(), 4);
    }

    // Input Validation
    @Test
    public void testIsInvalidMenuOption() {
        String input1 = "3", input2 = "Hello World";
        assertFalse(MainMenu.isInvalidMenuOption(input1));
        assertTrue(MainMenu.isInvalidMenuOption(input2));
    }

    // Private Variable Interactions
    @Test
    public void testMenuIsRunning() {
        MainMenu menu = new MainMenu();
        assertTrue(menu.isRunning());
    }

    @Test
    public void testSetIsStillRunning() {
        MainMenu menu = new MainMenu();
        menu.setIsStillRunning(false);
        assertFalse(menu.isRunning());
    }

    // Display
    @Test
    public void testCanExitProgram() {
        MainMenu menu = new MainMenu();
        String input = "5\r";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        menu.start();

        assertFalse(menu.isRunning());
    }

}
