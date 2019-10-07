package lab5;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FilePropertiesWorkerTest {

    @Test
    void testWorkingFileParser() {
        assertThrows(
            FileNotFoundException.class,
            () -> new FilePropertiesWorker("dfgdrgzrg")
        );

        Path file = Paths.get("D:\\Program Files (x86)\\java-labs-spbpu-2019-2020\\pro.properties");
        try (FileWriter fw = new FileWriter(file.toFile())){
            fw.write(
                "# You are reading the \".properties\" entry.\n" +
                "! The exclamation mark can also mark text as comments.\n" +
                "# The key characters =, and : should be written with\n" +
                "# a preceding backslash to ensure that they are properly loaded.\n" +
                "# However, there is no need to precede the value characters =, and : by a backslash.\n" +
                "website = https://en.wikipedia.org/\n" +
                "language = English\n" +
                "# The backslash below tells the application to continue reading\n" +
                "# the value onto the next line.\n" +
                "message = Welcome to \\\n" +
                "          Wikipedia!\n" +
                "# Add spaces to the key\n" +
                "key\\ with\\ spaces = This is the value that could be looked up with the key \"key with spaces\".\n" +
                "# Unicode\n" +
                "tab : \\u0009\n" +
                "# If you want your property to include a backslash, it should be escaped by another backslash\n" +
                "path=c:\\\\wiki\\\\templates\n" +
                "# However, some editors will handle this automatically"
            );
            fw.close();
            assertDoesNotThrow(() -> new FilePropertiesWorker(file));
            FilePropertiesWorker fpw = new FilePropertiesWorker(file);
            fpw.parse();

            assertEquals(
                fpw.getProperties().size(),
                6
            );
            Map<String, String> temp = new HashMap<>();
            temp.put("path", "c:\\\\wiki\\\\templates");
            temp.put("website", "https://en.wikipedia.org/");
            temp.put("tab", "\\u0009");
            temp.put("key with spaces", "This is the value that could be looked up with the key \"key with spaces\".");
            temp.put("language", "English");
            temp.put("message", "Welcome to Wikipedia!");
            assertEquals(
                temp,
                fpw.getProperties()
            );

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
