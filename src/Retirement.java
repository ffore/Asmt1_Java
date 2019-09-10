import java.util.Scanner;

public class Retirement {

    private int currentAge;
    private double annualSalary;
    private int percentageSaved;
    private double savingsGoal;
    private double employerContributions;
    private Scanner scanner;

    public Retirement() {
        this.currentAge = 0;
        this.annualSalary = 0;
        this.percentageSaved = 0;
        this.savingsGoal = 0;
        this.employerContributions = 0;
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
        retirement.calculateRetirement();
    }

    public void setVariables() {
        this.checkAge();
        this.checkSalary();
        this.checkPercentage();
        this.checkSavings();
    }

    public void calculateRetirement() {
        int finalAge = this.findFinalAge();
        this.printResults(finalAge);
    }

    public int findFinalAge() {
        return 1;
    }

    public void printResults(int finalAge) {
        if(finalAge >= 100) {
            System.out.println("Given the provided information, you will not reach your " +
                    "savings goal in your lifetime");
        } else {
            System.out.println("You will reach your savings goal by the age of " + finalAge);
        }
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

        this.roundSalary(input);
    }

    public void checkPercentage() {
        System.out.println("Please enter the percentage of your salary that " +
                "will be saved each year towards your retirement: ");
        System.out.println("NOTE: Only whole numbers will be accepted.");
        String input = scanner.nextLine();

        while(isInvalidPercentage(input)) {
            System.out.println("Enter a new value for your percentage saved: ");
            input = scanner.nextLine();
        }

        int percentage = Integer.parseInt(input);
        this.setPercentageSaved(percentage);
    }

    public void checkSavings() {
        System.out.println("Please enter the savings goal you'd like to reach: ");
        System.out.println("NOTE: Your input will be rounded to the nearest cent.");
        String input = scanner.nextLine();

        while(isInvalidAmount(input)) {
            System.out.println("Enter a new value for your savings goal: ");
            input = scanner.nextLine();
        }

        double savings = roundToTwoDecimalDouble(input);
        this.setSavingsGoal(savings);
    }

    public void roundSalary(String input) {
        double annualSalary = roundToTwoDecimalDouble(input);
        this.setAnnualSalary(annualSalary);
    }

    public double roundToTwoDecimalDouble(String input) {
        double amount = Double.parseDouble(input);
        return Math.round(amount * 100.0) / 100.0;
    }

    public static boolean isInvalidAge(String age) {
        return !InputValidation.isValidAge(age);
    }

    public static boolean isInvalidAmount(String input) {
        return !InputValidation.isValidAmount(input);
    }

    public static boolean isInvalidPercentage(String input) {
        return !InputValidation.isValidPercentage(input);
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

    public void setPercentageSaved(int percent) {
        this.percentageSaved = percent;
    }

    public double getSavingsGoal() {
        return this.savingsGoal;
    }

    public void setSavingsGoal(double goal) {
        this.savingsGoal = goal;
    }

    public double getEmployerContributions() {
        return this.employerContributions;
    }

    public void setEmployerContributions(double salary) {
//        double amount = roundToTwoDecimalDouble(salary * 0.35);
//        this.employerContributions = amount;
    }
}
