import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Random;

public class Generator {
    private static int MAX = 10000;
    private static int count = 2000;
    public static void main(String[] args) {
        try {
            Random random = new Random();
            PrintWriter pw = new PrintWriter("GeneratedTestcase.txt", "UTF-8");
            pw.println(count);
            for (int i = 0; i < count; i++) {
                int x = random.nextInt(MAX);
                int y = random.nextInt(MAX);
                String line = Integer.toString(x) + " " + Integer.toString(y);
                pw.println(line);
            }
            pw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
