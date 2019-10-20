import java.io.IOException;
import java.util.Scanner;
import main.java.SqlDatabase;

public class MainMenu {

    private BodyMassIndex bodyMassIndex;
    private Retirement retirement;
    private ShortestDistance shortestDistance;
    private SqlDatabase database;
    private TipCalculator tipCalculator;
    private Server server;
    private boolean isStillRunning;

    public MainMenu() {
        this.database = new SqlDatabase();
        this.bodyMassIndex = new BodyMassIndex(this.database);
        this.retirement = new Retirement();
        this.shortestDistance = new ShortestDistance(this.database);
        this.tipCalculator = new TipCalculator();
        this.server = new Server(this.database);
        this.isStillRunning = true;
    }

    public MainMenu(SqlDatabase database) {
        this.bodyMassIndex = new BodyMassIndex();
        this.database = database;
        this.retirement = new Retirement();
        this.shortestDistance = new ShortestDistance(database);
        this.tipCalculator = new TipCalculator();
        this.server = new Server(database);
        this.isStillRunning = true;
    }

    public static void main(String[] args) throws Exception{
        MainMenu menu = new MainMenu();
        menu.openDatabaseConnection();

        menu.spawnServerThread(menu);

        menu.start();
        menu.closeDatabaseConnection();
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
                System.out.println("BODY MASS INDEX");
                System.out.println("------------");
                this.startBodyMassIndex();
                break;
            case 2:
                System.out.println("RETIREMENT");
                System.out.println("------------");
                this.startRetirement();
                break;
            case 3:
                System.out.println("SHORTEST DISTANCE");
                System.out.println("------------");
                this.startShortestDistance();
                break;
            case 4:
                System.out.println("SPLIT THE TIP");
                System.out.println("------------");
                this.startTipSplitter();
                break;
            case 5:
                this.setIsStillRunning(false);
                break;
        }
        System.out.println("");
    }

    public static boolean isInvalidMenuOption(String input) {
        return !main.java.InputValidation.isValidMenuOption(input);
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

    public void openDatabaseConnection() {
        this.database.connectToDatabase();
    }

    public void spawnServerThread(MainMenu menu) {
//        https://stackoverflow.com/a/29060130
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    menu.startServer();
                }
                catch (Exception e){
                    System.out.println(e);
                }
            }
        });
        thread.start();
    }

    public void startServer() throws Exception {
        this.server.startServer();
    }

    public void closeDatabaseConnection() {
        try {
            this.database.closeConnection();
        } catch (Exception e) {
            System.out.println(e);
        }
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
        try{
            this.database.printDistanceTable();
        }
        catch(Exception e){
            System.out.println(e);
        }
        this.shortestDistance.acceptInput();
    }

    public void startRetirement() { this.retirement.acceptInput(); }

    public void startBodyMassIndex() {
        try{
            this.database.printBmiTable();
        }
        catch(Exception e){
            System.out.println(e);
        }
        this.bodyMassIndex.acceptInput();
    }

    public void startTipSplitter() {
        this.tipCalculator.acceptInput();
    }
}
