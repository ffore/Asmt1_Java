public class InputValidation {

    public static boolean isValidMenuOption(String input) {
        int option = Integer.parseInt(input);

        if(option >= 1 && option <= 5) {
            return true;
        } else {
            System.out.println("Invalid Option");
            return false;
        }
    }

    public static boolean isValidAge(String input) {
        int age = Integer.parseInt(input);

        if(age >= 22 && age <=98) {
            return true;
        } else {
            System.out.println("Invalid Age. You must be at least 22 years of age or" +
                    " at most 98 years of age to calculate your retirement.");
            return false;
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

    public static boolean isValidInteger(String input) {
        try {
            int check = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("Invalid Input. Expected a whole number.");
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

    public static boolean isOnlyNumbers(String weight){
        if(!weight.matches("[0-9]+")) return false;
        else return true;
    }

    public static boolean isFirstCharValidDigit(String weight){
        if(weight.charAt(0) != '0') return true;
        else return false;
    }


}
