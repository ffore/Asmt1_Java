import org.junit.platform.commons.util.StringUtils;

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
        while(!validHeight){
            System.out.println("Height Measurement");
            System.out.println("Use single quote (\') for feet & double quote (\") for inches");
            System.out.println("This is valid input --> 5\'8\" (no spaces!)");
            System.out.print("Enter height: ");
            height = myScan.nextLine();
            validHeight = isValidHeight(height);
        }
        System.out.println("You entered: " + height);
    }

    public static boolean isValidHeight(String height){
//      ' and " exits AND in correct order
        int singleQuoteIndex = height.indexOf('\'');
        int doubleQuoteIndex = height.indexOf('\"');
        if(singleQuoteIndex == -1 || doubleQuoteIndex == -1 || doubleQuoteIndex < singleQuoteIndex){
            return false;
        }

//      has one ' and one " only
//      following code was inspired by: https://stackoverflow.com/a/8910767
        int singleCount = height.length() - height.replace("\'", "").length();
        int doubleCount = height.length() - height.replace("\"", "").length();
        if(singleCount != 1 || doubleCount != 1){
            return false;
        }

//      only numbers and ' and " are present (no decimals)
        String noQuoteHeight = height.replace("\'", "");
        noQuoteHeight = noQuoteHeight.replace("\"", "");
//      following code was inspired by: https://stackoverflow.com/a/10575676
        if(!noQuoteHeight.matches("[0-9]+")) return false;

//      ' and " are after numbers
        if( !height.substring(0, 1).matches("[0-9]+") ||
            !height.substring(singleQuoteIndex-1, singleQuoteIndex).matches("[0-9]+") ||
            !height.substring(singleQuoteIndex+1, singleQuoteIndex+2).matches("[0-9]+") ||
            !height.substring(doubleQuoteIndex-1, doubleQuoteIndex).matches("[0-9]+") ||
            height.length() != doubleQuoteIndex + 1
        ) return false;

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
        return true;
    }

//    public static boolean doQuotesExistInOrder(String height){
//
//    }
}

