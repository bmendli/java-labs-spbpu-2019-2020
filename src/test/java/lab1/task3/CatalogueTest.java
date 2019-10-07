package lab1.task3;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CatalogueTest {

    private static Catalogue catalogue;
    private static Book book1;
    private static Book book2;
    private static Book book3;
    private static Book book4;
    private static Book book5;
    private static Book book6;

    @BeforeAll
    static void createInstance() {
        book1 = new Book(
            "36546456",
            "Pushkin",
            "Onegin",
            "32443"
        );
        book2 = new Book(
            "87654345678",
            "Gogol",
            "Bulba",
            "6543456"
        );
        book3 = new Book(
            "53424354",
            "Aaaaaadddd",
            "ddddsffff",
            "12323"
        );
        book3 = new Book(
            "53424354",
            "Aaaaaadddd",
            "ddddsffff",
            "12323"
        );
        book4 = new Book(
            "123456789",
            "qwerty",
            "asdfgh",
            "1234"
        );
        book5 = new Book(
            "54321234",
            "hthsh",
            "fgrgrg",
            "4567"
        );
        book6 = new Book(
            "76543456",
            "hthth",
            "uytrfg",
            "3522"
        );

        catalogue = new Catalogue(List.of(book1, book2));
    }

    @AfterEach
    void clear() {
        catalogue.getBooks().clear();
        catalogue.getBooks().addAll(List.of(book1, book2));
    }

    @Test
    void testAdd() {
        int size = catalogue.getBooks().size();
        assertFalse(catalogue.getBooks().contains(book3));
        catalogue.add(book3);

        assertEquals(
            size,
            catalogue.getBooks().size() - 1
        );
        assertTrue(catalogue.getBooks().contains(book3));

        List<Book> tempBooks = List.of(book4, book5, book6);
        tempBooks.forEach(
            (book) -> assertFalse(catalogue.getBooks().contains(book))
        );
        size = catalogue.getBooks().size();
        catalogue.add(tempBooks);

        tempBooks.forEach(
            (book) -> assertTrue(catalogue.getBooks().contains(book))
        );
        assertEquals(
            size,
            catalogue.getBooks().size() - tempBooks.size()
        );
    }

    @Test
    void testRemove() {
        catalogue.add(List.of(book3, book4, book5, book6));
        assertTrue(catalogue.getBooks().contains(book1));
        int size = catalogue.getBooks().size();
        catalogue.remove(book1);
        assertFalse(catalogue.getBooks().contains(book1));
        assertEquals(
            size,
            catalogue.getBooks().size() + 1
        );

        assertTrue(catalogue.getBooks().contains(book2));
        size = catalogue.getBooks().size();
        catalogue.removeByID(book2.getID());
        assertFalse(catalogue.getBooks().contains(book2));
        assertEquals(
            size,
            catalogue.getBooks().size() + 1
        );

        assertTrue(catalogue.getBooks().contains(book3));
        size = catalogue.getBooks().size();
        catalogue.removeByName(book3.getName());
        assertFalse(catalogue.getBooks().contains(book3));
        assertEquals(
            size,
            catalogue.getBooks().size() + 1
        );

        assertTrue(catalogue.getBooks().contains(book4));
        size = catalogue.getBooks().size();
        catalogue.removeByAuthor(book4.getAuthor());
        assertFalse(catalogue.getBooks().contains(book4));
        assertEquals(
            size,
            catalogue.getBooks().size() + 1
        );

        assertTrue(catalogue.getBooks().contains(book5));
        size = catalogue.getBooks().size();
        catalogue.removeByReleaseYears(book5.getReleaseYear());
        assertFalse(catalogue.getBooks().contains(book5));
        assertEquals(
            size,
            catalogue.getBooks().size() + 1
        );
    }

    @Test
    void testSearch() {
        assertEquals(
            book1,
            catalogue.searchByID(book1.getID())
        );
        assertEquals(
            book1,
            catalogue.searchByName(book1.getName())
        );
        assertEquals(
            book1,
            catalogue.searchByAuthor(book1.getAuthor())
        );
        assertEquals(
            book1,
            catalogue.searchByReleaseYears(book1.getReleaseYear())
        );
    }

    @Test
    void testThrowException() {
        assertThrows(
            NoSuchBookException.class,
            () -> catalogue.remove(book6)
        );
        assertThrows(
            NoSuchBookException.class,
            () -> catalogue.removeByID(book5.getID())
        );
        assertThrows(
            NoSuchBookException.class,
            () -> catalogue.removeByName(book3.getName())
        );
        assertThrows(
            NoSuchBookException.class,
            () -> catalogue.removeByAuthor(book5.getAuthor())
        );
        assertThrows(
            NoSuchBookException.class,
            () -> catalogue.removeByReleaseYears(book4.getReleaseYear())
        );

        assertThrows(
            NoSuchBookException.class,
            () -> catalogue.searchByID(book3.getID())
        );
        assertThrows(
            NoSuchBookException.class,
            () -> catalogue.searchByName(book4.getName())
        );
        assertThrows(
            NoSuchBookException.class,
            () -> catalogue.searchByAuthor(book5.getAuthor())
        );
        assertThrows(
            NoSuchBookException.class,
            () -> catalogue.searchByReleaseYears(book6.getReleaseYear())
        );

        assertDoesNotThrow(() -> catalogue.add(book3));
        assertDoesNotThrow(() -> catalogue.add(book6));
        assertDoesNotThrow(() -> catalogue.add(List.of(book4, book5)));

        assertDoesNotThrow(() -> catalogue.searchByID(book3.getID()));
        assertDoesNotThrow(() -> catalogue.searchByName(book3.getName()));
        assertDoesNotThrow(() -> catalogue.searchByAuthor(book3.getAuthor()));
        assertDoesNotThrow(() -> catalogue.searchByReleaseYears(book3.getReleaseYear()));

        assertDoesNotThrow(() -> catalogue.remove(book1));
        assertDoesNotThrow(() -> catalogue.removeByID(book2.getID()));
        assertDoesNotThrow(() -> catalogue.removeByName(book3.getName()));
        assertDoesNotThrow(() -> catalogue.removeByAuthor(book4.getAuthor()));
        assertDoesNotThrow(() -> catalogue.removeByReleaseYears(book5.getReleaseYear()));
    }
}
