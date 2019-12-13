package lab1.task3;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Catalogue {

    private List<Book> books;

    Catalogue(){}

    Catalogue(Book book) {
        books = new ArrayList<>();
        books.add(book);
    }

    Catalogue(List<Book> books) {
        this.books = new ArrayList<>();
        this.books.addAll(books);
    }

    public void add(Book book) {
        books.add(book);
    }

    public void add(List<Book> books) {
        this.books.addAll(books);
    }

    public Book searchByID(String ID) {
        for (Book book : books) {
            if (book.getID().equals(ID)) {
                return book;
            }
        }
        throw new NoSuchBookException("Book doesn't exist in catalogue");
    }

    public Book searchByReleaseYears(String releaseYear) {
        for (Book book : books) {
            if (book.getReleaseYear().equals(releaseYear)) {
                return book;
            }
        }
        throw new NoSuchBookException("Book doesn't exist in catalogue");
    }

    public Book searchByName(String name) {
        for (Book book : books) {
            if (book.getName().equals(name)) {
                return book;
            }
        }
        throw new NoSuchBookException("Book doesn't exist in catalogue");
    }

    public Book searchByAuthor(String author) {
        for (Book book : books) {
            if (book.getAuthor().equals(author)) {
                return book;
            }
        }
        throw new NoSuchBookException("Book doesn't exist in catalogue");
    }

    public void remove(Book book) {
        if (!books.contains(book)) {
            throw new NoSuchBookException("Book doesn't exist in catalogue");
        }
        books.remove(book);
    }

    public void removeByID(String ID) {
        books.remove(searchByID(ID));
    }

    public void removeByReleaseYears(String releaseYear) {
        books.remove(searchByReleaseYears(releaseYear));
    }

    public void removeByName(String name) {
        books.remove(searchByName(name));
    }

    public void removeByAuthor(String author) {
        books.remove(searchByAuthor(author));
    }

    public void menu() {
        Scanner in = new Scanner(System.in);

        while (true) {
            System.out.println(
                """
                Нажмите на нужную клавишу для работы с каталогом:
                1 - добавить книгу
                2 - найти книгу
                3 - удалить книгу
                4 - список книг в каталоге
                0 - выход из меню
                """
            );
            switch (in.next()) {
                case "1" -> {
                    System.out.println("Введите данные книги, которую хотите добавить\n");
                    System.out.println("Имя = ");
                    String name = in.next();
                    System.out.println();

                    System.out.println("ID = ");
                    String ID = in.next();
                    System.out.println();

                    System.out.println("Автор = ");
                    String author = in.next();
                    System.out.println();

                    System.out.println("Год издания = ");
                    String releaseYear = in.next();
                    System.out.println();

                    add(new Book(ID, author, name, releaseYear));
                }
                case "2" -> {
                    System.out.println(
                        """
                            Для поиска книги по ID нажите 1
                            по имени - 2
                            по автору - 3
                            по году издания - 4
                            для выхода из режима "поиск" нажмите 0
                            """
                    );
                    boolean endSearching = false;
                    while (!endSearching) {
                        switch (in.next()) {
                            case "1" -> {
                                System.out.println("ID = ");
                                System.out.println(searchByID(in.next()).toString());
                                endSearching = true;
                            }
                            case "2" -> {
                                System.out.println("Имя = ");
                                System.out.println(searchByName(in.next()).toString());
                                endSearching = true;
                            }
                            case "3" -> {
                                System.out.println("Автор = ");
                                System.out.println(searchByAuthor(in.next()));
                                endSearching = true;
                            }
                            case "4" -> {
                                System.out.println("Год издания = ");
                                System.out.println(searchByReleaseYears(in.next()));
                                endSearching = true;
                            }
                            case "0" -> {
                                endSearching = true;
                            }
                            default -> {
                                System.out.println("Нет такой команды. Попробуйте ещё раз");
                            }
                        }
                    }
                }
                case "3" -> {
                    System.out.println(
                        """
                            "Для удаления книги по ID нажите 1
                             по имени - 2
                             по автору - 3
                             по году издания - 4
                             для выхода из режима "удаление" нажмите 0
                                """
                    );

                    boolean endRemoving = false;
                    while (!endRemoving) {
                        switch (in.next()) {
                            case "1" -> {
                                System.out.println("ID = ");
                                removeByID(in.next());
                                endRemoving = true;
                            }
                            case "2" -> {
                                System.out.println("Имя = ");
                                removeByName(in.next());
                                endRemoving = true;
                            }
                            case "3" -> {
                                System.out.println("Автор = ");
                                removeByAuthor(in.next());
                                endRemoving = true;
                            }
                            case "4" -> {
                                System.out.println("Год издания = ");
                                removeByReleaseYears(in.next());
                                endRemoving = true;
                            }
                            case "0" -> {
                                endRemoving = true;
                            }
                            default -> {
                                System.out.println("Нет такой команды. Попробуйте ещё раз");
                            }
                        }
                    }
                }
                case "4" -> {
                    System.out.println(toString());
                }
                case "0" -> {
                    System.exit(0);
                }
                default -> {
                    System.out.println("Нет такой команды. Попробуйте ещё раз");
                }
            }
        }
    }

    public static void main(String[] args) {
        Catalogue catalogue = new Catalogue(
            List.of(
                new Book(
                    "36546456",
                    "Pushkin",
                    "Onegin",
                    "32443"
                ),
                new Book(
                    "87654345678",
                    "Gogol",
                    "Bulba",
                    "6543456"
                )
            )
        );
        catalogue.menu();
    }

    public List<Book> getBooks() {
        return books;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("В каталоге есть следующие книги:\n");
        for (Book book : books) {
            result.append(book.toString());
        }
        return result.toString();
    }
}
