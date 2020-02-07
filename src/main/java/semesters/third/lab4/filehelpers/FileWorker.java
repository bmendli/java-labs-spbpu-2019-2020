package semesters.third.lab4.filehelpers;

import javafx.scene.control.Label;
import semesters.third.lab4.consolehelpers.ConsoleWriter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;

public class FileWorker implements AutoCloseable {

    private static final String YES = "y";
    private static final String NO = "n";
    private static final String REWRITE = "r";
    private static final String ADD = "a";
    private static final String QUIT = "q:";

    private static Path path;
    private ConsoleWriter consoleWriter;
    private Label currentDirectory;
    private String content;

    public FileWorker(
        ConsoleWriter consoleWriter,
        Label currentDirectory) {

        path = Paths.get("");
        this.consoleWriter = consoleWriter;
        this.currentDirectory = currentDirectory;
        this.currentDirectory.setText(path.toString());
    }

    public void pathToCurrentDir() {
        consoleWriter.println(path.toString());
    }

    public void listOfFile() throws IOException {
        if (Files.notExists(path)) {
            throw new FileNotFoundException("incorrect path to file");
        }
        if (Files.isDirectory(path)) {
            Files
                .walk(path, 1)
                .skip(1)
                .map(path::relativize)
                .collect(Collectors.toList())
                .forEach(path1 -> consoleWriter.print(path1.toString() + "   "));
        } else {
            consoleWriter.println("it isn't directory");
        }
    }

    public void goToSpecifiedDirectory(String tempPath) throws FileNotFoundException {
        goToSpecifiedDirectory(path.resolve(tempPath));
    }

    public void goToSpecifiedDirectory(Path tempPath) throws FileNotFoundException {
        Path newPath;
        if (tempPath.isAbsolute()) {
            newPath = tempPath;
        } else {
            newPath = tempPath.resolve(tempPath);
        }
        if (Files.notExists(newPath)) {
            throw new FileNotFoundException("file not found");
        }
        if (!Files.isDirectory(newPath)) {
            throw new FileNotFoundException("it isn't directory");
        }
        path = newPath;
        currentDirectory.setText(path.toString());
    }

    public void makeDirectory(String name) throws IOException {
        File file = new File(name);
        if (file.isAbsolute()) {
            Files.createDirectories(Paths.get(name));
        } else {
            Files.createDirectories(Paths.get(path.toString() + File.separator + name));
        }
    }

    public void createFile(String name) throws IOException {
        File file = new File(name);
        if (file.isAbsolute()) {
            Files.createFile(Paths.get(name));
        } else {
            Files.createFile(Paths.get(path.toString() + File.separator + name));
        }
    }

    public String showContentInFile(File name) throws Exception {
        if (!name.isAbsolute()) {
            name = Paths.get(path.toString() + File.separator + name).toFile();
        }
        if (name.isFile()) {
            try (BufferedReader br = new BufferedReader(new FileReader(name))) {
                String line;
                StringBuilder stringBuilder = new StringBuilder();
                while ((line = br.readLine()) != null) {
                    stringBuilder.append(line).append("\n");
                }
                return stringBuilder.toString();
            } catch (IOException e) {
                throw new IOException("Can not show content in file");
            }
        }
        throw new Exception("No such file");
    }

    public String openFile(File name) throws Exception {
        if (!name.isAbsolute()) {
            path = Paths.get(path.toString() + File.separator + name);
        }
        if (path.toFile().isFile()) {
            try (BufferedReader br = new BufferedReader(new FileReader(path.toFile()))) {
                String line;
                StringBuilder stringBuilder = new StringBuilder();
                while ((line = br.readLine()) != null) {
                    stringBuilder.append(line).append("\n");
                }
                content = stringBuilder.toString();
                return content;
            }
        } else {
            throw new Exception("No such file that open");
        }
    }

    public void deleteFile(File file) throws IOException {
        if (!file.isAbsolute()) {
            file = Paths.get(path.toString() + File.separator + file).toFile();
        }
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (files != null) {
                for (File tempFile : files) {
                    deleteFile(tempFile);
                }
            }
        }
        Files.delete(file.toPath());
    }

    public void addToFile(String input) {
        try (FileWriter fileWriter = new FileWriter(path.toFile(),false)){
            fileWriter.write(content + input);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() throws Exception {

    }
}
