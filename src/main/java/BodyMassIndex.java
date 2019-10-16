import main.java.InputValidation;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.InputMismatchException;
import java.util.Scanner;

public class BodyMassIndex {

    public static userInput acceptInput(){
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
        printResult(height, weight);
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

    public static double getBMI(String height, int weight){
        double heightInMeters = getHeightInMeters(getHeightFt(height), getHeightIn(height));
        double weightInKilos = getWeightInKilos(weight);
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

    public static void printResult(String height, int weight){
        double bmi = getBMI(height, weight);
        String category = getBMICategory(bmi);
        System.out.println("\nBMI: " + bmi + " (" + category + ")");
    }

    public static int getHeightIn(String height){
        int singleQuoteIndex = height.indexOf('\'');
        int doubleQuoteIndex = height.indexOf('\"');
        String inchStr = height.substring(singleQuoteIndex+1, doubleQuoteIndex);
        return Integer.parseInt(inchStr);
    }

    public static int getHeightFt(String height){
        int singleQuoteIndex = height.indexOf('\'');
        String inchStr = height.substring(0, singleQuoteIndex);
        return Integer.parseInt(inchStr);
    }

}

