package semesters.fourth.lab4.presenters;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import semesters.fourth.lab4.enums.ErrorType;
import semesters.fourth.lab4.controllers.ConsoleViewController;
import semesters.fourth.lab4.controllers.ViewController;
import semesters.fourth.lab4.db.DBHelper;
import semesters.fourth.lab4.db.ProductDBHelper;
import semesters.fourth.lab4.parsers.DataProductHandler;
import semesters.fourth.lab4.parsers.Handler;

public class DataProductPresenter implements Presenter {

    @NotNull
    private final Handler handler;
    @NotNull
    private final DBHelper dbHelper;
    @NotNull
    private final ViewController viewController;

    public DataProductPresenter() {
        this.handler = new DataProductHandler(this);
        this.dbHelper = new ProductDBHelper(this);
        this.viewController = new ConsoleViewController(this);
    }

    @Override
    public void addExecute(@NotNull final String productId, @NotNull final String productName, final int price) {
        dbHelper.add(productId, productName, price);
    }

    @Override
    public void updatePriceExecute(@NotNull final String productName, final int price) {
        dbHelper.update(productName, price);
    }

    @Override
    public void filterByPriceExecute(final int priceFrom, final int priceTo) {
        dbHelper.selectProductsByPriceInRange(priceFrom, priceTo);
    }

    @Override
    public void deleteExecute(@NotNull final String productName) {
        dbHelper.delete(productName);
    }

    @Override
    public void priceExecute(@NotNull final String productName) {
        dbHelper.selectPriceByName(productName);
    }

    @Override
    public void showAllExecute() {
        dbHelper.selectAll();
    }

    @Override
    public void exitExecute() {

    }

    @Override
    public void execute(@Nullable String data) {

    }

    @Override
    public void start() {
        viewController.readLine();
    }

    @Override
    public void showErrorMsg(@NotNull final ErrorType errorType) {

    }
}
