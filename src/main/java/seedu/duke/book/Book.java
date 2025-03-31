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

    // --- Instance Variables ---
    private final String title;
    private final String author;
    private boolean isBorrowed;
    private LocalDate returnDueDate;
    private String bookID; // Format: IDENTIFIER-ShelfNum-Index
    private int quantity;
    private String borrowerName;

    /**
     * Full constructor for creating a Book instance.
     *
     * @param title         Title of the book (non-empty).
     * @param author        Author of the book (non-empty).
     * @param isBorrowed    Borrowing status.
     * @param returnDueDate Due date if borrowed, null otherwise.
     * @param bookID        Unique identifier (e.g., "AD-0-1").
     * @param quantity      Number of copies.
     * @param borrowerName  Name of the borrower if borrowed, null otherwise.
     */
    // Line 52 broken down
    public Book(String title, String author, boolean isBorrowed, LocalDate returnDueDate,
                String bookID, int quantity, String borrowerName) {
        assert title != null && !title.trim().isEmpty() : "Title cannot be empty";
        assert author != null && !author.trim().isEmpty() : "Author cannot be empty";

        this.title = title;
        this.author = author;
        this.isBorrowed = isBorrowed;
        this.returnDueDate = returnDueDate;
        this.bookID = bookID;
        this.quantity = quantity;
        this.borrowerName = borrowerName;
    }

    /**
     * Simplified constructor for adding a new book initially.
     *
     * @param title  Title of the book.
     * @param author Author of the book.
     */
    public Book(String title, String author) {
        this(title, author, false, null, "NIL", 1, null);
    }

    // --- Quantity Management ---
    public void increaseQuantity() {
        this.quantity++;
    }

    public void decreaseQuantity() {
        // Consider adding validation: if (this.quantity > 0) this.quantity--;
        this.quantity--;
    }

    public void setQuantity(int quantity) {
        // Consider adding validation: quantity >= 0
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
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

    /**
     * Returns a string representation of the book for display purposes.
     * Includes status, title, author, ID, genre, quantity, and due date if applicable.
     *
     * @return Formatted string representation.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        String status = getStatusSymbol(); // Use helper
        String dueDateStr = (returnDueDate != null) ? "(Due: " + returnDueDate.format(formatter) + ")" : "";
        String displayId = (bookID != null ? bookID : "N/A");

        // Line 136 broken down using String.format placeholders for better readability
        return String.format("%s %s (by %s) | ID: %s | Genre: %s | Quantity: %d %s",
                status, title, author, displayId, getGenre(), quantity, dueDateStr);
    }

    /**
     * Generates a string representation suitable for saving to a file.
     * Uses '|' as a delimiter. Handles null values for saving.
     *
     * @return Delimited string for file storage.
     */
    public String toFileFormat() {
        // Use "null" string for null values to avoid issues during loading
        String borrowerNameToSave = (borrowerName == null || borrowerName.trim().isEmpty())
                ? "null" : borrowerName.trim();
        String dueDateToSave = (returnDueDate == null) ? "null" : returnDueDate.toString();
        String idToSave = (bookID == null || bookID.equals("NIL")) ? "NIL" : bookID;

        return String.join(" | ",
                title,
                author,
                (isBorrowed ? "1" : "0"),
                dueDateToSave,
                idToSave,
                String.valueOf(quantity), // Convert quantity to string
                borrowerNameToSave
        );
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
