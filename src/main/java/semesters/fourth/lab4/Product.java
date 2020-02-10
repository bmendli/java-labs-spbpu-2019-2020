package semesters.fourth.lab4;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id
                && price == product.price
                && name.equals(product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price);
    }

    @Override
    public String toString() {
        return "Product: id = " + id + ", name = \"" + name + "\", price = " + price + '\n';
    }
}
