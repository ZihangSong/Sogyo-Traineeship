package FractCalc;


public class FractCalc{
    public static void main(String[] args) {
        Fraction num1 = new Fraction(1,3);
        Fraction num2 = new Fraction(2,5);
        Fraction num3 = new Fraction(15,5);
        Fraction num4 = new Fraction(10,5);
        System.out.println(num1.numerator + " " + num1.denominator);
        System.out.println(num2.numerator + " " + num2.denominator);
        num1.toDecimalNotation();
        num1.toString();
        num1.add(8);
        num1.add(num2);
        num3.substract(1);
        num3.substract(num4);
        num1.multiply(2);
        num1.multiply(num2);
        num2.divide(2);
        num1.divide(num2);
    }
}
