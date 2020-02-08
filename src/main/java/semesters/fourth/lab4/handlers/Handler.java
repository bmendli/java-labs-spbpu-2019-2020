package semesters.fourth.lab4.handlers;

import org.jetbrains.annotations.Nullable;

@FunctionalInterface
public interface Handler {

    void handle(@Nullable final String data);
}
