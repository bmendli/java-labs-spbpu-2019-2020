package semesters.fourth.lab4.controllers;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import semesters.fourth.lab4.enums.ErrorType;
import semesters.fourth.lab4.enums.StateType;
import semesters.fourth.lab4.presenters.Presenter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class ConsoleViewController implements ViewController {

    private static final String ADDED_MSG = "product successfully added";
    private static final String EXIT_MSG = "jobs is finished";

    @NotNull
    private final Presenter presenter;
    @NotNull
    private final BufferedReader reader;
    @NotNull
    private final PrintWriter writer;

    public ConsoleViewController(@NotNull final Presenter presenter) {
        this.presenter = presenter;
        this.reader = new BufferedReader(new InputStreamReader(System.in));
        this.writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
    }

    @Override
    public void readLine() {
        try {
            presenter.execute(reader.readLine());
        } catch (IOException e) {
            presenter.showErrorMsg(ErrorType.INPUT_ERROR);
        }
    }

    @Override
    public void println(@NotNull StateType state, @Nullable final String... data) {
        switch (state) {
            case ADD:
                writer.println(ADDED_MSG);
                presenter.start();
                break;
            case EXIT:
                writer.println(EXIT_MSG);
                System.exit(0);
                break;
            default:
                presenter.showErrorMsg(ErrorType.INCORRECT_STATE);
        }
    }

    @Override
    public void printlnErrorMsg(@NotNull ErrorType errorType) {

    }
}
