import lab5.FilePropertiesWorker;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {
        try {
            FilePropertiesWorker filePropertiesWorker = new FilePropertiesWorker("pro.properties");
            filePropertiesWorker.parse();
            filePropertiesWorker.getProperties().forEach(
                (String key, String value) -> {
                    System.out.println(key + " " + value);
                }
            );
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
