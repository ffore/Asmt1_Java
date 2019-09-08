import java.util.Scanner;

public class TipCalculator {
    public static void main(String args[]){
        acceptInput();
    }

    public static void acceptInput(){
        Scanner myScan = new Scanner(System.in);
        boolean validPrice = false;
        while(validPrice) {
            System.out.print("Enter total price in USD($): ");
            String price = myScan.next();
            System.out.println("you entered: " + price);
            validPrice = isValidPrice(price);
        }
    }

    public static boolean isValidPrice(String price){
        return true;
    }
}
