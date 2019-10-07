package lab4.ConsoleHelpers;

import java.util.Scanner;

public class ConsoleReader implements AutoCloseable{

    private Scanner in;

    public ConsoleReader() {
        in = new Scanner(System.in);
    }

    public String nextLine() {
        return in.nextLine();
    }

    public String next() {
        return in.next();
    }

    @Override
    public void close() throws Exception {
        in.close();
    }
}
