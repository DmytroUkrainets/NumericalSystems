import java.util.ArrayList;
import java.util.List;

public class CalculationSystems {
    /** Створюємо об'єкт класу */
    CalculationSystems(int p, int q, String R){
        this.p = p;
        this.q = q;
        this.R = R;

        //System.out.println("Дана стрічна при переведені в число = " + toNumber(R));
    }

    /** Обчислюємо число у заданій сис. числення */
    public String getInRadix(int p, int q, String R) {
        char[] digits = characters();
        if (q<2 || q>36)
            throw new IllegalArgumentException();
        if (p==q)
            return new String(R);
        else if (p==10) {
            String res = new String();
            double beginFloat = Double.parseDouble(R);/*toNumber(R, 10);*/
            if(beginFloat == (int) beginFloat) {
                if (beginFloat >= 0) {
                    do {
                        res = digits[(int) (beginFloat % q)] + res;
                        beginFloat = beginFloat / q;
                    } while (beginFloat >= q);
                    res = digits[(int) beginFloat] + res;
                }
                if (beginFloat < 0) {
                    beginFloat = Math.abs(beginFloat);
                    do {
                        res = digits[(int) (beginFloat % q)] + res;
                        beginFloat = beginFloat / q;
                    } while (beginFloat >= q);
                    res = digits[(int) beginFloat] + res;
                    res = new String("-" + res);
                }
                return res.toString();
            }
            else {
                double n = Math.abs(beginFloat);
                double fraction = n - ((int) n);//початковий дріб
                String drib = new String(",");// стрічка куди записуємо результат
                String str = "" + fraction;// просто для визначення довжини дробу
                int element;// результит покракового обчислення
                for(int i=0; i<str.length()-1; i++) {
                    element = (int) (fraction * q);
                    fraction = (fraction * q) - element;
                    drib += digits[element];
                }
                if (beginFloat >= 0) {
                    do {
                        res = digits[(int) (beginFloat % q)] + res;
                        beginFloat = beginFloat / q;
                    } while (beginFloat >= q);
                    res = digits[(int) beginFloat] + res;
                }
                if (beginFloat < 0) {
                    beginFloat = Math.abs(beginFloat);
                    do {
                        res = digits[(int) (beginFloat % q)] + res;
                        beginFloat = beginFloat / q;
                    } while (beginFloat >= q);
                    res = digits[(int) beginFloat] + res;
                    res = new String("-" + res);
                }
                res += drib;
                return res.toString();
            }
        }
        else {
            double beginFloat = toNumber(R, p);
            String res = "" + beginFloat;
            return getInRadix(10, q, res);
        }
    }

    /** Приймаємо число у вигляді стрічки та перетворюємо його на число у 10-ковій сис. числення */
    private double toNumber(String input, int p) {
        double res = 0;
        int koma = -1;
        boolean minus = false;
        for(int i=0; i<input.length(); i++) {// знаходимо номер коми та перевіряємо на мінус
            char symbol = input.charAt(i);
            if (symbol=='-')
                minus = true;
            if(symbol==',' || symbol=='.') {
                koma = i;
                break;
            }
            koma = -1;
        }
        if(koma == -1) { // ціле число
            if (!minus) {
                int power = input.length() - 1;
                for (int i = 0; i < input.length(); i++) {
                    char symbol = input.charAt(i);
                    int number = getIndex(symbol);
                    res += number * Math.pow(p, power--);
                }
            }
            else {
                int power = input.length() - 2;
                for (int i = 1; i < input.length(); i++) {
                    char symbol = input.charAt(i);
                    int number = getIndex(symbol);
                    res += number * Math.pow(p, power--);
                }
                res *= -1;
            }
        }
        if(koma != -1) { // дробове
            double drib=0;
            for (int i = 1; i < input.length()-koma; i++) {
                char symbol = input.charAt(i+koma);
                int number = getIndex(symbol);
                drib += number * Math.pow(p, -i);
            }
            if (!minus) {
                int power = koma - 1;
                for (int i = 0; i < koma; i++) {
                    char symbol = input.charAt(i);
                    int number = getIndex(symbol);
                    res += number * Math.pow(p, power--);
                }
                res += drib;
            }
            else {
                int power = koma - 2;
                for (int i = 1; i < koma; i++) {
                    char symbol = input.charAt(i);
                    int number = getIndex(symbol);
                    res += number * Math.pow(p, power--);
                }
                res += drib;
                res *= -1;
            }
        }
        return res;
    }

    /** Приймаємо число у вигляді символа та перетворюємо його в число типу float */
    private int getIndex(char input) {
        char[] digits = characters();
        for(int i=0; i<digits.length; i++) {
            if (digits[i] == input) {
                return i;
            }
        }
        return -1;
    }

    /** Створюємо масив чисел */
    private char[] characters() {
        int k=0;
        for(char i='0'; i<='9'; i++){
            digits[k++] = i;
        }
        for(char i='A'; i<='Z'; i++){
            digits[k++] = i;
        }
        return digits;
    }



    private int p; // задана основа системи числення з якої переводимо
    private int q; // задана основа числення в яку переводимо
    private String R; // дійсне вхідне число від користувача
    private char[] digits = new char[36]; // масив чисел
}
