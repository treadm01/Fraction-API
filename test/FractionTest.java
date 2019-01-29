import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class FractionTest {
    private Fraction testFraction, firstFraction, secondFraction, sumFraction;

    @Before
    public void setUp() {
        testFraction = new Fraction(1, 2);
        firstFraction = new Fraction("1/3");
        secondFraction = new Fraction(1,3);
        sumFraction = new Fraction(2,3 );

    }

    @Test
    public void testAdd() {
        assertEquals(sumFraction, firstFraction.add(secondFraction));

        firstFraction = new Fraction(7);
        secondFraction = new Fraction(1,2);

        firstFraction = new Fraction(1,8);
        secondFraction = new Fraction("7/8");
        sumFraction = new Fraction(1);
        assertEquals(sumFraction, firstFraction.add(secondFraction));

        firstFraction = new Fraction(-2, 8);
        secondFraction = new Fraction(3,4);
        sumFraction = new Fraction("1/2");
        assertEquals(firstFraction.add(secondFraction), sumFraction);

        firstFraction = new Fraction(-2, -8);
        secondFraction = new Fraction(3,-18);
        sumFraction = new Fraction("1/12");
        assertEquals(firstFraction.add(secondFraction), sumFraction);

        firstFraction = new Fraction(999, 99999);
        secondFraction = new Fraction(6, 32);
        sumFraction = new Fraction(35109, 177776);
        assertEquals(sumFraction, firstFraction.add(secondFraction));

        firstFraction = new Fraction(999, 9999);
        secondFraction = new Fraction(1,1);
        sumFraction = new Fraction(1222, 1111);
        assertEquals(sumFraction, firstFraction.add(secondFraction));

        firstFraction = new Fraction(9, 999);
        secondFraction = new Fraction(1, 1);
        sumFraction = new Fraction(112, 111);
        assertEquals(sumFraction, firstFraction.add(secondFraction));

        firstFraction = new Fraction(1, 1000);
        secondFraction = new Fraction(1, 1);
        sumFraction = new Fraction(1001, 1000);
        assertEquals(sumFraction, firstFraction.add(secondFraction));

        firstFraction = new Fraction(994999, 999999);
        secondFraction = new Fraction(77777, 77777);
        sumFraction = new Fraction(1994998, 999999);
        assertEquals(sumFraction, firstFraction.add(secondFraction));
    }

    @Test(expected = ArithmeticException.class)
    public void testAddZeroDenominator() {
        firstFraction = new Fraction("1/1");
        sumFraction = new Fraction(2,3 );
        assertEquals(sumFraction, firstFraction.add(new Fraction(3,0)));

        firstFraction.add(new Fraction(0, 1).inverse());
    }

    @Test
    public void testSubtract() {
        firstFraction = new Fraction(4,4);
        secondFraction = new Fraction("2/4");
        sumFraction = new Fraction(2,4);
        assertEquals(sumFraction, firstFraction.subtract(secondFraction));

        firstFraction = new Fraction(-8);
        secondFraction = new Fraction("-64/-128");
        sumFraction = new Fraction("-17/2");
        assertEquals(sumFraction, firstFraction.subtract(secondFraction));

        firstFraction = new Fraction(999, 99999);
        secondFraction = new Fraction(6, 32);
        sumFraction = new Fraction(-31557, 177776);
        assertEquals(sumFraction, firstFraction.subtract(secondFraction));
    }

    @Test
    public void testMultiply() {
        firstFraction = new Fraction("1/2");
        sumFraction = new Fraction("1/4");
        assertEquals(sumFraction, firstFraction.multiply(firstFraction));

        firstFraction = new Fraction(1, 4);
        secondFraction = new Fraction(1,4);
        sumFraction = new Fraction(1,16);
        assertEquals(sumFraction, firstFraction.multiply(secondFraction));

        firstFraction = new Fraction(1, -4);
        sumFraction = new Fraction("-1/16");
        assertEquals(sumFraction, firstFraction.multiply(secondFraction));

        firstFraction = new Fraction(999, 99999);
        secondFraction = new Fraction(6, 32);
        sumFraction = new Fraction(333, 177776);
        assertEquals(sumFraction, firstFraction.multiply(secondFraction));
    }

    @Test
    public void testDivide() {
        firstFraction = new Fraction(3, 4);
        secondFraction = new Fraction(1, 4);
        sumFraction = new Fraction(3);
        assertEquals(sumFraction, firstFraction.divide(secondFraction));

        firstFraction = new Fraction(1, 4);
        secondFraction = new Fraction(1, 8);
        sumFraction = new Fraction(2);
        assertEquals(sumFraction, firstFraction.divide(secondFraction));

        firstFraction = new Fraction(64, 128);
        secondFraction = new Fraction(6, 32);
        sumFraction = new Fraction(8, 3);
        assertEquals(sumFraction, firstFraction.divide(secondFraction));

        firstFraction = new Fraction(999, 99999);
        secondFraction = new Fraction(6, 32);
        sumFraction = new Fraction(592, 11111);
        assertEquals(sumFraction, firstFraction.divide(secondFraction));

        firstFraction = new Fraction(1, 2);
        sumFraction = new Fraction(1, 1);
        assertEquals(sumFraction, firstFraction.divide(firstFraction));
    }

    @Test
    public void testAbs() {
        firstFraction = new Fraction(-1, 4);
        sumFraction = new Fraction(1, 4);
        assertEquals(sumFraction, firstFraction.abs());
        assertEquals(new Fraction(-1, 4), firstFraction); // first fraction is not changed

        firstFraction = new Fraction(-1, -8);
        sumFraction = new Fraction(1, 8);
        assertEquals(sumFraction, firstFraction.abs());

        firstFraction = new Fraction(1, -8);
        sumFraction = new Fraction(1, 8);
        assertEquals(sumFraction, firstFraction.abs());

        firstFraction = new Fraction(0, 1);
        sumFraction = new Fraction(0, 1);
        assertEquals(sumFraction, firstFraction);
        assertEquals(sumFraction, firstFraction.abs());

        firstFraction = new Fraction("-9/12").abs();
        sumFraction = new Fraction(3, 4);
        assertEquals(sumFraction, firstFraction);
    }

    @Test
    public void testNegate() {
        firstFraction = new Fraction(-1, 8);
        assertEquals(new Fraction(1, 8), firstFraction.negate());
        assertEquals(new Fraction(-1, 8), firstFraction);

        firstFraction = new Fraction(1, 8);
        assertEquals(new Fraction(-1, 8), firstFraction.negate());

        firstFraction = new Fraction(3, -4);
        assertEquals(new Fraction(3, 4), firstFraction.negate());

        firstFraction = new Fraction(0, 5);
        assertEquals(firstFraction, firstFraction.negate());
    }

    @Test
    public void testInverse() {
        firstFraction = new Fraction(2, 4);
        assertEquals(new Fraction(4, 2), firstFraction.inverse());

        firstFraction = new Fraction(-1, 2);
        assertEquals(new Fraction(-2, 1), firstFraction.inverse());

        firstFraction = new Fraction(1, -2);
        assertEquals(new Fraction(-2, 1), firstFraction.inverse());

    }

    @Test(expected = ArithmeticException.class)
    public void testInverseZero() {
        firstFraction = new Fraction(0, 1);
        firstFraction.inverse();

        firstFraction = new Fraction(0, 0);
        firstFraction.inverse();
    }

    @Test
    public void testCompareTo() {
        firstFraction = new Fraction(1, 2);
        secondFraction = new Fraction(3,4);
        assertTrue(secondFraction.compareTo(firstFraction) > 0);

        secondFraction = new Fraction(1, 2);
        assertTrue(firstFraction.compareTo(secondFraction) == 0);

        assertTrue(firstFraction.compareTo(1) < 0);

        firstFraction = new Fraction(1,1 );
        assertEquals(0, firstFraction.compareTo(1));

        assertEquals(1, firstFraction.compareTo(-6));

        assertEquals(1, firstFraction.compareTo(0));

        int z = 2;
        assertEquals(-1, firstFraction.compareTo(z));

        firstFraction = new Fraction(3,4);
        secondFraction = new Fraction(2, 4);
        Fraction thirdFraction = new Fraction(1, 4);

        assertTrue(firstFraction.compareTo(secondFraction) > 0 && secondFraction.compareTo(thirdFraction) > 0);
        assertTrue(firstFraction.compareTo(thirdFraction) > 0);

        firstFraction = new Fraction(2,4);
        secondFraction = new Fraction(2, 4);

        assertEquals(firstFraction.compareTo(secondFraction) == 0, firstFraction.equals(secondFraction));

    }

    @Test(expected = ClassCastException.class)
    public void testCompareToException(){
        firstFraction.compareTo("a");
        firstFraction.compareTo(null);
    }

    @Test
    public void testNormalizeSingleIntZero() {
        Fraction zeroFraction = new Fraction(0);
        assertEquals("0", zeroFraction.toString());
        Fraction zeroOneFraction = new Fraction(0, 1);
        assertEquals(zeroOneFraction, zeroFraction);
    }

    @Test
    public void testWholeNumber() {
        Fraction wholeNumberFraction = new Fraction(-1);
        assertEquals("-1", wholeNumberFraction.toString());

        Fraction minusOneFraction = new Fraction(-1, 1);
        assertEquals(minusOneFraction, wholeNumberFraction);

        wholeNumberFraction = new Fraction(1234567890);
        assertEquals(new Fraction(1234567890, 1), wholeNumberFraction);

        wholeNumberFraction = new Fraction(0000000);
        assertEquals(new Fraction(0, 1), wholeNumberFraction);
    }

    @Test
    public void testFractionConstructorNumeratorDenominator() {
        Fraction fractionND = new Fraction(8, -12);
        assertEquals("-2/3", fractionND.toString());

        // too negatives make a positive
        fractionND = new Fraction(-1, -1);
        assertEquals("1", fractionND.toString());

        fractionND = new Fraction(1, -1);
        assertEquals("-1", fractionND.toString());

        fractionND = new Fraction(24, 12);
        System.out.println(fractionND);
    }

    @Test(expected = ArithmeticException.class)
    public void testFractionConstructorDenominatorZero() {
        Fraction zeroFraction = new Fraction(1,0);
        zeroFraction = new Fraction(-1,0);
        zeroFraction = new Fraction(1, 000);
        zeroFraction = new Fraction(0, -0);
    }

    @Test
    public void testStringConstructorFraction() {
        Fraction compare = new Fraction(3, 1);
        Fraction stringFraction = new Fraction("3");
        assertEquals(true, stringFraction.equals(compare));

        compare = new Fraction(1,2);
        stringFraction = new Fraction("1/2");
        assertEquals(true, stringFraction.equals(compare));

        compare = new Fraction(18, 24);
        stringFraction = new Fraction("18/24");
        assertEquals(true, stringFraction.equals(compare));

        compare = new Fraction(0);
        stringFraction = new Fraction("0/1");
        assertEquals(true, stringFraction.equals(compare));

        compare = new Fraction(0);
        stringFraction = new Fraction("0");
        assertEquals(true, stringFraction.equals(compare));

        compare = new Fraction(1, -1);
        stringFraction = new Fraction("1/-1");
        assertEquals(true, stringFraction.equals(compare));

        compare = new Fraction(1, -1);
        stringFraction = new Fraction(" 1 / -1 ");
        assertEquals(true, stringFraction.equals(compare));

        compare = new Fraction(1, -1);
        stringFraction = new Fraction("1 / -1 ");
        assertEquals(true, stringFraction.equals(compare));

        compare = new Fraction(1, -1);
        stringFraction = new Fraction(" 1 /-1");
        assertEquals(true, stringFraction.equals(compare));

        compare = new Fraction(12, 10);
        stringFraction = new Fraction("          12 /              10                    ");
        assertEquals(true, stringFraction.equals(compare));

        compare = new Fraction(0);
        stringFraction = new Fraction(" 0 ");
        assertEquals(true, stringFraction.equals(compare));

    }

    @Test(expected = NumberFormatException.class)
    public void testIncorrectInput() {
        Fraction stringFraction = new Fraction("what does this do");
        stringFraction = new Fraction("slkdjfs/sdfsods/sd");
        stringFraction = new Fraction("1/2/3");
        stringFraction = new Fraction("1.2/3");
        stringFraction = new Fraction("asd");
        stringFraction = new Fraction("asd/0");
        stringFraction = new Fraction("0x2");
    }

    @Test(expected = ArithmeticException.class)
    public void testZeroDenominatorString() {
        Fraction zeroDenominatorString = new Fraction("5/0");
        zeroDenominatorString = new Fraction("asd/0");
        zeroDenominatorString = new Fraction("1/ 0");
        zeroDenominatorString = new Fraction("1/ 0000");
        zeroDenominatorString = new Fraction("1/ 0.0 ");
    }

    @Test
    public void testFractionEquals() {
        Fraction first = new Fraction(1,2);
        Fraction second = new Fraction(1,2);
        Integer test = 3;
        assertNotEquals(first, test);

        assertEquals(first, second);

        second = new Fraction(1,5);
        assertNotEquals(first, second);

        second = new Fraction(0,1);
        Fraction zero = new Fraction(0);
        assertEquals(second, zero);

        first = new Fraction(3, 4);
        second = new Fraction(18, 24);
        assertEquals(first, second);

        Fraction thirdString = new Fraction("3/4");
        assertEquals(first, thirdString);

        thirdString = new Fraction("-1/1");
        second = new Fraction(1, -1);
        assertEquals(thirdString, second);

        assertEquals(second, second);

        assertNotEquals(second, null);

        first = new Fraction(1, 1000);
        assertEquals(new Fraction(1, 1000), first);
        assertFalse(first.equals(3));

        first = new Fraction(1,1);
        assertFalse(first.equals(1));


        first = new Fraction(10,64);
        assertFalse(first.equals("Hello"));

    }

    @Test
    public void testFractionToString() {
        assertEquals("1/2", testFraction.toString());

        Fraction denominatorOne = new Fraction(2,1);
        assertEquals("2", denominatorOne.toString());

        denominatorOne = new Fraction(-2,1);
        assertEquals("-2", denominatorOne.toString());

        denominatorOne = new Fraction(2,-1);
        assertEquals("-2", denominatorOne.toString());
    }

    @Test
    public void testHashCode() {
        Fraction hashOne = new Fraction(3,1);
        Fraction hashTwo = new Fraction(3,1);
        Fraction hashThree = new Fraction(3);
        assertEquals(hashOne.hashCode(), hashTwo.hashCode());
        assertEquals(hashThree.hashCode(), hashTwo.hashCode());

        Fraction hashFour = new Fraction(1,2);
        assertNotEquals(hashOne.hashCode(), hashFour.hashCode());

        hashOne = new Fraction(1, 1000);
        hashTwo = new Fraction("1/1000");
        assertEquals(hashTwo, hashOne);
    }



// TESTS FOR PRIVATE FUNCTIONS
//    @Test
//    public void testGreatestCommonDivisor() {
//        Integer x = 9;
//        Integer y = 12;
//        Fraction f = new Fraction(x, y);
//        f.greatestCommonDivisor(x, y);
//        assertEquals(3, f.greatestCommonDivisor(x, y));
//
//        x = 0;
//        y = 1;
//        assertEquals(1, f.greatestCommonDivisor(x, y));
//
//        x = 1;
//        y = 1000000;
//        assertEquals(1, f.greatestCommonDivisor(x, y));
//
//
//        x = 0;
//        y = 1000000;
//        assertEquals(1000000, f.greatestCommonDivisor(x, y));
//
//        x = 24;
//        y = 16;
//        assertEquals(8, testFraction.greatestCommonDivisor(x, y));
//
//        x = 56;
//        y = 24;
//        assertEquals(8, testFraction.greatestCommonDivisor(x, y));
//
//        x = 48;
//        y = 30;
//        assertEquals(6, testFraction.greatestCommonDivisor(x, y));
//
//        x = 44;
//        y = 99;
//        assertEquals(11, testFraction.greatestCommonDivisor(x, y));
//
//        // negative numbers
//        x = -18;
//        y = 24;
//        assertEquals(6, testFraction.greatestCommonDivisor(x, y));
//
//        x = 18;
//        y = -24;
//        assertEquals(6, testFraction.greatestCommonDivisor(x, y));
//
//    }

//    @Test
//    public void testNormalizeSimplestForm() {
//        Fraction normalFraction = new Fraction(36, 42);
//        normalFraction.normalize(36, 42);
//        assertEquals("6/7", normalFraction.toString());
//    }

//    @Test
//    public void testNormalizePositiveDenominator() {
//        Fraction normalFraction = new Fraction(36, -42);
//        normalFraction.normalize(36, -42);
//        assertEquals("-6/7", normalFraction.toString());
//
//    }

}
