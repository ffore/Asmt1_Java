import java.util.Scanner;

public class ShortestDistance {

    private double distance;
    private double[] coordinate;

    public ShortestDistance() {
        this.distance = 0.0;
        this.coordinate = new double[4];
    }

    public ShortestDistance(double[] coordinate) {
        this.distance = 0.0;
        this.coordinate = coordinate;
    }

    public static void main(String[] args) {
        ShortestDistance shortestDistance = new ShortestDistance();
        String[] parameter = {"x1", "y1", "x2", "y2"};

        for(int i = 0; i < 4; i++) {
            shortestDistance.coordinate[i] = shortestDistance.acceptCoordinate(parameter[i]);
        }

        shortestDistance.calculateDistance();
        System.out.println("The calculated distance is: " + shortestDistance.getDistance());
    }

    public double acceptCoordinate(String currentCoordinate) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please enter a value for " + currentCoordinate);
        String input = scanner.nextLine();

        while(isInvalidInput(input)) {
            System.out.println("Enter a new value for " + currentCoordinate);
            input = scanner.nextLine();
        }

        return Double.parseDouble(input);
    }

    public void calculateDistance() {
        double xDifference, yDifference, xSquared, ySquared, sum;
        double[] values = this.getCoordinates();

        xDifference = subtract(values[2], values[0]);
        yDifference = subtract(values[3], values[1]);

        xSquared = square(xDifference);
        ySquared = square(yDifference);

        sum = add(xSquared, ySquared);

        // Math Library being used for square root ONLY.
        this.setDistance(Math.sqrt(sum));
    }

    public static double subtract(double a, double b) {
        return a - b;
    }

    public static double add(double a, double b) {
        return a + b;
    }

    public static double square(double a) {
        return a * a;
    }

    public double getDistance() {
        return this.distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public double[] getCoordinates() {
        return this.coordinate;
    }

    public void setCoordinates(double[] coordinate) {
        this.coordinate = coordinate;
    }

    public static boolean isInvalidInput(String input) {
        return !InputValidation.isValidDouble(input);
    }
}
