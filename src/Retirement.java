public class Retirement {

    // Accept user input for age
    // Accept user input for salary
        // Create Rounding function for 2 decimal places
    // Accept user input for "percentage saved" ?
    // Accept user input for desired savings goal
    private int currentAge;
    private double annualSalary;
    private double percentageSaved;
    private double savingsGoal;

    public Retirement() {
        this.currentAge = 0;
        this.annualSalary = 0;
        this.percentageSaved = 0;
        this.savingsGoal = 0;
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
