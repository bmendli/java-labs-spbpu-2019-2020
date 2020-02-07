package semesters.fourth.lab4.db;

import org.jetbrains.annotations.NotNull;
import semesters.fourth.lab4.presenters.Presenter;

public class ProductDBHelper implements DBHelper {

    @NotNull
    private final Presenter presenter;

    public ProductDBHelper(@NotNull final Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void add(@NotNull final String productId, @NotNull final String productName, final int price) {

    }

    @Override
    public void update(@NotNull final String productName, final int price) {

    }

    @Override
    public void selectProductsByPriceInRange(final int priceFrom, final int priceTo) {

    }

    @Override
    public void delete(@NotNull final String productName) {

    }

    @Override
    public void selectPriceByName(@NotNull final String productName) {

    }

    @Override
    public void selectAll() {

    }
}
