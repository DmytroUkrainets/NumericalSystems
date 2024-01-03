public class CalculationSystems {

    /** We calculate the number in the given system. calculus */
    public String getInRadix(int p, int q, String R) {
        char[] digits = characters();
        if (q<2 || q>36)
            throw new IllegalArgumentException();
        if (p==q)
            return R;
        else if (p==10) {
            StringBuilder res = new StringBuilder();
            double beginFloat = Double.parseDouble(R);/*toNumber(R, 10);*/
            if(beginFloat == (int) beginFloat) {
                res = getStringBuilder(q, digits, res, beginFloat);
            }
            else {
                double n = Math.abs(beginFloat);
                double fraction = n - ((int) n); //initial fraction
                StringBuilder drib = new StringBuilder(","); // tape where we record the result
                String str = "" + fraction;// just to determine the length of the fraction
                int element; // the result of a step-by-step calculation
                for(int i=0; i<str.length()-1; i++) {
                    element = (int) (fraction * q);
                    fraction = (fraction * q) - element;
                    drib.append(digits[element]);
                }
                res = getStringBuilder(q, digits, res, beginFloat);
                res.append(drib);
            }
            return res.toString();
        }
        else {
            double beginFloat = toNumber(R, p);
            String res = "" + beginFloat;
            return getInRadix(10, q, res);
        }
    }

    private StringBuilder getStringBuilder(int q, char[] digits, StringBuilder res, double beginFloat) {
        if (beginFloat >= 0) {
            do {
                res.insert(0, digits[(int) (beginFloat % q)]);
                beginFloat = beginFloat / q;
            } while (beginFloat >= q);
            res.insert(0, digits[(int) beginFloat]);
        }
        if (beginFloat < 0) {
            beginFloat = Math.abs(beginFloat);
            do {
                res.insert(0, digits[(int) (beginFloat % q)]);
                beginFloat = beginFloat / q;
            } while (beginFloat >= q);
            res.insert(0, digits[(int) beginFloat]);
            res = new StringBuilder("-" + res);
        }
        return res;
    }

    /** We take the number in the form of a tape and convert it into a number in the 10-digit system. counting */
    private double toNumber(String input, int p) {
        double res = 0;
        int koma = -1;
        boolean minus = false;
        for(int i=0; i<input.length(); i++) {// find the comma number and check for minus
            char symbol = input.charAt(i);
            if (symbol=='-')
                minus = true;
            if(symbol==',' || symbol=='.') {
                koma = i;
                break;
            }
        }
        if(koma == -1) { // integer
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
        if(koma != -1) { // fractional
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

    /** We accept a number in the form of a symbol and convert it into a number of type float */
    private int getIndex(char input) {
        char[] digits = characters();
        for(int i=0; i<digits.length; i++) {
            if (digits[i] == input) {
                return i;
            }
        }
        return -1;
    }

    /** We create an array of numbers */
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


    private final char[] digits = new char[36]; // array of numbers
}
