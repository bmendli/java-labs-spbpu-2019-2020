package lab6;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;

public class OwnLogger {

    private FileWriter fileWriter;

    OwnLogger() {
        try {
            this.fileWriter = new FileWriter(
                new File("D:\\Program Files (x86)\\java-labs-spbpu-2019-2020\\log.txt")
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void log(Level level, String message) {
        try {
            fileWriter.write(level.getName() + " : " + message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void close() throws IOException {
        fileWriter.close();
    }
}
