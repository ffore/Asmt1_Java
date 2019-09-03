public class InputValidation {

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

}
