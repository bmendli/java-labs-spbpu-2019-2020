package semesters.fourth.lab4.db;

import org.jetbrains.annotations.NotNull;

public interface DBHelper {

    void add(final int productId, @NotNull final String productName, final long price);

    void update(@NotNull final String productName, final long price);

    void selectProductsByPriceInRange(final long priceFrom, final long priceTo);

    void delete(@NotNull final String productName);

    void selectPriceByName(@NotNull final String productName);

    void selectAll();
}
