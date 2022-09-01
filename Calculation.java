// Клас для арифметических действий
public class Calculation {
    int adding(int num1, int num2) {
        return num1 + num2;
    }
    int subtracting(int num1, int num2) {
        return num1 - num2;
    }
    int multiplication(int num1, int num2) {
        return num1 * num2;
    }
    int division(int num1, int num2) {
        if (num2 == 0) {
            try {
                throw new ArithmeticException();
            } catch (ArithmeticException e) {
                System.out.println("На 0 делить нельзя!");
            }
        }
        return num1 / num2;
    }
}
