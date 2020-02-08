package semesters.fourth.lab4;

import org.jetbrains.annotations.NotNull;

public class Product {

    private final int id;
    @NotNull
    private final String name;
    private final long price;

    public Product(final int id, @NotNull final String name, final long price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    @NotNull
    public String getName() {
        return name;
    }

    public long getPrice() {
        return price;
    }
}
