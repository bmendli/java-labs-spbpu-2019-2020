package semesters.fourth.lab5.view.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import semesters.fourth.lab4.Product;
import semesters.fourth.lab4.db.ProductDBHelper;
import semesters.fourth.lab4.enums.ErrorType;
import semesters.fourth.lab4.enums.StateType;
import semesters.fourth.lab4.handlers.DataProductHandler;
import semesters.fourth.lab4.handlers.Handler;
import semesters.third.lab7.Alertable;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class FxmlViewController implements Initializable, Alertable {

    private static final String ERROR_COUNT_PARAMS = "Wrong count of parameters, try again.";
    private static final String ERROR_PRICE_FORMAT = "Price and product id must be the number, try again.";
    private static final String ERROR_TRANSACTION = "Transaction to bd is failed, try again.";
    private static final String ERROR_DURING_INPUT = "Input failed, try again.";
    private static final String ERROR_COMMAND = "Command is incorrect, try again.";
    private static final String ERROR_INPUT_DATA = "Input data is incorrect, try again.";
    private static final String ERROR_EXECUTION_FAIL = "Fail execution of program.";
    private static final String ERROR_NOT_DATA = "Doesn't exist data for this request, try again.";
    private static final String SOMETHING_WENT_WRONG = "Something went wrong, try again.";

    private static final String ADDED_MSG = "product successfully added";
    private static final String DELETED_MSG = "product successfully deleted";
    private static final String UPDATED_MSG = "price of product successfully updated";
    private static final String HELP_MSG = "Show all commands - " + DataProductHandler.COMMAND_HELP + "\n"
            + "Add product - " + DataProductHandler.COMMAND_ADD + " \"product_id\" \"product_name\" \"price\"\n"
            + "Delete product - " + DataProductHandler.COMMAND_DELETE + " \"product_name\"\n"
            + "Update product price - " + DataProductHandler.COMMAND_UPDATE_PRICE + " \"product_name\" \"new_price\"\n"
            + "Show all products - " + DataProductHandler.COMMAND_SHOW_ALL + "\n"
            + "Show products by price in range - " + DataProductHandler.COMMAND_FILTER_BY_PRICE + " \"start_price\" \"end_price\"\n"
            + "Show price of product - " + DataProductHandler.COMMAND_PRICE + " \"product_name\"\n"
            + "Exit from program - " + DataProductHandler.COMMAND_EXIT + ".";

    @FXML
    public TextField inputTextField;

    @FXML
    public TableView<Product> productTableView;

    @FXML
    public TableColumn<String, Integer> productIdColumn;

    @FXML
    public TableColumn<String, String> productTitleColumn;

    @FXML
    public TableColumn<String, Long> productPriceColumn;

    @FXML
    public Button submitButton;

    private ProductDBHelper dbHelper;
    private DataProductHandler handler;
    private boolean inProcess = false;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        productIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        productTitleColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        productPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        dbHelper = new ProductDBHelper();
        handler = new DataProductHandler();
        inProcess = true;
        if (!dbHelper.create()) {
            showErrorMsg(dbHelper.getErrorType());
        }
        inProcess = false;
    }

    public void onInputTextFieldAction(ActionEvent actionEvent) {
        onButtonClicked(actionEvent);
    }

    public void onButtonClicked(ActionEvent actionEvent) {
        if (inProcess) {
            alert(Alert.AlertType.WARNING, "REJECTED", "In process",
                    "Cannot execute this command while in process!");
        }
        inProcess = true;
        execute(inputTextField.getText());
        inputTextField.clear();
    }

    public void execute(@NotNull String inputData) {
        final String[] data = handler.handle(inputData);
        if (data == null) {
            showErrorMsg(handler.getErrorType());
        } else {
            switch (handler.getState()) {
                case HELP:
                    alert(Alert.AlertType.INFORMATION, "HELP", "Help info", HELP_MSG);
                    break;
                case ADD:
                    if (!dbHelper.add(Integer.parseInt(data[1]), data[2], Long.parseLong(data[3]))) {
                        showErrorMsg(dbHelper.getErrorType());
                        break;
                    }
                    alert(Alert.AlertType.INFORMATION, "ADD", "Add info", ADDED_MSG);
                    break;
                case DELETE:
                    if (!dbHelper.delete(data[1])) {
                        showErrorMsg(dbHelper.getErrorType());
                        break;
                    }
                    alert(Alert.AlertType.INFORMATION, "DELETE", "Delete info", DELETED_MSG);
                    break;
                case UPDATE:
                    if (!dbHelper.update(data[1], Long.parseLong(data[2]))) {
                        showErrorMsg(dbHelper.getErrorType());
                        break;
                    }
                    alert(Alert.AlertType.INFORMATION, "UPDATE", "Update info", UPDATED_MSG);
                    break;
                case SHOW_PRICE:
                    final List<Product> productsPrice = dbHelper.selectPriceByName(data[1]);
                    if (productsPrice == null) {
                        showErrorMsg(dbHelper.getErrorType());
                    } else {
                        productTableView.getItems().clear();
                        productTableView.getItems().add(productsPrice.get(0));
                    }
                    break;
                case SHOW_ALL:
                    final List<Product> productsAll = dbHelper.selectAll();
                    if (productsAll == null) {
                        showErrorMsg(dbHelper.getErrorType());
                    } else {
                        productTableView.getItems().clear();
                        productTableView.getItems().addAll(productsAll);
                    }
                    break;
                case SHOW_PRODUCTS_BY_PRICE_IN_RANGE:
                    final List<Product> productsFilter
                            = dbHelper.selectProductsByPriceInRange(Long.parseLong(data[1]), Long.parseLong(data[2]));
                    if (productsFilter == null) {
                        showErrorMsg(dbHelper.getErrorType());
                    } else {
                        productTableView.getItems().clear();
                        productTableView.getItems().addAll(productsFilter);
                    }
                    break;
                case EXIT:
                    System.exit(0);
                    break;
                case ERROR:
                    showErrorMsg(handler.getErrorType());
                default:
                    showErrorMsg(ErrorType.FAIL_EXECUTION);
                    break;
            }
        }
        inProcess = false;
    }

    public void showErrorMsg(@NotNull ErrorType errorType) {
        final String errorMsg;
        switch (errorType) {
            case INCORRECT_COUNT_PARAMS:
                errorMsg = ERROR_COUNT_PARAMS;
                break;
            case INCORRECT_PRICE_OR_PRICE_FORMAT:
                errorMsg = ERROR_PRICE_FORMAT;
                break;
            case INCORRECT_TRANSACTION:
                errorMsg = ERROR_TRANSACTION;
                break;
            case INPUT_ERROR:
                errorMsg = ERROR_DURING_INPUT;
                break;
            case INCORRECT_COMMAND:
                errorMsg = ERROR_COMMAND;
                break;
            case INCORRECT_INPUT_DATA:
                errorMsg = ERROR_INPUT_DATA;
                break;
            case FAIL_EXECUTION:
                errorMsg = ERROR_EXECUTION_FAIL;
                break;
            case NOT_DATA:
                errorMsg = ERROR_NOT_DATA;
                break;
            default:
                errorMsg = SOMETHING_WENT_WRONG;
        }
        productTableView.getItems().clear();
        alert(Alert.AlertType.ERROR, "ERROR", errorType.name(), errorMsg);
        inProcess = false;
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
