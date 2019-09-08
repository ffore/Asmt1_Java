public class Retirement {

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

    public static void main(String[] args) {

        for(int i = 0; i < 4; i++) {

        }
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
