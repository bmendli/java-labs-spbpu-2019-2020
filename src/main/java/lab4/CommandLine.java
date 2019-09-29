package lab4;

import lab4.ConsoleHelpers.ConsoleReader;
import lab4.ConsoleHelpers.ConsoleWriter;
import lab4.FileHelpers.FileWorker;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class CommandLine implements AutoCloseable {

    private static final String COMMAND_HELP = "help";
    private static final String BEGIN_LINE_$ = "$";
    private static final String SPACE = " ";
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

    public CommandLine() {
        this.consoleWriter = new ConsoleWriter();
        this.consoleReader = new ConsoleReader();
        this.fileWorker = new FileWorker(consoleWriter);
    }

    public void launchCommandLine() {
        while (true) {
            consoleWriter.print(BEGIN_LINE_$ + SPACE);
            String[] commands = consoleReader.nextLine().trim().split(SPACE);

            if ((commands.length == 0) || (!isCorrectCommands(commands))) {
                continue;
            }

            switch (commands[0]) {
                case COMMAND_HELP : {
                    consoleWriter.writelnHelp();
                    break;
                }
                case COMMAND_MAN :
                case COMMAND_INFO : {
                    consoleWriter.writelnInfo(commands[1]);
                    break;
                }
                case COMMAND_PWD : {
                    fileWorker.pathToCurrentDir();
                    break;
                }
                case COMMAND_LS : {
                    try {
                        fileWorker.listOfFile();
                    } catch (IOException e) {
                        consoleWriter.println(e.getMessage());
                    }
                    break;
                }
                case COMMAND_CD : {
                    try {
                        fileWorker.goToSpecifiedDirectory(commands[1]);
                    } catch (FileNotFoundException e) {
                        consoleWriter.println(e.getMessage());
                    }
                    break;
                }
                case COMMAND_MKDIR : {
                    try {
                        fileWorker.makeDirectory(commands[1]);
                    } catch (IOException e) {
                        consoleWriter.println(e.getMessage());
                    }
                    break;
                }
                case COMMAND_CAT : {
                    fileWorker.showContentInFile(new File(commands[1]));
                    break;
                }
                case COMMAND_TOUCH : {
                    try {
                        fileWorker.createFile(commands[1]);
                    } catch (IOException e) {
                        consoleWriter.println(e.getMessage());
                    }
                    break;
                }
                case COMMAND_RM : {
                    try {
                        fileWorker.deleteFile(new File(commands[1]));
                    } catch (IOException e) {
                        consoleWriter.println(e.getMessage());
                    }
                    break;
                }
                case COMMAND_OPEN : {
                    fileWorker.openFile(new File(commands[1]), consoleReader);
                    break;
                }
                case COMMAND_Q : {
                    System.exit(0);
                    break;
                }
                default : {
                    consoleWriter.println("incorrect command");
                }
            }
        }
    }

    private boolean isCorrectCommands(String[] commands) {
        switch (commands[0]) {
            case COMMAND_HELP :
            case COMMAND_Q :
            case COMMAND_LS :
            case COMMAND_PWD : {
                if (commands.length != 1) {
                    consoleWriter.println("incorrect command");
                    return false;
                }
                break;
            }
            case COMMAND_MAN :
            case COMMAND_INFO :
            case COMMAND_CD :
            case COMMAND_MKDIR :
            case COMMAND_RM :
            case COMMAND_OPEN :
            case COMMAND_TOUCH :
            case COMMAND_CAT : {
                if (commands.length != 2) {
                    consoleWriter.println("incorrect command");
                    return false;
                }
                break;
            }
            default : {
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
