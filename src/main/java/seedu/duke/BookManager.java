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
        // Assert that books is properly initialized
        assert this.books != null : "Books list should never be null after initialization";
    }

    /**
     * Gets the list of all books.
     *
     * @return The list of books
     */
    public List<Book> getBooks() {
        // Assert that we're returning a non-null list
        assert books != null : "Book list should never be null";
        return books;
    }

    /**
     * Adds a new book based on the provided details.
     *
     * @param bookDetails String containing title and author, expected format: "TITLE / AUTHOR"
     * @return A message confirming the book addition or an error message
     */
    public String addNewBook(String bookDetails) {
        assert bookDetails != null : "Book details cannot be null";

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
        int oldSize = books.size();
        books.add(newBook);

        // Assert that the book was successfully added
        assert books.size() == oldSize + 1 : "Book size should increase by 1 after adding";
        assert books.contains(newBook) : "New book should be in the collection";

        return "I've added: " + newBook + "\nNow you have " + books.size() + " books in the library.";
    }


    /**
     * Deletes a book from the books list.
     *
     * @param bookDetails Array containing the book index information [index]
     * @return A message indicating whether the deletion was successful or if there was an error
     */
    public String deleteBook(String[] bookDetails) {
        assert bookDetails != null : "Book details array cannot be null";

        if (bookDetails.length < 1) {
            return "Please specify a book number!";
        }

        try {
            int bookIndex = Integer.parseInt(bookDetails[0]) - 1;

            if (bookIndex < 0 || bookIndex >= books.size()) {
                return "There is no such book in the library!";
            }

            Book removedBook = books.get(bookIndex);
            int oldSize = books.size();
            books.remove(bookIndex);

            // Assert that the book was successfully removed
            assert books.size() == oldSize - 1 : "Book size should decrease by 1 after deletion";
            assert !books.contains(removedBook) : "Removed book should not be in the collection";

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
        // Assert that books is never null when listing
        assert books != null : "Book list should never be null when listing";

        if (books.isEmpty()) {
            return "No books in the library yet.";
        } else {
            StringBuilder output = new StringBuilder("Here are the books in your library:\n");
            for (int i = 0; i < books.size(); i++) {
                assert books.get(i) != null : "Book at index " + i + " should not be null";
                output.append(i + 1).append(". ").append(books.get(i)).append("\n");
            }
            output.append("Total books: ").append(books.size());

            // Assert that the output message contains the expected elements
            assert output.toString().contains("Here are the books") : "List output should contain header";
            assert output.toString().contains("Total books: " + books.size()) :
                    "List output should contain total book count";

            return output.toString();
        }
    }
}