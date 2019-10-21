package main.java;

import main.java.Bill;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Scanner;

public class TipCalculator {

    public static Bill acceptInput(){
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
                System.out.println("\nNumber of People");
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
        Bill bill = new Bill(price, numOfPeople);
        splitTip(price, numOfPeople);
        return bill;
    }

    public static boolean isValidPrice(String price){
        if( main.java.InputValidation.hasOnlyNumbersAndOnePeriod(price)
            && main.java.InputValidation.hasOnlyTwoDecimalPlaces(price)
            && main.java.InputValidation.notZero(price)
        ) return true;
        else return false;
    }

    public static boolean isValidNumOfPeople(String numOfPeople){
        if( main.java.InputValidation.isOnlyNumbers(numOfPeople)
            && main.java.InputValidation.notZeroPeople(numOfPeople) ) return true;
        else return false;
    }

//    returns boolean for testing
    public static boolean splitTip(String price, String numOfPeople){
        BigDecimal bdPrice = new BigDecimal(price);
        BigDecimal total = addGratuity(bdPrice);
        BigDecimal people = new BigDecimal(numOfPeople);
        BigDecimal piece = getPiece(total, people);
        BigDecimal zero = new BigDecimal("0.00");
//        System.out.println("piece: " + piece);
        if(piece.equals(zero)) {
            System.out.println("Can\'t divide by zero, too many people to split bill with");
            return false;
        }
        BigDecimal[] distribution = getDistribution(total, people, piece);
        printResult(distribution, total);
        return true;
    }

    public static BigDecimal addGratuity(BigDecimal price){
        BigDecimal rate = new BigDecimal("0.15");
        BigDecimal tip = price.multiply(rate);
        BigDecimal total = price.add(tip);
        //      following is inspired by https://www.baeldung.com/java-round-decimal-number
        total = total.setScale(2, RoundingMode.HALF_UP);
        return total;
    }

    public static BigDecimal[] getDistribution(BigDecimal total, BigDecimal people, BigDecimal piece){
        BigDecimal hun = new BigDecimal("100");
//      checked if piece == 0 before getDistribution() is called
        int mod = total.remainder(piece).multiply(hun).intValue();

        BigDecimal[] dist = new BigDecimal[people.intValue()];
        Arrays.fill(dist, piece);
//        is the piece longer than 2 decimals
        if(mod != 0){
            for(int i = 0; i < mod; i++){
                BigDecimal old = dist[i];
                BigDecimal cent = new BigDecimal("0.01");
                dist[i] = old.add(cent);
            }
        }
        return dist;
    }

    public static BigDecimal getPiece(BigDecimal total, BigDecimal people){
//       would throw Arithmetic Exception if didn't have truncation in division
//       non-terminating decimal expansion prevention inpsired by https://stackoverflow.com/a/10603722
        BigDecimal piece = total.divide(people, RoundingMode.FLOOR);
        piece = piece.setScale(2, RoundingMode.FLOOR);  // switched to floor from half-up
        return piece;
    }

    public static void printResult(BigDecimal[] dist, BigDecimal total){
        System.out.println("\nWith gratuity, total is: $" + total);
//        assumes dist is good
        for(int i = 0; i < dist.length; i++){
            int count = i+1;
            System.out.println("guest" + count + ": $" + dist[i]);
        }
    }
}

