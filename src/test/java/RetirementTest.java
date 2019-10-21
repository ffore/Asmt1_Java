import main.java.Retirement;
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

    @Test
    public void testInvalidSalaryLoopsUntilValid() {
        String input = "1.0.0\r1.00\r";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        Retirement retirement = new Retirement();

        retirement.checkSalary();
        assertEquals(retirement.getAnnualSalary(), 1.00);
    }

    @Test
    public void testValidSalaryPassesAmountCheck() {
        String input = "100\r";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        Retirement retirement = new Retirement();

        retirement.checkSalary();
        assertEquals(retirement.getAnnualSalary(), 100);
    }

    @Test
    public void testInvalidPercentageLoopsUntilValid() {
        String input = "1001\r0\r";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        Retirement retirement = new Retirement();

        retirement.checkPercentage();
        assertEquals(retirement.getPercentageSaved(), 0);
    }

    @Test
    public void testValidPercentagePassesPercentageCheck() {
        String input = "100\r";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        Retirement retirement = new Retirement();

        retirement.checkPercentage();
        assertEquals(retirement.getPercentageSaved(), 100);
    }

    @Test
    public void testInvalidSavingsGoalLoopsUntilValid() {
        String input = "1.0.0\r1.00\r";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        Retirement retirement = new Retirement();

        retirement.checkSavings();
        assertEquals(retirement.getSavingsGoal(), 1.00);
    }

    @Test
    public void testValidSavingsGoalPassesSavingsCheck() {
        String input = "11111.001111\r";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        Retirement retirement = new Retirement();

        retirement.checkSavings();
        assertEquals(retirement.getSavingsGoal(), 11111.00);
    }

    @Test
    public void testRoundSalary() {
        // Input reaching this point is ALWAYS valid
        String salary = "1000.0035";
        Retirement retirement = new Retirement();
        retirement.roundSalary(salary);
        assertEquals(retirement.getAnnualSalary(), 1000.00);
    }

    @Test
    public void testRoundToTwoDecimalDouble() {
        String amount = "1000.123456";
        Retirement retirement = new Retirement();
        assertEquals(retirement.roundToTwoDecimalDouble(amount), 1000.12);
    }

    // Input Validation
    @Test
    public void testBadSalaryInputIsInvalidAmount() {
        assertTrue(Retirement.isInvalidAmount("$10"));
        assertTrue(Retirement.isInvalidAmount("1.0.0"));
        assertTrue(Retirement.isInvalidAmount("1,000.00"));
    }

    @Test
    public void testSalaryIsValidAmount() {
        assertFalse(Retirement.isInvalidAmount("1000.00"));
    }

    @Test
    public void testWordsAreInvalidAmounts() {
        assertTrue(Retirement.isInvalidAmount("Hello World"));
    }

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

    @Test
    public void testDecimalIsInvalidPercentage() {
        assertTrue(Retirement.isInvalidPercentage("1.5"));
    }

    @Test
    public void testWholeNumberIsValidPercentage() {
        assertFalse(Retirement.isInvalidPercentage("100"));
    }

    @Test
    public void testLargeAndSmallNumbersAreInvalidPercentage() {
        assertTrue(Retirement.isInvalidPercentage("-1"));
        assertTrue(Retirement.isInvalidPercentage("1001"));
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

    @Test
    public void testCanGetEmployerContribution() {
        Retirement user = new Retirement();
        assertEquals(user.getEmployerContributions(), 0);
    }

    @Test
    public void testCanSetEmployerContributions() {
        // Input is ALWAYS valid here
        Retirement user = new Retirement();
        user.setPercentageSaved(100);
        user.setAnnualSalary(100);
        user.setSavingsPerYear();
        user.setEmployerContributions();
        assertEquals(user.getEmployerContributions(), 35);
    }

    @Test
    public void testCanGetSavingsPerYear() {
        Retirement user = new Retirement();
        assertEquals(user.getSavingsPerYear(), 0);
    }

    @Test
    public void testCanSetSavingsPerYear() {
        Retirement user = new Retirement();
        user.setPercentageSaved(5);
        user.setAnnualSalary(100);

        user.setSavingsPerYear();
        assertEquals(user.getSavingsPerYear(), 5);
    }

    // main.java.Retirement Calculation
    @Test
    public void testCalculateRetirementWhenSavingsIsNotReached() {
        Retirement user = new Retirement(50, 1000, 10, 200000);
        user.setSavingsPerYear();
        user.setEmployerContributions();

        assertEquals(user.calculateRetirement(), 0);
    }

    @Test
    public void testCalculateRetirementIfUserDoesNotSave() {
        Retirement user = new Retirement(30, 1000, 0, 2000);
        user.setSavingsPerYear();
        user.setEmployerContributions();

        assertEquals(user.calculateRetirement(), -1);
    }

    @Test
    public void testCalculateRetirementWithSimpleValues() {
        Retirement user = new Retirement(30, 1000, 100, 2000);
        user.setSavingsPerYear();
        user.setEmployerContributions();

        assertEquals(user.calculateRetirement(), 32);
    }

    @Test
    public void testFindFinalAgeForSimpleValues() {
        Retirement user = new Retirement(30, 1000, 100, 2000);
        user.setSavingsPerYear();
        user.setEmployerContributions();

        assertEquals(user.findFinalAge(), 32);
    }

    @Test
    public void testFindFinalAgeForMediumValues() {
        Retirement user = new Retirement(30, 1000, 10, 2000);
        user.setSavingsPerYear();
        user.setEmployerContributions();

        // Calculation is as follows:
        // 100 (user supplied per year) + 35 (employer supplied)
        // 135 * 15 = 2025, which meets our goal of 2000
        assertEquals(user.findFinalAge(), 45);
    }

    @Test
    public void testFindFinalAgeIfUserDoesNotSaveAnything() {
        Retirement user = new Retirement(50, 1000, 10, 200000);
        user.setSavingsPerYear();
        user.setEmployerContributions();

        assertEquals(user.findFinalAge(), 0);
    }
}
