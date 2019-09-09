import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.jupiter.api.Assertions.*;

public class TipCalculatorTest {

    @Test
    public void testOnlyNumbersAndOnePeriod(){
        assertFalse(InputValidation.hasOnlyNumbersAndOnePeriod("$100.00"));
        assertFalse(InputValidation.hasOnlyNumbersAndOnePeriod("$100"));
        assertFalse(InputValidation.hasOnlyNumbersAndOnePeriod("$100.00."));
        assertFalse(InputValidation.hasOnlyNumbersAndOnePeriod("hello"));
        assertTrue(InputValidation.hasOnlyNumbersAndOnePeriod("100.00"));
        assertTrue(InputValidation.hasOnlyNumbersAndOnePeriod("100.900"));
    }

    @Test
    public void testOnlyTwoDecimalPlaces(){
        assertFalse(InputValidation.hasOnlyTwoDecimalPlaces("100.900"));
        assertTrue(InputValidation.hasOnlyTwoDecimalPlaces("45.75"));
        assertTrue(InputValidation.hasOnlyTwoDecimalPlaces("hell o45.75"));
        assertFalse(InputValidation.hasOnlyTwoDecimalPlaces("9.9"));
    }

    @Test
    public void testNotZero(){
        assertTrue(InputValidation.notZero("10.00"));
        assertFalse(InputValidation.notZero("0.00"));
        assertFalse(InputValidation.notZero("000.00"));
        assertFalse(InputValidation.notZero(".00"));
        assertTrue(InputValidation.notZero(".15"));
    }

    @Test
    public void testValidPrice(){
        assertFalse(TipCalculator.isValidPrice("hell o45.75"));
        assertFalse(TipCalculator.isValidPrice("100.900"));
        assertFalse(TipCalculator.isValidPrice("95.111"));
        assertTrue(TipCalculator.isValidPrice("95.11"));
        assertFalse(TipCalculator.isValidPrice("0.0"));
        assertFalse(TipCalculator.isValidPrice("0.00"));
        assertFalse(TipCalculator.isValidPrice("18.6"));
    }

    @Test
    public void testNumOfPeopleIsPositiveInt(){
        assertFalse(InputValidation.isOnlyNumbers("hello"));
        assertFalse(InputValidation.isOnlyNumbers("99.99"));
        assertTrue(InputValidation.isOnlyNumbers("44"));
        assertFalse(InputValidation.isOnlyNumbers("-3"));
        assertTrue(InputValidation.isOnlyNumbers("0"));
    }

    @Test
    public void testNumOfPeopleNotZero(){
        assertFalse(InputValidation.notZeroPeople("0"));
        assertTrue(InputValidation.notZeroPeople("4"));
        assertTrue(InputValidation.notZeroPeople("-2"));
    }

    @Test
    public void testIsValidNumOfPeople(){
        assertFalse(TipCalculator.isValidNumOfPeople("-2"));
        assertFalse(TipCalculator.isValidNumOfPeople("0"));
        assertFalse(TipCalculator.isValidNumOfPeople("00"));
        assertFalse(TipCalculator.isValidNumOfPeople("hello"));
        assertFalse(TipCalculator.isValidNumOfPeople("1.5"));
        assertTrue(TipCalculator.isValidNumOfPeople("9"));
        assertFalse(TipCalculator.isValidNumOfPeople("five3"));
    }

    @Test
    public void testAddGratuity(){
        BigDecimal total1 = new BigDecimal("100.00");
        BigDecimal ans1 = new BigDecimal("115.00");
        assertEquals(ans1, TipCalculator.addGratuity(total1));

        BigDecimal total2 = new BigDecimal("55.40");
        BigDecimal ans2 = new BigDecimal("63.71");
        assertEquals(ans2, TipCalculator.addGratuity(total2));

        BigDecimal total3 = new BigDecimal("37.89");
        BigDecimal ans3 = new BigDecimal("43.57");
        assertEquals(ans3, TipCalculator.addGratuity(total3));

        BigDecimal total4 = new BigDecimal("22.19");
        BigDecimal ans4 = new BigDecimal("25.52");
        assertEquals(ans4, TipCalculator.addGratuity(total4));

        BigDecimal total5 = new BigDecimal("26.55");
        BigDecimal ans5 = new BigDecimal("30.53");
        assertEquals(ans5, TipCalculator.addGratuity(total5));

        BigDecimal total6 = new BigDecimal("26.17");
        BigDecimal ans6 = new BigDecimal("30.10");
        assertEquals(ans6, TipCalculator.addGratuity(total6));

        BigDecimal total7 = new BigDecimal("16.17");
        BigDecimal ans7 = new BigDecimal("18.60");
        assertEquals(ans7, TipCalculator.addGratuity(total7));
    }

    @Test
    public void testGetPiece(){
        BigDecimal total1 = new BigDecimal("25.52");
        BigDecimal people1 = new BigDecimal("5");
        BigDecimal ans1 = new BigDecimal("5.10");
        assertEquals(ans1, TipCalculator.getPiece(total1, people1));

        BigDecimal total2 = new BigDecimal("30.53");
        BigDecimal people2 = new BigDecimal("4");
        BigDecimal ans2 = new BigDecimal("7.63");
        assertEquals(ans2, TipCalculator.getPiece(total2, people2));

        BigDecimal total3 = new BigDecimal("25.52");
        BigDecimal people3 = new BigDecimal("30");
        BigDecimal ans3 = new BigDecimal("0.85");
        assertEquals(ans3, TipCalculator.getPiece(total3, people3));

        BigDecimal total4 = new BigDecimal("30.10");
        BigDecimal people4 = new BigDecimal("3");
        BigDecimal ans4 = new BigDecimal("10.03");
        assertEquals(ans4, TipCalculator.getPiece(total4, people4));

        BigDecimal total5 = new BigDecimal("18.60");
        BigDecimal people5 = new BigDecimal("3");
        BigDecimal ans5 = new BigDecimal("6.20");
        assertEquals(ans5, TipCalculator.getPiece(total5, people5));
    }

    @Test
    public void testGetDistribution(){
//        double[] arr1 = {7.64, 7.63, 7.63, 7.63};
//        assertArrayEquals( arr1, TipCalculator.getDistribution(30.53, 4));
        BigDecimal x1 = new BigDecimal("5.11");
        BigDecimal y1 = new BigDecimal("5.10");
        BigDecimal[] arr1 = {x1, x1, y1, y1, y1};
        BigDecimal total1 = new BigDecimal("25.52");
        BigDecimal people1 = new BigDecimal("5");
        assertArrayEquals(arr1, TipCalculator.getDistribution(total1, people1));

        BigDecimal x2 = new BigDecimal("7.64");
        BigDecimal y2 = new BigDecimal("7.63");
        BigDecimal[] arr2 = {x2, y2, y2, y2};
        BigDecimal total2 = new BigDecimal("30.53");
        BigDecimal people2 = new BigDecimal("4");
        assertArrayEquals(arr2, TipCalculator.getDistribution(total2, people2));

        BigDecimal x3 = new BigDecimal("0.86");
        BigDecimal y3 = new BigDecimal("0.85");
        BigDecimal[] arr3 = {x3, x3, y3, y3, y3, y3, y3, y3, y3, y3, y3, y3, y3, y3, y3, y3, y3, y3, y3, y3, y3, y3, y3, y3, y3, y3, y3, y3, y3, y3};
        BigDecimal total3 = new BigDecimal("25.52");
        BigDecimal people3 = new BigDecimal("30");
        assertArrayEquals(arr3, TipCalculator.getDistribution(total3, people3));

        BigDecimal x4 = new BigDecimal("10.04");
        BigDecimal y4 = new BigDecimal("10.03");
        BigDecimal[] arr4 = {x4, y4, y4};
        BigDecimal total4 = new BigDecimal("30.10");
        BigDecimal people4 = new BigDecimal("3");
        assertArrayEquals(arr4, TipCalculator.getDistribution(total4, people4));

        BigDecimal x5 = new BigDecimal("6.20");
        BigDecimal[] arr5 = {x5, x5, x5};
        BigDecimal total5 = new BigDecimal("18.60");
        BigDecimal people5 = new BigDecimal("3");
        assertArrayEquals(arr5, TipCalculator.getDistribution(total5, people5));
    }
//
//    @Test
//    public void testGetSprinkle(){
//        assertEquals(2, TipCalculator.getSprinkle(25.52, 30, 0.85));
//        assertEquals(1, TipCalculator.getSprinkle(30.53, 4, 7.63));
//    }

}
