package seedu.duke.book;

import seedu.duke.exception.BookNotFoundException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.ArrayList;

import seedu.duke.member.Member;
import seedu.duke.member.MemberManager;
import seedu.duke.utility.GroupReturns;

/**
 * Manages the collection of books: adding, deleting, listing, updating status,
 * providing access to the book list, and validating genres.
 * Search functionality is delegated to BookFinder.
 */
public class BookManager {
    private static BookManager bookManagerInstance;

    private static final String BORROW = "borrow";
    private static final String RETURN = "return";

    // Central list of valid genres
    private static final List<String> VALID_GENRES = Arrays.asList(
            "romance", "adventure", "action", "horror", "mystery", "nonfiction", "scifi"
    );

    private static List<Book> books;

    /**
     * Constructs a BookManager with the given list of books.
     * If the provided list is null, an empty list is initialized.
     *
     * @param books The list of books to manage, or null to initialize an empty list.
     */
    private BookManager(List<Book> books) {
        BookManager.books = books;
    }

    /**
     * Returns the singleton instance of BookManager.
     * If the instance does not exist, it is created using the given list of books.
     *
     * @param books The list of books to initialize the manager if the instance does not exist.
     * @return The singleton instance of BookManager.
     */
    public static BookManager getBookManagerInstance(List<Book> books) {
        if (bookManagerInstance == null) {
            bookManagerInstance = new BookManager(books);
        }
        return bookManagerInstance;
    }

    /**
     * Checks if the genre is supported by the library.
     */
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
     * @param title  Title of the book.
     * @param author Author of the book.
     * @param genre  Genre of the book (must be valid).
     * @param bookID Generated Shelf ID for the book.
     * @return Confirmation or error message string.
     */
    public String addNewBookToCatalogue(String title, String author, String genre, String bookID) {
        assert title != null : "Title cannot be null";
        assert author != null : "Author cannot be null";
        assert genre != null : "Genre cannot be null";

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
            return "This Library does not support this Genre! Valid genres are: "
                    + String.join(", ", VALID_GENRES);
        }

        Book newBook = new Book(title, author);
        newBook.setBookID(bookID);
        books.add(newBook);

        return "I've added: \"" + title + "\" (by " + author + ") (Genre: " + genre + ", ID: " + bookID + ").\n"
                + "Total books in library: " + books.size();
    }

    /**
     * Deletes a book by its 0-based index or decreases its quantity.
     *
     * @param bookIndex The 0-based index of the book in the list.
     * @return Confirmation or error message string.
     */
    public String deleteBook(int bookIndex) {
        if (bookIndex < 0 || bookIndex >= books.size()) {
            return "Invalid book index provided. There is no book at index "
                    + (bookIndex + 1) + ".";
        }
        Book removedBook = books.get(bookIndex);

        for (Member member : MemberManager.getInstance().getAllMembers()) {
            member.returnBook(removedBook);
        }

        int oldSize = books.size();
        books.remove(bookIndex);

        assert books.size() == oldSize - 1 : "Book size should decrease by 1 after deletion";
        assert !books.contains(removedBook) : "Removed book should not be in the collection";

        return "Book deleted:\n  " + removedBook + "\nNow you have " + books.size() + " books in the library";
    }

    /**
     * Generates a string listing all books in the library.
     *
     * @return Formatted string of all books or a message if the library is empty.
     */
    public String listBooks() {
        if (books.isEmpty()) {
            return "No books in the library yet.";
        } else {
            StringBuilder output = new StringBuilder("Here are the books in your library:\n");
            int totalQuantity = 0;
            for (int i = 0; i < books.size(); i++) {
                Book book = books.get(i);
                assert book != null : "Book at index " + i + " should not be null";
                output.append(i + 1).append(". ").append(book).append("\n");
                totalQuantity++;
            }
            output.append("Total books: ").append(totalQuantity);

            // Assert that the output message contains the expected elements
            assert totalQuantity >= books.size() : "Total quantity should be >= number of unique titles";
            return output.toString();
        }
    }

    /**
     * Updates the borrowing status of a book (borrow or return).
     *
     * @param command       "borrow" or "return".
     * @param bookIndex     0-based index of the book.
     * @param borrowerName  Name of the borrower (required for borrow).
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
        Member borrower;

        if (command.equals(BORROW)) {
            if (borrowerName == null || borrowerName.trim().isEmpty()) {
                return "Borrower name cannot be empty for borrowing.";
            }
            borrower = memberManager.getMemberByName(borrowerName);
            if (book.isBorrowed()) {
                return "\"" + book.getTitle() + "\" is already borrowed by "
                        + book.getBorrowerName() + ".";
            }
            book.setStatus(true);
            book.setReturnDueDate(LocalDate.now().plusWeeks(2));
            book.setBorrowerName(borrowerName);
            borrower.borrowBook(book);
            return borrowerName + " has borrowed: \"" + book.getTitle() + "\" (Due: "
                    + book.getReturnDueDate() + ")";

        } else if (command.equals(RETURN)) {
            if (!book.isBorrowed()) {
                return "\"" + book.getTitle() + "\" is not currently borrowed.";
            }
            String originalBorrowerName = book.getBorrowerName();
            if (originalBorrowerName != null && !originalBorrowerName.equals("null")
                    && !originalBorrowerName.trim().isEmpty()) {
                borrower = memberManager.getMemberByName(originalBorrowerName);
                borrower.returnBook(book); //update member's borrowed list
            }
            book.setStatus(false);
            book.setReturnDueDate(null);
            book.setBorrowerName(null); //clear borrower info on the book
            return "Returned: \"" + book.getTitle() + "\"";

        } else {
            return "Invalid update command!";
        }
    }

    //@@author jenmarieng
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

        if (borrowed.isEmpty()) {
            return "No books are currently borrowed.";
        }

        StringBuilder output = new StringBuilder("Borrowed Books:\n");
        return getListOfBooks(borrowed, output);
    }

    //@@author jenmarieng
    /**
     * Generates a formatted string listing all books in the given list, including their title, author,
     * borrower name, and return due date (if available).
     *
     * @param typeOfBooks the list of books to be displayed
     * @param output      a {@code StringBuilder} used to accumulate the formatted output
     * @return a string representation of the list of books with their details
     */
    private static String getListOfBooks(List<Book> typeOfBooks, StringBuilder output) {
        for (int i = 0; i < typeOfBooks.size(); i++) {
            Book book = typeOfBooks.get(i);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
            String dueDateStr = (book.getReturnDueDate() != null) ? book.getReturnDueDate().format(formatter) : "N/A";
            output.append(i + 1).append(". ")
                    .append(book.getTitle()).append(" (by ").append(book.getAuthor())
                    .append(") (Borrowed by: ").append(book.getBorrowerName())
                    .append(") | Due: ").append(dueDateStr)
                    .append("\n");
        }
        return output.toString();
    }

    //@@author eth4n22
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

        if (overdue.isEmpty()) {
            return "No books are currently overdue.";
        }

        StringBuilder output = new StringBuilder("Overdue Books:\n");
        return getListOfBooks(overdue, output);
    }

    //@@author Deanson-Choo
    /**
     * Retrieves the book ID of a book given its 0-based index.
     *
     * @param bookIndex The 0-based index.
     * @return The book's book ID.
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

    //@@author Deanson-Choo
    /**
     * Retrieves the 0-based index and shelf ID of a book given its title and author
     *
     * @param bookTitle The title to search for.
     * @param author    The author to search for.
     * @return A GroupReturns object containing the index and shelf ID.
     * @throws BookNotFoundException If the book is not found or lacks a valid ID.
     */
    public GroupReturns getBookID(String bookTitle, String author) throws BookNotFoundException {
        for (int i = 0; i < books.size(); i++) {
            Book book = books.get(i);
            if (book.getTitle().equals(bookTitle) && book.getAuthor().equals(author)) {
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

    //@@author eth4n22
    public String getStatistics() {
        int borrowedBooks = 0;
        int overdueBooks = 0;

        for (Book book : books) {
            if (book.isBorrowed()) {
                borrowedBooks++;
            }
            if (book.isOverdue()) {
                overdueBooks++;
            }
        }

        StringBuilder stats = new StringBuilder();
        stats.append("========== Library Statistics ==========\n");
        stats.append("Total books copies: ").append(books.size()).append("\n");
        stats.append("Unique titles: ").append(getUniqueTitleSize()).append("\n");
        stats.append("Total books borrowed: ").append(borrowedBooks).append("\n");
        stats.append("Total books overdue: ").append(overdueBooks).append("\n");
        stats.append("List of unique titles: ").append(getUniqueTitles()).append("\n");

        return stats.toString();
    }

    //@@author eth4n22
    public int getUniqueTitleSize() {
        Set<String> uniqueTitlesSet = new HashSet<>();
        for (Book book : books) {
            uniqueTitlesSet.add(book.getTitle());
        }
        return uniqueTitlesSet.size();
    }

    //@@author eth4n22
    public Set<String> getUniqueTitles() {
        Set<String> uniqueTitlesSet = new HashSet<>();
        for (Book book : books) {
            uniqueTitlesSet.add(book.getTitle());
        }
        return uniqueTitlesSet;
    }

    public static int findBookQuantity(String title, String authorName) {
        int count = 0;
        for (Book books : books) {
            if (books.corresponds(title, authorName)) {
                count++;
            }
        }
        return count;
    }

    public static int findBookQuantityBorrowed(String title, String authorName) {
        int count = 0;
        for (Book books : books) {
            if (books.correspondsToBorrowed(title, authorName)) {
                count++;
            }
        }
        return count;
    }

    public int getBookIndex(String bookID) throws BookNotFoundException {
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getBookID().equals(bookID)) {
                return i;
            }
        }
        throw new BookNotFoundException("Book not found in library!");
    }

    //@@author WayneCh0y

    /**
     * Clears the list of books, removing all entries.
     */
    public void cleanup() {
        books.clear();
    }
}
