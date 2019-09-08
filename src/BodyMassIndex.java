import org.junit.platform.commons.util.StringUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.InputMismatchException;
import java.util.Scanner;

public class BodyMassIndex {

//    temporary main function to run BodyMassIndex.java
//    public static void main(String args[]){
//        userInput userInput = acceptUserInput();
//        double bmi = getBMI(userInput);
//        String category = getBMICategory(bmi);
//        System.out.println("BMI: " + bmi + " (" + category + ")");
//    }

    public static userInput acceptUserInput(){
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
//        System.out.println("You entered: " + height + "\n");
        boolean validWeight = false;
        String weightStr = "";
        count = 0;
        while(!validWeight){
            if(count == 0){
                System.out.println("Weight Measurement");
                System.out.println("This is valid input --> 120");
                System.out.print("Enter weight in lbs: ");
            }
            else System.out.print("Something went wrong, please enter weight again: ");
            count++;
            weightStr = myScan.nextLine();
            validWeight = isValidWeight(weightStr);
        }
//        System.out.println("weight is: " + weightStr);
        int weight = Integer.parseInt(weightStr);
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

    public static double getHeightInMeters(int ft, int in){
        double totalHeightInches = (double) getHeightInInches(ft, in);
        double htInMeters = totalHeightInches*0.025;
        return round(htInMeters, 3);
    }

    public static int getHeightInInches(int ft, int in){
        return (ft*12 + in);
    }

    public static double getWeightInKilos(int lbs){
        double lbsInDbl = (double) lbs;
        double kilos = lbsInDbl*0.45;
        return round(kilos, 2);

    }

    public static double getBMI(userInput usr){
        double heightInMeters = getHeightInMeters(usr.getHeightFt(), usr.getHeightIn());
        double weightInKilos = getWeightInKilos(usr.getWeight());
        double metersSquared = heightInMeters*heightInMeters;
        double rawBMI = weightInKilos/metersSquared;
        return round(rawBMI, 1);
    }

    public static double round(double value, int places){
//      following is inspired by https://www.baeldung.com/java-round-decimal-number
        BigDecimal bdRounded = new BigDecimal(Double.toString(value));
        bdRounded = bdRounded.setScale(places, RoundingMode.HALF_UP);
        return bdRounded.doubleValue();
    }

    public static String getBMICategory(double bmi){
        if(bmi<18.5) return "Underweight";
        else if(bmi >= 18.5 && bmi <= 24.9) return "Normal weight";
        else if(bmi >= 25 && bmi <= 29.9) return "Overweight";
        else return "Obese";
    }

}

final class userInput {
//    only good values will be here since
//    checking is done before the instance
//    of this object
    private final String height;
    private final int weight;
    int singleQuoteIndex;
    int doubleQuoteIndex;

    public userInput(String height, int weight){
        this.height = height;
        this.weight = weight;
        singleQuoteIndex = height.indexOf('\'');
        doubleQuoteIndex = height.indexOf('\"');
    }

    public String getHeight(){
        return height;
    }

    public int getWeight(){
        return weight;
    }

    public int getHeightIn(){
        String inchStr = height.substring(singleQuoteIndex+1, doubleQuoteIndex);
        return Integer.parseInt(inchStr);
    }

    public int getHeightFt(){
        String inchStr = height.substring(0, singleQuoteIndex);
        return Integer.parseInt(inchStr);
    }
}

