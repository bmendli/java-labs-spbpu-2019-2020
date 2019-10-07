package lab4.filehelpers;

import lab4.consolehelpers.ConsoleReader;
import lab4.consolehelpers.ConsoleWriter;

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

    private Path path;
    private ConsoleWriter consoleWriter;

    public FileWorker(ConsoleWriter consoleWriter) {
        this.path = Paths.get("");
        this.consoleWriter = consoleWriter;
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
    }

    public void makeDirectory(String name) throws IOException {
        Files.createDirectories(Paths.get(name));
    }

    public void createFile(String name) throws IOException {
        Files.createFile(Paths.get(name));
    }

    public void showContentInFile(File name) {
        if (name.isFile()) {
            try (BufferedReader br = new BufferedReader(new FileReader(name))) {
                String line;
                while ((line = br.readLine()) != null) {
                    consoleWriter.println(line);
                }
            } catch (IOException e) {
                consoleWriter.println(e.getMessage());
            }
        }
    }

    public void openFile(File name, ConsoleReader consoleReader) {
        if (name.isFile()) {
            try (BufferedReader br = new BufferedReader(new FileReader(name))) {
                String line;
                while ((line = br.readLine()) != null) {
                    consoleWriter.println(line);
                }
                FileWriter fileWriter;
                boolean endOpen = false;
                consoleWriter.println("for quit write q:");
                String newLine = consoleReader.nextLine();
                consoleWriter.println("do you want to change file? y/n");
                while (!endOpen) {
                    switch (consoleReader.nextLine()) {
                        case YES: {
                            consoleWriter.println("for quit write q:");
                            consoleWriter.println("rewrite or append? r/a");
                            boolean endWorkingAppRew = false;
                            while (!endWorkingAppRew) {
                                switch (consoleReader.nextLine()) {
                                    case REWRITE: {
                                        fileWriter = new FileWriter(name, false);
                                        fileWriter.write(newLine);
                                        endWorkingAppRew = true;
                                        fileWriter.close();
                                        break;
                                    }
                                    case ADD: {
                                        fileWriter = new FileWriter(name, true);
                                        fileWriter.write(newLine);
                                        endWorkingAppRew = true;
                                        fileWriter.close();
                                        break;
                                    }
                                    case QUIT: {
                                        endWorkingAppRew = true;
                                        break;
                                    }
                                    default : {
                                        consoleWriter.println("incorrect command");
                                    }
                                }
                            }
                            endOpen = true;
                            break;
                        }
                        case NO :
                        case QUIT : {
                            endOpen = true;
                            break;
                        }
                        default : {
                            consoleWriter.println("incorrect command");
                        }
                    }
                }
            } catch (IOException e) {
                consoleWriter.println(e.getMessage());
            }
        }
    }

    public void deleteFile(File file) throws IOException {
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

    @Override
    public void close() throws Exception {

    }
}
