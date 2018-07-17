package Main;

import java.util.Scanner;
import java.util.InputMismatchException;

public class JUnitTests {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BasicCalculator basic = new BasicCalculator();
        FieldCalculator field = new FieldCalculator();
        int choice;
        double arg1, arg2;

        try {
            System.out.println("1.Basic \n 2.Field ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Calculator: \n 1.Sum \n 2.Diffirence \n 3.Multiplication \n 4.Division \n 5.Power \n 6.Square");
                    choice = scanner.nextInt();
                    switch (choice) {
                        case 1:
                            System.out.println("input 1 and 2 argument");
                            arg1 = scanner.nextDouble();
                            arg2 = scanner.nextDouble();
                            System.out.println("Sum: " + basic.calculateSum(arg1, arg2));
                            break;
                        case 2:
                            System.out.println("input 1 and 2 argument");
                            arg1 = scanner.nextDouble();
                            arg2 = scanner.nextDouble();
                            System.out.println("Diffirence: " + basic.calculateDiffirence(arg1, arg2));
                            break;
                        case 3:
                            System.out.println("input 1 and 2 argument");
                            arg1 = scanner.nextDouble();
                            arg2 = scanner.nextDouble();
                            System.out.println("Multiplication: " + basic.calculateMultiplication(arg1, arg2));
                            break;
                        case 4:
                            System.out.println("input 1 and 2 argument");
                            arg1 = scanner.nextDouble();
                            arg2 = scanner.nextDouble();
                            System.out.println("Division: " + basic.calculateDivision(arg1, arg2));
                            break;
                        case 5:
                            System.out.println("input 1 and 2 argument");
                            arg1 = scanner.nextDouble();
                            arg2 = scanner.nextDouble();
                            System.out.println("Power: " + basic.calculatePow(arg1, arg2));
                            break;
                        case 6:
                            System.out.println("input 1 argument");
                            arg1 = scanner.nextDouble();
                            System.out.println("Square: " + basic.calculateSqrt(arg1));
                            break;
                        default:
                            System.out.println("You've chosen other than 1-6");
                            System.exit(0);
                    }
                    break;

                case 2:
                    System.out.println("Field: \n 1.Square \n 2.Circle \n 3.Triangle \n 4.Rectangle");
                    choice = scanner.nextInt();
                    switch (choice) {
                        case 1:
                            System.out.println("input 1 argument");
                            arg1 = scanner.nextDouble();
                            System.out.println("Square: " + field.calculateSquare(arg1));
                            break;
                        case 2:
                            System.out.println("input 1 argument");
                            arg1 = scanner.nextDouble();
                            System.out.println("Circle: " + field.calculateCircle(arg1));
                            break;
                        case 3:
                            System.out.println("input 1 and 2 argument");
                            arg1 = scanner.nextDouble();
                            arg2 = scanner.nextDouble();
                            System.out.println("Triangle: " + field.calculateTriangle(arg1, arg2));
                            break;
                        case 4:
                            System.out.println("input 1 and 2 argument");
                            arg1 = scanner.nextDouble();
                            arg2 = scanner.nextDouble();
                            System.out.println("Rectangle: " + field.calculateRectangle(arg1, arg2));
                            break;
                        default:
                            System.out.println("You've chosen other than 1-4");
                            System.exit(0);
                    }
                    break;
                default:
                    System.out.println("you've choosen other than 1 or 2");
                    System.exit(0);
            }
        } catch (InputMismatchException e) {
            System.out.println("only numbers");

        }
    }
}