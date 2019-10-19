package lab4;

import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import lab4.consolehelpers.ConsoleReader;
import lab4.consolehelpers.ConsoleWriter;
import lab4.filehelpers.FileWorker;
import lab7.controllers.FourthLabController;

import java.io.File;

public class CommandLine implements AutoCloseable {

    private static final String COMMAND_HELP = "help";
    public static final String COMMAND_MAN = "man";
    public static final String COMMAND_INFO = "info";
    public static final String COMMAND_PWD = "pwd";
    public static final String COMMAND_LS = "ls";
    public static final String COMMAND_Q = "q";
    public static final String COMMAND_CD = "cd";
    public static final String COMMAND_MKDIR = "mkdir";
    public static final String COMMAND_CAT = "cat";
    public static final String COMMAND_TOUCH = "touch";
    public static final String COMMAND_OPEN = "open";
    public static final String COMMAND_RM = "rm";

    private ConsoleWriter consoleWriter;
    private ConsoleReader consoleReader;
    private FileWorker fileWorker;

    public CommandLine(
        TextArea textArea,
        TextField textField,
        FileWorker fileWorker) {

        this.consoleWriter = new ConsoleWriter(textArea);
        this.consoleReader = new ConsoleReader(textField);
        this.fileWorker = fileWorker;
    }

    public void launchCommandLine(String line) throws Exception {
        String[] commands = line.trim().split(" ");

        if ((commands.length == 0) || (!isCorrectCommands(commands))) {
            return;
        }

        switch (commands[0]) {
            case COMMAND_HELP: {
                consoleWriter.printlnHelp();
                break;
            }
            case COMMAND_MAN:
            case COMMAND_INFO: {
                consoleWriter.printlnInfo(commands[1]);
                break;
            }
            case COMMAND_PWD: {
                fileWorker.pathToCurrentDir();
                break;
            }
            case COMMAND_LS: {
                fileWorker.listOfFile();
                break;
            }
            case COMMAND_CD: {
                fileWorker.goToSpecifiedDirectory(commands[1]);
                break;
            }
            case COMMAND_MKDIR: {
                fileWorker.makeDirectory(commands[1]);
                break;
            }
            case COMMAND_CAT: {
                consoleWriter.println(fileWorker.showContentInFile(new File(commands[1])));
                break;
            }
            case COMMAND_TOUCH: {
                fileWorker.createFile(commands[1]);
                break;
            }
            case COMMAND_RM: {
                fileWorker.deleteFile(new File(commands[1]));
                break;
            }
            case COMMAND_OPEN: {
                consoleWriter.println(fileWorker.openFile(new File(commands[1])) + "\n\n");
                FourthLabController.isOpenFile = true;
                break;
            }
            case COMMAND_Q: {
                System.exit(0);
                break;
            }
            default: {
                consoleWriter.println("incorrect command");
            }
        }
    }

    private boolean isCorrectCommands(String[] commands) {
        switch (commands[0]) {
            case COMMAND_HELP:
            case COMMAND_Q:
            case COMMAND_LS:
            case COMMAND_PWD: {
                if (commands.length != 1) {
                    consoleWriter.println("incorrect command");
                    return false;
                }
                break;
            }
            case COMMAND_MAN:
            case COMMAND_INFO:
            case COMMAND_CD:
            case COMMAND_MKDIR:
            case COMMAND_RM:
            case COMMAND_OPEN:
            case COMMAND_TOUCH:
            case COMMAND_CAT: {
                if (commands.length != 2) {
                    consoleWriter.println("incorrect command");
                    return false;
                }
                break;
            }
            default: {
                consoleWriter.println("Incorrect commands");
                return false;
            }
        }
        return true;
    }

    @Override
    public void close() throws Exception {
        consoleReader.close();

    }
}
