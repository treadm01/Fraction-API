import java.util.Arrays;
import java.util.Scanner;

/**
 * A small fraction calculator that can do single operations (+,-,/,*,=, !=, <, >) on two
 * fractions or convert a fraction using inverse, negation, simplify or absolute.
 * Input is entered a part at a time through the terminal.
 */
public class FractionCalculator {

    private Scanner scanner;
    private Fraction firstFraction;
    private Fraction secondFraction;
    private String mathsOperator;
    private static final String[] singleOperator = new String[] {"inv", "neg", "abs", "sim"};
    private static final String[] doubleOperator = new String[] {"+", "-", "/", "*"};
    private static final String[] comparitorOperator = new String[] {"=", "<", ">", "!="};

    public static void main(String[] args) {
        FractionCalculator fractionCalculator = new FractionCalculator();
        System.out.println("A fraction calculator.");
        fractionCalculator.run();
    }

    /**
     * Contains main loop of the calculator. Takes fraction, operator, fraction
     * via input one at a time and displays the result of the calculation.
     * If the input is incorrect or the calculation is complete, the user is asked
     * if they want to perform another calculation (any input to continue, y to quit)
     */
    private void run() {
        scanner = new Scanner(System.in);
        String answer = "null";
        do {
            try {
                System.out.println("\nPlease enter first Fraction (x/y or x): ");
                setFirstFraction(new Fraction(scanner.next()));

                System.out.println("Enter operator (+, -, /, *, =, !=, <, >) or (neg, inv, abs, sim): ");
                setMathsOperator(scanner.next());

                if (Arrays.asList(singleOperator).contains(getMathsOperator())) {
                    answer = convertFraction().toString();
                }
                else if (Arrays.asList(doubleOperator).contains(getMathsOperator())) {
                    System.out.println("Enter second fraction (x/y or x): ");
                    setSecondFraction(new Fraction(scanner.next()));
                    answer = doCalculation().toString();
                }
                else if (Arrays.asList(comparitorOperator).contains(getMathsOperator())) {
                    System.out.println("Enter second fraction (x/y or x): ");
                    setSecondFraction(new Fraction(scanner.next()));
                    answer = String.valueOf(compareFraction());
                }
                else {
                    System.out.println("\nIncorrect operator input.");
                }
            }
            catch (ArithmeticException ex) {
                System.out.println("Denominator cannot be zero.");
            }
            catch (NumberFormatException ex) {
                System.out.println("Fraction must be of the form x/y or x.");
            }
            finally {
                System.out.println("\nAnswer is: " + answer);
                System.out.println("\nExit? (Enter y to exit, anything else to continue)");
            }
        } while (!scanner.next().equals("y"));
        scanner.close();
    }

    /**
     * Performs corresponding maths operation on two fractions
     * for +, -, *, / and returns a new Fraction object of the result
     * @return new fraction object result of the calculation
     */
    private Fraction doCalculation() {
        Fraction result = new Fraction(0,1);
        switch (getMathsOperator()) {
            case "+":
                result = getFirstFraction().add(getSecondFraction());
                break;
            case "-":
                result = getFirstFraction().subtract(getSecondFraction());
                break;
            case "/":
                result = getFirstFraction().divide(getSecondFraction());
                break;
            case "*":
                result = getFirstFraction().multiply(getSecondFraction());
                break;
        }
        return result;
    }

    /**
     * performs absolute, negate and inverse on fraction and returns
     * the corresponding result as a new fraction object
     * @return new fraction object of the resulting operation
     */
    private Fraction convertFraction() {
        Fraction result = new Fraction(0,1);
        switch (getMathsOperator()) {
            case "abs":
                result = getFirstFraction().abs();
                break;
            case "neg":
                result = getFirstFraction().negate();
                break;
            case "inv":
                result = getFirstFraction().inverse();
                break;
            case "sim":
                result = getFirstFraction();
                break;
        }
        return result;
    }

    /**
     * compares two fractions and whether the first is
     * less than, equal, more than or not equal to the second
     * and returns boolean of the result
     * @return boolean of two fractions ordering and equality
     */
    private Boolean compareFraction() {
        Boolean result = false;
        switch (getMathsOperator()) {
            case "<":
                result = (getFirstFraction().compareTo(getSecondFraction()) < 0);
                break;
            case ">":
                result = (getFirstFraction().compareTo(getSecondFraction()) > 0);
                break;
            case "=":
                result = (getFirstFraction().compareTo(getSecondFraction()) == 0);
                break;
            case "!=":
                result = (getFirstFraction().compareTo(getSecondFraction()) != 0);
                break;
        }
        return result;
    }

    private Fraction getFirstFraction() {
        return firstFraction;
    }

    private void setFirstFraction(Fraction firstFraction) {
        this.firstFraction = firstFraction;
    }

    private Fraction getSecondFraction() {
        return secondFraction;
    }

    private void setSecondFraction(Fraction secondFraction) {
        this.secondFraction = secondFraction;
    }

    private String getMathsOperator() {
        return mathsOperator;
    }

    private void setMathsOperator(String mathsOperator) {
        this.mathsOperator = mathsOperator;
    }
}
