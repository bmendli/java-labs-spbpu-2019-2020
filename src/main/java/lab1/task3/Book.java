package lab1.task3;

import javax.validation.constraints.NotNull;
import java.util.Objects;

public class Book {

    private final String ID;
    private final String author;
    private final String name;
    private final String releaseYear;

    public Book(
        @NotNull String ID,
        @NotNull String name,
        @NotNull String author,
        @NotNull String releaseYear
    ) {
        this.ID = ID;
        this.author = author;
        this.name = name;
        this.releaseYear = releaseYear;
    }

    public String getID() {
        return ID;
    }

    public String getAuthor() {
        return author;
    }

    public String getName() {
        return name;
    }

    public String getReleaseYear() {
        return releaseYear;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(getID(), book.getID()) &&
            Objects.equals(getAuthor(), book.getAuthor()) &&
            Objects.equals(getName(), book.getName()) &&
            Objects.equals(getReleaseYear(), book.getReleaseYear());
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, author, name, releaseYear);
    }

    @Override
    public String toString() {
        return "Book with ID=" + ID
            + "\n author='" + author + '\''
            + "\n name='" + name + '\''
            + "\n releaseYear=" + releaseYear + "\n\n";
    }
}
