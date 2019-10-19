package lab4.consolehelpers;

import javafx.scene.control.TextField;

public class ConsoleReader implements AutoCloseable {

    private TextField in;

    public ConsoleReader(TextField in) {
        this.in = in;
    }

    public String next() {
        String result =  in.getText();
        in.clear();
        return result;
    }

    @Override
    public void close() throws Exception {

    }
}
