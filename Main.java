import java.util.Objects;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws CatchingExceptions {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите Ваше выражение по типу x + y для вычислений: ");
        String input = scanner.nextLine();

        System.out.println(calc(input));
    }

//    Метод принимает строку с арифметическим выражением между двумя числами и возвращает строку с результатом их выполнения.
    static String calc(String input) throws CatchingExceptions {
        String result = new String(" ");
        int resultArab = 0;
        String resultRoman = "";
        String[] signArray = {"+", "-", "*", "/"};
        String[] romanNumArray = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};

        RomanNumbers roman = new RomanNumbers();

        checkInputLength(input);

        String [] userArray = input.split(" ");

        checkForSeveralSignes(signArray, userArray);
        Boolean boolForArbNum = checkForArabicNumber(userArray[0].codePointAt(0), userArray[2].codePointAt(0));
        Boolean checkSign = checkInArray(userArray[1], signArray);

        if (!checkSign){
            throw new CatchingExceptions("Использован недопустимый оператор");
        }

        // расчет результата римских цифр
        if (!boolForArbNum){
            checkDifCountSystem(userArray, romanNumArray);
            int userNum1 = roman.romanToArab(userArray[0]);
            int userNum2 = roman.romanToArab(userArray[2]);
            String sign = userArray[1];
            resultArab = calculation(userNum1, userNum2, sign);
            if (resultArab <= 0){
                throw new CatchingExceptions("В римской системе нет 0 и отрицательных чисел");
            }
            resultRoman = roman.arabToRoman(resultArab);
            result = resultRoman;
        }
        // расчет результата арабских цифр
        if (boolForArbNum){
            checkValueOfArabicNumber(Integer.parseInt(userArray[0]), Integer.parseInt(userArray[2]));
            int userNum1 = Integer.parseInt(userArray[0]);
            String sign = userArray[1];
            int userNum2 = Integer.parseInt(userArray[2]);
            resultArab = calculation(userNum1, userNum2, sign);
            result = Integer.toString(resultArab);
        }
        return result;
    }

    // проверям на достаточность символов в строке, введенной пользователем
    static void checkInputLength(String str) throws CatchingExceptions{
        if (str.isEmpty())  {
            throw new CatchingExceptions("Нельзя вводить пустую строку");
        } else if (str.length() < 4){
            throw new CatchingExceptions("Cтрока не является математической операцией");
        } else if (str.length() < 5) {
            throw new CatchingExceptions("Не достаточно символов в выражении");
        } else if (str.length() > 11) {
            throw new CatchingExceptions("Слишком много символов в выражении");
        }
    }

    // проверяем на несколько операндов
    static void checkForSeveralSignes(String [] arrayOfSigns, String [] arrayInInput) throws CatchingExceptions{
        int signCounter = 0;
        for (String item1 : arrayOfSigns){
            for (String item2 : arrayInInput){
                if (item1.equals(item2)){
                    signCounter += 1;
                }
            }
        }
        if (signCounter >= 2){
            throw new CatchingExceptions("Формат математической операции не удовлетворяет заданию - два операнда и один оператор");
        }
    }

    // проверям 0 и 4 символ в строке на принадлежность к арабским цифрам
    static Boolean checkForArabicNumber(int num1, int num2) {
        if ((num1 < 48 || num1 > 57) || (num2 < 48 || num2 > 57)){
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    // проверяем на присутствие в массиве
    static Boolean checkInArray(String symbol, String[] array) {
        boolean result = false;
        for (String item : array ) {
            if (Objects.equals(symbol, item)) {
                result = Boolean.TRUE;
                break;
            }
        }
        return result;
    }

    // проверяем на разные системы счисления
    static void checkDifCountSystem(String [] str, String [] romAr) throws CatchingExceptions {
        int romeCounter = 0;
        for (String item1 : str) {
            for (String item2 : romAr) {
                if (item1.equals(item2))  {
                    romeCounter += 1;
                }
            }
        }
        if (romeCounter == 1)  {
            throw new CatchingExceptions("Используются одновременно разные системы счисления");
        }
    }

    // метод калькулятора
    static int calculation(int num1, int num2, String sign) throws CatchingExceptions {
        int result = 0;
        Calculation calc = new Calculation();
        switch (sign) {
            case "+" -> result = calc.adding(num1, num2);
            case "-" -> result = calc.subtracting(num1, num2);
            case "*" -> result = calc.multiplication(num1, num2);
            case "/" -> result = calc.division(num1, num2);
            default -> throw new CatchingExceptions("Не правильно указан оператор");
        }
        return result;
    }

    // проверям на значения от 1 до 10
    static void checkValueOfArabicNumber(int num1, int num2) throws CatchingExceptions{
        if ((num1 <= 0 || num2 <= 0) || (num1 > 10 || num2 > 10)) {
            throw new CatchingExceptions("Цифры не могут быть меньше 1 или больше 10");
        }
    }
}
