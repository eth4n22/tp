// src/main/java/seedu/duke/book/BookManager.java
package seedu.duke.book;

import seedu.duke.exception.BookNotFoundException;
import seedu.duke.member.Member;
import seedu.duke.member.MemberManager;
import seedu.duke.utility.GroupReturns;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
// You might need this import if you uncomment the stream().filter() lines below
// import java.util.stream.Collectors;

/**
 * Manages the collection of books: adding, deleting, listing, updating status,
 * providing access to the book list, and validating genres.
 * Search functionality is delegated to BookFinder.
 */
public class BookManager {
    private static final String BORROW = "borrow";
    private static final String RETURN = "return";

    // Central list of valid genres
    private static final List<String> VALID_GENRES = Arrays.asList(
            "romance", "adventure", "action", "horror", "mystery", "nonfiction", "scifi"
    );

    private final List<Book> books; // The authoritative list of books

    public BookManager(List<Book> books) {
        this.books = (books != null) ? new ArrayList<>(books) : new ArrayList<>();
    }

    /** Checks if the genre is supported by the library. */
    private boolean isAppropriateGenre(String genre) {
        return isValidGenre(genre);
    }

    /**
     * Validates if a given genre string is one of the supported genres (case-insensitive).
     *
     * @param genre The genre string to check.
     * @return true if the genre is valid, false otherwise.
     */
    public boolean isValidGenre(String genre) {
        if (genre == null) {
            return false;
        }
        return VALID_GENRES.contains(genre.toLowerCase());
    }

    /**
     * Gets the list of all books managed by this manager.
     *
     * @return The list of books.
     */
    public List<Book> getBooks() {
        return books;
    }

    /**
     * Adds a new book or increases the quantity if it already exists.
     *
     * @param title Title of the book.
     * @param author Author of the book.
     * @param genre Genre of the book (must be valid).
     * @param bookID Generated Shelf ID for the book.
     * @return Confirmation or error message string.
     */
    public String addNewBookToCatalogue(String title, String author, String genre, String bookID) {
        assert title != null : "Title cannot be null";
        assert author != null : "Author cannot be null";
        assert genre != null : "Genre cannot be null";

        // Moved checks to separate lines for clarity
        if (title.isEmpty()) {
            return "Book title cannot be empty!";
        }
        if (author.isEmpty()) {
            return "Book author cannot be empty!";
        }
        if (genre.isEmpty()) {
            return "Book genre cannot be empty!";
        }
        if (!isValidGenre(genre)) {
            // Broke long line
            return "This Library does not support this Genre! Valid genres are: "
                    + String.join(", ", VALID_GENRES);
        }

        // Check for existing book (case-insensitive)
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title) && book.getAuthor().equalsIgnoreCase(author)) {
                book.increaseQuantity();
                // Broke long line
                return "Increased quantity of \"" + title + "\" by " + author
                        + ".\nTotal Quantity: " + book.getQuantity();
            }
        }

        // Add new book if not found
        Book newBook = new Book(title, author);
        newBook.setBookID(bookID);
        newBook.setQuantity(1);
        books.add(newBook);

        // Broke long line
        return "I've added: \"" + title + "\" by " + author + " (Genre: " + genre + ", ID: " + bookID + ").\n"
                + "Total unique titles in library: " + books.size();
    }

    /**
     * Deletes a book by its 0-based index or decreases its quantity.
     *
     * @param bookIndex The 0-based index of the book in the list.
     * @return Confirmation or error message string.
     */
    public String deleteBook(int bookIndex) {
        if (bookIndex < 0 || bookIndex >= books.size()) {
            // Broke long line
            return "Invalid book index provided. There is no book at index "
                    + (bookIndex + 1) + ".";
        }

        Book book = books.get(bookIndex);
        if (book.getQuantity() > 1) {
            book.decreaseQuantity();
            // Broke long line
            return "Decreased quantity of \"" + book.getTitle() + "\" by " + book.getAuthor()
                    + ".\nRemaining Quantity: " + book.getQuantity();
        }

        // Remove the book entirely if quantity is 1
        Book removedBook = books.remove(bookIndex);
        int newSize = books.size();
        // Broke long line
        return "Book deleted:\n  " + removedBook.getTitle() + " by " + removedBook.getAuthor()
                + "\nNow you have " + newSize + " unique titles in the library.";
    }

    /**
     * Generates a string listing all books in the library.
     *
     * @return Formatted string of all books or a message if the library is empty.
     */
    public String listBooks() {
        if (books.isEmpty()) {
            return "The library is empty. Add some books!";
        }

        StringBuilder output = new StringBuilder("Here are the books in your library:\n");
        int totalQuantity = 0;
        for (int i = 0; i < books.size(); i++) {
            Book book = books.get(i);
            // Uses Book.toString() which is already formatted
            output.append(i + 1).append(". ").append(book.toString()).append("\n");
            totalQuantity += book.getQuantity();
        }
        output.append("-----\nTotal unique titles: ").append(books.size());
        output.append("\nTotal copies: ").append(totalQuantity);
        return output.toString();
    }

    /**
     * Updates the borrowing status of a book (borrow or return).
     *
     * @param command      "borrow" or "return".
     * @param bookIndex    0-based index of the book.
     * @param borrowerName Name of the borrower (required for borrow).
     * @param memberManager Manager to handle member records.
     * @return Confirmation or error message string.
     */
    public String updateBookStatus(String command, int bookIndex, String borrowerName, MemberManager memberManager) {
        if (bookIndex < 0 || bookIndex >= books.size()) {
            // Broke long line
            return "Invalid book index provided. There is no book at index "
                    + (bookIndex + 1) + ".";
        }

        Book book = books.get(bookIndex);
        Member borrower; // Declare borrower outside the blocks

        if (command.equals(BORROW)) {
            if (borrowerName == null || borrowerName.trim().isEmpty()) {
                return "Borrower name cannot be empty for borrowing.";
            }
            borrower = memberManager.getMemberByName(borrowerName);
            if (book.isBorrowed()) {
                // Broke long line
                return "\"" + book.getTitle() + "\" is already borrowed by "
                        + book.getBorrowerName() + ".";
            }
            book.setStatus(true);
            book.setReturnDueDate(LocalDate.now().plusWeeks(2));
            book.setBorrowerName(borrowerName);
            borrower.borrowBook(book);
            // Broke long line
            return borrowerName + " has borrowed: \"" + book.getTitle() + "\" (Due: "
                    + book.getReturnDueDate() + ")";

        } else if (command.equals(RETURN)) {
            if (!book.isBorrowed()) {
                return "\"" + book.getTitle() + "\" is not currently borrowed.";
            }
            String originalBorrowerName = book.getBorrowerName();
            // Ensure borrower name from book record is valid before looking up member
            if (originalBorrowerName != null && !originalBorrowerName.equals("null")
                    && !originalBorrowerName.trim().isEmpty()) {
                borrower = memberManager.getMemberByName(originalBorrowerName);
                borrower.returnBook(book); // Update member's borrowed list
            }
            book.setStatus(false);
            book.setReturnDueDate(null);
            book.setBorrowerName(null); // Clear borrower info on the book
            return "Returned: \"" + book.getTitle() + "\"";

        } else {
            // This case should ideally not be reached if parser validates commands
            return "Invalid update command!";
        }
    }

    /**
     * Generates a string listing all currently borrowed books.
     *
     * @return Formatted string of borrowed books or a message if none are borrowed.
     */
    public String listBorrowedBooks() {
        List<Book> borrowed = new ArrayList<>();
        for (Book book : books) {
            if (book.isBorrowed()) {
                borrowed.add(book);
            }
        }
        // Alternative using streams (requires import):
        // List<Book> borrowed = books.stream().filter(Book::isBorrowed).collect(Collectors.toList());

        if (borrowed.isEmpty()) {
            return "No books are currently borrowed.";
        }

        StringBuilder output = new StringBuilder("Borrowed Books:\n");
        for (int i = 0; i < borrowed.size(); i++) {
            Book book = borrowed.get(i);
            String dueDateStr = (book.getReturnDueDate() != null) ? book.getReturnDueDate().toString() : "N/A";
            // Broke long line using append chaining
            output.append(i + 1).append(". ")
                    .append(book.getTitle()).append(" by ").append(book.getAuthor())
                    .append(" (Borrowed by: ").append(book.getBorrowerName())
                    .append(", Due: ").append(dueDateStr)
                    .append(")\n");
        }
        return output.toString();
    }

    /**
     * Generates a string listing all currently overdue books.
     *
     * @return Formatted string of overdue books or a message if none are overdue.
     */
    public String listOverdueBooks() {
        List<Book> overdue = new ArrayList<>();
        for (Book book : books) {
            if (book.isOverdue()) {
                overdue.add(book);
            }
        }
        // Alternative using streams (requires import):
        // List<Book> overdue = books.stream().filter(Book::isOverdue).collect(Collectors.toList());

        if (overdue.isEmpty()) {
            return "No books are currently overdue.";
        }

        StringBuilder output = new StringBuilder("Overdue Books:\n");
        for (int i = 0; i < overdue.size(); i++) {
            Book book = overdue.get(i);
            // Broke long line using append chaining
            output.append(i + 1).append(". ")
                    .append(book.getTitle()).append(" by ").append(book.getAuthor())
                    .append(" (Borrowed by: ").append(book.getBorrowerName())
                    .append(", Due: ").append(book.getReturnDueDate()) // Assured non-null by isOverdue()
                    .append(")\n");
        }
        return output.toString();
    }

    /**
     * Retrieves the shelf ID of a book given its 0-based index.
     *
     * @param bookIndex The 0-based index.
     * @return The book's shelf ID.
     * @throws BookNotFoundException If the index is invalid or the book has no valid ID.
     */
    public String getBookID(int bookIndex) throws BookNotFoundException {
        if (bookIndex < 0 || bookIndex >= books.size()) {
            throw new BookNotFoundException("Invalid book index: " + (bookIndex + 1));
        }
        Book book = books.get(bookIndex);
        if (book.getBookID() == null || book.getBookID().equals("NIL")) {
            // Broke long line
            throw new BookNotFoundException("Book at index " + (bookIndex + 1)
                    + " does not have a valid Shelf ID.");
        }
        return book.getBookID();
    }

    /**
     * Retrieves the 0-based index and shelf ID of a book given its title and author (case-insensitive).
     *
     * @param bookTitle The title to search for.
     * @param author The author to search for.
     * @return A GroupReturns object containing the index and shelf ID.
     * @throws BookNotFoundException If the book is not found or lacks a valid ID.
     */
    public GroupReturns getBookID(String bookTitle, String author) throws BookNotFoundException {
        for (int i = 0; i < books.size(); i++) {
            Book book = books.get(i);
            if (book.getTitle().equalsIgnoreCase(bookTitle) && book.getAuthor().equalsIgnoreCase(author)) {
                if (book.getBookID() == null || book.getBookID().equals("NIL")) {
                    // Broke long line
                    throw new BookNotFoundException("Found book '" + bookTitle
                            + "' but it has no valid Shelf ID.");
                }
                return new GroupReturns(i, book.getBookID());
            }
        }
        // Broke long line
        throw new BookNotFoundException("Book not found with title '" + bookTitle
                + "' and author '" + author + "'.");
    }

    /**
     * Generates a string containing library statistics.
     *
     * @return Formatted statistics string.
     */
    public String getStatistics() {
        // Separated variable definitions and initialization
        int totalCopies = 0;
        int borrowedCount = 0;
        int overdueCount = 0;

        for (Book book : books) {
            totalCopies += book.getQuantity();
            if (book.isBorrowed()) {
                borrowedCount++;
            }
            if (book.isOverdue()) {
                overdueCount++;
            }
        }
        int uniqueTitles = books.size();

        StringBuilder stats = new StringBuilder();
        stats.append("========== Library Statistics ==========\n");
        // Used String.format for consistent alignment if needed later
        stats.append(String.format("Unique book titles: %d%n", uniqueTitles));
        stats.append(String.format("Total book copies:  %d%n", totalCopies));
        stats.append(String.format("Titles borrowed:    %d%n", borrowedCount));
        stats.append(String.format("Titles overdue:     %d%n", overdueCount));
        stats.append("========================================");
        return stats.toString();
    }
}
