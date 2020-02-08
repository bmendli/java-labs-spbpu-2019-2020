package semesters.fourth.lab4.controllers;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import semesters.fourth.lab4.Product;
import semesters.fourth.lab4.enums.ErrorType;
import semesters.fourth.lab4.enums.StateType;
import semesters.fourth.lab4.handlers.DataProductHandler;
import semesters.fourth.lab4.presenters.Presenter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.List;

public class ConsoleViewController implements ViewController {

    private static final String ERROR_COUNT_PARAMS = "Wrong count of parameters, try again.";
    private static final String ERROR_PRICE_FORMAT = "Price and product id must be the number, try again.";
    private static final String ERROR_TRANSACTION = "Transaction to bd is failed, try again.";
    private static final String ERROR_DURING_INPUT = "Input failed, try again.";
    private static final String ERROR_COMMAND = "Command is incorrect, try again.";
    private static final String ERROR_INPUT_DATA = "Input data is incorrect, try again.";
    private static final String ERROR_EXECUTION_FAIL = "Fail execution of program.";
    private static final String ERROR_NOT_DATA = "Doesn't exist data for this request, try again.";
    private static final String ERROR_CLOSE_CONNECTION_WITH_DB = "Cannot close connection with data base.";
    private static final String SOMETHING_WENT_WRONG = "Something went wrong, try again.";

    private static final String ADDED_MSG = "product successfully added";
    private static final String DELETED_MSG = "product successfully deleted";
    private static final String UPDATED_MSG = "price of product successfully updated";
    private static final String EXIT_MSG = "jobs is finished";
    private static final String HELP_MSG = "Show all commands - " + DataProductHandler.COMMAND_HELP + "\n"
            + "Add product - " + DataProductHandler.COMMAND_ADD + " \"product_id\" \"product_name\" \"price\"\n"
            + "Delete product - " + DataProductHandler.COMMAND_DELETE + " \"product_name\"\n"
            + "Update product price - " + DataProductHandler.COMMAND_UPDATE_PRICE + " \"product_name\" \"new_price\"\n"
            + "Show all products - " + DataProductHandler.COMMAND_SHOW_ALL + "\n"
            + "Show products by price in range - " + DataProductHandler.COMMAND_FILTER_BY_PRICE + " \"start_price\" \"end_price\"\n"
            + "Show price of product - " + DataProductHandler.COMMAND_PRICE + " \"product_name\"\n"
            + "Exit from program - " + DataProductHandler.COMMAND_EXIT + ".";

    @NotNull
    private final Presenter presenter;
    @NotNull
    private final BufferedReader reader;
    @NotNull
    private final PrintWriter writer;

    public ConsoleViewController(@NotNull final Presenter presenter) {
        this.presenter = presenter;
        this.reader = new BufferedReader(new InputStreamReader(System.in));
        this.writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
    }

    @Override
    public void receiveData() {
        try {
            presenter.execute(reader.readLine());
        } catch (IOException e) {
            presenter.showErrorMsg(ErrorType.INPUT_ERROR);
        }
    }

    @Override
    public void show(@NotNull StateType state, @Nullable final List<Product> data) {
        final String msg;
        switch (state) {
            case HELP:
                msg = HELP_MSG;
                break;
            case ADD:
                msg = ADDED_MSG;
                break;
            case DELETE:
                msg = DELETED_MSG;
                break;
            case UPDATE:
                msg = UPDATED_MSG;
                break;
            case SHOW_PRICE:
                if (data != null && data.size() == 1) {
                    writer.println(data.get(0).getPrice());
                    writer.flush();
                    presenter.receiveExecute();
                } else {
                    presenter.showErrorMsg(ErrorType.INCORRECT_TRANSACTION);
                }
                return;
            case SHOW_ALL:
            case SHOW_PRODUCTS_BY_PRICE_IN_RANGE:
                if (data != null && !data.isEmpty()) {
                    data.forEach(this::printlnProduct);
                    writer.flush();
                    presenter.receiveExecute();
                } else {
                    presenter.showErrorMsg(ErrorType.INCORRECT_TRANSACTION);
                }
                return;
            case EXIT:
                writer.println(EXIT_MSG);
                writer.close();
                System.exit(0);
                return;
            case ERROR:
            default:
                presenter.showErrorMsg(ErrorType.FAIL_EXECUTION);
                return;
        }
        writer.println(msg);
        writer.flush();
        presenter.receiveExecute();
    }

    @Override
    public void showErrorMsg(@NotNull final ErrorType errorType) {
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
            case FAIL_CLOSE_CONNECTION_WITH_DB:
                errorMsg = ERROR_CLOSE_CONNECTION_WITH_DB;
                break;
            default:
                errorMsg = SOMETHING_WENT_WRONG;
        }
        writer.println(errorMsg);
        writer.flush();
        presenter.receiveExecute();
    }

    private void printlnProduct(@NotNull final Product product) {
        writer.println(product.getId() + " " + product.getName() + " " + product.getPrice());
    }
}
