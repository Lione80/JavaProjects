import java.util.HashMap;
import java.util.Map;

// Клас для римских цифр
public class RomanNumbers {
    //Метод принимает строку с римской цифрой, возврщает арбскую цифру
    int romanToArab(String s){
        Map<Character, Integer> map = new HashMap();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);

        int result = 0;
        for (int i = 0; i < s.length(); i++){
            if(i > 0 && map.get(s.charAt(i)) > map.get(s.charAt(i - 1))) {
                result += map.get(s.charAt(i)) - 2 * map.get(s.charAt(i -1));
            } else {
                result += map.get(s.charAt(i));
            }
        }
        return result;
    }

    //Метод принимает арбскую цифру, возврщает строку с римской цифрой
    String arabToRoman(int num) {
        String [] hundreds = {"", "C"};
        String [] tens = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
        String [] units = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
        return hundreds[(num % 1000) / 100] +
                tens[(num % 100) / 10] +
                units[num % 10];
    }
}
