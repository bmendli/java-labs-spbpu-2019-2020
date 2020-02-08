package semesters.fourth.lab4.controllers;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import semesters.fourth.lab4.Product;
import semesters.fourth.lab4.enums.ErrorType;
import semesters.fourth.lab4.enums.StateType;

import java.util.List;

public interface ViewController {

    void receiveData();

    void show(@NotNull final StateType state, @Nullable final List<Product> data);

    void showErrorMsg(@NotNull final ErrorType errorType);
}
