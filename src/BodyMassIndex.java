import org.junit.platform.commons.util.StringUtils;

import java.util.InputMismatchException;
import java.util.Scanner;

public class BodyMassIndex {

//    temporary main function to run BodyMassIndex.java
    public static void main(String args[]){
        acceptUserInput();
    }

    public static void acceptUserInput(){
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
        System.out.println("You entered: " + height + "\n");
        boolean validWeight = false;
        int weight = 0;
        while(!validWeight){
            System.out.println("Weight Measurement");
            System.out.println("This is valid input --> 120");
            System.out.print("Enter weight in lbs: ");
            try{
                weight = myScan.nextInt();
            }
            catch(InputMismatchException e) {
                myScan.next();
                continue;
            }
            validWeight = isValidWeight(weight);
        }
        System.out.println("weight is: " + weight);
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

    public static boolean isValidWeight(int weight){
        if(weight <= 0) return false;
        return true;
    }

}

