import main.java.Bill;
import main.java.TipCalculator;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class TipCalculatorTest {

    @Test
    public void testBillObject(){
        Bill bill = new Bill("19.39", "2");
        assertEquals("19.39", bill.getPrice());
        assertEquals("2", bill.getPeople());
    }

    @Test
    public void testInput(){
        String input = "26.17\r3\r";
        //        following code inspired by: https://stackoverflow.com/a/31635737
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        Bill x = TipCalculator.acceptInput();
        assertEquals("26.17", x.getPrice());
        assertEquals("3", x.getPeople());
    }

    @Test
    public void testValidPrice(){
        assertTrue(TipCalculator.isValidPrice("95.11"));
    }

    @Test
    public void testPriceNeedsTwoDecimalPlaces() {
        assertFalse(TipCalculator.isValidPrice("18.6"));
    }

    @Test
    public void testWordsAreInvalidPrice() {
        assertFalse(TipCalculator.isValidPrice("hell o45.75"));
    }

    @Test
    public void testMoreThanTwoDecimalsAreInvalidPrice() {
        assertFalse(TipCalculator.isValidPrice("100.900"));
        assertFalse(TipCalculator.isValidPrice("95.111"));
    }

    @Test
    public void testZerosAreInvalidPrice() {
        assertFalse(TipCalculator.isValidPrice("0.0"));
        assertFalse(TipCalculator.isValidPrice("0.00"));
    }

    @Test
    public void testIsValidNumOfPeople(){
        assertTrue(TipCalculator.isValidNumOfPeople("9"));
    }

    @Test
    public void testNegativePeopleIsInvalid() {
        assertFalse(TipCalculator.isValidNumOfPeople("-2"));
    }

    @Test
    public void testZeroIsInvalidPeople() {
        assertFalse(TipCalculator.isValidNumOfPeople("0"));
        assertFalse(TipCalculator.isValidNumOfPeople("00"));
    }

    @Test
    public void testWordsAreInvalidPeople() {
        assertFalse(TipCalculator.isValidNumOfPeople("hello"));
        assertFalse(TipCalculator.isValidNumOfPeople("five3"));
    }

    @Test
    public void testDecimalsAreInvalidPeople() {
        assertFalse(TipCalculator.isValidNumOfPeople("1.5"));
    }

    @Test
    public void testAddGratuity(){
        BigDecimal total1 = new BigDecimal("100.00");
        BigDecimal ans1 = new BigDecimal("115.00");
        assertEquals(ans1, TipCalculator.addGratuity(total1));
    }

    @Test
    public void testGratuityOnRealisticValues() {
        BigDecimal total2 = new BigDecimal("55.40");
        BigDecimal ans2 = new BigDecimal("63.71");
        assertEquals(ans2, TipCalculator.addGratuity(total2));
    }

    @Test
    public void testTruncateOneDecimalToTwo(){
        BigDecimal total5 = new BigDecimal("18.60");
        BigDecimal people5 = new BigDecimal("3");
        BigDecimal ans5 = new BigDecimal("6.20");
        assertEquals(ans5, TipCalculator.getPiece(total5, people5));
    }

    @Test
    public void testTruncateThreeDecimalsToTwo() {
        BigDecimal total1 = new BigDecimal("25.52");
        BigDecimal people1 = new BigDecimal("5");
        BigDecimal ans1 = new BigDecimal("5.10");
        assertEquals(ans1, TipCalculator.getPiece(total1, people1));
    }

    @Test
    public void testTruncateFourDecimalsToTwo() {
        BigDecimal total2 = new BigDecimal("30.53");
        BigDecimal people2 = new BigDecimal("4");
        BigDecimal ans2 = new BigDecimal("7.63");
        assertEquals(ans2, TipCalculator.getPiece(total2, people2));
    }

    @Test
    public void testTruncateNonTerminatingToTwo() {
        BigDecimal total3 = new BigDecimal("25.52");
        BigDecimal people3 = new BigDecimal("30");
        BigDecimal ans3 = new BigDecimal("0.85");
        assertEquals(ans3, TipCalculator.getPiece(total3, people3));
    }


    @Test
    public void testSplitOnMediumGroup(){
        BigDecimal x1 = new BigDecimal("5.11");
        BigDecimal y1 = new BigDecimal("5.10");
        BigDecimal[] arr1 = {x1, x1, y1, y1, y1};
        BigDecimal total1 = new BigDecimal("25.52");
        BigDecimal people1 = new BigDecimal("5");
        BigDecimal piece1 = TipCalculator.getPiece(total1, people1);
        assertArrayEquals(arr1, TipCalculator.getDistribution(total1, people1, piece1));
    }

    @Test
    public void testSplitOnSmallGroup() {
        BigDecimal x2 = new BigDecimal("7.64");
        BigDecimal y2 = new BigDecimal("7.63");
        BigDecimal[] arr2 = {x2, y2, y2, y2};
        BigDecimal total2 = new BigDecimal("30.53");
        BigDecimal people2 = new BigDecimal("4");
        BigDecimal piece2 = TipCalculator.getPiece(total2, people2);
        assertArrayEquals(arr2, TipCalculator.getDistribution(total2, people2, piece2));
    }

    @Test
    public void testSplitOnLargeGroup() {
        BigDecimal x3 = new BigDecimal("0.86");
        BigDecimal y3 = new BigDecimal("0.85");
        BigDecimal[] arr3 = {x3, x3, y3, y3, y3, y3, y3, y3, y3, y3, y3, y3, y3, y3, y3, y3, y3, y3, y3, y3, y3, y3, y3, y3, y3, y3, y3, y3, y3, y3};
        BigDecimal total3 = new BigDecimal("25.52");
        BigDecimal people3 = new BigDecimal("30");
        BigDecimal piece3 = TipCalculator.getPiece(total3, people3);
        assertArrayEquals(arr3, TipCalculator.getDistribution(total3, people3, piece3));
    }

    @Test
    public void testOddNumberOfPeopleLeadsToUnevenSplit() {
        BigDecimal x4 = new BigDecimal("10.04");
        BigDecimal y4 = new BigDecimal("10.03");
        BigDecimal[] arr4 = {x4, y4, y4};
        BigDecimal total4 = new BigDecimal("30.10");
        BigDecimal people4 = new BigDecimal("3");
        BigDecimal piece4 = TipCalculator.getPiece(total4, people4);
        assertArrayEquals(arr4, TipCalculator.getDistribution(total4, people4, piece4));
    }

    @Test
    public void testEvenSplit() {
        BigDecimal x5 = new BigDecimal("6.20");
        BigDecimal[] arr5 = {x5, x5, x5};
        BigDecimal total5 = new BigDecimal("18.60");
        BigDecimal people5 = new BigDecimal("3");
        BigDecimal piece5 = TipCalculator.getPiece(total5, people5);
        assertArrayEquals(arr5, TipCalculator.getDistribution(total5, people5, piece5));
    }

    @Test
    public void testEvenNumberOfPeopleLeadsToUnevenSplit() {
        BigDecimal x6 = new BigDecimal("1.13");
        BigDecimal y6 = new BigDecimal("1.12");
        BigDecimal[] arr6 = {x6, x6, x6, x6, y6, y6, y6, y6};
        BigDecimal total6 = new BigDecimal("9.00");
        BigDecimal people6 = new BigDecimal("8");
        BigDecimal piece6 = TipCalculator.getPiece(total6, people6);
        assertArrayEquals(arr6, TipCalculator.getDistribution(total6, people6, piece6));
    }

    @Test
    public void testSplitTip(){
        assertFalse(TipCalculator.splitTip("10.00", "10000"));
        assertTrue(TipCalculator.splitTip("10.00", "1000"));
    }


}
