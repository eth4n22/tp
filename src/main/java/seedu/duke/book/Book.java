package seedu.duke.book;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Book {
    private final String title;
    private final String author;
    private boolean isBorrowed;
    private LocalDate returnDueDate;
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

    public Book(String title, String author) {
        this(title, author, false, null, "NIL", null);
    }

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
     * Returns the string representation of the book.
     *
     * @return Formatted string with book details.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        String status = isBorrowed ? "[X]" : "[ ]";
        String dueDateStr = (returnDueDate != null) ? " | (Due: " + returnDueDate.format(formatter) + ") " : "";
        return status + " " + title + " (by " + author + ")" + dueDateStr;
    }

    public String toFileFormat() {
        return title + " | " + author + " | " + (isBorrowed ? 1 : 0)
                + " | " + returnDueDate + " | " + bookID
                +  " | " + borrowerName;
    }

    public boolean isOverdue() {
        return isBorrowed() && getReturnDueDate() != null && getReturnDueDate().isBefore(LocalDate.now());
    }

    // Getters
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

    // Setters
    public void setBookID(String bookID) {
        this.bookID = bookID;
    }
}
