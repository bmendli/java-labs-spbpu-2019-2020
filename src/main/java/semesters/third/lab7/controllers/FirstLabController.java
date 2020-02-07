package semesters.third.lab7.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import semesters.third.lab1.task3.Book;
import semesters.third.lab1.task3.Catalogue;
import semesters.third.lab1.task3.NoSuchBookException;
import semesters.third.lab7.Alertable;

import java.net.URL;
import java.util.ResourceBundle;

public class FirstLabController implements Initializable, Alertable {

    @FXML
    public TableView<Book> booksTable;

    @FXML
    public TableColumn<Book, String> IDColumn;

    @FXML
    public TableColumn<Book, String> nameColumn;

    @FXML
    public TableColumn<Book, String> authorColumn;

    @FXML
    public TableColumn<Book, String> releaseYearColumn;

    @FXML
    public TextField IDTextField;

    @FXML
    public TextField nameTextField;

    @FXML
    public TextField authorTextField;

    @FXML
    public TextField releaseYearTextField;

    private Catalogue catalogue;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        catalogue = new Catalogue();
        IDColumn.setCellValueFactory(new PropertyValueFactory<>("ID"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));
        releaseYearColumn.setCellValueFactory(new PropertyValueFactory<>("releaseYear"));
    }

    public void onAddButtonClicked(ActionEvent actionEvent) {
        String ID = IDTextField.getText();
        if (!isCorrectID(ID)) {
            IDTextField.clear();
            alert(Alert.AlertType.ERROR, "ADD ERROR", "Can not add book", "ID is incorrect");
            return;
        }

        String name = nameTextField.getText();
        if (!isCorrectName(name)) {
            nameTextField.clear();
            alert(Alert.AlertType.ERROR, "ADD ERROR", "Can not add book", "Name is incorrect");
            return;
        }

        String author = authorTextField.getText();
        if (!isCorrectAuthor(author)) {
            authorTextField.clear();
            alert(Alert.AlertType.ERROR, "ADD ERROR", "Can not add book", "Author is incorrect");
            return;
        }

        String year = releaseYearTextField.getText();
        if (!isCorrectYear(year)) {
            releaseYearTextField.clear();
            alert(
                Alert.AlertType.ERROR, "ADD ERROR",
                "Can not add book", "Release year is incorrect"
            );
            return;
        }

        Book book = new Book(ID, name, author, year);
        catalogue.add(book);
        booksTable.getItems().add(book);
        IDTextField.clear();
        nameTextField.clear();
        authorTextField.clear();
        releaseYearTextField.clear();
    }

    public void onRemoveButtonClicked(ActionEvent actionEvent) {
        String ID = IDTextField.getText();
        if (!isCorrectID(ID)) {
            IDTextField.clear();
            alert(Alert.AlertType.ERROR, "REMOVE ERROR", "Can not remove book", "ID is incorrect");
            return;
        }

        String name = nameTextField.getText();
        if (!isCorrectName(name)) {
            nameTextField.clear();
            alert(
                Alert.AlertType.ERROR, "REMOVE ERROR",
                "Can not remove book", "Name is incorrect"
            );
            return;
        }

        String author = authorTextField.getText();
        if (!isCorrectAuthor(author)) {
            authorTextField.clear();
            alert(
                Alert.AlertType.ERROR, "REMOVE ERROR",
                "Can not remove book", "Author is incorrect"
            );
            return;
        }

        String year = releaseYearTextField.getText();
        if (!isCorrectYear(year)) {
            releaseYearTextField.clear();
            alert(
                Alert.AlertType.ERROR, "REMOVE ERROR",
                "Can not remove book", "Release year is incorrect"
            );
            return;
        }

        Book book = new Book(ID, name, author, year);
        try {
            catalogue.remove(book);
            booksTable.getItems().remove(book);
            IDTextField.clear();
            nameTextField.clear();
            authorTextField.clear();
            releaseYearTextField.clear();
        } catch (NoSuchBookException e) {
            alert(
                Alert.AlertType.ERROR, "REMOVE ERROR",
                "Can not remove book", "Book doesn't exist"
            );
        }
    }

    private boolean isCorrectID(String ID) {
        return ID.matches("[0-9]{4,8}");
    }

    private boolean isCorrectName(String name) {
        return name.matches("^[A-Z][a-zA-Z0-9]*");
    }

    private boolean isCorrectAuthor(String author) {
        return author.matches("^[A-Z]([a-zA-Z]+)");
    }

    private boolean isCorrectYear(String year) {
        return year.matches("[0-9]{4}") && Integer.parseInt(year) <= 2019;
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
