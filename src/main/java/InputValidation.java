package main.java;

public class InputValidation {

    public static boolean isValidMenuOption(String input) {
        int option = convertToInteger(input);
        return option != -1 && fallsInOptionRange(option);
    }

    public static boolean isValidAge(String input) {
        int age = convertToInteger(input);
        return age != -1 && fallsInAgeRange(age);
    }

    public static boolean isValidAmount(String input) {
        if(containsExtraSymbols(input)) {
            return false;
        } else {
            double amount = convertToAmount(input);
            return amount != -1;
        }
    }

    public static boolean isValidPercentage(String input) {
        int percentage = convertToInteger(input);
        return percentage != -1 && fallsInPercentageRange(percentage);
    }

    public static boolean containsExtraSymbols(String input) {
        return containsDollarSign(input) || containsCommas(input)
                || containsMultipleDecimals(input);
    }

    public static boolean containsDollarSign(String input) {
        if(input.contains("$")) {
            System.out.println("Do not include \'$\' in your input");
            return true;
        } else {
            return false;
        }
    }

    public static boolean containsCommas(String input) {
        if(input.contains(",")) {
            System.out.println("Do not include \',\' in your input");
            return true;
        } else {
            return false;
        }
    }

    public static boolean containsMultipleDecimals(String input) {
        if(input.indexOf(".") != input.lastIndexOf(".")) {
            System.out.println("Only include one \'.\' in your input");
            return true;
        } else {
            return false;
        }
    }

    public static boolean fallsInOptionRange(int option) {
        if(option >= 1 && option <= 5) {
            return true;
        } else {
            System.out.println("Invalid Option");
            return false;
        }
    }

    public static boolean fallsInAgeRange(int age) {
        if(age >= 22 && age <=98) {
            return true;
        } else {
            System.out.println("Invalid Age. You must be at least 22 years of age or" +
                    " at most 98 years of age to calculate your retirement.");
            return false;
        }
    }

    public static boolean fallsInPercentageRange(int percentage) {
        if(percentage > 0 && percentage <= 100) {
            return true;
        } else if(percentage == 0) {
            System.out.println("A percentage of 0 leads in not reaching your savings goal");
            return true;
        } else {
            System.out.println("Invalid Percentage. The provided value does not fall between 0 and 100");
            return false;
        }
    }

    public static int convertToInteger(String input) {
        if(isValidInteger(input)) {
            return Integer.parseInt(input);
        } else {
            return -1;
        }
    }

    public static boolean isValidInteger(String input) {
        try {
            int check = Integer.parseInt(input);

            if(check < 0) {
                System.out.println("Invalid Input. Expected a positive number");
                return false;
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid Input. Expected a whole number.");
            return false;
        }

        return true;
    }

    public static double convertToAmount(String input) {
        if(isValidDouble(input)) {
            double amount = Double.parseDouble(input);

            if(amount < 0) {
                System.out.println("Invalid Input. Expected a positive number");
                return -1;
            } else {
                return Math.round(amount * 100.0) / 100.0;
            }
        } else {
            return -1;
        }
    }

    public static boolean isValidDouble(String input) {
        try {
            double check = Double.parseDouble(input);
        } catch (NumberFormatException e) {
            System.out.println("Invalid Input. Expected a single valid number.");
            return false;
        }

        return true;
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

    public static boolean areNumbersZeroPreceding(String height, int singleQuoteIndex, int doubleQuoteIndex){
        String ftString = height.substring(0,singleQuoteIndex);
        String inString = height.substring(singleQuoteIndex+1, doubleQuoteIndex);
        if( ftString.charAt(0) == '0' && ftString.length() != 1 ||
                inString.charAt(0) == '0' && inString.length() != 1) return false;
        return true;

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

    public static boolean isOnlyNumbers(String str){
        if(!str.matches("[0-9]+")) return false;
        else return true;
    }

    public static boolean isFirstCharValidDigit(String weight){
        if(weight.charAt(0) != '0') return true;
        else return false;
    }

    public static boolean hasOnlyNumbersAndOnePeriod(String price){
//        does price have a period?
        if(price.indexOf('.') == -1) return false;
//        does price only have 1 period?
        if(price.indexOf('.') != price.lastIndexOf('.')) return false;
        String tmp = price.replace(".", "");
        if(!tmp.matches("[0-9]+")) return false;
        else return true;
    }

    public static boolean hasOnlyTwoDecimalPlaces(String price){
//        price is guaranteed to have only numbers and 1 period
        int periodIndex = price.indexOf('.');
        String cents = price.substring(periodIndex+1, price.length());
        if(cents.length() != 2) return false;
        return true;
    }

    public static boolean notZero(String price){
//        price is guaranteed to have only numbers, 1 period, & 2 decimal places
        double priceInDouble = Double.parseDouble(price);
        if(priceInDouble == 0) return false;
        else return true;
    }

    public static boolean notZeroPeople(String numOfPeople){
//        numOfPeople is guaranteed to only be numbers
        int intPeople = Integer.parseInt(numOfPeople);
        if(intPeople == 0) return false;
        else return true;
    }

}
