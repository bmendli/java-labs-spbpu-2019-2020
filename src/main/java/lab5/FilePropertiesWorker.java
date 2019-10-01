package lab5;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

public class FilePropertiesWorker {

    private Map<String, String> properties;
    private File file;
    private String key;
    private String value;
    private boolean isEndLastLineOfBackSlash;
    private boolean isParse;

    public FilePropertiesWorker(File file) throws FileNotFoundException{
        if (!file.toString().endsWith("\t.properties")) {
            throw new FileNotFoundException("Incorrect file");
        }
        this.file = file;
        this.isParse = false;
        this.isEndLastLineOfBackSlash = false;
    }

    public FilePropertiesWorker(String file) throws FileNotFoundException {
        if (!file.endsWith(".properties")) {
            throw new FileNotFoundException("Incorrect file");
        }
        this.file = new File(file);
        this.isParse = false;
        this.isEndLastLineOfBackSlash = false;
    }

    public void parse() {
        if (isParse) {
            System.out.println("file " + file.toString() + " is parse yet");
        }

        properties = new HashMap<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            br.lines().forEach(this::parseLine);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void parseLine(String line) {
        String tempLine = line.trim();
        if (tempLine.startsWith("#")
            || tempLine.startsWith("!")
            || "".equals(tempLine)
        ) {
            return;
        }

        if (isEndLastLineOfBackSlash) {
            if (tempLine.charAt(tempLine.length() - 1) == '\\') {
                value += tempLine.substring(0, tempLine.length() - 1);
                return;
            }
            else {
                isEndLastLineOfBackSlash = false;
                value += tempLine;
                properties.put(key, value);
                return;
            }
        }

        int i = 0;
        while (tempLine.charAt(i) != '=' && tempLine.charAt(i) != ':') {
            i++;
        }

        parseKey(tempLine.substring(0, i).trim());
        parseValue(tempLine.substring(i + 1, tempLine.length()).trim());
        properties.put(key, value);
    }

    private void parseValue(String parseValue) {
        if (parseValue.charAt(parseValue.length() - 1) == '\\') {
            value = parseValue.substring(0, parseValue.length() - 1);
            isEndLastLineOfBackSlash = true;
            return;
        }
        value = parseValue.trim();
    }

    private void parseKey(String parseKey) {
        StringBuilder stringBuilder = new StringBuilder();
        for (char character : parseKey.toCharArray()) {
            if (character != '\\') {
                stringBuilder.append(character);
            }
        }
        key = stringBuilder.toString();
    }

    public String getValue(String key) {
        if (properties == null) {
            throw new NullPointerException();
        }
        if (!properties.containsKey(key)) {
            return null;
        }
        return properties.get(key);
    }

    public Map<String, String> getProperties() {
        return properties;
    }
}
