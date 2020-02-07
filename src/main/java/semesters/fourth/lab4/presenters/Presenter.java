package semesters.fourth.lab4.presenters;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import semesters.fourth.lab4.enums.ErrorType;

public interface Presenter {

    void addExecute(@NotNull final String productId, @NotNull final String productName, final int price);

    void updatePriceExecute(@NotNull final String productName, final int price);

    void filterByPriceExecute(final int priceFrom, final int priceTo);

    void deleteExecute(@NotNull final String productName);

    void priceExecute(@NotNull final String productName);

    void showAllExecute();

    void exitExecute();

    void execute(@Nullable final String data);

    void start();

    void showErrorMsg(@NotNull final ErrorType errorType);
}
