import java.util.Scanner;

public class Retirement {

    private int currentAge;
    private double annualSalary;
    private double percentageSaved;
    private double savingsGoal;
    private Scanner scanner;

    public Retirement() {
        this.currentAge = 0;
        this.annualSalary = 0;
        this.percentageSaved = 0;
        this.savingsGoal = 0;
        this.scanner = new Scanner(System.in);
    }

    // Check user's age is valid (loop) -> Round to integer
    // Check user's salary is valid (loop) -> Round to double (2 digits)
    // Check user's percentage is valid (loop) -> Round to double (1 digit)
    // Check user's savings goal is valid (loop) -> Round to double (2 digits)
    public static void main(String[] args) {
        String[] parameters = {"age", "annual salary", "percentage saved per year", "savings goal"};
        Retirement retirement = new Retirement();
        retirement.setVariables();
    }

    public void setVariables() {
        this.checkAge();
//        this.checkSalary();
//        this.checkPercentage();
//        this.checkSavings();
    }

    public void checkAge() {
        System.out.println("Please enter your current age in years: ");
        String input = scanner.nextLine();

        while(isInvalidAge(input)) {
            System.out.println("Enter a new value for your current age: ");
            input = scanner.nextLine();
        }

        int age = Integer.parseInt(input);
        this.setCurrentAge(age);
    }

    public void checkSalary() {
        System.out.println("Please enter your annual salary in dollars: ");
        System.out.println("NOTE: Your input will be rounded to the nearest cent.");
        String input = scanner.nextLine();

        while(isInvalidAmount(input)) {
            System.out.println("Enter a new value for your annual salary: ");
            input = scanner.nextLine();
        }

        this.cleanSalary(input);
    }

    public void cleanSalary(String input) {
//        String strippedInput = stripInput(input);
//        double salary = Double.parseDouble(strippedInput);
//        double annualSalary = convertToCurrency(salary);
//        this.setAnnualSalary(annualSalary);
    }

    public static boolean isInvalidAge(String age) {
        return !InputValidation.isValidAge(age);
    }

    public static boolean isInvalidAmount(String input) {
        return !InputValidation.isValidAmount(input);
    }

    public int getCurrentAge() {
        return this.currentAge;
    }

    public void setCurrentAge(int age) {
        this.currentAge = age;
    }

    public double getAnnualSalary() {
        return this.annualSalary;
    }

    public void setAnnualSalary(double salary) {
        this.annualSalary = salary;
    }

    public double getPercentageSaved() {
        return this.percentageSaved;
    }

    public void setPercentageSaved(double percent) {
        this.percentageSaved = percent;
    }

    public double getSavingsGoal() {
        return this.savingsGoal;
    }

    public void setSavingsGoal(double goal) {
        this.savingsGoal = goal;
    }
}
