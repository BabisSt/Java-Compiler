import java_cup.runtime.*;
import java.io.*;

class Driver {
    public static void main(String[] argv) throws Exception{
        new File("./product").mkdirs();
        PrintStream outputFile = new PrintStream(new File("./product/Main.java"));
        System.setOut(outputFile);

        Parser p = new Parser(new Scanner(new InputStreamReader(System.in)));
        p.parse();
    }
}
