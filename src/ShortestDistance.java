import java.util.Scanner;

public class ShortestDistance {

    public static void main(String[] args) {
        ShortestDistance shortestDistance = new ShortestDistance();
        double[] coordinate = new double[4];
        String[] parameter = {"x1", "y1", "x2", "y2"};

        for(int i = 0; i < 4; i++) {
            coordinate[i] = shortestDistance.acceptCoordinate(parameter[i]);
        }

    }

    public static boolean isValidNumber(String input) {
        try {
            Double check = Double.parseDouble(input);
        } catch (NumberFormatException e) {
            System.out.println("Invalid Input. Expected a valid number");
            return false;
        }

        return true;
    }

    public double acceptCoordinate(String currentCoordinate) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please enter a value for " + currentCoordinate);
        String input = scanner.nextLine();

        while(!isValidNumber(input)) {
            System.out.println("Enter a new value for " + currentCoordinate);
            input = scanner.nextLine();
        }

        return Double.parseDouble(input);
    }
}
