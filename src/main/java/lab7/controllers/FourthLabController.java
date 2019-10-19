package lab7.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import lab4.CommandLine;
import lab4.consolehelpers.ConsoleWriter;
import lab4.filehelpers.FileWorker;
import lab7.Alertable;

import java.net.URL;
import java.util.ResourceBundle;

public class FourthLabController implements Initializable, Alertable {

    @FXML
    public TextArea showDirectory;

    @FXML
    public TextField commandLineInput;

    @FXML
    public Label currentDirectory;

    private CommandLine commandLine;
    private boolean isFirst;
    private FileWorker fileWorker;
    public static boolean isOpenFile;

    public void onClearButtonClicked(ActionEvent actionEvent) {
        showDirectory.clear();
        commandLineInput.clear();
        isFirst = false;
    }

    public void onCloseButtonClicked(ActionEvent actionEvent) {
        isOpenFile = false;
        commandLineInput.clear();
    }

    public void onSaveButtonClicked(ActionEvent actionEvent) {
        fileWorker.addToFile(commandLineInput.getText());
        isOpenFile = false;
        commandLineInput.clear();
    }

    public void onInputFieldAction(ActionEvent actionEvent) {
        try {
            if (!isOpenFile) {
                if (!isFirst) {
                    showDirectory.setText(showDirectory.getText() + commandLineInput.getText() + "\n");
                    isFirst = true;
                } else {
                    showDirectory.setText(showDirectory.getText() + "\n" + commandLineInput.getText() + "\n");
                }
                commandLine.launchCommandLine(commandLineInput.getText());
            } else {
                throw new Exception("you don't finish last action");
            }
        } catch (Exception e) {
            e.printStackTrace();
            alert(Alert.AlertType.ERROR, "FILE ERROR", "Wrong action", e.getMessage());
        } finally {
            commandLineInput.clear();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        isFirst = false;
        isOpenFile = false;
        ConsoleWriter consoleWriter = new ConsoleWriter(showDirectory);
        fileWorker = new FileWorker(consoleWriter, currentDirectory);
        commandLine = new CommandLine(showDirectory, commandLineInput, fileWorker);
    }

    @Override
    public void alert(Alert.AlertType alertType, String tittle, String header, String context) {
        Alert alert = new Alert(alertType);
        alert.setTitle(tittle);
        alert.setHeaderText(header);
        alert.setContentText(context);
        alert.showAndWait();
    }
}
