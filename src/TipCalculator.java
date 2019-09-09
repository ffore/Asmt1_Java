import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Scanner;

public class TipCalculator {
    public static void main(String args[]){
        acceptInput();
    }

    public static void acceptInput(){
        Scanner myScan = new Scanner(System.in);
        boolean validPrice = false;
        int count = 0;
        String price = "";
        while(!validPrice) {
            if(count == 0) {
                System.out.println("Total Price");
                System.out.println("Input dollars and cents (no symbols)");
                System.out.println("This is valid input --> 15.99");
                System.out.print("Enter total price in USD($): ");
            }
            else {
                System.out.print("Try again: ");
            }
            price = myScan.nextLine();
            validPrice = isValidPrice(price);
            count++;
        }
        count = 0;
        String numOfPeople = "";
        boolean validNumOfPeople = false;
        while(!validNumOfPeople){
            if(count == 0){
                System.out.println("Number of People");
                System.out.println("Input whole positive numbers only");
                System.out.println("This is valid input --> 9");
                System.out.print("Enter number of people: ");
            }
            else{
                System.out.print("Try again: ");
            }
            numOfPeople = myScan.nextLine();
            validNumOfPeople = isValidNumOfPeople(numOfPeople);
            count++;
        }
        splitTip(price, numOfPeople);
    }

    public static boolean isValidPrice(String price){
        if( InputValidation.hasOnlyNumbersAndOnePeriod(price)
            && InputValidation.hasOnlyTwoDecimalPlaces(price)
            && InputValidation.notZero(price)
        ) return true;
        else return false;
    }

    public static boolean isValidNumOfPeople(String numOfPeople){
        if( InputValidation.isOnlyNumbers(numOfPeople)
            && InputValidation.notZeroPeople(numOfPeople) ) return true;
        else return false;
    }

    public static void splitTip(String price, String numOfPeople){
        BigDecimal bdPrice = new BigDecimal(price);
        BigDecimal total = addGratuity(bdPrice);
        BigDecimal people = new BigDecimal(numOfPeople);
        BigDecimal[] distribution = getDistribution(total, people);
        printResult(distribution, total);
    }

    public static BigDecimal addGratuity(BigDecimal price){
        BigDecimal rate = new BigDecimal("0.15");
        BigDecimal tip = price.multiply(rate);
        BigDecimal total = price.add(tip);
        //      following is inspired by https://www.baeldung.com/java-round-decimal-number
        total = total.setScale(2, RoundingMode.HALF_UP);
        return total;
    }

    public static BigDecimal[] getDistribution(BigDecimal total, BigDecimal people){
        BigDecimal piece = getPiece(total, people);
//        try{
            BigDecimal hun = new BigDecimal("100");
            int mod = total.remainder(piece).multiply(hun).intValue();
//        }
//        catch(ArithmeticException e){
////            temporary
//            System.out.println("Something went wrong");
//            double[] x = {1.0};
//            return x;
//        }
//        double[] x = {1.0};
//        return x;
        BigDecimal[] dist = new BigDecimal[people.intValue()];
        Arrays.fill(dist, piece);
//        is the piece longer than 2 decimals
        if(mod != 0){
            for(int i = 0; i < mod; i++){
                BigDecimal old = dist[i];
//                System.out.println("old: "+old);
                BigDecimal cent = new BigDecimal("0.01");
                dist[i] = old.add(cent);
//                System.out.println("new: "+dist[i]);
            }
        }
        return dist;
    }

    public static BigDecimal getPiece(BigDecimal total, BigDecimal people){
//       non-terminating decimal expansion prevention inpsired by https://stackoverflow.com/a/10603722
        BigDecimal piece = total.divide(people, RoundingMode.FLOOR);
        piece = piece.setScale(2, RoundingMode.HALF_UP);
        return piece;
    }

//    public static int getSprinkle(double total, double people, double piece){
//        double sprinkle = 100*(total - (piece*people));
//        BigDecimal bdRounded = new BigDecimal(Double.toString(sprinkle));
//        bdRounded = bdRounded.setScale(2, RoundingMode.HALF_UP);
//        return bdRounded.intValue();
//    }

    public static void printResult(BigDecimal[] dist, BigDecimal total){
        System.out.println("With gratuity, total is: " + total);
//        assumes dist is good
        for(int i = 0; i < dist.length; i++){
            int count = i+1;
            System.out.println("guest" + count + ": $" + dist[i]);
        }
    }
}
