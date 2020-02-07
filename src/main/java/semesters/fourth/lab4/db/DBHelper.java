package semesters.fourth.lab4.db;

import org.jetbrains.annotations.NotNull;

public interface DBHelper {

    void add(@NotNull final String productId, @NotNull final String productName, final int price);

    void update(@NotNull final String productName, final int price);

    void selectProductsByPriceInRange(final int priceFrom, final int priceTo);

    void delete(@NotNull final String productName);

    void selectPriceByName(@NotNull final String productName);

    void selectAll();
}
