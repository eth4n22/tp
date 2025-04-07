// src/main/java/seedu/duke/book/Book.java
package seedu.duke.book;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Book {
    // --- Genre constants ---
    private static final String ROMANCE = "romance";
    private static final String ADVENTURE = "adventure";
    private static final String ACTION = "action";
    private static final String HORROR = "horror";
    private static final String MYSTERY = "mystery";
    private static final String NONFICTION = "nonfiction";
    private static final String SCIFI = "scifi";

    // --- Genre ID constants ---
    private static final String ROMANCE_ID = "R";
    private static final String ADVENTURE_ID = "AD";
    private static final String ACTION_ID = "AC";
    private static final String HORROR_ID = "H";
    private static final String MYSTERY_ID = "MY";
    private static final String NONFICTION_ID = "NF";
    private static final String SCIFI_ID = "SCIF";
    private static final String UNKNOWN_GENRE = "Unknown"; // Fallback
    private static final String REGEX = " | ";

    // --- Instance Variables ---
    private final String title;
    private final String author;
    private boolean isBorrowed;
    private LocalDate returnDueDate;

    /**
     * Full constructor for creating a Book instance.
     *
     * @param title         Title of the book (non-empty).
     * @param author        Author of the book (non-empty).
     * @param isBorrowed    Borrowing status.
     * @param returnDueDate Due date if borrowed, null otherwise.
     * @param bookID        Unique identifier (e.g., "AD-0-1").
     * @param borrowerName  Name of the borrower if borrowed, null otherwise.
     */
    // Line 52 broken down
    private String bookID; //IDENTIFIER-ShelfNum-Index
    private String borrowerName;

    public Book(String title, String author, boolean isBorrowed, LocalDate returnDueDate, String bookID,
                String borrowerName) {
        assert title != null && !title.trim().isEmpty() : "Title cannot be empty";
        assert author != null && !author.trim().isEmpty() : "Author cannot be empty";

        this.title = title;
        this.author = author;
        this.isBorrowed = isBorrowed;
        this.returnDueDate = returnDueDate;
        this.bookID = bookID;
        this.borrowerName = borrowerName;
    }

    /**
     * Simplified constructor for adding a new book initially.
     *
     * @param title  Title of the book.
     * @param author Author of the book.
     */
    public Book(String title, String author) {
        this(title, author, false, null, "NIL", null);
    }

    // --- Borrowing Status Management ---
    public void setStatus(boolean isBorrowed) {
        this.isBorrowed = isBorrowed;
    }

    public void setReturnDueDate(LocalDate date) {
        this.returnDueDate = date;
    }

    public String getBorrowerName() {
        return borrowerName;
    }

    public void setBorrowerName(String borrowerName) {
        this.borrowerName = borrowerName;
    }


    //@@author Xavierleejrui
    /**
     * Derives the full genre name from the book's ID.
     * Assumes the bookID format is "IDENTIFIER-ShelfNum-Index".
     *
     * @return The full genre name (e.g., "adventure", "romance") or "Unknown" if parsing fails or ID is NIL.
     */
    public String getGenre() {
        if (bookID == null || bookID.equals("NIL") || !bookID.contains("-")) {
            return UNKNOWN_GENRE; // Cannot determine genre
        }
        try {
            String genreId = bookID.split("-")[0];
            // Corrected Indentation for switch block
            switch (genreId.toUpperCase()) { // Use uppercase for reliable matching
            case ROMANCE_ID:
                return ROMANCE;
            case ADVENTURE_ID:
                return ADVENTURE;
            case ACTION_ID:
                return ACTION;
            case HORROR_ID:
                return HORROR;
            case MYSTERY_ID:
                return MYSTERY;
            case NONFICTION_ID:
                return NONFICTION;
            case SCIFI_ID:
                return SCIFI;
            default:
                return UNKNOWN_GENRE;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            // Handle cases where split doesn't produce enough parts
            return UNKNOWN_GENRE;
        }
    }

    /**
     * Returns the status of the book as a display symbol "[ ]" or "[X]".
     *
     * @return Status symbol string.
     */
    public String getStatusSymbol() {
        return isBorrowed ? "[X]" : "[ ]";
    }

    //@@author eth4n22
    /**
     * Returns a string representation of the book for display purposes.
     * Includes status, title, author, ID, genre, quantity, and due date if applicable.
     *
     * @return Formatted string representation.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        String status = isBorrowed ? "[X]" : "[ ]";
        String dueDateStr = (returnDueDate != null) ? " | Due: " + returnDueDate.format(formatter) : "";
        return status + " " + title + " (by " + author + ") " + "(ID: " + bookID + ")" + dueDateStr;
    }

    /**
     * Generates a string representation suitable for saving to a file.
     * Uses '|' as a delimiter. Handles null values for saving.
     *
     * @return Delimited string for file storage.
     */
    public String toFileFormat() {
        return title + REGEX + author + REGEX + (isBorrowed ? 1 : 0)
                + REGEX + returnDueDate + REGEX + bookID
                + REGEX + borrowerName;
    }

    /**
     * Checks if the book is currently borrowed and past its due date.
     *
     * @return true if the book is overdue, false otherwise.
     */
    public boolean isOverdue() {
        return isBorrowed() && getReturnDueDate() != null && getReturnDueDate().isBefore(LocalDate.now());
    }

    // --- Standard Getters ---
    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean corresponds(String title, String authorName) {
        return this.title.equals(title) && this.author.equals(authorName);
    }

    public boolean correspondsToBorrowed(String title, String authorName) {
        return (this.title.equals(title) && this.author.equals(authorName) && this.isBorrowed);
    }

    public boolean isBorrowed() {
        return isBorrowed;
    }

    public LocalDate getReturnDueDate() {
        return returnDueDate;
    }

    public String getBookID() {
        return bookID;
    }

    // --- Standard Setters ---
    public void setBookID(String bookID) {
        this.bookID = bookID;
    }
}
