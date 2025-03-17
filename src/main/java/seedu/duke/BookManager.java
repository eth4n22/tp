package seedu.duke;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Manages a collection of books by adding, deleting, listing, searching, and updating their status.
 */
public class BookManager {
    private static final String BORROW_COMMAND = "borrow";
    private static final String RETURN_COMMAND = "return";
    private static final String DATE_FORMAT = "yyyy-MM-dd";

    private final List<Book> books;

    /**
     * Constructs a new BookManager with the given books.
     *
     * @param books The initial list of books
     */
    public BookManager(List<Book> books) {
        this.books = books != null ? new ArrayList<>(books) : new ArrayList<>();
    }

    /**
     * Gets the list of all books.
     *
     * @return The list of books
     */
    public List<Book> getBooks() {
        return books;
    }

    /**
     * Adds a new book based on the provided details.
     *
     * @param bookDetails String containing title and author, expected format: "TITLE / AUTHOR"
     * @return A message confirming the book addition or an error message
     */
    public String addNewBook(String bookDetails) {
        String[] parts = bookDetails.split(" / ", 2);

        if (parts.length < 2) {
            return "Invalid book format! It should be 'TITLE / AUTHOR'.";
        }

        String title = parts[0].trim();
        String author = parts[1].trim();

        if (title.isEmpty()) {
            return "Book title cannot be empty!";
        }

        if (author.isEmpty()) {
            return "Book author cannot be empty!";
        }

        Book newBook = new Book(title, author);
        books.add(newBook);

        return "I've added: " + newBook + "\nNow you have " + books.size() + " books in the library.";
    }


    /**
     * Deletes a book from the books list.
     *
     * @param bookDetails Array containing the book index information [index]
     * @return A message indicating whether the deletion was successful or if there was an error
     */
    public String deleteBook(String[] bookDetails) {
        if (bookDetails.length < 1) {
            return "Please specify a book number!";
        }

        try {
            int bookIndex = Integer.parseInt(bookDetails[0]) - 1;

            if (bookIndex < 0 || bookIndex >= books.size()) {
                return "There is no such book in the library!";
            }

            Book removedBook = books.remove(bookIndex);
            return "Book deleted:\n  " + removedBook + "\nNow you have " + books.size() + " books in the library.";
        } catch (NumberFormatException e) {
            return "Please provide a valid book number!";
        }
    }

    /**
     * Lists all books in the library.
     *
     * @return A formatted string of all books
     */
    public String listBooks() {
        if (books.isEmpty()) {
            return "No books in the library yet.";
        } else {
            StringBuilder output = new StringBuilder("Here are the books in your library:\n");
            for (int i = 0; i < books.size(); i++) {
                output.append(i + 1).append(". ").append(books.get(i)).append("\n");
            }
            output.append("Total books: ").append(books.size());
            return output.toString();
        }
    }
}