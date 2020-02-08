package semesters.fourth.lab4.presenters;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import semesters.fourth.lab4.Product;
import semesters.fourth.lab4.enums.ErrorType;
import semesters.fourth.lab4.controllers.ConsoleViewController;
import semesters.fourth.lab4.controllers.ViewController;
import semesters.fourth.lab4.db.DBHelper;
import semesters.fourth.lab4.db.ProductDBHelper;
import semesters.fourth.lab4.enums.StateType;
import semesters.fourth.lab4.handlers.DataProductHandler;

import java.util.List;

public class DataProductPresenter implements Presenter {

    @NotNull
    private final DataProductHandler handler;
    @NotNull
    private final DBHelper dbHelper;
    @NotNull
    private final ViewController viewController;
    @Nullable
    private StateType state;

    public DataProductPresenter() {
        this.handler = new DataProductHandler(this);
        this.viewController = new ConsoleViewController(this);
        this.dbHelper = new ProductDBHelper(this);
    }

    @Override
    public void addExecute(final int productId, @NotNull final String productName, final long price) {
        state = StateType.ADD;
        dbHelper.add(productId, productName, price);
    }

    @Override
    public void updatePriceExecute(@NotNull final String productName, final long price) {
        state = StateType.UPDATE;
        dbHelper.update(productName, price);
    }

    @Override
    public void filterByPriceExecute(final long priceFrom, final long priceTo) {
        state = StateType.SHOW_PRODUCTS_BY_PRICE_IN_RANGE;
        dbHelper.selectProductsByPriceInRange(priceFrom, priceTo);
    }

    @Override
    public void deleteExecute(@NotNull final String productName) {
        state = StateType.DELETE;
        dbHelper.delete(productName);
    }

    @Override
    public void priceExecute(@NotNull final String productName) {
        state = StateType.SHOW_PRICE;
        dbHelper.selectPriceByName(productName);
    }

    @Override
    public void showAllExecute() {
        state = StateType.SHOW_ALL;
        dbHelper.selectAll();
    }

    @Override
    public void receiveExecute() {
        viewController.receiveData();
    }

    @Override
    public void helpExecute() {
        state = StateType.HELP;
        showResult(null);
    }

    @Override
    public void exitExecute() {
        state = StateType.EXIT;
        viewController.show(state, null);
    }

    @Override
    public void execute(@Nullable String data) {
        handler.handle(data);
    }

    @Override
    public void showResult(@Nullable List<Product> data) {
        viewController.show(state == null ? StateType.ERROR : state, data);
    }

    @Override
    public void showErrorMsg(@NotNull final ErrorType errorType) {
        state = StateType.ERROR;
        viewController.showErrorMsg(errorType);
    }
}
