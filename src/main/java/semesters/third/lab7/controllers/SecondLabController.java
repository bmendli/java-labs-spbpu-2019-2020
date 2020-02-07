package semesters.third.lab7.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import semesters.third.lab2.Animal;
import semesters.third.lab2.FoodType;
import semesters.third.lab2.SecondLab;
import semesters.third.lab7.Alertable;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SecondLabController implements Initializable, Alertable {

    @FXML
    public TextField inputFile;

    @FXML
    public TextField outputFile;

    @FXML
    public TableView<Animal> animalsTable;

    @FXML
    public TableColumn<Animal, Integer> IDColumn;

    @FXML
    public TableColumn<Animal, String> nameColumn;

    @FXML
    public TableColumn<Animal, FoodType> foodTypeColumn;

    @FXML
    public TableColumn<Animal, Integer> foodAmountColumn;

    @FXML
    public TextArea tasksTextArea;

    private SecondLab secondLab;
    private boolean isSorted;

    public void onFirstTaskButtonClicked(ActionEvent actionEvent) {
        if (!isSorted) {
            alert(
                Alert.AlertType.WARNING, "DATA WARNING",
                "Doesn't have data", "You don't entered file with animals yet"
            );
            return;
        }
        StringBuilder result = new StringBuilder();
        secondLab.firstTask().forEach(animal -> result.append(animal.getName()).append("\n"));
        tasksTextArea.setText(result.toString());
    }

    public void onSecondTaskButtonClicked(ActionEvent actionEvent) {
        if (!isSorted) {
            alert(
                Alert.AlertType.WARNING, "DATA WARNING",
                "Doesn't have data", "You don't entered file with animals yet"
            );
            return;
        }
        StringBuilder result = new StringBuilder();
        secondLab.secondTask().forEach(animal -> result.append(animal.getID()).append("\n"));
        tasksTextArea.setText(result.toString());
    }

    public void onInputFileTextFiledEnter(ActionEvent actionEvent) {
        try {
            if ("".equals(inputFile.getText())) {
                return;
            }
            secondLab.read(inputFile.getText());
            secondLab.sort();
            isSorted = true;
            animalsTable.getItems().setAll(secondLab.getAnimals());
            inputFile.clear();
        } catch (FileNotFoundException e) {
            alert(Alert.AlertType.ERROR, "FILE ERROR", "Incorrect input file", "No such file");
        }
    }

    public void onOutputFileTextFiledEnter(ActionEvent actionEvent) {
        try {
            if ("".equals(outputFile.getText())) {
                return;
            }
            if (!isSorted) {
                alert(
                    Alert.AlertType.WARNING, "DATA WARNING",
                    "Doesn't have data", "You don't entered file with animals yet"
                );
                return;
            }
            if (!isCorrectOutputFile(outputFile.getText())) {
                throw new IOException();
            }

            secondLab.write(outputFile.getText());
            outputFile.clear();
        } catch (IOException e) {
            alert(Alert.AlertType.ERROR, "FILE ERROR", "Incorrect output file", "No such file");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        secondLab = new SecondLab();
        isSorted = false;
        IDColumn.setCellValueFactory(new PropertyValueFactory<>("ID"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        foodTypeColumn.setCellValueFactory(new PropertyValueFactory<>("foodType"));
        foodAmountColumn.setCellValueFactory(new PropertyValueFactory<>("foodAmount"));
    }

    @Override
    public void alert(Alert.AlertType alertType, String tittle, String header, String context) {
        Alert alert = new Alert(alertType);
        alert.setTitle(tittle);
        alert.setHeaderText(header);
        alert.setContentText(context);
        alert.showAndWait();
    }

    private boolean isCorrectOutputFile(String name) {
        return name.matches("\\w+\\.txt");
    }
}
