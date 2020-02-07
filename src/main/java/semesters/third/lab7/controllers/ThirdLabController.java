package semesters.third.lab7.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import semesters.third.lab3.ExecuteException;
import semesters.third.lab3.builder.OwnStringBuilder;
import semesters.third.lab7.Alertable;

import java.net.URL;
import java.util.ResourceBundle;

public class ThirdLabController implements Initializable, Alertable {

    @FXML
    public TextArea ownStringBuilderText;

    @FXML
    public TextField appendText;

    @FXML
    public TextField startDeleteText;

    @FXML
    public TextField startReplaceText;

    @FXML
    public TextField offsetInsertIndex;

    @FXML
    public TextField endDeleteText;

    @FXML
    public TextField endReplaceIndex;

    @FXML
    public TextField strInsertText;

    @FXML
    public TextField strReplaceText;

    private OwnStringBuilder ownStringBuilder;

    public void onReverseButtonClicked(ActionEvent actionEvent) {
        try {
            ownStringBuilder.reverse();
            ownStringBuilderText.setText(ownStringBuilder.toString());
        } catch (ExecuteException|RuntimeException e) {
            alert(
                Alert.AlertType.ERROR, "EXECUTE ERROR",
                "Can not execute action", "impossible reverse this string"
            );
            clearContent();
        }
    }

    public void onClearButtonClicked(ActionEvent actionEvent) {
        ownStringBuilder.clear();
        ownStringBuilderText.clear();
    }

    public void onUndoButtonClicked(ActionEvent actionEvent) {
        try {
            ownStringBuilder.undo();
            ownStringBuilderText.setText(ownStringBuilder.toString());
        } catch (Exception e) {
            alert(
                Alert.AlertType.ERROR, "EXECUTE ERROR",
                "Can not execute action", "impossible undo action"
            );
            clearContent();
        }
    }

    public void onSubmitAppendButtonClicked(ActionEvent actionEvent) {
        try {
            ownStringBuilder.append(appendText.getText());
            ownStringBuilderText.setText(ownStringBuilder.toString());
            appendText.clear();
        } catch (ExecuteException|RuntimeException e) {
            alert(
                Alert.AlertType.ERROR, "EXECUTE ERROR",
                "Can not execute action", "impossible append this string"
            );
            clearContent();
        }
    }

    public void onSubmitDeleteButtonClicked(ActionEvent actionEvent) {
        try {
            ownStringBuilder.delete(
                Integer.parseInt(startDeleteText.getText()),
                Integer.parseInt(endDeleteText.getText())
            );
            ownStringBuilderText.setText(ownStringBuilder.toString());
            startDeleteText.clear();
            endDeleteText.clear();
        } catch (ExecuteException|RuntimeException e) {
            alert(
                Alert.AlertType.ERROR, "EXECUTE ERROR",
                "Can not execute action", "impossible delete this string"
            );
            clearContent();
        }
    }

    public void onSubmitReplaceButtonClicked(ActionEvent actionEvent) {
        try {
            ownStringBuilder.replace(
                Integer.parseInt(startReplaceText.getText()),
                Integer.parseInt(endReplaceIndex.getText()),
                strReplaceText.getText()
            );
            ownStringBuilderText.setText(ownStringBuilder.toString());
            startReplaceText.clear();
            endReplaceIndex.clear();
            strReplaceText.clear();
        } catch (ExecuteException e) {
            alert(
                Alert.AlertType.ERROR, "EXECUTE ERROR",
                "Can not execute action", "impossible replace this string"
            );
            clearContent();
        }
    }

    public void onSubmitInsertButtonClicked(ActionEvent actionEvent) {
        try {
            ownStringBuilder.insert(
                Integer.parseInt(offsetInsertIndex.getText()),
                strInsertText.getText()
            );
            ownStringBuilderText.setText(ownStringBuilder.toString());
            offsetInsertIndex.clear();
            strInsertText.clear();
        } catch (ExecuteException e) {
            alert(
                Alert.AlertType.ERROR, "EXECUTE ERROR",
                "Can not execute action", "impossible insert this string"
            );
            clearContent();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ownStringBuilder = new OwnStringBuilder();
    }

    @Override
    public void alert(Alert.AlertType alertType, String tittle, String header, String context) {
        Alert alert = new Alert(alertType);
        alert.setTitle(tittle);
        alert.setHeaderText(header);
        alert.setContentText(context);
        alert.showAndWait();
    }

    private void clearContent() {
        appendText.clear();
        startDeleteText.clear();
        startReplaceText.clear();
        offsetInsertIndex.clear();
        endDeleteText.clear();
        endReplaceIndex.clear();
        strInsertText.clear();
        strReplaceText.clear();
    }
}
