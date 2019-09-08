import java.util.Scanner;

public class MainMenu {

    private BodyMassIndex bodyMassIndex;
    private ShortestDistance shortestDistance;
    private boolean isStillRunning;

    public MainMenu() {
        this.bodyMassIndex = new BodyMassIndex();
        this.shortestDistance = new ShortestDistance();
        this.isStillRunning = true;
    }

    public static void main(String[] args) {
        MainMenu menu = new MainMenu();
        int option;

        while(menu.isRunning()) {
            displayMenu();
            option = menu.acceptInput();
            menu.runFunction(option);
        }

        close();
    }

    public static void displayMenu() {
        displayMenuHeader();
        displayOptions();
    }

    public int acceptInput() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        while(isInvalidMenuOption(input)) {
            displayOptions();
            input = scanner.nextLine();
        }

        return Integer.parseInt(input);
    }

    public void runFunction(int option) {
        switch(option) {
            case 1:
                bodyMassIndex = this.getBodyMassIndex();
                bodyMassIndex.acceptUserInput();
                break;
            case 2:
                // Function
                break;
            case 3:
                System.out.println("Hello World");
                shortestDistance = this.getShortestDistance();
                shortestDistance.acceptInput();
                break;
            case 4:
                // Function
                break;
            case 5:
                this.isStillRunning = false;
                break;
        }
    }

    public boolean isInvalidMenuOption(String input) {
        return !InputValidation.isValidMenuOption(input);
    }

    public static void displayMenuHeader() {
        System.out.println("MAIN MENU");
        System.out.println("------------");
    }

    public static void displayOptions() {
        System.out.println("Please choose an option:");
        System.out.println("1 - Body Mass Index Function");
        System.out.println("2 - Retirement Function");
        System.out.println("3 - Shortest Distance Function");
        System.out.println("4 - Split the Tip Function");
        System.out.println("5 - Quit the program");
    }

    public static void close() {
        System.out.println("Thank you for running our program");
        System.out.println("Closing application...");
    }

    public boolean isRunning() {
        return this.isStillRunning;
    }

    public BodyMassIndex getBodyMassIndex() {
        return this.bodyMassIndex;
    }

    public ShortestDistance getShortestDistance() {
        return this.shortestDistance;
    }
}
