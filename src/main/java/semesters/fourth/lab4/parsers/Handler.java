package semesters.fourth.lab4.parsers;

import org.jetbrains.annotations.Nullable;

@FunctionalInterface
public interface Handler {

    void handle(@Nullable final String data);
}
