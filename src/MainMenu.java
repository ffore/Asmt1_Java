import java.util.Scanner;

public class MainMenu {

    private ShortestDistance shortestDistance;
    private boolean isStillRunning;

    public MainMenu() {
        this.shortestDistance = new ShortestDistance();
        this.isStillRunning = true;
    }

    public static void main(String[] args) {
        MainMenu menu = new MainMenu();
        menu.start();
    }

    public void start() {
        int option;

        while(this.isRunning()) {
            displayMenu();
            option = this.acceptInput();
            this.runFunction(option);
        }

        closeMenu();
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
                // Body Mass Index Function
                break;
            case 2:
                // Retirement Function
                break;
            case 3:
                this.startShortestDistance();
                break;
            case 4:
                // Split the Tip Function
                break;
            case 5:
                this.setIsStillRunning(false);
                break;
        }
    }

    public static boolean isInvalidMenuOption(String input) {
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

    public static void closeMenu() {
        System.out.println("Thank you for running our program");
        System.out.println("Closing application...");
    }

    public boolean isRunning() {
        return this.isStillRunning;
    }

    public void setIsStillRunning(boolean value) {
        this.isStillRunning = value;
    }

    public void startShortestDistance() {
        this.shortestDistance.acceptInput();
    }
}
