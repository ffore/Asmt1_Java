import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

public class RetirementTest {

    // User Input
    @Test
    public void testValidAgePassesAgeCheck() {
        String input = "30\r";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        Retirement retirement = new Retirement();

        retirement.checkAge();
        assertEquals(retirement.getCurrentAge(), 30);
    }

    @Test
    public void testInvalidAgeLoopsUntilValid() {
        String input = "Hello World\r30\r";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        Retirement retirement = new Retirement();

        retirement.checkAge();
        assertEquals(retirement.getCurrentAge(), 30);
    }

    // Input Validation
    @Test
    public void testThirtyIsValidAge() {
        assertFalse(Retirement.isInvalidAge("30"));
    }

    @Test
    public void testTenIsInvalidAge() {
        assertTrue(Retirement.isInvalidAge("10"));
    }

    @Test
    public void testWordsAreInvalidAges() {
        assertTrue(Retirement.isInvalidAge("Hello World"));
    }

    // Private Variable Interactions
    @Test
    public void testCanGetCurrentAge() {
        Retirement user = new Retirement();
        assertEquals(user.getCurrentAge(), 0);
    }

    @Test
    public void testCanSetCurrentAge() {
        Retirement user = new Retirement();
        user.setCurrentAge(50);
        assertEquals(user.getCurrentAge(), 50);
    }

    @Test
    public void testCanGetAnnualSalary() {
        Retirement user = new Retirement();
        assertEquals(user.getAnnualSalary(), 0);
    }

    @Test
    public void testCanSetAnnualSalary() {
        Retirement user = new Retirement();
        user.setAnnualSalary(100.00);
        assertEquals(user.getAnnualSalary(), 100.00);
    }

    @Test
    public void testCanGetPercentageSaved() {
        Retirement user = new Retirement();
        assertEquals(user.getPercentageSaved(), 0);
    }

    @Test
    public void testCanSetPercentageSaved() {
        Retirement user = new Retirement();
        user.setPercentageSaved(20);
        assertEquals(user.getPercentageSaved(), 20);
    }

    @Test
    public void testCanGetSavingsGoal() {
        Retirement user = new Retirement();
        assertEquals(user.getSavingsGoal(), 0);
    }

    @Test
    public void testCanSetSavingsGoal() {
        Retirement user = new Retirement();
        user.setSavingsGoal(300);
        assertEquals(user.getSavingsGoal(), 300);
    }
}
