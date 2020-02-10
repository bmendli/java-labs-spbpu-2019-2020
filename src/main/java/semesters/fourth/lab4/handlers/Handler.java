package semesters.fourth.lab4.handlers;

import org.jetbrains.annotations.Nullable;

@FunctionalInterface
public interface Handler {

    @Nullable
    String[] handle(final String data);
}
