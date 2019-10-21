import main.java.InputValidation;
import main.java.SqlDatabase;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.Scanner;

public class BodyMassIndex {

    private SqlDatabase db;
    private String height;
    private int weight;
    private double bmi;

    public BodyMassIndex() {}

    public BodyMassIndex(SqlDatabase database) {
        this.db = database;
    }

    public BodyMassIndex(SqlDatabase database, String height, int weight) {
        this.db = database;
        this.height = height;
        this.weight = weight;
    }

    public BodyMassIndex(SqlDatabase database, String height, int weight, double bmi) {
        this.db = database;
        this.height = height;
        this.weight = weight;
        this.bmi = bmi;
    }

    public userInput acceptInput(){
        Scanner myScan = new Scanner(System.in);
        boolean validHeight = false;
        String height="";
        int count = 0;
        while(!validHeight){
            if(count == 0) {
                System.out.println("Height Measurement");
                System.out.println("Use single quote (\') for feet & double quote (\") for inches");
                System.out.println("This is valid input --> 5\'8\" (no spaces!)");
                System.out.print("Enter height: ");
            }
            else System.out.print("Something went wrong, please enter height again: ");
            count++;
            height = myScan.nextLine();
            validHeight = isValidHeight(height);
        }
        setHeight(height);
        boolean validWeight = false;
        String weightStr = "";
        count = 0;
        while(!validWeight){
            if(count == 0){
                System.out.println("\nWeight Measurement");
                System.out.println("This is valid input --> 120");
                System.out.print("Enter weight in lbs: ");
            }
            else System.out.print("Something went wrong, please enter weight again: ");
            count++;
            weightStr = myScan.nextLine();
            validWeight = isValidWeight(weightStr);
        }
        int weight = Integer.parseInt(weightStr);
        setWeight(weight);
        printResult(height, weight);
        writeToDatabase();
        return new userInput(height, weight);
    }

    public static boolean isValidHeight(String height){
        int singleQuoteIndex = height.indexOf('\'');
        int doubleQuoteIndex = height.indexOf('\"');

        if( !InputValidation.doQuotesExistInOrder(singleQuoteIndex, doubleQuoteIndex) ||
            !InputValidation.doQuotesOccurOnce(height) ||
            !InputValidation.isOnlyNumbersAndQuotes(height) ||
            !InputValidation.areQuotesAfterNumbers(height, singleQuoteIndex, doubleQuoteIndex) ||
            !InputValidation.areNumbersZeroPreceding(height, singleQuoteIndex, doubleQuoteIndex) ||
            !InputValidation.areMeasurementsValid(height, singleQuoteIndex, doubleQuoteIndex)) return false;
        else return true;
    }

    public static boolean isValidWeight(String weight){
        if( InputValidation.isOnlyNumbers(weight) &&
            InputValidation.isFirstCharValidDigit(weight) ) return true;
        return false;
    }

    public double getHeightInMeters(int ft, int in){
        double totalHeightInches = (double) getHeightInInches(ft, in);
        double htInMeters = totalHeightInches*0.025;
        return round(htInMeters, 3);
    }

    public int getHeightInInches(int ft, int in){
        return (ft*12 + in);
    }

    public double getWeightInKilos(int lbs){
        double lbsInDbl = (double) lbs;
        double kilos = lbsInDbl*0.45;
        return round(kilos, 2);

    }

    public double getBMI(String height, int weight){
        double heightInMeters = getHeightInMeters(getHeightFt(height), getHeightIn(height));
        double weightInKilos = getWeightInKilos(weight);
        double metersSquared = heightInMeters*heightInMeters;
        double rawBMI = weightInKilos/metersSquared;
        double bmi = round(rawBMI, 1);
        this.setBmi(bmi);
        return bmi;
    }

    public double round(double value, int places){
//      following is inspired by https://www.baeldung.com/java-round-decimal-number
        BigDecimal bdRounded = new BigDecimal(Double.toString(value));
        bdRounded = bdRounded.setScale(places, RoundingMode.HALF_UP);
        return bdRounded.doubleValue();
    }

    public String getBMICategory(double bmi){
        if(bmi<18.5) return "Underweight";
        else if(bmi >= 18.5 && bmi <= 24.9) return "Normal weight";
        else if(bmi >= 25 && bmi <= 29.9) return "Overweight";
        else return "Obese";
    }

    public void printResult(String height, int weight){
        double bmi = getBMI(height, weight);
        setBmi(bmi);
        String category = getBMICategory(bmi);
        System.out.println("\nBMI: " + bmi + " (" + category + ")");
    }

    public int getHeightIn(String height){
        int singleQuoteIndex = height.indexOf('\'');
        int doubleQuoteIndex = height.indexOf('\"');
        String inchStr = height.substring(singleQuoteIndex+1, doubleQuoteIndex);
        return Integer.parseInt(inchStr);
    }

    public int getHeightFt(String height){
        int singleQuoteIndex = height.indexOf('\'');
        String inchStr = height.substring(0, singleQuoteIndex);
        return Integer.parseInt(inchStr);
    }

    public int writeToDatabase() {
        String timestamp = this.createTimeStamp();
        return this.writeToTable(timestamp);
    }

    public String createTimeStamp() {
        // https://www.geeksforgeeks.org/new-date-time-api-java8/
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        LocalDateTime timestamp = LocalDateTime.now();
        String formattedTime = timestamp.format(format);
        return formattedTime;
    }

    public int writeToTable(String timestamp) {
        SqlDatabase db = this.getDatabase();
        String height = this.getHeight();
        int weight = this.getWeight();
        double bmi = this.getBMI(height, weight);
        String bmiCategory = this.getBMICategory(bmi);
        try {
            return db.writeToBmiTable(timestamp, bmi, bmiCategory, height, weight);
        }
        catch(Exception e) {
            System.out.println(e);
        }
        return -1;
    }

    public SqlDatabase getDatabase() {
        return this.db;
    }

    public String getHeight() {
        return this.height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public int getWeight() {
        return this.weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public double getBmi() {
        return this.bmi;
    }

    public void setBmi(double bmi) {
        this.bmi = bmi;
    }

}

