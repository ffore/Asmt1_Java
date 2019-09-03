import org.junit.platform.commons.util.StringUtils;

import java.util.InputMismatchException;
import java.util.Scanner;

public class BodyMassIndex {

//    temporary main function to run BodyMassIndex.java
    public static void main(String args[]){
        userInput userInput = acceptUserInput();
        System.out.println("user put in height " + userInput.getHeight() + " and weight " + userInput.getWeight());
    }

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
            else System.out.print("Something went wrong, please enter height again: ");
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

        if( !doQuotesExistInOrder(singleQuoteIndex, doubleQuoteIndex) ||
            !doQuotesOccurOnce(height) ||
            !isOnlyNumbersAndQuotes(height) ||
            !areQuotesAfterNumbers(height, singleQuoteIndex, doubleQuoteIndex) ||
            !areNumbersZeroPreceding(height, singleQuoteIndex, doubleQuoteIndex) ||
            !areMeasurementsValid(height, singleQuoteIndex, doubleQuoteIndex)) return false;
        else return true;
    }

    public static boolean doQuotesExistInOrder(int singleQuoteIndex, int doubleQuoteIndex){
//      ' and " exits AND in correct order
        if(singleQuoteIndex == -1 || doubleQuoteIndex == -1 || doubleQuoteIndex < singleQuoteIndex) return false;
        else return true;
    }

    public static boolean doQuotesOccurOnce(String height){
//      has one ' and one " only
//      following code was inspired by: https://stackoverflow.com/a/8910767
        int singleCount = height.length() - height.replace("\'", "").length();
        int doubleCount = height.length() - height.replace("\"", "").length();
        if(singleCount != 1 || doubleCount != 1) return false;
        else return true;
    }

    public static boolean isOnlyNumbersAndQuotes(String height){
//      only numbers and ' and " are present (no decimals)
        String noQuoteHeight = height.replace("\'", "");
        noQuoteHeight = noQuoteHeight.replace("\"", "");
//      following code was inspired by: https://stackoverflow.com/a/10575676
        if(!noQuoteHeight.matches("[0-9]+")) return false;
        else return true;
    }

    public static boolean areNumbersZeroPreceding(String height, int singleQuoteIndex, int doubleQuoteIndex){
        String ftString = height.substring(0,singleQuoteIndex);
        String inString = height.substring(singleQuoteIndex+1, doubleQuoteIndex);
        if( ftString.charAt(0) == '0' && ftString.length() != 1 ||
            inString.charAt(0) == '0' && inString.length() != 1) return false;
        return true;

    }

    public static boolean areQuotesAfterNumbers(String height, int singleQuoteIndex, int doubleQuoteIndex){
//      ' and " are after numbers
        if( !height.substring(0, 1).matches("[0-9]+") ||
                !height.substring(singleQuoteIndex-1, singleQuoteIndex).matches("[0-9]+") ||
                !height.substring(singleQuoteIndex+1, singleQuoteIndex+2).matches("[0-9]+") ||
                !height.substring(doubleQuoteIndex-1, doubleQuoteIndex).matches("[0-9]+") ||
                height.length() != doubleQuoteIndex + 1
        ) return false;
        else return true;
    }

    public static boolean areMeasurementsValid(String height, int singleQuoteIndex, int doubleQuoteIndex){
//      height measurements are valid numbers
        String heightFtString = height.substring(0, singleQuoteIndex);
        String heightInString = height.substring(singleQuoteIndex+1, doubleQuoteIndex);
        int heightFt;
        int heightIn;
        try {
            heightFt = Integer.parseInt(heightFtString);
            heightIn = Integer.parseInt(heightInString);
        } catch(NumberFormatException e) {
            return false;
        }
        if(heightIn > 11 || (heightFt == 0 && heightIn == 0)) return false;
        else return true;
    }

    public static boolean isValidWeight(String weight){
        if( isOnlyNumbers(weight) &&
            isFirstCharValidDigit(weight) ) return true;
        return false;
    }

    public static boolean isOnlyNumbers(String weight){
        if(!weight.matches("[0-9]+")) return false;
        else return true;
    }

    public static boolean isFirstCharValidDigit(String weight){
        if(weight.charAt(0) != '0') return true;
        else return false;
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

