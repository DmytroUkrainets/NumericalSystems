import java.io.IOException;
import java.util.Scanner;

/**
 * <p>
 * @author Dmytro Ukarinets
 * @Завдання: Create a program that for a given base of the number system p and a real number R
 * in this system ( -109 < D(R) < +109 ; D(R) is the decimal equivalent of the number R;
 * after the comma - up to three digits) would receive and display the number S - the representation of the number R
 * in the number system with base q. The number of decimal places in the number S is not less than in R.
 * </p>
 *
 * <p>
 * Input data: p,R,q (2 ≤ p,q ≤ 36)
 * </p>
 *
 * <p>
 * Source data: S.
 * </p>
 */
public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Numerical systems.");
        Scanner s = new Scanner(System.in);
        int restart;
        do{
            // Enter the number system for the entered number
            System.out.println("Enter the base of the number system (p) from which we translate the number (R):");
            // given basis of the number system from which we translate
            int p;
            do {
                p = s.nextInt();
                if (p <2 || p >36)
                    System.out.println("Enter 2<=p<=36:");
            }while(p <2 || p >36);

            // Enter a number
            boolean correct;
            // valid input from user
            String r;
            do {
                System.out.println("Enter a number (R) (only capital letters and numbers 0 to 9):");
                r = DataInput.getString();
                correct = check(r, p);
            }while(!correct);

            // We introduce the number system into which we will translate the given number
            System.out.println("Enter the base of the number system (q) into which we translate the number (R):");
            // given basis of calculation into which we convert
            int q;
            do {
                q = s.nextInt();
                if (q <2 || q >36)
                    System.out.println("Enter 2<=q<=36:");
            }while(q <2 || q >36);

            // We count the number
            CalculationSystems number = new CalculationSystems();
            // calculation result
            String s1 = number.getInRadix(p, q, r);

            // We derive the result
            System.out.println("Number " + r + " from " + p + " to " + q + " number system: " + s1);

            // We ask whether to repeat
            System.out.println("Would you like to repeat??\n1-Yes, 0-No:");
            do{
                restart = s.nextInt();
                if (restart!=0 && restart!=1)
                    System.out.println("Please enter 1-Yes, або 0-No:");
            }while(restart!=1 && restart!=0);
        }while(restart == 1);

        System.out.println("The end of the program.");
    }

    private static boolean check(String R, int p) {
        char[] digits = characters();
        int koma = 0;
        for (int i = 0; i < R.length(); i++) {
             char c = R.charAt(i);
             if (c == '-' && i == 0){}
             else if(c == '-'){
                 System.out.println("Error: Lots of minuses.");
                 return false;
             }
             else if((c == ',' || c == '.') && koma == 0) {
                 koma++;
             }
             else if((c == ',' || c == '.') && koma > 0) {
                 System.out.println("Error: Lots of commas. ");
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
                     System.out.println("Error: incorrect sign: " + c);
                     return false;
                 }
                 else {
                     if(n>=p) {
                         System.out.println("Error: digit " + n + " is not from " + p + " number system.");
                         return false;
                     }
                 }
             }

        }
        return true;
    }

    public static char[] characters() {
        int k = 0;
        for(char i='0'; i<='9'; i++){
            digits[k++] = i;
        }
        for(char i='A'; i<='Z'; i++){
            digits[k++] = i;
        }
        return digits;
    }

    private static final char[] digits = new char[36]; // array of numbers
}