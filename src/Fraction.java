import java.util.Objects;

/**
 * A fraction api that allows for creating fractions from two ints, a single
 * int or a string representing either. A fraction object is created in it's simplest
 * form, can be compared to other fractions or ints for ordering and can be displayed as a
 * string. Fraction includes a number of methods: add, subtract, multiply, divide,
 * inverse, negate and absolute.
 * @author Toby Readman (treadm01)
 */

public class Fraction implements Comparable {
    private int numerator;
    private int denominator;

    /**
     * Constructs a normalized Fraction object, taking two int parameters
     * for the numerator and the denominator respectively. denominator
     * cannot be zero. If it is an ArithmeticException is thrown.
     * @param numerator an int for numerator
     * @param denominator and int for denominator (should not be zero)
     * @throws ArithmeticException thrown if the denominator is zero
     */
    public Fraction(int numerator, int denominator) throws ArithmeticException {
        if (denominator == 0) {
            throw new ArithmeticException("Divide by 0");
        }
        normalize(numerator, denominator);
    }

    /**
     * Constructs a Fraction object with wholeNumber as the numerator. The denominator
     * is initialised to 1 as default.
     * @param wholeNumber an int for numerator
     */
    public Fraction(int wholeNumber) {
        this.numerator = wholeNumber;
        this.denominator = 1;
    }

    /**
     * Constructs a Fraction object from the String parameter fraction. The denominator
     * cannot be zero and fraction must follow the form: integer / integer (with or without
     * spaces around the integers) or integer, eg "1/3" or "3" and not "one/three", "three".
     * If fraction consists of a numerator and denominator, the full fraction is returned,
     * else fraction is taken as the numerator and the denominator is set to one.
     * @param fraction string representation of a complete fraction or a numerator
     * @throws ArithmeticException thrown if the denominator is zero
     * @throws NumberFormatException thrown if fraction is not a valid form of integer or integer/integer
     */
    public Fraction(String fraction) throws ArithmeticException, NumberFormatException {
        if (fraction.contains("/")) { // handles complete fraction input
            String[] numeratorDenominator = fraction.split("/");
            this.numerator = Integer.parseInt(numeratorDenominator[0].trim());
            this.denominator = Integer.parseInt(numeratorDenominator[1].trim());

            if (this.denominator == 0) {
                throw new ArithmeticException("Divide by 0");
            }

            normalize(this.numerator, this.denominator);
        }
        else { // handles numerator only input
            this.numerator = Integer.parseInt(fraction.trim());
            this.denominator = 1;
        }
    }

    /**
     * Computes the greatest common divisor of two ints n and d, in this case
     * representing the numerator and denominator. Returns an int of the greatest
     * common divisor.
     * @param n int representing numerator
     * @param d int representing denominator
     * @return an int of the greatest common divisor
     */
    private int greatestCommonDivisor(int n, int d) {
        int largest = Math.abs(n);
        int smallest = Math.abs(d);
        int temp;
        while (largest > 0) {
            temp = Integer.max(largest, smallest);
            smallest = Integer.min(largest, smallest);
            largest = temp;
            largest = largest % smallest;
        }
        return smallest;
    }

    /**
     * Takes two int parameters, numerator and denominator, of the fraction
     * and normalises them so the fraction is in its simplest form. Both values
     * are divided by their greatest common divisor and if the denominator
     * is negative then both values' signs are inverted. This method
     * mutates the Fraction Objects' respective numerator and denominator
     * variables.
     * @param numerator an int received from the constructor call of the numerator value
     * @param denominator an int received from the constructor call of the denominator value
     */
    private void normalize(int numerator, int denominator) {
        int gcd = greatestCommonDivisor(numerator, denominator);
        numerator /= gcd;
        denominator /= gcd;

        // if denominator is negative, invert sign of both numerator and denominator
        if (denominator < 0) {
            numerator *= -1;
            denominator *= -1;
        }

        //assign normalised values to objects variables
        this.numerator = numerator;
        this.denominator = denominator;
    }

    /**
     * Returns the result of adding Fraction f to this Fraction
     * @param f the Fraction being added
     * @return result of the added fractions
     */
    public Fraction add(Fraction f) {
        int numerator = ((this.numerator * f.denominator) + (this.denominator * f.numerator));
        int denominator = (this.denominator * f.denominator);
        return new Fraction(numerator, denominator);
    }

    /**
     * Returns the result of subtracting Fraction f from this Fraction
     * @param f the Fraction being subtracted
     * @return result of subtracting the fractions
     */
    public Fraction subtract(Fraction f) {
        int numerator = ((this.numerator * f.denominator) - (this.denominator * f.numerator));
        int denominator = this.denominator * f.denominator;
        return new Fraction(numerator, denominator);
    }

    /**
     * Returns the result of multiplying this Fraction by Fraction f
     * @param f the Fraction to multiply by
     * @return result of multiplying the fractions
     */
    public Fraction multiply(Fraction f) {
        int numerator = (this.numerator * f.numerator);
        int denominator = (this.denominator * f.denominator);
        return new Fraction(numerator, denominator);
    }

    /**
     * Returns the result of dividing this Fraction by f
     * @param f Fraction object to divide by
     * @return result of dividing the fractions
     */
    public Fraction divide(Fraction f) {
        int numerator = (this.numerator * f.denominator);
        int denominator = (this.denominator * f.numerator);
        return new Fraction(numerator, denominator);
    }

    /**
     * Returns a new Fraction object with the absolute value of the calling object
     * @return new absolute Fraction
     */
    public Fraction abs() {
        Fraction absolute = new Fraction(this.numerator, this.denominator);
        if (absolute.numerator < 0) {
            absolute.numerator = Math.abs(absolute.numerator);
        }
        return absolute;
    }

    /**
     * Returns a new Fraction object with the opposite sign value
     * @return Fraction object with opposite sign value
     */
    public Fraction negate() {
        Fraction negated = new Fraction(this.numerator, this.denominator);
        negated.numerator *= -1;
        return negated;
    }

    /**
     * Returns Fraction with inverted numerator and denominator.
     * @return New inverted Fraction
     * @throws ArithmeticException throws if for example 0/1 is inverted
     */
    public Fraction inverse() {
        return new Fraction(this.denominator, this.numerator);
    }

    /**
     * Checks two Fraction objects to see if they are of equal value
     * based on the equality of their numerator and denominator. Also
     * provides standard checks for if both objects are the same or if
     * o is null. Returns the result as a boolean.
     * @param o the Object to check equality with
     * @return boolean of whether the two objects are equal or not
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;

        if (!(o instanceof Fraction)) {
            return false;
        }
        else {
            Fraction f = (Fraction) o;
            return (f.denominator == this.denominator && f.numerator == this.numerator);
        }
    }

    /**
     * Generate hashcode from the objects numerator and denominator
     * to check for equality of actual object
     * @return hashcode of the Fraction object
     */
    @Override
    public int hashCode() {
        return Objects.hash(numerator, denominator);
    }

    /**
     * Compare this Fraction to o in regards to value size.
     * o must be a Fraction or int, any other object throws a ClassCastException.
     * Returns -1 if this Fraction is less than o, 0 if equal and 1 if greater
     * @param o Fraction or int object to compare to
     * @return int value: -1 if less than, 0 if equal, 1 if greater than
     * @throws ClassCastException thrown if object parameter is not a Fraction or int/Integer
     */
    @Override
    public int compareTo(Object o) {
        double comparisonValue;
        double thisFractionAsDouble = ((double) this.numerator / this.denominator);
        double difference;

        // check that o is either a Fraction or int
        if (!(o instanceof Fraction) && !(o instanceof Integer)) {
            throw new ClassCastException("Must be Fraction or an int. Received " + o.getClass());
        }

        // if a fraction object cast to a new fraction and convert to double, else cast to int
        if (o instanceof Fraction) {
            Fraction f = (Fraction) o;
            comparisonValue = ((double) f.numerator / f.denominator);
        }
        else {
            comparisonValue = (int) o;
        }

        // get difference and normalize it to value between -1, 0, 1
        difference = (thisFractionAsDouble - comparisonValue);
        if (difference != 0) {
            difference = (int) (difference / Math.abs(difference));
        }
        return (int) difference;
    }

    /**
     * Returns a String of this Fraction in the form
     * integer/integer, or if the denominator is 1, as a whole number
     * @return string of the Fraction as a whole number or complete fraction
     */
    @Override
    public String toString() {
        String fractionString;
        if (this.denominator == 1) {
            fractionString = String.valueOf(this.numerator);
        }
        else {
            fractionString = this.numerator + "/" + this.denominator;
        }
        return fractionString;
    }
}
