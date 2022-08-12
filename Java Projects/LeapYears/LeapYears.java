package LeapYears;
import java.util.Scanner;
public class LeapYears {
    /*A program that has a year as input and then checks if this year is a leap year.
A year is a leap year when: 
every fourth year is a leap year.
a new century is only a leap year when it is dividable by 400. */   
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Please enter year:");
        int year = input.nextInt();
        if (year >= 99) {
            if (year % 400 == 0)
                System.out.print(year + " is a leap year!");
            else
                System.out.print(year + " is not a leap year!");
        }
        else{
            if (year % 4 == 0)
                System.out.print(year + " is a leap year!");
            else
                System.out.print(year + " is not a leap year!");
        }
        input.close();
 }
}
