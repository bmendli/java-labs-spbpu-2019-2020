package semesters.third.lab1.task3;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Catalogue {

    private List<Book> books;

    public Catalogue(){
        books = new ArrayList<>();
    }

    public Catalogue(Book book) {
        books = new ArrayList<>();
        books.add(book);
    }

    public Catalogue(List<Book> books) {
        this.books = new ArrayList<>();
        this.books.addAll(books);
    }

    public void add(Book book) {
        books.add(book);
    }

    public void add(List<Book> books) {
        this.books.addAll(books);
    }

    public Book searchByID(String ID) throws NoSuchBookException {
        for (Book book : books) {
            if (book.getID().equals(ID)) {
                return book;
            }
        }
        throw new NoSuchBookException("Book doesn't exist in catalogue");
    }

    public Book searchByReleaseYears(String releaseYear) throws NoSuchBookException {
        for (Book book : books) {
            if (book.getReleaseYear().equals(releaseYear)) {
                return book;
            }
        }
        throw new NoSuchBookException("Book doesn't exist in catalogue");
    }

    public Book searchByName(String name) throws NoSuchBookException {
        for (Book book : books) {
            if (book.getName().equals(name)) {
                return book;
            }
        }
        throw new NoSuchBookException("Book doesn't exist in catalogue");
    }

    public Book searchByAuthor(String author) throws NoSuchBookException {
        for (Book book : books) {
            if (book.getAuthor().equals(author)) {
                return book;
            }
        }
        throw new NoSuchBookException("Book doesn't exist in catalogue");
    }

    public void remove(Book book) throws NoSuchBookException {
        if (!books.contains(book)) {
            throw new NoSuchBookException("Book doesn't exist in catalogue");
        }
        books.remove(book);
    }

    public void removeByID(String ID) throws NoSuchBookException {
        books.remove(searchByID(ID));
    }

    public void removeByReleaseYears(String releaseYear) throws NoSuchBookException {
        books.remove(searchByReleaseYears(releaseYear));
    }

    public void removeByName(String name) throws NoSuchBookException {
        books.remove(searchByName(name));
    }

    public void removeByAuthor(String author) throws NoSuchBookException {
        books.remove(searchByAuthor(author));
    }

    public void menu() {
        Scanner in = new Scanner(System.in);

        while (true) {
            System.out.println(
                "Нажмите на нужную клавишу для работы с каталогом:\n"
                    + "1 - добавить книгу\n"
                    + "2 - найти книгу\n"
                    + "3 - удалить книгу\n"
                    + "4 - список книг в каталоге\n"
                    + "0 - выход из меню\n\n"
            );
            switch (in.next()) {
                case "1" : {
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
                    break;
                }
                case "2" : {
                    System.out.println(
                        "Для поиска книги по ID нажите 1\n"
                            + "по имени - 2\n"
                            + "по автору - 3\n"
                            + "по году издания - 4\n"
                            + "для выхода из режима \"поиск\" нажмите 0\n"
                    );
                    boolean endSearching = false;
                    while (!endSearching) {
                        switch (in.next()) {
                            case "1": {
                                System.out.println("ID = ");
                                try {
                                    System.out.println(searchByID(in.next()).toString());
                                } catch (NoSuchBookException e) {
                                    e.printStackTrace();
                                }
                                endSearching = true;
                                break;
                            }
                            case "2" : {
                                System.out.println("Имя = ");
                                try {
                                    System.out.println(searchByName(in.next()).toString());
                                } catch (NoSuchBookException e) {
                                    e.printStackTrace();
                                }
                                endSearching = true;
                                break;
                            }
                            case "3" : {
                                System.out.println("Автор = ");
                                try {
                                    System.out.println(searchByAuthor(in.next()));
                                } catch (NoSuchBookException e) {
                                    e.printStackTrace();
                                }
                                endSearching = true;
                                break;
                            }
                            case "4" : {
                                System.out.println("Год издания = ");
                                try {
                                    System.out.println(searchByReleaseYears(in.next()));
                                } catch (NoSuchBookException e) {
                                    e.printStackTrace();
                                }
                                endSearching = true;
                                break;
                            }
                            case "0" : {
                                endSearching = true;
                                break;
                            }
                            default : {
                                System.out.println("Нет такой команды. Попробуйте ещё раз");
                            }
                        }
                    }
                    break;
                }
                case "3" : {
                    System.out.println(
                        "Для удаления книги по ID нажите 1\n"
                            + "по имени - 2\n"
                            + "по автору - 3\n"
                            + "по году издания - 4\n"
                            + "для выхода из режима \"удаление\" нажмите 0\n"
                    );

                    boolean endRemoving = false;
                    while (!endRemoving) {
                        switch (in.next()) {
                            case "1": {
                                System.out.println("ID = ");
                                try {
                                    removeByID(in.next());
                                } catch (NoSuchBookException e) {
                                    e.printStackTrace();
                                }
                                endRemoving = true;
                                break;
                            }
                            case "2" : {
                                System.out.println("Имя = ");
                                try {
                                    removeByName(in.next());
                                } catch (NoSuchBookException e) {
                                    e.printStackTrace();
                                }
                                endRemoving = true;
                                break;
                            }
                            case "3" : {
                                System.out.println("Автор = ");
                                try {
                                    removeByAuthor(in.next());
                                } catch (NoSuchBookException e) {
                                    e.printStackTrace();
                                }
                                endRemoving = true;
                                break;
                            }
                            case "4" : {
                                System.out.println("Год издания = ");
                                try {
                                    removeByReleaseYears(in.next());
                                } catch (NoSuchBookException e) {
                                    e.printStackTrace();
                                }
                                endRemoving = true;
                                break;
                            }
                            case "0" : {
                                endRemoving = true;
                                break;
                            }
                            default : {
                                System.out.println("Нет такой команды. Попробуйте ещё раз");
                            }
                        }
                    }
                    break;
                }
                case "4" : {
                    System.out.println(toString());
                    break;
                }
                case "0" : {
                    System.exit(0);
                }
                default : {
                    System.out.println("Нет такой команды. Попробуйте ещё раз");
                }
            }
        }
    }

    public static void main(String[] args) {
        Book book1 = new Book(
            "36546456",
            "Pushkin",
            "Onegin",
            "32443"
        );
        Book book2 = new Book(
            "87654345678",
            "Gogol",
            "Bulba",
            "6543456"
        );
        Catalogue catalogue = new Catalogue(book1);
        catalogue.add(book2);
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