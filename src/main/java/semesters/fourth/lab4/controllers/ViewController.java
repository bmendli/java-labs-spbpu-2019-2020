package semesters.fourth.lab4.controllers;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import semesters.fourth.lab4.enums.ErrorType;
import semesters.fourth.lab4.enums.StateType;

public interface ViewController {

    void readLine();

    void println(@NotNull final StateType state, @Nullable final String... data);

    void printlnErrorMsg(@NotNull final ErrorType errorType);
}
