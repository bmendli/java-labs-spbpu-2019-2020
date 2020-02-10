package semesters.fourth.lab4.db;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import semesters.fourth.lab4.Product;

import java.util.List;

public interface DBHelper {

    boolean add(final int productId, @NotNull final String productName, final long price);

    boolean update(@NotNull final String productName, final long price);

    @Nullable
    List<Product> selectProductsByPriceInRange(final long priceFrom, final long priceTo);

    boolean delete(@NotNull final String productName);

    @Nullable
    List<Product> selectPriceByName(@NotNull final String productName);

    @Nullable
    List<Product> selectAll();
}
