package semesters.third.lab4.consolehelpers;

import javafx.scene.control.TextArea;
import semesters.third.lab4.CommandLine;

public class ConsoleWriter {

    private static final String INFO_Q = CommandLine.COMMAND_Q
        + " - close command line";
    private static final String INFO_LS = CommandLine.COMMAND_LS
        + " - list of the files and directories in the current directory";
    private static final String INFO_PWD = CommandLine.COMMAND_PWD
        + " - path to the current directory of file";
    private static final String INFO_MAN = CommandLine.COMMAND_MAN
        + " <command_name> - information about command";
    private static final String INFO_INFO =  CommandLine.COMMAND_INFO
        + " <command_name> - information about command";
    private static final String INFO_CD = CommandLine.COMMAND_CD
        + " <directory_name> - change current directory";
    private static final String INFO_MKDIR = CommandLine.COMMAND_MKDIR
        + " <directory_name> - create a new directory";
    private static final String INFO_RM = CommandLine.COMMAND_RM
        + " <file_name> - remove file";
    private static final String INFO_OPEN = CommandLine.COMMAND_OPEN
        + " <file_name> - open file to read and rewrite or append";
    private static final String INFO_TOUCH = CommandLine.COMMAND_TOUCH
        + " <file_name> - create a new file";
    private static final String INFO_CAT = CommandLine.COMMAND_CAT
        + " <file_name> - content output in the file";

    private TextArea out;

    public ConsoleWriter(TextArea out) {
        this.out = out;
    }

    public void print(String s) {
        out.setText(out.getText() + "\n" + s);
    }

    public void println(String s) {
        out.setText(out.getText() + "\n" + s + "\n");
    }

    public void println() {
        out.setText(out.getText() + "\n");
    }

    public void printlnHelp() {
        println(
            INFO_Q + "\n"
            + INFO_LS + "\n"
            + INFO_PWD + "\n"
            + INFO_MAN + "\n"
            + INFO_INFO + "\n"
            + INFO_CD + "\n"
            + INFO_MKDIR + "\n"
            + INFO_RM + "\n"
            + INFO_OPEN + "\n"
            + INFO_TOUCH + "\n"
            + INFO_CAT
        );
    }

    public void printlnInfo(String command) throws Exception {
        switch (command) {
            case CommandLine.COMMAND_Q : {
                println(INFO_Q);
                break;
            }
            case CommandLine.COMMAND_LS : {
                println(INFO_LS);
                break;
            }
            case CommandLine.COMMAND_PWD : {
                println(INFO_PWD);
                break;
            }
            case CommandLine.COMMAND_CD : {
                println(INFO_CD);
                break;
            }
            case CommandLine.COMMAND_MKDIR : {
                println(INFO_MKDIR);
                break;
            }
            case CommandLine.COMMAND_RM : {
                println(INFO_RM);
                break;
            }
            case CommandLine.COMMAND_OPEN : {
                println(INFO_OPEN);
                break;
            }
            case CommandLine.COMMAND_TOUCH : {
                println(INFO_TOUCH);

                break;
            }
            case CommandLine.COMMAND_CAT : {
                println(INFO_CAT);
                break;
            }
            default : {
                throw new Exception("Incorrect command");
            }
        }
    }
}
