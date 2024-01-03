import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DataInput {

    public DataInput() {
    }

    private static void writeText(String wr) {
        if (wr == null) {
            System.out.print("Введіть дані: ");
        } else {
            System.out.print(wr);
        }

    }

    public static Long getLong() throws IOException {
        String s = getString();
        Long value = Long.valueOf(s);
        return value;
    }

    public static char getChar() throws IOException {
        String s = getString();
        return s.charAt(0);
    }

    public static Integer getInt(String wr) {
        String s = "";

        int counter;
        do {
            writeText(wr);
            counter=0;

            try {
                s = getString();
            } catch (IOException var3) {
                var3.printStackTrace();
            }

            for(int i = 0; i < s.length(); ++i) {
                if (s.charAt(i)!='0' && s.charAt(i)!='9' && s.charAt(i)!='-' && s.charAt(i)!='1' && s.charAt(i)!='2'
                && s.charAt(i)!='3' && s.charAt(i)!='4' && s.charAt(i)!='5' && s.charAt(i)!='6' && s.charAt(i)!='7'
                && s.charAt(i)!='8') {
                    ++counter;
                }
            }
        }while(counter !=0);


        Integer value = Integer.valueOf(s);
        return value;
    }

    public static String getString() throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        String s = br.readLine();
        return s;
    }
}
