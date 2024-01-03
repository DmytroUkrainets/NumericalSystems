import java.io.IOException;
import java.util.Scanner;

/**
 * @author Dmytro Ukarinets
 * @Завдання: Create a program that for a given base of the number system p and a real number R
 * in this system ( -109 < D(R) < +109 ; D(R) is the decimal equivalent of the number R;
 * after the comma - up to three digits) would receive and display the number S - the representation of the number R
 * in the number system with base q. The number of decimal places in the number S is not less than in R.
 *
 * Input data: p,R,q (2 ≤ p,q ≤ 36)
 *
 * Source data: S.
 *
 *
 *
 */
public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Numerical systems.");
        Scanner s = new Scanner(System.in);
        int restart = 0;
        do{
            // Вводимо систему числення для введеного числа
            System.out.println("Введіть основу системи числення (p) з якої переводимо число (R):");
            do {
                p = s.nextInt();
                if (p<2 || p>36)
                    System.out.println("Введіть 2<=p<=36:");
            }while(p<2 || p>36);

            // Вводимо число
            boolean correct = true;
            do {
                System.out.println("Введіть число (R) (лише великі літери латинського алфавіту та цифри від 0 до 9):");
                R = DataInput.getString();
                correct = check(R, p);
            }while(!correct);

            // Вводимо систему числення в яку будемо переводити задане число
            System.out.println("Введіть основу системи числення (q) в яку переводимо число (R):");
            do {
                q = s.nextInt();
                if (q<2 || q>36)
                    System.out.println("Введіть 2<=q<=36:");
            }while(q<2 || q>36);

            // Рахуємо число
            CalculationSystems number = new CalculationSystems(p, q, R);
            S = number.getInRadix(p, q, R);

            // Виводимо результат
            System.out.println("Число " + R + " з " + p + "-кової в " + q + "-кову систему числення: " + S);

            // Питаємо чи повторити ще раз
            System.out.println("Бажаєте повторити?\n1-Так, 0-Ні:");
            do{
                restart = s.nextInt();
                if (restart!=0 && restart!=1)
                    System.out.println("Будь-ласка, введіть 1-Так, або 0-Ні:");
            }while(restart!=1 && restart!=0);
        }while(restart == 1);

        System.out.println("Кінець роботи програми.");
    }

    private static boolean check(String R, int p) {
        char[] digits = characters();
        int koma = 0;
        for (int i = 0; i < R.length(); i++) {
             char c = R.charAt(i);
             if (c == '-' && i == 0){}
             else if(c == '-' && i != 0){
                 System.out.println("Помилка: забагато мінусів.");
                 return false;
             }
             else if((c == ',' || c == '.') && koma == 0) {
                 koma++;
             }
             else if((c == ',' || c == '.') && koma > 0) {
                 System.out.println("Помилка: забагато ком. ");
                 return false;
             }
             else {
                 int n = -1;
                 for (int j = 0; j < digits.length; j++) {
                     if (c == digits[j]) {
                         n = j;
                         break;
                     }
                 }
                 if(c == ',' || c == '.'){}
                 else if (n == -1) {
                     System.out.println("Помилка: хибний знак: " + c);
                     return false;
                 }
                 else if(n!=-1){
                     if(n>=p) {
                         System.out.println("Помилка: цифра " + n + " не входить до " + p + "-кової системи числення.");
                         return false;
                     }
                 }
             }

        }
        return true;
    }

    private int getIndex(char input) {
        char[] digits = characters();
        for(int i=0; i<digits.length; i++) {
            if (digits[i] == input) {
                return i;
            }
        }
        return -1;
    }
    public static char[] characters() {
        int k=0;
        for(char i='0'; i<='9'; i++){
            digits[k++] = i;
        }
        for(char i='A'; i<='Z'; i++){
            digits[k++] = i;
        }
        return digits;
    }
    private static char[] digits = new char[36]; // масив чисел
    private static int p; // задана основа системи числення з якої переводимо
    private static int q; // задана основа числення в яку переводимо
    private static String S; // результат обчислень
    private static String R; // дійсне вхідне число від користувача
}