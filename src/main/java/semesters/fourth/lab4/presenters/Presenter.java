package semesters.fourth.lab4.presenters;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import semesters.fourth.lab4.Product;
import semesters.fourth.lab4.enums.ErrorType;

import java.util.List;

public interface Presenter {

    void addExecute(final int productId, @NotNull final String productName, final long price);

    void updatePriceExecute(@NotNull final String productName, final long price);

    void filterByPriceExecute(final long priceFrom, final long priceTo);

    void deleteExecute(@NotNull final String productName);

    void priceExecute(@NotNull final String productName);

    void showAllExecute();

    void receiveExecute();

    void helpExecute();

    void exitExecute();

    void execute(@Nullable final String data);

    void showResult(@Nullable List<Product> data);

    void showErrorMsg(@NotNull final ErrorType errorType);
}
