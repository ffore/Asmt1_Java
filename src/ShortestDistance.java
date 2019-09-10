import java.util.Scanner;

public class ShortestDistance {

    private double distance;
    private double[] coordinates;

    public ShortestDistance() {
        this.distance = 0.0;
        this.coordinates = new double[4];
    }

    public ShortestDistance(double[] coordinates) {
        this.distance = 0.0;
        this.coordinates = coordinates;
    }

    public void acceptInput() {
        String[] parameter = {"x1", "y1", "x2", "y2"};

        for(int i = 0; i < 4; i++) {
            double coordinate = this.acceptCoordinate(parameter[i]);
            this.setCoordinate(i, coordinate);
        }

        this.printResults();
    }

    public double acceptCoordinate(String currentCoordinate) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Please enter a value for " + currentCoordinate + ": ");
        String input = scanner.nextLine();

        while(isInvalidInput(input)) {
            System.out.println("");
            System.out.print("Enter a new value for " + currentCoordinate + ": ");
            input = scanner.nextLine();
        }
        System.out.println("");

        return Double.parseDouble(input);
    }

    public double printResults() {
        this.calculateDistance();
        double result = this.getDistance();
        System.out.println("The resulting distance is: " + result);
        return result;
    }

    public void calculateDistance() {
        double xDifference, yDifference, xSquared, ySquared, sum, result;
        double[] values = this.getCoordinates();

        xDifference = subtract(values[2], values[0]);
        yDifference = subtract(values[3], values[1]);

        xSquared = square(xDifference);
        ySquared = square(yDifference);

        sum = add(xSquared, ySquared);

        result = round(Math.sqrt(sum));
        this.setDistance(result);
    }

    public static double subtract(double a, double b) {
        return round(a - b);
    }

    public static double add(double a, double b) {
        return round(a + b);
    }

    public static double square(double a) {
        return round(a * a);
    }

    public static double round(double a) {
        return Math.round(a * 1000.0) / 1000.0;
    }

    public double getDistance() {
        return this.distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public double[] getCoordinates() {
        return this.coordinates;
    }

    public void setCoordinate(int position, double coordinate) {
        this.coordinates[position] = coordinate;
    }

    public static boolean isInvalidInput(String input) {
        return !InputValidation.isValidDouble(input);
    }
}
