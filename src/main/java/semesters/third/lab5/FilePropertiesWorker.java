package semesters.third.lab5;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class FilePropertiesWorker {

    private Map<String, String> properties;
    private Path file;
    private String key;
    private String value;
    private boolean isEndLastLineOfBackSlash;
    private boolean isParse;

    public FilePropertiesWorker() {}

    public FilePropertiesWorker(Path file) throws FileNotFoundException {
        if (!Files.exists(file)) {
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
        this.file = Paths.get(file);
        this.isParse = false;
        this.isEndLastLineOfBackSlash = false;
    }

    public void parse() throws FileNotFoundException {
        if (isParse) {
            System.out.println("file " + file.toString() + " is parse yet");
        }

        properties = new HashMap<>();
        BufferedReader br = new BufferedReader(new FileReader(file.toFile()));
        br.lines().forEach(this::parseLine);
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
            } else {
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

    public void setFile(Path path) {
        this.file = path;
    }
}
