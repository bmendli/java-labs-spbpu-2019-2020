package lab7.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import lab6.MainController;
import lab6.OwnLogger;
import lab7.Alertable;

import java.net.URL;
import java.util.ResourceBundle;

public class SixthLabController implements Initializable, Alertable {

    @FXML
    public TextArea logArea;

    private StringBuilder stringBuilder;

    public void onRunButtonClicked(ActionEvent actionEvent) {
        try {
            logArea.clear();
            new MainController(new OwnLogger(stringBuilder)).executeRandomFile();
            logArea.setText(stringBuilder.toString());
        } catch (Exception e) {
            alert(
                Alert.AlertType.ERROR, "EXECUTE ERROR",
                "Something went wrong", "Try again"
            );
            logArea.clear();
        } finally {
            stringBuilder = new StringBuilder();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        stringBuilder = new StringBuilder();
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
