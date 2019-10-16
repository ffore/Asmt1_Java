import java.util.Scanner;

public class Retirement {

    private int currentAge;
    private double annualSalary;
    private int percentageSaved;
    private double savingsGoal;
    private double savingsPerYear;
    private double employerContributions;
    private Scanner scanner;

    public Retirement() {
        this.currentAge = 0;
        this.annualSalary = 0;
        this.percentageSaved = 0;
        this.savingsGoal = 0;
        this.savingsPerYear = 0;
        this.employerContributions = 0;
        this.scanner = new Scanner(System.in);
    }

    public Retirement(int age, double salary, int saved, double goal) {
        this.currentAge = age;
        this.annualSalary = salary;
        this.percentageSaved = saved;
        this.savingsGoal = goal;
        this.savingsPerYear = 0;
        this.employerContributions = 0;
        this.scanner = new Scanner(System.in);
    }

    public void acceptInput() {
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

    public int calculateRetirement() {
        int finalAge;

        if(this.getPercentageSaved() == 0) {
            finalAge = -1;
        } else {
            finalAge = this.findFinalAge();
        }

        this.printResults(finalAge);
        return finalAge;
    }

    public int findFinalAge() {
        int years = 0, finalAge = 0, age;
        double userSavingsPerYear, employerSavingsPerYear, savingsGoal, totalSavings = 0;

        age = this.getCurrentAge();
        savingsGoal = this.getSavingsGoal();
        userSavingsPerYear = this.getSavingsPerYear();
        employerSavingsPerYear = this.getEmployerContributions();;

        while(age + years < 100) {
            if(totalSavings >= savingsGoal) {
                finalAge = age + years;
                break;
            }

            totalSavings += userSavingsPerYear + employerSavingsPerYear;
            totalSavings = Math.round(totalSavings * 100.0) / 100.0;
            years++;
        }

        return finalAge;
    }

    public void printResults(int finalAge) {
        if(finalAge == 0) {
            System.out.println("Given the provided information, you will not reach your " +
                    "savings goal in your lifetime");
        } else if(finalAge == -1) {
            System.out.println("You cannot reach your savings goal because your percentage " +
                    "saved from your annual salary is set to 0");
        } else {
            System.out.println("You will reach your savings goal by the age of " + finalAge);
        }
    }

    public void checkAge() {
        System.out.print("Please enter your current age in years: ");
        String input = scanner.nextLine();

        while(isInvalidAge(input)) {
            System.out.println("");
            System.out.print("Enter a new value for your current age: ");
            input = scanner.nextLine();
        }
        System.out.println("");

        int age = Integer.parseInt(input);
        this.setCurrentAge(age);
    }

    public void checkSalary() {
        System.out.println("NOTE: Your input will be rounded to the nearest cent.");
        System.out.print("Please enter your annual salary in dollars: ");
        String input = scanner.nextLine();

        while(isInvalidAmount(input)) {
            System.out.println("");
            System.out.print("Enter a new value for your annual salary: ");
            input = scanner.nextLine();
        }
        System.out.println("");

        this.roundSalary(input);
    }

    public void checkPercentage() {
        System.out.println("NOTE: Only whole numbers will be accepted.");
        System.out.print("Please enter the percentage of your salary that " +
                "will be saved each year towards your retirement: ");
        String input = scanner.nextLine();

        while(isInvalidPercentage(input)) {
            System.out.println("");
            System.out.print("Enter a new value for your percentage saved: ");
            input = scanner.nextLine();
        }
        System.out.println("");

        int percentage = Integer.parseInt(input);
        this.setPercentageSaved(percentage);
        this.setSavingsPerYear();
        this.setEmployerContributions();
    }

    public void checkSavings() {
        System.out.println("NOTE: Your input will be rounded to the nearest cent.");
        System.out.print("Please enter the savings goal you'd like to reach: ");
        String input = scanner.nextLine();

        while(isInvalidAmount(input)) {
            System.out.println("");
            System.out.print("Enter a new value for your savings goal: ");
            input = scanner.nextLine();
        }
        System.out.println("");

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
        return !main.java.InputValidation.isValidAge(age);
    }

    public static boolean isInvalidAmount(String input) {
        return !main.java.InputValidation.isValidAmount(input);
    }

    public static boolean isInvalidPercentage(String input) {
        return !main.java.InputValidation.isValidPercentage(input);
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

    public void setEmployerContributions() {
        double userAmount = this.getSavingsPerYear();
        double employerAmount = userAmount * 0.35;
        this.employerContributions = Math.round(employerAmount * 100.0) / 100.0;
    }

    public double getSavingsPerYear() {
        return this.savingsPerYear;
    }

    public void setSavingsPerYear() {
        double percent = this.getPercentageSaved() / 100.0;
        this.savingsPerYear = this.getAnnualSalary() * percent;
    }
}
